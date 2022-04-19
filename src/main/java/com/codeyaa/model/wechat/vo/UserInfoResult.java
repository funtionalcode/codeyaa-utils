package com.codeyaa.model.wechat.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoResult implements Serializable {

    private static final long serialVersionUID = -2901140275315274253L;
    /**
     * subscribe : 1
     * openid : o6_bmjrPTlm6_2sgVt7hMZOPfL2M
     * language : zh_CN
     * subscribe_time : 1382694957
     * unionid :  o6_bmasdasdsad6_2sgVt7hMZOPfL
     * remark :
     * groupid : 0
     * tagid_list : [128,2]
     * subscribe_scene : ADD_SCENE_QR_CODE
     * qr_scene : 98765
     * qr_scene_str :
     */

    private Integer subscribe;
    private String openid;
    private String language;
    private Integer subscribe_time;
    private String unionid;
    private String remark;
    private Integer groupid;
    private String subscribe_scene;
    private Integer qr_scene;
    private String qr_scene_str;
    private List<Integer> tagid_list;
}
