package com.codeyaa.utils.tripartite.upyun;

import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.utils.common.date.DateUtil;
import net.dongliu.requests.Header;
import net.dongliu.requests.Requests;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Base64;

public class UpYunAuth {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static byte[] hashHmac(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return mac.doFinal(data.getBytes());
    }

    public static String sign(String key, String secret, String method, String uri, String date, String policy,
                              String md5) throws Exception {
        String value = method + "&" + uri + "&" + date;
        if (policy != "") {
            value = value + "&" + policy;
        }
        if (md5 != "") {
            value = value + "&" + md5;
        }
        byte[] hmac = hashHmac(value, secret);
        String sign = Base64.getEncoder().encodeToString(hmac).trim();
        return "UPYUN " + key + ":" + sign;
    }


    public static void main(String[] args) throws Exception {
        String[] split = FileUtil.readFile("d:/sk/upyun.txt").split("\r\n");
        String key = split[1];
        String secret = split[2];
        String method = "GET";
        String uri = "http://v0.api.upyun.com/xin11//";
        String date = DateUtil.getRfc1123Time();

        // 上传，处理，内容识别有存储
        String sign = sign(key, md5(secret), method, (new URI(uri)).getRawPath(), date, "", "");
        System.out.println(sign);
        // 内容识别无存储，容器云
        String sign1 = sign(key, secret, method, uri, date, "", "");
        System.out.println(sign1);
        Header header1 = new Header("Authorization", sign);
        Header header2 = new Header("date", date);
        Header header3 = new Header("x-list-limit", 10000);
        ArrayList<Header> list = new ArrayList<>();
        list.add(header1);
        list.add(header2);
        list.add(header3);
        String readToText = Requests.session()
                .get(uri)
                .headers(list)
                .send()
                .readToText();
        System.out.println("readToText = " + readToText);
    }
}
