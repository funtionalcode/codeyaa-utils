package com.codeyaa.model.shopee.v1.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className RecipientAddress
 * @description TODO(用一句话描述该文件做什么)
 * @date 2021/6/3 23:16
 */
@Data
@NoArgsConstructor
public class RecipientAddress {
    private String name;
    private String zipcode;
    private String full_address;
    private String phone;
}
