package com.codeyaa.model.vx.gzh.vo;

import com.codeyaa.model.vx.gzh.GzhMediaBase;
import lombok.Data;

@Data
public class UploadImgResultl extends GzhMediaBase {
    private String media_id;
    private String url;
    private String name;
    private Integer errcode;
    private String errmsg;
}
