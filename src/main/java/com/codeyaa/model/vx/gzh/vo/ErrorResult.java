package com.codeyaa.model.vx.gzh.vo;

import lombok.Data;

@Data
public class ErrorResult {
    private String errorcode;
    private String errmsg;
    private String msg_id;
    private String msg_data_id;
}
