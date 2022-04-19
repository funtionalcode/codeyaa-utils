package com.codeyaa.model.wechat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className VxReuest
 * @description TODO(用一句话描述该文件做什么)
 * @date 2021/6/15 00:22
 */
@Data
@NoArgsConstructor
public class VxReuest {

    private String mchid;
    private String out_trade_no;
    private String appid;
    private String description;
    private String notify_url;
    private Amount amount;
    private Payer payer;
    /**
     * 微信公众号授权携带参数
     */
    private String code;
    private String state;
    private String attach;
}
