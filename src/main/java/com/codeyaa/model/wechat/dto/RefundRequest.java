package com.codeyaa.model.wechat.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RefundRequest implements Serializable {
    private static final long serialVersionUID = 2643548302581216598L;
    /**
     * transaction_id : 1217752501201407033233368018
     * out_refund_no : 1217752501201407033233368018
     * reason : 商品已售完
     * notify_url : https://weixin.qq.com
     * funds_account : AVAILABLE
     * amount : {"refund":888,"from":[{"account":"AVAILABLE","amount":444}],"total":888,"currency":"CNY"}
     * goods_detail : [{"merchant_goods_id":"1217752501201407033233368018","wechatpay_goods_id":"1001","goods_name":"iPhone6s 16G","unit_price":528800,"refund_amount":528800,"refund_quantity":1}]
     */

    private String transaction_id;
    private String out_trade_no;
    private String out_refund_no;
    private String reason;
    private String notify_url;
    private String funds_account;
    private AmountBean amount;
    private List<GoodsDetailBean> goods_detail;

    @Data
    public static class AmountBean implements Serializable {
        private static final long serialVersionUID = 2917420776401148851L;
        /**
         * refund : 888
         * from : [{"account":"AVAILABLE","amount":444}]
         * total : 888
         * currency : CNY
         */

        private int refund;
        private int total;
        private String currency;
        private List<FromBean> from;

        @Data
        public static class FromBean implements Serializable {
            private static final long serialVersionUID = -4940577398647469448L;
            /**
             * account : AVAILABLE
             * amount : 444
             */

            private String account;
            private int amount;
        }
    }

    @Data
    public static class GoodsDetailBean implements Serializable {
        private static final long serialVersionUID = 5283746731157388182L;
        /**
         * merchant_goods_id : 1217752501201407033233368018
         * wechatpay_goods_id : 1001
         * goods_name : iPhone6s 16G
         * unit_price : 528800
         * refund_amount : 528800
         * refund_quantity : 1
         */

        private String merchant_goods_id;
        private String wechatpay_goods_id;
        private String goods_name;
        private int unit_price;
        private int refund_amount;
        private int refund_quantity;
    }
}
