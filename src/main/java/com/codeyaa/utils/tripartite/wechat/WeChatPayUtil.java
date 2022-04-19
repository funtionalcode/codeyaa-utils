package com.codeyaa.utils.tripartite.wechat;

import com.codeyaa.model.wechat.dto.Payer;
import com.codeyaa.model.wechat.dto.VxReuest;
import com.codeyaa.utils.common.wxgzh.WXPayMsgCrypt;
import com.google.gson.Gson;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * @author Funtionalcode
 * @className WeChatPay
 * @description 微信支付工具类
 * @date 2021/6/15 11:52
 */
@Data
public class WeChatPayUtil {
    /**
     * 回调Url
     */
    private String notifyUrl;
    /**
     * 证书文件路径
     */
    private String certFileName;
    /**
     * 商户号Id
     */
    private String merchantId;
    /**
     * Api密钥
     */
    private String merchantSerialNumber;
    /**
     * ApiV3密钥
     */
    private String apiV3PrivateKey;

    /**
     * 公众号appid
     */
    private String appid;
    /**
     * 公众号AppSecret
     */
    private String secret;

    /**
     * 微信调用工具
     */
    private CloseableHttpClient httpClient;
    private PrivateKey privateKey;
    private WXPayMsgCrypt wxpayMsgCrypt;
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
    private final Gson gson = new Gson();

    public WeChatPayUtil(String notifyUrl, String certFileName, String merchantId, String merchantSerialNumber, String apiV3PrivateKey, String appid, String secret) {
        this.notifyUrl = notifyUrl;
        this.certFileName = certFileName;
        this.merchantId = merchantId;
        this.merchantSerialNumber = merchantSerialNumber;
        this.apiV3PrivateKey = apiV3PrivateKey;
        this.appid = appid;
        this.secret = secret;
        loadPrivateKey();
        wxpayMsgCrypt = new WXPayMsgCrypt(apiV3PrivateKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param void
     * @Description: 加载证书创建httpclient
     * @return: void
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/15 12:18
     */
    private void loadPrivateKey() {
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(certFileName);
            privateKey = PemUtil.loadPrivateKey(new BufferedInputStream(resourceAsStream));

            AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                    new WechatPay2Credentials(merchantId, new PrivateKeySigner(merchantSerialNumber, privateKey)),
                    apiV3PrivateKey.getBytes(StandardCharsets.UTF_8));
            WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                    .withMerchant(merchantId, merchantSerialNumber, privateKey)
                    .withValidator(new WechatPay2Validator(verifier));

            httpClient = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * "{" +
     * "\"mchid\": \""+merchantId+"\"," +
     * "\"out_trade_no\": \"1217752501201407033233368318\"," +
     * "\"appid\": \""+appid+"\"," +
     * "\"description\": \"Image形象店-深圳腾大-QQ公仔\"," +
     * "\"notify_url\": \""+notifyUrl+"\"," +
     * "\"amount\": {" +
     * "\"total\": 1," +
     * "\"currency\": \"CNY\"" +
     * "}," +
     * "\"payer\": {" +
     * "\"openid\": \"openid\"" +
     * "}" +
     * "}";
     */
    public Payer jsApiCreateOrder(VxReuest reqdata) {
        //请求URL
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");

        CloseableHttpResponse response = null;
        try {
            StringEntity entity = new StringEntity(gson.toJson(reqdata), "UTF-8");
            entity.setContentType("application/json");
            entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            //完成签名并执行请求
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            String bodyStr = EntityUtils.toString(response.getEntity());
            Payer payer = new Payer();
            if (statusCode == 200) {
                payer = gson.fromJson(bodyStr, Payer.class);
                System.out.println("success,return body = " + bodyStr);
            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                payer = gson.fromJson(bodyStr, Payer.class);
                System.out.println("failed,resp code = " + statusCode + ",return body = " + bodyStr);
            }
            return payer;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Payer();
    }


    public String signPayParam(String appid, String timestamp, String nonce, String prepayId) {
        String param = appid + "\n" +
                timestamp + "\n" +
                nonce + "\n" +
                "prepay_id=" + prepayId + "\n";

        Signature sign = null;
        String signStr = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(param.getBytes(StandardCharsets.UTF_8));
            signStr = Base64.getEncoder().encodeToString(sign.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signStr;
    }
}
