package com.codeyaa.model.shopee.v1.dto;


import com.codeyaa.model.shopee.v1.vo.RecipientAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className PreOrderRequest
 * @description 发货预报请求报文
 * @date 2021/6/3 11:39
 */
@Data
@NoArgsConstructor
public class PreOrderRequest {
    private String ids;
    private String logisticId;
    private String orderId;
    private String fm;
    private Long shopId;
    private RecipientAddress recipientAddress;
}
