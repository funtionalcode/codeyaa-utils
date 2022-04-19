package com.codeyaa.model.vx.gzh.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendPreMsgRequest extends SendMsgBase implements Serializable {
    /**
     * touser : OPENID
     * text : {"content":"CONTENT"}
     * msgtype : text
     */

    private String touser;
    private TextBean text;
    private String msgtype;
    private Boolean isTmp;
    private String token;

    @Data
    public static class TextBean implements Serializable {
        /**
         * content : CONTENT
         */

        private String content;
    }
}
