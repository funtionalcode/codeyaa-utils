package com.codeyaa.model.vx.gzh.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OpenIdResult implements Serializable {
    /**
     * total : 2
     * count : 2
     * data : {"openid":["OPENID1","OPENID2"]}
     * next_openid : NEXT_OPENID
     */

    private Long total;
    private Integer count;
    private DataBean data;
    private String next_openid;
    private String errorcode;
    private String errmsg;

    @Data
    public static class DataBean implements Serializable {
        private List<String> openid;
    }
}
