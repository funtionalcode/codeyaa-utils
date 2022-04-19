package com.codeyaa.model.shopee.v1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className SellerInfo
 * @description 发货报文
 * @date 2021/6/3 18:17
 */
@Data
@NoArgsConstructor
class SellerInfo {
    /**
     * 收获地址
     */
    private String address;
    /**
     * 购买地址
     */
    private String name;
    /**
     * 邮政编码
     */
    private String zipcode;
    /**
     * 地区,官方只支持'CN'
     */
    private String area;
    /**
     * 收获人手机号
     */
    private String phone;
}
