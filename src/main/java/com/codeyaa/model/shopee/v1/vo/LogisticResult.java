package com.codeyaa.model.shopee.v1.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Funtionalcode
 * @className LogisticResult
 * @description 快递Id响应体
 * @date 2021/6/3 10:44
 */
@Data
@NoArgsConstructor
public class LogisticResult {
    private List<Logistic> logistics;
    private Boolean success;

    public LogisticResult(Boolean success) {
        this.success = success;
    }
}
