package com.codeyaa.utils.tripartite.wechat.impl;

import com.codeyaa.exception.AesException;
import com.codeyaa.exception.GzhException;
import com.codeyaa.model.vx.gzh.dto.DeleteDiyMenuRequest;
import com.codeyaa.model.vx.gzh.GzhMediaBase;
import com.codeyaa.model.vx.gzh.dto.GzhImageConditionDto;
import com.codeyaa.model.vx.gzh.dto.GzhMediaDto;
import com.codeyaa.model.vx.gzh.dto.SendMsgBase;
import com.codeyaa.model.vx.gzh.dto.SendMsgRequest;
import com.codeyaa.model.vx.gzh.dto.SendPreMsgRequest;
import com.codeyaa.model.vx.gzh.dto.VxCheckRequest;
import com.codeyaa.model.vx.gzh.dto.diy.CreateDiyMenuRequest;
import com.codeyaa.model.vx.gzh.dto.only.CreateOnlyMenuRequest;
import com.codeyaa.model.vx.gzh.dto.xml.ImgResultXml;
import com.codeyaa.model.vx.gzh.dto.xml.RequestBodyXml;
import com.codeyaa.model.vx.gzh.vo.*;
import com.codeyaa.model.wechat.vo.AccessTokenResult;
import com.codeyaa.model.wechat.vo.UserInfoResult;
import com.codeyaa.thread.DefaultPoolExecutor;
import com.codeyaa.utils.common.RandomUtil;
import com.codeyaa.utils.common.client.CommonClient;
import com.codeyaa.utils.common.wxgzh.WXBizMsgCrypt;
import com.codeyaa.utils.tripartite.wechat.WeChatGzh;
import com.codeyaa.utils.tripartite.wechat.client.WeChatGzhClient;
import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Funtionalcode
 * @className WeChatGZHUtil
 * @description 微信公众号工具类
 * @date 2021/5/27 12:12
 */
@Data
@NoArgsConstructor
public class WeChatGZHUtil implements WeChatGzh {
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
    private static final String MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
    private static final String DELETE_URL = "https://api.weixin.qq.com/cgi-bin/material/del_material";
    private static final String UPLOAD_IMG_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material";
    private static final String PRE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=";
    private static final String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
    private static final String GET_OPENID_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
    private static final String CREATE_ONLY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    private static final String DELETE_ONLY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
    private static final String CREATE_DIY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";
    private static final String DELETE_DIY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";

    /**
     * 公众号 -> 开发 -> 基本配置
     * 开发者ID(AppID)
     * 开发者密码(AppSecret)
     */
    private String appId;
    private String secret;
    /**
     * 公众号 -> 开发 -> 服务器配置
     * 消息加解密采用安全模式
     * 令牌(Token)
     * 消息加解密密钥(EncodingAESKey)
     */
    private String serverToken;
    private String encodingAesKey;
    /**
     * qq 交流群
     */
    private String qqFlock;
    /**
     * 微信加解密工具类
     */
    private WXBizMsgCrypt pc;

    private Gson gson = new Gson();

    /**
     * show 简易版构造方法.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @Param null
     * @Return
     * @Author xin11.xin
     * @Date 2021/10/28 22:47
     */
    public WeChatGZHUtil(String appId, String secret, String serverToken) {
        this.appId = appId;
        this.secret = secret;
        this.serverToken = serverToken;
    }

    /**
     * show 构造方法.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @Param null
     * @Return
     * @Author xin11.xin
     * @Date 2021/10/28 22:47
     */
    public WeChatGZHUtil(String appId, String secret, String serverToken, String encodingAesKey, String qqFlock, WXBizMsgCrypt pc) {
        this.appId = appId;
        this.secret = secret;
        this.serverToken = serverToken;
        this.encodingAesKey = encodingAesKey;
        this.qqFlock = qqFlock;
        this.pc = pc;
    }

    /**
     * @Package: com.codeyaa.utils
     * @ClassName: WeChatGZHUtil
     * @Description: 获取公众号 Token
     * @Param:
     * @return: 公众号 rest 返回值
     * @Author DELL
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 00:26
     * @Version V1.0
     */
    @Override
    public AccessTokenResult getToken() {
        final String url = "https://api.weixin.qq.com/cgi-bin/token";
        String apiUrl = url + "?grant_type=" + "client_credential&appid=" + appId + "&secret=" + secret;
        Session session = Requests.session();
        String respText = session.post(apiUrl)
                .send()
                .charset(StandardCharsets.UTF_8)
                .readToText();
        return checkGzhJson(respText, AccessTokenResult.class);

    }

    @Override
    public AccessTokenResult getOpenId(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                appId, secret, code);
        AccessTokenResult accessTokenResult = Requests.session().get(url).send().readToJson(AccessTokenResult.class);
        return accessTokenResult;
    }

    /**
     * @param token 公众号 Token
     * @Description: 获取公众号关注人数
     * @return: 公众号关注人数
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 00:31
     */
    @Override
    public double getGZHUserNum(String token) {
        String apiUrlGZH = String.format("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s", token);
        String resGZH = Requests.session().get(apiUrlGZH)
                .charset(StandardCharsets.UTF_8)
                .send()
                .readToText();
        Map map = checkGzhJson(resGZH, Map.class);
        double userNum = (Double) map.get("total");
        return userNum;

    }

    /**
     * @param token
     * @param openid
     * @return 用户基本信息
     */
    @Override
    public UserInfoResult getUserInfo(String token, String openid) {
        String apiUrlGZH = String.format("%s?access_token=%s&openid=%s&lang=zh_CN", USER_INFO_URL, token, openid);
        String resGZH = Requests.session().get(apiUrlGZH)
                .charset(StandardCharsets.UTF_8)
                .send()
                .readToText();
        UserInfoResult userInfoResult = checkGzhJson(resGZH, UserInfoResult.class);
        return userInfoResult;
    }

    /**
     * @param requestBodyXml 微信公众号请求 XML 报文封装后的实体类
     * @param content        文本消息
     * @param vxCheckRequest 接收公众号发送的校验服务器参数封装实体类
     * @param encode         公众号接口是否为安全模式
     * @Description: 微信公众号发送文本消息给用户
     * @return: 返回发送文本消息的 XML 格式
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 00:44
     */
    @Override
    public String sendXmlBody(RequestBodyXml requestBodyXml, String content, VxCheckRequest vxCheckRequest, boolean encode) {
        StringBuilder builder = new StringBuilder();
        String res = "";
        content += String.format("\n交流群：%s（QQ）", qqFlock);
        builder.append("<xml>\n")
                .append("<ToUserName>").append("<![CDATA[" + requestBodyXml.getFromUserName() + "]]>").append("</ToUserName>\n")
                .append("<FromUserName>").append("<![CDATA[" + requestBodyXml.getToUserName() + "]]>").append("</FromUserName>\n")
                .append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>\n")
                .append("<MsgType>").append("<![CDATA[text]]>").append("</MsgType>\n")
                .append("<Content>").append("<![CDATA[" + content + "]]>").append("</Content>\n")
                .append("</xml>");
        try {
            if (encode) {
                res = pc.encryptMsg(builder.toString(), vxCheckRequest.getTimestamp(), vxCheckRequest.getNonce());
            } else {
                res = builder.toString();
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param requestBodyXml 微信公众号请求 XML 报文封装后的实体类
     * @param vxCheckRequest 接收公众号发送的校验服务器参数封装实体类
     * @param encode         公众号接口是否为安全模式
     * @Description: 微信公众号发送语音消息给用户
     * @return: 返回发送语音消息的 XML 格式
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 00:41
     */
    @Override
    public String sendVoiceXmlBody(RequestBodyXml requestBodyXml, VxCheckRequest vxCheckRequest, boolean encode) {
        StringBuilder builder = new StringBuilder();
        String res = "";
        builder.append("<xml>\n")
                .append("<ToUserName>").append("<![CDATA[" + requestBodyXml.getFromUserName() + "]]>").append("</ToUserName>\n")
                .append("<FromUserName>").append("<![CDATA[" + requestBodyXml.getToUserName() + "]]>").append("</FromUserName>\n")
                .append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>\n")
                .append("<MsgType>").append("<![CDATA[voice]]>").append("</MsgType>\n")
                .append("<Voice>")
                .append("<MediaId>").append("<![CDATA[" + requestBodyXml.getMediaId() + "]]>").append("</MediaId>\n")
                .append("</Voice>")
                .append("</xml>");

        try {
            if (encode) {
                res = pc.encryptMsg(builder.toString(), vxCheckRequest.getTimestamp(), vxCheckRequest.getNonce());
            } else {
                res = builder.toString();
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param requestBodyXml 微信公众号请求 XML 报文封装后的实体类
     * @param content        图文消息的描述
     * @param pc             微信官方加解密工具类
     * @param vxCheckRequest 接收公众号发送的校验服务器参数封装实体类
     * @param encode         公众号接口是否为安全模式
     * @Description: 微信公众号发送图文消息给用户
     * @return: 返回发送图文消息的 XML 格式
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 00:45
     */
    @Override
    public String sendArticleXmlBody(RequestBodyXml requestBodyXml, String content, WXBizMsgCrypt pc, VxCheckRequest vxCheckRequest, boolean encode) {
        StringBuilder builder = new StringBuilder();
        String res = "";
        content += String.format("\n交流群：%s（QQ）", qqFlock);
        builder.append("<xml>\n")
                .append("<ToUserName>").append("<![CDATA[" + requestBodyXml.getFromUserName() + "]]>").append("</ToUserName>\n")
                .append("<FromUserName>").append("<![CDATA[" + requestBodyXml.getToUserName() + "]]>").append("</FromUserName>\n")
                .append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>\n")
                .append("<MsgType>").append("<![CDATA[news]]>").append("</MsgType>\n")
                .append("<ArticleCount>").append("1").append("</ArticleCount>\n")
                .append("<Articles>")
                .append("<item>")
                .append("<Title>").append("<![CDATA[" + "" + "]]>").append("</Title>\n")
                .append("<Description>").append("<![CDATA[" + content + "]]>").append("</Description>\n")
                .append("<PicUrl>").append("<![CDATA[" + requestBodyXml.getPicUrl() + "]]>").append("</PicUrl>\n")
                .append("<Url>").append("<![CDATA[" + "#" + "]]>").append("</Url>\n")
                .append("</Articles>")
                .append("</item>")
                .append("</xml>");

        try {
            if (encode) {
                res = pc.encryptMsg(builder.toString(), vxCheckRequest.getTimestamp(), vxCheckRequest.getNonce());
            } else {
                res = builder.toString();
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param requestBodyXml 微信公众号请求 XML 报文封装后的实体类
     * @param vxCheckRequest 接收公众号发送的校验服务器参数封装实体类
     * @param encode         公众号接口是否为安全模式
     * @Description: 微信公众号发送图片消息给用户
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/26 00:47
     */
    @Override
    public String sendImgXmlBody(RequestBodyXml requestBodyXml, VxCheckRequest vxCheckRequest, boolean encode) {
        StringBuilder builder = new StringBuilder();
        String res = "";
        builder.append("<xml>\n")
                .append("<ToUserName>").append("<![CDATA[" + requestBodyXml.getFromUserName() + "]]>").append("</ToUserName>\n")
                .append("<FromUserName>").append("<![CDATA[" + requestBodyXml.getToUserName() + "]]>").append("</FromUserName>\n")
                .append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>\n")
                .append("<MsgType>").append("<![CDATA[image]]>").append("</MsgType>\n")
                .append("<Image>")
                .append("<MediaId>").append(String.format("<![CDATA[%s]]>", requestBodyXml.getMediaId())).append("</MediaId>\n")
                .append("</Image>")
                .append("</xml>");
        try {
            if (encode) {
                res = pc.encryptMsg(builder.toString(), vxCheckRequest.getTimestamp(), vxCheckRequest.getNonce());
            } else {
                res = builder.toString();
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param file  上传的图片文件
     * @param token 公众号 Token
     * @Description: 前端点击上传文件到公众号，上传永久素材（非临时）
     * @return: 公众号上传的图片结果
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:14
     */
    @Override
    public GzhMediaBase uploadImageInput(String type, File file, String token) {
        String apiUrl = UPLOAD_IMG_URL + "?access_token=" + token + "&type=" + type;
        UploadImgResultl uploadImgResultl = WeChatGzhClient.uploadImage(apiUrl, file.toString());
        uploadImgResultl.setName(file.getName());
        return uploadImgResultl;
    }

    @Override
    public List<UploadImgResultl> batchUploadImages(String type, File[] files, String token) {
        ArrayList<UploadImgResultl> resultls = new ArrayList<>();
        int length = files.length;
        String threadGroupName = "公众号批量上传本地图片";
        ThreadPoolExecutor threadPoolExecutor = DefaultPoolExecutor.newInstance(length, threadGroupName);
        for (File file : files) {
            threadPoolExecutor.submit(() -> {
                UploadImgResultl uploadImgResult = Optional.ofNullable(uploadImageInput(type, file, token))
                        .map(item -> (UploadImgResultl) item)
                        .orElse(new UploadImgResultl());
                uploadImgResult.setName(file.getName());
                resultls.add(uploadImgResult);
            });
        }
        threadPoolExecutor.shutdown();
        return resultls;
    }

    /**
     * @param type  获取的素材类型
     * @param token 公众号 Token
     * @Description: 获取在公众号对应类型的素材 MediaId
     * @return: 返回一个 List<MediaId>
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:15
     */
    @Override
    public List<GzhMediaBase> getAllGzhMedia(String type, String token) {
        GzhMediaCountVo mediaCount = getMediaCount(token);
        Double image_count = mediaCount.getImageCount();

        if (image_count > 20) {
            return manyGzhImgMedia(type, image_count.intValue(), token);
        }
        return oneGzhImgMedia(type, image_count.intValue(), token);

    }

    @Override
    public ErrorResult deleteAllMedia(String type, String token) {
        ErrorResult[] errorResult = {new ErrorResult()};
        String apiUrl = DELETE_URL + "?access_token=" + token;

        List<GzhMediaBase> allGzhMedia = getAllGzhMedia(type, token);
        ThreadPoolExecutor executor = DefaultPoolExecutor.newInstance(allGzhMedia.size() + 1, "删除公众号图片");
        Map<Integer, List<Object>> cutListMap = RandomUtil.cutList(allGzhMedia, 5);
        cutListMap.forEach((k, gzhMediaList) -> {
            CompletableFuture.runAsync(() -> {
                gzhMediaList.forEach(mediaObj -> {
                    ManyImgGzhMedia media = (ManyImgGzhMedia) mediaObj;
                    String respText = CommonClient.REQUESTS.post(apiUrl)
                            .jsonBody(new GzhMediaDto(media.getMediaId()))
                            .send()
                            .charset(StandardCharsets.UTF_8)
                            .readToText();
                    errorResult[0] = checkGzhJson(respText, ErrorResult.class);
                });
            });
        });
        executor.shutdown();
        return errorResult[0];
    }

    /**
     * JAXB 解析 "<![CDATA[]></![CDATA[]>" 异常
     * 导致转换后的 XML 字符串微信公众号不识别，回复信息无效
     *
     * @param imgResultXml
     * @param writer
     * @return
     */
    @Deprecated
    public static String convertImgResToXmlStr(ImgResultXml imgResultXml, StringWriter writer) {
        try {
            JAXBContext context = JAXBContext.newInstance(ImgResultXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(imgResultXml, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImgListModel getImgListDetailModel(String type, String token) {
        String apiUrl = MATERIAL_URL + "?access_token=" + token;
        GzhImageConditionDto gzhImageConditionDto = new GzhImageConditionDto(type, 0L, 20L);

        String respText = CommonClient.REQUESTS.post(apiUrl)
                .jsonBody(gzhImageConditionDto)
                .send()
                .charset(StandardCharsets.UTF_8)
                .readToText();

        return checkGzhJson(respText, ImgListModel.class);
    }

    /**
     * @param url
     * @param location
     * @return 下载公众号素材图片
     */
    @Override
    public Boolean downloadImage(String url, String location) {
        String millis = System.currentTimeMillis() + "";
        File result = WeChatGzhClient.downloadImage(url, location, millis);
        String fileStr = result.toString();
        String prePath = fileStr.substring(0, fileStr.lastIndexOf("\\") + 1);
        File newFile = new File(prePath + System.currentTimeMillis() + ".png");
        return result.renameTo(newFile);
    }

    @Override
    public File downloadImage(String url, String location, String name) {
        return WeChatGzhClient.downloadImage(url, location, name);
    }

    @Override
    public OpenIdResult getUserOpenId(String token) {
        String toText = CommonClient.REQUESTS.get(GET_OPENID_URL + token).send().readToText();
        OpenIdResult openIdResult = gson.fromJson(toText, OpenIdResult.class);
        List<String> openIdRes = Optional.ofNullable(openIdResult)
                .map(OpenIdResult::getData)
                .map(OpenIdResult.DataBean::getOpenid).orElse(new ArrayList<>());
        Long total = openIdResult.getTotal();
        // 一次最多获取 10000 个
        if (total > 10000) {
            String lastOpenId = Optional.of(openIdRes)
                    .map(item -> item.get(item.size() - 1)).orElse("");
            for (int i = 0; i < total / 10000; i++) {
                String toTextTmp = CommonClient.REQUESTS.get(GET_OPENID_URL + token + "&next_openid=" + lastOpenId).send().readToText();
                OpenIdResult openIdResultTmp = gson.fromJson(toTextTmp, OpenIdResult.class);
                List<String> openIdTmp = Optional.ofNullable(openIdResultTmp)
                        .map(OpenIdResult::getData)
                        .map(OpenIdResult.DataBean::getOpenid).orElse(new ArrayList<>());
                openIdRes.addAll(openIdTmp);
            }
        }
        OpenIdResult.DataBean data = openIdResult.getData();
        data.setOpenid(openIdRes);
        openIdResult.setData(data);
        return openIdResult;
    }

    @Override
    public ErrorResult sendPreText(SendMsgRequest sendMsgRequest) {
        if (sendMsgRequest.getIsTmp()) {
            return WeChatGzhClient.sendMsg(PRE_SEND_URL, sendMsgRequest);
        }
        return null;
    }

    @Override
    public ErrorResult sendPreTextRequests(SendMsgBase sendMsgRequest) {
        SendPreMsgRequest request = (SendPreMsgRequest) sendMsgRequest;
        String toText = CommonClient.REQUESTS.post(PRE_SEND_URL + request.getToken())
                .jsonBody(request)
                .send()
                .readToText();
        return gson.fromJson(toText, ErrorResult.class);
    }

    @Override
    public ErrorResult sendTextRequests(SendMsgBase sendMsgRequest) {
        SendMsgRequest request = (SendMsgRequest) sendMsgRequest;
        String toText = CommonClient.REQUESTS.post(SEND_URL + request.getToken())
                .jsonBody(request)
                .send()
                .readToText();
        return gson.fromJson(toText, ErrorResult.class);
    }

    @Override
    public ErrorResult createOnlyMenu(CreateOnlyMenuRequest createOnlyMenuRequest, String token) {
        String toText = CommonClient.REQUESTS.post(CREATE_ONLY_MENU_URL + token)
                .jsonBody(createOnlyMenuRequest)
                .send()
                .readToText();
        return gson.fromJson(toText, ErrorResult.class);
    }

    @Override
    public ErrorResult deleteOnlyMenu(String token) {
        String toText = CommonClient.REQUESTS.get(DELETE_ONLY_MENU_URL + token)
                .send()
                .readToText();
        return gson.fromJson(toText, ErrorResult.class);
    }

    @Override
    public ErrorResult createDiyMenu(CreateDiyMenuRequest createMenuRequest, String token) {
        String toText = CommonClient.REQUESTS.post(CREATE_DIY_MENU_URL + token)
                .jsonBody(createMenuRequest)
                .send()
                .readToText();
        return gson.fromJson(toText, ErrorResult.class);
    }

    @Override
    public ErrorResult deleteDiyMenu(DeleteDiyMenuRequest deleteDiyMenu, String token) {
        String toText = CommonClient.REQUESTS.post(DELETE_DIY_MENU_URL + token)
                .jsonBody(deleteDiyMenu)
                .send()
                .readToText();
        return gson.fromJson(toText, ErrorResult.class);
    }

    /**
     * @param type     获取的素材类型
     * @param imgCount 获取的数量
     * @param token    公众号 Token
     * @Description: 公众号对应类型素材小于等于20时调用此方法（公众号一次最多只能获取20个MediaId）
     * @return: 返回一个 List<MediaId>
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:16
     */
    private List<GzhMediaBase> oneGzhImgMedia(String type, int imgCount, String token) {
        List<GzhMediaBase> medias = new ArrayList<>();

        final String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
        String apiUrl = url + "?access_token=" + token;
        GzhImageConditionDto gzhImageConditionDto = new GzhImageConditionDto(type, 0L, Long.parseLong(imgCount+""));

        String respText = CommonClient.REQUESTS.post(apiUrl)
                .jsonBody(gzhImageConditionDto)
                .send()
                .charset(StandardCharsets.UTF_8)
                .readToText();
        GzhAllImageVo gzhAllImageVo = checkGzhJson(respText, GzhAllImageVo.class);
        gzhAllImageVo.getItem().forEach(row -> medias.add(new ManyImgGzhMedia(row.getName(), row.getMedia_id(), row.getUrl())));
        return medias;
    }

    /**
     * @param type     获取的素材类型
     * @param imgCount 获取的数量
     * @param token    公众号 Token
     * @Description: 公众号对应类型素材大于20时调用此方法
     * @return: 返回一个 List<MediaId>
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:18
     */
    private List<GzhMediaBase> manyGzhImgMedia(String type, int imgCount, String token) {
        final String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
        String apiUrl = url + "?access_token=" + token;
        List<GzhMediaBase> medias = new ArrayList<>();
        for (int i = 0, j = 0, z = 20; i <= imgCount / 20; i++, j += 20) {
            int postCount = imgCount / (i + 1);
            if (postCount < 20) {
                z = imgCount % 20;
            }
            if (z == 0) {
                break;
            }
            GzhImageConditionDto gzhImageConditionDto = new GzhImageConditionDto(type, Long.parseLong(j + ""), Long.parseLong(z + ""));
            String readToText = CommonClient.REQUESTS.post(apiUrl)
                    .jsonBody(gzhImageConditionDto)
                    .send()
                    .charset(StandardCharsets.UTF_8)
                    .readToText();
            GzhAllImageVo gzhAllImageVo = checkGzhJson(readToText,GzhAllImageVo.class);
            gzhAllImageVo.getItem().forEach(row -> medias.add(new ManyImgGzhMedia(row.getName(), row.getMedia_id(), row.getUrl())));
        }
        return medias;
    }

    /**
     * @param token 公众号 Token
     * @Description: 获取公众号所有素材的总数 {"voice_count":COUNT,"video_count":COUNT,"image_count":COUNT,"news_count":COUNT}
     * @return: {"voice_count":COUNT,"video_count":COUNT,"image_count":COUNT,"news_count":COUNT}
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:19
     */
    private GzhMediaCountVo getMediaCount(String token) {

        final String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";
        String apiUrl = url + "?access_token=" + token;

        Session session = Requests.session();
        String respText = session.get(apiUrl)
                .send()
                .charset(StandardCharsets.UTF_8)
                .readToText();
        return checkGzhJson(respText, GzhMediaCountVo.class);
    }

    /**
     * @param gzhJson 公众号接口返回值
     * @Description: 校验获取公众号信息的返回值是否有误（是否在平台配置 IP）
     * @return: 把值转换 Map 返回
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:21
     */
    private <T> T checkGzhJson(String gzhJson, Class<T> t) {
        if (gzhJson.contains("invalid ip")) {
            String substring = gzhJson.substring(gzhJson.indexOf("invalid ip"), gzhJson.indexOf("ipv6"));
            throw new GzhException(substring + "未配置！");
        } else if (gzhJson.contains("\"errmsg\": ok")) {
            return gson.fromJson(gzhJson, t);
        } else if (gzhJson.contains("errcode")) {
            throw new GzhException(gzhJson);
        }
        return gson.fromJson(gzhJson, t);
    }
}
