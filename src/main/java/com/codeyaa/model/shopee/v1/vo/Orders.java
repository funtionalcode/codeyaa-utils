package com.codeyaa.model.shopee.v1.vo;

import com.codeyaa.model.shopee.v1.dto.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Funtionalcode
 * @className Orders
 * @description TODO(用一句话描述该文件做什么)
 * @date 2021/6/3 23:18
 */
@Data
@NoArgsConstructor
class Orders {
    private RecipientAddress recipient_address;
    private List<Order> orders;
}
