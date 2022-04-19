package com.codeyaa.model.shopee.v1.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Funtionalcode
 * @className FmResult
 * @description shopee v1 接口 获取预览编号响应体
 * @date 2021/6/3 23:04
 */
@Data
@NoArgsConstructor
public class FmResult {
    private Boolean success;
    private String msg;
    private List<String> fm_tn_list;

    public FmResult(Boolean success) {
        this.success = success;
    }

    public FmResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
