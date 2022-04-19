package com.codeyaa.model.shopee.v1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className Order
 * @description 订单类
 * @date 2021/6/3 18:26
 */
@Data
@NoArgsConstructor
public class Order {

    /**
     * 订单编号
     */
    private String ordersn;
    /**
     * 包裹Id,已弃用,传入空值""即可
     */
    private String forder_id;
}
