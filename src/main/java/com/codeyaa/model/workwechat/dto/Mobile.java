package com.codeyaa.model.workwechat.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname Mobile
 * @Description 手机号查询企业微信用户Id请求体
 * @Date 2021/7/21 15:03
 * @Author Funtionalcode
 */
@Data
public class Mobile implements Serializable {
    private static final long serialVersionUID = 2818858586893170840L;
    /**
     * mobile : 13430388888
     */

    private String mobile;
}
