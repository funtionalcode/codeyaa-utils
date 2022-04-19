package com.codeyaa.model.workwechat.vo;

import lombok.Data;

/**
 * @Classname AccessToken
 * @Description 企业微信 AccessToken 实体类
 * @Date 2021/7/21 14:28
 * @Author Funtionalcode
 */
@Data
public class AccessTokenResult {
    private Integer errcode;
    private String errmsg;
    private String access_token;
    private Integer expires_in;
}
