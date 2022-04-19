package com.codeyaa.model.shopee.v1.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className Logistic
 * @description 快递id实体类
 * @date 2021/6/3 10:45
 */
@Data
@NoArgsConstructor
public class Logistic {
    /**
     * 送货、提货
     */
    private String shipment_method;
    /**
     * 快递Id
     */
    private Integer logistic_id;
    /**
     * 物流名称
     */
    private String logistic_name;
}
