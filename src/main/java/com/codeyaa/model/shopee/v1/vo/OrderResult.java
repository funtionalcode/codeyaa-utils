package com.codeyaa.model.shopee.v1.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Funtionalcode
 * @className OrderResult
 * @description 获取订单接口响应体
 * @date 2021/6/3 23:10
 */
@Data
@NoArgsConstructor
public class OrderResult {
    private List<Orders> orders;
    private Boolean success;
}
