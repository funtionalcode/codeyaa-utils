package com.codeyaa.utils.tripartite.xiumi.v1;

import com.codeyaa.model.xm.v1.dto.XiuMiModel;
import com.codeyaa.utils.common.MD5Util;
import lombok.AllArgsConstructor;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Funtionalcode
 * @className XiuMiUtil
 * @description 秀米编辑器接口工具类
 * @date 2021/5/27 12:12
 */
@AllArgsConstructor
public class XiuMiUtil {

    private final String appId;
    private final String token;
    private final String secret;

    /**
     * @param uid 传给秀米的用户唯一标识弗（自定义）
     * @Description: 获取秀米登录 url 来进入秀米编辑页面
     * @return: 返回一个登录 url
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 10:08
     */
    public String getLoginUrl(String uid) {

        /**
         * route_type	登录后使用的功能，当前固定为article，表示“图文编辑器”
         * timestamp	当前UNIX时间戳，单位：秒。
         * uid	正整数，小于2的32次方。用户在您的平台的唯一标识。 由您提供，秀米将使用其作为用户在秀米的身份标识。
         * appid	您的平台在秀米平台的唯一标识，由秀米提前颁发，形式为一个32个字符的字符串。
         * nonce	随机字符串，由您的平台在每一次签名时生成，使用于签名算法内。
         * signature	每次登录时根据各项参数计算得出的签名字符串。算法见下面的说明。
         * mediaid	图文在秀米的唯一编号，通过“接收图文内容的接口”获得。 如果参数合法，并且图文存在，则打开该图文继续编辑。 如果不传此参数，则打开一个新的空白图文。
         */
        final String pre = "https://xiumi.us/auth/partner/login?";
        final String route_type = "article";
        String signature;
        Long timestamp = System.currentTimeMillis();
        Integer nonce;
        final Integer mediaid = 0;

        SecureRandom r = new SecureRandom();

        final int start = 0;
        final int end = 999999;
        nonce = r.nextInt(end - start + 1);
        String[] params = new String[4];
        params[0] = token;
        params[1] = String.valueOf(timestamp);
        params[2] = String.valueOf(nonce);
        params[3] = uid;

        Arrays.sort(params);
        String flagStr = params[0] + params[1] + params[2] + params[3];
        // 计算两次获取MD5
        String tokenOne = MD5Util.MD5Encode(flagStr,"utf-8");
        signature = MD5Util.MD5Encode(tokenOne,"utf-8");

        String loginUrl = pre + "route_type=" + route_type +
                "&signature=" + signature + "&timestamp=" + timestamp +
                "&nonce=" + nonce + "&uid=" + uid +
                "&appid=" + appId + "&mediaid=" + mediaid;
        return loginUrl;
    }

    /**
     * @param xiuMiModel 接收秀米服务器发送的参数，封装到实体类
     * @Description: 返回一个系统 Token 给秀米服务器，用来校验我方系统的合法性
     * @return: 返回一个系统 Token
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 10:14
     */
    public Object getToken(XiuMiModel xiuMiModel) {
        String secret = this.secret;
        Long timestamp = xiuMiModel.getTimestamp();

        String nonce = xiuMiModel.getNonce();
        nonce = nonce != null ? nonce : "";

        String appid = xiuMiModel.getAppid();
        appid = appid != null ? appid : "";

        String signature = xiuMiModel.getSignature();
        signature = signature != null ? signature : "";

        HashMap<String, String> map = new HashMap<>();
        map.put("error", "signature does not match token");
        String xiumi_token = MD5Util.MD5Encode(String.valueOf(System.currentTimeMillis()), "utf-8");
        if (checkSign(timestamp, nonce, appId, signature)) {
            return xiumi_token;
        } else {
            return map;
        }
    }

    /**
     * @param timestamp 计算秀米认证参数
     * @param nonce     计算秀米认证参数
     * @param appId     秀米颁发的 appId
     * @param signature 预期计算后的认证结果（用户判断是否认证成功）
     * @Description: 秀米校验我方服务器接口
     * @return: 是否校验成功
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 11:24
     */
    private boolean checkSign(long timestamp, String nonce, String appId, String signature) {
        String secret = this.secret;
        // 计算signature
        String[] params = new String[4];
        params[0] = secret;
        params[1] = String.valueOf(timestamp);
        params[2] = nonce;
        params[3] = appId;

        Arrays.sort(params);
        String flagStr = String.join("", params);
        // 计算两次获取MD5
        String tokenOne = MD5Util.MD5Encode(flagStr, "utf-8");
        String tokenTwo = MD5Util.MD5Encode(tokenOne, "utf-8");
        return tokenTwo.equals(signature);
    }
}
