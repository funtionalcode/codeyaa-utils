package com.codeyaa.utils.tripartite.qiniu;


import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.utils.common.StringUtils;
import com.codeyaa.utils.tripartite.HttpClientBase;
import com.codeyaa.utils.tripartite.upyun.UpYunAuth;
import com.google.gson.Gson;
import net.dongliu.requests.Header;
import net.dongliu.requests.Requests;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

public final class QiNiuAuth {
    private static final Gson GSON = new Gson();
    public static final String DTOKEN_ACTION_VOD = "linking:vod";
    public static final String DTOKEN_ACTION_STATUS = "linking:status";
    public static final String DTOKEN_ACTION_TUTK = "linking:tutk";
    /**
     * 上传策略
     * 参考文档：<a href="https://developer.qiniu.com/kodo/manual/put-policy">上传策略</a>
     */
    private static final String[] policyFields = new String[]{
            "callbackUrl",
            "callbackBody",
            "callbackHost",
            "callbackBodyType",
            "callbackFetchKey",

            "returnUrl",
            "returnBody",

            "endUser",
            "saveKey",
            "insertOnly",
            "isPrefixalScope",

            "detectMime",
            "mimeLimit",
            "fsizeLimit",
            "fsizeMin",

            "persistentOps",
            "persistentNotifyUrl",
            "persistentPipeline",

            "deleteAfterDays",
            "fileType",
    };
    private static final String[] deprecatedPolicyFields = new String[]{
            "asyncOps",
    };
    private static boolean[] isTokenTable = genTokenTable();
    private static int toLower = 'a' - 'A';
    public final String accessKey;
    private final SecretKeySpec secretKey;
    private final String secret;

    private QiNiuAuth(String accessKey, SecretKeySpec secretKeySpec, String secret) {
        this.accessKey = accessKey;
        this.secretKey = secretKeySpec;
        this.secret = secret;
    }

    public static QiNiuAuth create(String accessKey, String secretKey) {
        if (com.codeyaa.utils.common.StringUtils.isBlank(accessKey) || com.codeyaa.utils.common.StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("empty key");
        }
        byte[] sk = com.codeyaa.utils.common.StringUtils.utf8Bytes(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
        return new QiNiuAuth(accessKey, secretKeySpec, secretKey);
    }

    private static void copyPolicy(final Map policy, Map originPolicy, final boolean strict) {
        if (originPolicy == null) {
            return;
        }
        originPolicy.forEach((key, value) -> {
                    if (!strict || com.codeyaa.utils.common.StringUtils.inStringArray(key + "", policyFields)) {
                        policy.put(key, value);
                    }
                }
        );
    }

    // https://github.com/golang/go/blob/master/src/net/textproto/reader.go#L596
    // CanonicalMIMEHeaderKey returns the canonical format of the
    // MIME header key s. The canonicalization converts the first
    // letter and any letter following a hyphen to upper case;
    // the rest are converted to lowercase. For example, the
    // canonical key for "accept-encoding" is "Accept-Encoding".
    // MIME header keys are assumed to be ASCII only.
    // If s contains a space or invalid header field bytes, it is
    // returned without modifications.
    private static String canonicalMIMEHeaderKey(String name) {
        // com.qiniu.http.Headers 已确保 header name 字符的合法性，直接使用 byte ，否则要使用 char //
        byte[] a = name.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < a.length; i++) {
            byte c = a[i];
            if (!validHeaderFieldByte(c)) {
                return name;
            }
        }

        boolean upper = true;
        for (int i = 0; i < a.length; i++) {
            byte c = a[i];
            if (upper && 'a' <= c && c <= 'z') {
                c -= toLower;
            } else if (!upper && 'A' <= c && c <= 'Z') {
                c += toLower;
            }
            a[i] = c;
            upper = c == '-'; // for next time
        }
        return new String(a);
    }

    private static boolean validHeaderFieldByte(byte b) {
        //byte: -128 ~ 127, char:  0 ~ 65535
        return 0 < b && b < isTokenTable.length && isTokenTable[b];
    }

    private static boolean[] genTokenTable() {
        int[] idx = new int[]{
                '!', '#', '$', '%', '&', '\'', '*', '+', '-', '.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'W', 'V', 'X', 'Y', 'Z', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '|', '~'};
        boolean[] tokenTable = new boolean[127];
        Arrays.fill(tokenTable, false);
        for (int i : idx) {
            tokenTable[i] = true;
        }
        return tokenTable;
    }

    private Mac createMac() {
        Mac mac;
        try {
            mac = javax.crypto.Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
        return mac;
    }

    @Deprecated // private
    public String sign(byte[] data) {
        Mac mac = createMac();
        String encodedSign = Base64.getEncoder().encodeToString(mac.doFinal(data));
        return this.accessKey + ":" + encodedSign;
    }

    @Deprecated // private
    public String sign(String data) {
        return sign(com.codeyaa.utils.common.StringUtils.utf8Bytes(data));
    }

    @Deprecated // private
    public String signWithData(byte[] data) {
        String s = Base64.getEncoder().encodeToString(data);
        return sign(com.codeyaa.utils.common.StringUtils.utf8Bytes(s)) + ":" + s;
    }

    @Deprecated // private
    public String signWithData(String data) {
        return signWithData(com.codeyaa.utils.common.StringUtils.utf8Bytes(data));
    }

    /**
     * 生成HTTP请求签名字符串
     *
     * @param urlString
     * @param body
     * @param contentType
     * @return
     */
    @Deprecated // private
    public String signRequest(String urlString, byte[] body, String contentType) {
        URI uri = URI.create(urlString);
        String path = uri.getRawPath();
        String query = uri.getRawQuery();

        Mac mac = createMac();

        mac.update(com.codeyaa.utils.common.StringUtils.utf8Bytes(path));

        if (query != null && query.length() != 0) {
            mac.update((byte) ('?'));
            mac.update(com.codeyaa.utils.common.StringUtils.utf8Bytes(query));
        }
        mac.update((byte) '\n');
        if (body != null && HttpClientBase.ContentTypes.FORM_MIME.equalsIgnoreCase(contentType)) {
            mac.update(body);
        }

        String digest = Base64.getEncoder().encodeToString(mac.doFinal());

        return this.accessKey + ":" + digest;
    }

    /**
     * 验证回调签名是否正确
     *
     * @param originAuthorization 待验证签名字符串，以 "QBox "作为起始字符
     * @param url                 回调地址
     * @param body                回调请求体。原始请求体，不要解析后再封装成新的请求体--可能导致签名不一致。
     * @param contentType         回调ContentType
     * @return
     */
    public boolean isValidCallback(String originAuthorization, String url, byte[] body, String contentType) {
        String authorization = "QBox " + signRequest(url, body, contentType);
        return authorization.equals(originAuthorization);
    }

    /**
     * 下载签名
     *
     * @param baseUrl 待签名文件url，如 http://img.domain.com/u/3.jpg 、
     *                http://img.domain.com/u/3.jpg?imageView2/1/w/120
     * @return
     */
    public String privateDownloadUrl(String baseUrl) {
        return privateDownloadUrl(baseUrl, 3600);
    }

    /**
     * 下载签名
     *
     * @param baseUrl 待签名文件url，如 http://img.domain.com/u/3.jpg 、
     *                http://img.domain.com/u/3.jpg?imageView2/1/w/120
     * @param expires 有效时长，单位秒。默认3600s
     * @return
     */
    public String privateDownloadUrl(String baseUrl, long expires) {
        long deadline = System.currentTimeMillis() / 1000 + expires;
        return privateDownloadUrlWithDeadline(baseUrl, deadline);
    }

    public String privateDownloadUrlWithDeadline(String baseUrl, long deadline) {
        StringBuilder b = new StringBuilder();
        b.append(baseUrl);
        int pos = baseUrl.indexOf("?");
        if (pos > 0) {
            b.append("&e=");
        } else {
            b.append("?e=");
        }
        b.append(deadline);
        String token = sign(com.codeyaa.utils.common.StringUtils.utf8Bytes(b.toString()));
        b.append("&token=");
        b.append(token);
        return b.toString();
    }

    /**
     * scope = bucket
     * 一般情况下可通过此方法获取token
     *
     * @param bucket 空间名
     * @return 生成的上传token
     */
    public String uploadToken(String bucket) {
        return uploadToken(bucket, null, 3600, null, true);
    }

    /**
     * scope = bucket:key
     * 同名文件覆盖操作、只能上传指定key的文件可以可通过此方法获取token
     *
     * @param bucket 空间名
     * @param key    key，可为 null
     * @return 生成的上传token
     */
    public String uploadToken(String bucket, String key) {
        return uploadToken(bucket, key, 3600, null, true);
    }

    /**
     * 生成上传token
     *
     * @param bucket  空间名
     * @param key     key，可为 null
     * @param expires 有效时长，单位秒
     * @param policy  上传策略的其它参数，如 new Map().put("endUser", "uid").putNotEmpty("returnBody", "")。
     *                scope通过 bucket、key间接设置，deadline 通过 expires 间接设置
     * @return 生成的上传token
     */
    public String uploadToken(String bucket, String key, long expires, Map policy) {
        return uploadToken(bucket, key, expires, policy, true);
    }

    /**
     * 生成上传token
     *
     * @param bucket  空间名
     * @param key     key，可为 null
     * @param expires 有效时长，单位秒。默认3600s
     * @param policy  上传策略的其它参数，如 new Map().put("endUser", "uid").putNotEmpty("returnBody", "")。
     *                scope通过 bucket、key间接设置，deadline 通过 expires 间接设置
     * @param strict  是否去除非限定的策略字段，默认true
     * @return 生成的上传token
     */
    public String uploadToken(String bucket, String key, long expires, Map policy, boolean strict) {
        long deadline = System.currentTimeMillis() / 1000 + expires;
        return uploadTokenWithDeadline(bucket, key, deadline, policy, strict);
    }

    public String uploadTokenWithDeadline(String bucket, String key, long deadline, Map policy, boolean strict) {
        // TODO   UpHosts Global
        String scope = bucket;
        if (key != null) {
            scope = bucket + ":" + key;
        }
        Map<String, java.io.Serializable> x = new HashMap<>();
        copyPolicy(x, policy, strict);
        x.put("scope", scope);
        x.put("deadline", deadline);

        String s = GSON.toJson(x);
        return signWithData(com.codeyaa.utils.common.StringUtils.utf8Bytes(s));
    }

    public String uploadTokenWithPolicy(Object obj) {
        String s = GSON.toJson(obj);
        return signWithData(com.codeyaa.utils.common.StringUtils.utf8Bytes(s));
    }

    @Deprecated
    public Map authorization(String url, byte[] body, String contentType) {
        String authorization = "QBox " + signRequest(url, body, contentType);
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", authorization);
        return map;
    }

    @Deprecated
    public Map authorization(String url) {
        return authorization(url, null, null);
    }

    /**
     * 生成HTTP请求签名字符串
     *
     * @param url
     * @param body
     * @param contentType
     * @return
     */
    @Deprecated
    public String signRequestV2(String url, String method, byte[] body, String contentType) {
        return signQiniuAuthorization(url, method, body, contentType);
    }

    public String signQiniuAuthorization(String url, String method, byte[] body, String contentType) {
        net.dongliu.requests.Header header = null;
        if (StringUtils.isNotBlank(contentType)) {
            header = new net.dongliu.requests.Header("Content-Type", contentType);
        }
        ArrayList<net.dongliu.requests.Header> headers = new ArrayList<>();
        headers.add(header);
        return signQiniuAuthorization(url, method, body, headers);
    }

    public String signQiniuAuthorization(String url, String method, byte[] body, List<net.dongliu.requests.Header> headers) {
        URI uri = URI.create(url);

        StringBuilder sb = new StringBuilder();
        sb.append(method).append(" ").append(uri.getRawPath());

        if (uri.getQuery() != null) {
            sb.append("?").append(uri.getQuery());
        }

        sb.append("\nHost: ").append(uri.getHost() != null ? uri.getHost() : "");

        if (uri.getPort() > 0) {
            sb.append(":").append(uri.getPort());
        }

        String contentType = null;

        if (null != headers) {
            contentType = headers.get(0).value();
            if (contentType != null) {
                sb.append("\nContent-Type: ").append(contentType);
            }

            List<net.dongliu.requests.Header> xQiniuheaders = genXQiniuHeader(headers);
            if (xQiniuheaders.size() > 0) {
                for (Header h : xQiniuheaders) {
                    sb.append("\n").append(h.name()).append(": ").append(h.value());
                }
            }
        }

        sb.append("\n\n");

        if (null != body && body.length > 0 && null != contentType && !"".equals(contentType)
                && !"application/octet-stream".equals(contentType)) {
            sb.append(new String(body));
        }
        String signStr = "";
        try {
            byte[] bytes = UpYunAuth.hashHmac(sb.toString(), secret);
            signStr = Base64.getEncoder().encodeToString(bytes).trim();
        } catch (SignatureException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return this.accessKey + ":" + signStr;
    }

    private List<net.dongliu.requests.Header> genXQiniuHeader(List<net.dongliu.requests.Header> headers) {
        ArrayList<net.dongliu.requests.Header> hs = new ArrayList<>();
        for (net.dongliu.requests.Header header : headers) {
            String name = header.name();
            if (name.length() > "X-Qiniu-".length() && name.startsWith("X-Qiniu-")) {
                String val = header.value();
                hs.add(new net.dongliu.requests.Header(canonicalMIMEHeaderKey(name), val));
            }
        }
        return hs;
    }

    public List<Header> qiniuAuthorization(String url, String method, byte[] body, List<Header> headers) {
        String authorization = "Qiniu " + signQiniuAuthorization(url, method, body, headers);
        if (headers.isEmpty()) {
            headers = new ArrayList<>();
            headers.add(new net.dongliu.requests.Header("Authorization", authorization));
            return headers;
        }
        headers.add(new net.dongliu.requests.Header("Authorization", authorization));
        return headers;
    }

    @Deprecated
    public Map authorizationV2(String url, String method, byte[] body, String contentType) {
        String authorization = "Qiniu " + signRequestV2(url, method, body, contentType);
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", authorization);
        return map;
    }

    @Deprecated
    public Map authorizationV2(String url) {
        return authorizationV2(url, "GET", null, null);
    }

    //连麦 RoomToken
    public String signRoomToken(String roomAccess) throws Exception {
        String encodedRoomAcc = Base64.getEncoder().encodeToString(roomAccess.getBytes(StandardCharsets.UTF_8));
        byte[] sign = createMac().doFinal(encodedRoomAcc.getBytes());
        String encodedSign = Base64.getEncoder().encodeToString(sign);
        return this.accessKey + ":" + encodedSign + ":" + encodedRoomAcc;
    }

    public String generateLinkingDeviceToken(String appid, String deviceName, long deadline, String[] actions) {
        String[] staments = new String[actions.length];

        for (int i = 0; i < actions.length; ++i) {
            staments[i] = new String(actions[i]);
        }

        SecureRandom random = new SecureRandom();
        Map<String, Object> map = new HashMap<>();
        map.put("appid", appid);
        map.put("device", deviceName);
        map.put("deadline", deadline);
        map.put("random", random.nextInt());
        map.put("statement", staments);
        String s = GSON.toJson(map);
        return signWithData(com.codeyaa.utils.common.StringUtils.utf8Bytes(s));
    }

    public String generateLinkingDeviceTokenWithExpires(String appid, String deviceName,
                                                        long expires, String[] actions) {
        long deadline = (System.currentTimeMillis() / 1000) + expires;
        return generateLinkingDeviceToken(appid, deviceName, deadline, actions);
    }

    public String generateLinkingDeviceVodTokenWithExpires(String appid, String deviceName, long expires) {
        return generateLinkingDeviceTokenWithExpires(appid, deviceName, expires, new String[]{DTOKEN_ACTION_VOD});
    }

    public String generateLinkingDeviceStatusTokenWithExpires(String appid, String deviceName, long expires) {
        return generateLinkingDeviceTokenWithExpires(appid, deviceName, expires, new String[]{DTOKEN_ACTION_STATUS});
    }

    public static void main(String[] args) {
        String[] split = FileUtil.readFile("d:/sk/qiniu.txt").split("\r\n");
        String key = split[0];
        String secret = split[1];
        String uri = "https://rsf.qbox.me/list";
        String apiUrl = String.format("%s?bucket=%s", uri, split[2]);
        QiNiuAuth qiNiuAuth = QiNiuAuth.create(key, secret);
        ArrayList<Header> heads = new ArrayList<>();
        heads.add(new Header(HttpClientBase.HeaderParams.CONTENT_TYPE, HttpClientBase.ContentTypes.FORM_MIME));
        qiNiuAuth.qiniuAuthorization(apiUrl, HttpClientBase.Methons.GET, null, heads);
        String res = Requests.get(apiUrl)
                .headers(heads)
                .send()
                .readToText();
        System.out.println("res = " + res);
    }
}