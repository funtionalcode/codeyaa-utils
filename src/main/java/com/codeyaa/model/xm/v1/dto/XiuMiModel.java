package com.codeyaa.model.xm.v1.dto;

import com.codeyaa.utils.common.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class XiuMiModel {
    private String uid;
    /**
     * 秀米颁发的用户Id
     */
    private String appid;
    /**
     * 秀米颁发的用户密钥
     */
    private String secret;
    /**
     * 当前时间戳
     */
    private Long timestamp;
    /**
     * 验证参数
     */
    private String nonce;
    /**
     * 验证字符串
     */
    private String signature;
    /**
     * 秀米颁发的token
     */
    private String access_token;
    /**
     * 秀米接口回调Url
     */
    private String url;

    public boolean empty() {

        return !StringUtils.isBlank(uid) ||
                !StringUtils.isBlank(appid) ||
                !StringUtils.isBlank(secret) ||
                !Objects.isNull(timestamp) ||
                !StringUtils.isBlank(nonce) ||
                !StringUtils.isBlank(signature);
    }

    public static String[] toStrnigArr(String str) {
        String[] res = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            res[i] = String.valueOf(str.charAt(i));
        }
        return res;
    }


}
