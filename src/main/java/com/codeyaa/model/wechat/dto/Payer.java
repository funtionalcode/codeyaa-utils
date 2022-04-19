package com.codeyaa.model.wechat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className Payer
 * @description 微信支付响应实体类
 * @date 2021/6/15 00:24
 */
@Data
@NoArgsConstructor
public class Payer {
    private String openid;
    private String prepay_id;
    private String message;
    private String code;
}
