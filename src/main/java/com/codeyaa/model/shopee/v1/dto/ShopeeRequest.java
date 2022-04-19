package com.codeyaa.model.shopee.v1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Funtionalcode
 * @className ShopeeRequest
 * @description shopee报文
 * @date 2021/6/3 18:00
 */
@Data
@NoArgsConstructor
public class ShopeeRequest {
    /**
     * 合作方(代发商店铺Id)
     */
    private Long partner_id;
    /**
     * 订单编号
     */
    private String ordersn;
    /**
     * 店铺Id
     */
    private Long shopid;
    /**
     * 操作时间/秒
     */
    private Long timestamp;
    /**
     * 地区,官方只支持'CN'
     */
    private String area;
    /**
     * 物流Id
     */
    private Long logistic_id;
    /**
     * 揽收编号(一般填写自定义快递单号)
     */
    private String fm_tn;
    /**
     * 揽收方式（取货(pickup)/送货(dropoff)）
     */
    private String shipment_method;
    /**
     * 生成日期 例:2020-01-01
     */
    private String declare_date;
    /**
     * 开始时间 例:2020-01-01
     */
    private String from_date;
    /**
     * 结束时间 例:2020-01-01
     */
    private String to_date;
    /**
     * 生成几个揽收编号,单次最多20
     */
    private Integer quantity;
    /**
     * 订单列表
     */
    private List<Order> order_list;
    /**
     * 订单号列表,同上order_list
     */
    private List<String> ordersn_list;
    /**
     * 揽收编号(快递单号)列表
     */
    private List<String> fm_tn_list;
    /**
     * 收货信息
     */
    private SellerInfo seller_info;

}
