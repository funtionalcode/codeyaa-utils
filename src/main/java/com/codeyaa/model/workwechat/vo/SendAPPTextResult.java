package com.codeyaa.model.workwechat.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname SendAPPTextResult
 * @Description 企业微信应用发送消息响应实体类
 * @Date 2021/7/21 15:33
 * @Author Funtionalcode
 */
@Data
public class SendAPPTextResult implements Serializable {
    private static final long serialVersionUID = 165079599117623716L;
    /**
     * errcode : 0
     * errmsg : ok
     * invaliduser : userid1|userid2
     * invalidparty : partyid1|partyid2
     * invalidtag : tagid1|tagid2
     */

    private Integer errcode;
    private String errmsg;
    private String invaliduser;
    private String invalidparty;
    private String invalidtag;
}
