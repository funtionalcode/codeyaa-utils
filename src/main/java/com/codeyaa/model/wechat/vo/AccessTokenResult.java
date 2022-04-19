package com.codeyaa.model.wechat.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className AccessTokenResult
 * @description AccessToken
 * @date 2021/6/15 16:36
 */
@Data
@NoArgsConstructor
public class AccessTokenResult {
    private String access_token;
    private String refresh_token;
    private Long expires_in;
    private String openid;
    private String scope;
    private Long errcode;
    private String errmsg;
}
