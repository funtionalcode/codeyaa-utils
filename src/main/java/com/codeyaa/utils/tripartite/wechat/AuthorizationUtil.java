package com.codeyaa.utils.tripartite.wechat;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * @Classname PayUtil
 * @Description 支付工具类
 * @Date 2021/4/10 21:32
 * @Created by DELL
 */
public class AuthorizationUtil {
    public static final int KEY_LENGTH_BYTE = 32;
    public static final int TAG_LENGTH_BIT = 128;

    public static String getVxToken(String fileName, String method, String url, String timestamp, String nonce, String body, String merchantId, String merchantSerialNumber) {
        PrivateKey privateKey = null;
        try {
            privateKey = PemUtil.loadPrivateKey(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String vxBody = getVxBody(method, url, timestamp, nonce, body);
        String vxToken = getVxToken(privateKey, merchantId, timestamp, nonce, merchantSerialNumber, vxBody);
        return vxToken;
    }

    public static String getVxToken(InputStream key, String method, String url, String timestamp, String nonce, String body, String merchantId, String merchantSerialNumber) {
        PrivateKey privateKey = null;
        privateKey = PemUtil.loadPrivateKey(key);

        String vxBody = getVxBody(method, url, timestamp, nonce, body);
        String vxToken = getVxToken(privateKey, merchantId, timestamp, nonce, merchantSerialNumber, vxBody);
        return vxToken;
    }

    public static String getVxRefundToken(String fileName, String method, String url, String timestamp, String nonce, String body, String merchantId, String merchantSerialNumber) {
        PrivateKey privateKey = null;
        try {
            privateKey = PemUtil.loadPrivateKey(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String vxBody = getVxBody(method, url, timestamp, nonce, body);
        String vxToken = getVxToken(privateKey, merchantId, timestamp, nonce, merchantSerialNumber, vxBody);
        return vxToken;
    }

    private static String getVxBody(String method, String url, String timestamp, String nonce, String body) {
        String bodyStr = body;
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String canonicalUrl = uri.getRawPath();
        if (uri.getQuery() != null) {
            canonicalUrl += "?" + uri.getRawQuery();
        }

        return method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonce + "\n"
                + bodyStr + "\n";
    }

    private static String getVxToken(PrivateKey privateKey, String merchantId, String timestamp, String nonce, String merchantSerialNumber, String body) {
        Signature sign = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(body.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String signStr = Base64.getEncoder().encodeToString(sign.sign());
            return "WECHATPAY2-SHA256-RSA2048 mchid=\"" + merchantId + "\",nonce_str=\"" + nonce + "\",timestamp=\"" + timestamp + "\",serial_no=\"" + merchantSerialNumber + "\",signature=\"" + signStr + "\"";
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Package: cn.haoxue.framework.utils
     * @ClassName: PayUtil
     * @Description: 微信响应数据解密
     * @Param: aesKey：微信api3密钥
     * @return:
     * @Author DELL
     * @Email: 1103005123@qq.com
     * @Date: 2021/4/12 9:42
     * @Version V1.0
     */
    public static String decryptToString(byte[] aesKey, byte[] associatedData, byte[] nonce, String ciphertext)
            throws GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData);

            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
