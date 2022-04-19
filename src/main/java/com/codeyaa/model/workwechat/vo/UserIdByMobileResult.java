package com.codeyaa.model.workwechat.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserIdByMobileResult
 * @Description 企业微信手机号查询用户 Id 响应实体类
 * @Date 2021/7/21 15:23
 * @Author Funtionalcode
 */
@Data
public class UserIdByMobileResult implements Serializable {
    private static final long serialVersionUID = -3492621105588217720L;
    /**
     * errcode : 0
     * errmsg : ok
     * userid : zhangsan
     */

    private Integer errcode;
    private String errmsg;
    private String userid;
}
