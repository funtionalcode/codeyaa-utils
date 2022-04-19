package com.codeyaa.model.wechat.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RefundResult implements Serializable {
    private static final long serialVersionUID = -1159805808771057523L;
    /**
     * refund_id : 50000000382019052709732678859
     * out_refund_no : 1217752501201407033233368018
     * transaction_id : 1217752501201407033233368018
     * out_trade_no : 1217752501201407033233368018
     * channel : ORIGINAL
     * user_received_account : 招商银行信用卡0403
     * success_time : 2020-12-01T16:18:12+08:00
     * create_time : 2020-12-01T16:18:12+08:00
     * status : SUCCESS
     * funds_account : UNSETTLED
     * amount : {"total":100,"refund":100,"from":[{"account":"AVAILABLE","amount":444}],"payer_total":90,"payer_refund":90,"settlement_refund":100,"settlement_total":100,"discount_refund":10,"currency":"CNY"}
     * promotion_detail : [{"promotion_id":"109519","scope":"SINGLE","type":"DISCOUNT","amount":5,"refund_amount":100,"goods_detail":{"merchant_goods_id":"1217752501201407033233368018","wechatpay_goods_id":"1001","goods_name":"iPhone6s 16G","unit_price":528800,"refund_amount":528800,"refund_quantity":1}}]
     */

    private String refund_id;
    private String out_refund_no;
    private String transaction_id;
    private String out_trade_no;
    private String channel;
    private String user_received_account;
    private String success_time;
    private String create_time;
    private String status;
    private String code;
    private String message;
    private String funds_account;
    private AmountBean amount;
    private List<PromotionDetailBean> promotion_detail;
    /**
     * detail : {"detail":{"issue":"sign not match"},"field":"signature","location":"authorization","sign_information":{"method":"POST","sign_message_length":363,"truncated_sign_message":"POST\n/v3/refund/domestic/refunds\n1634317966\n6420bcb423290faac4feb09233b8dc32\n{\"transa\n","url":"/v3/refund/domestic/refunds"}}
     */

    private DetailBeanX detail;
    /**
     * detail : {"location":"body","value":36}
     */

    @SerializedName("detail")
    private DetailBean detailX;

    @Data
    public static class AmountBean implements Serializable {
        private static final long serialVersionUID = -4516016587810960019L;
        /**
         * total : 100
         * refund : 100
         * from : [{"account":"AVAILABLE","amount":444}]
         * payer_total : 90
         * payer_refund : 90
         * settlement_refund : 100
         * settlement_total : 100
         * discount_refund : 10
         * currency : CNY
         */

        private int total;
        private int refund;
        private int payer_total;
        private int payer_refund;
        private int settlement_refund;
        private int settlement_total;
        private int discount_refund;
        private String currency;
        private List<FromBean> from;

        @Data
        public static class FromBean implements Serializable {
            private static final long serialVersionUID = 4989947903883888611L;
            /**
             * account : AVAILABLE
             * amount : 444
             */

            private String account;
            private int amount;
        }
    }

    @Data
    public static class PromotionDetailBean implements Serializable {
        private static final long serialVersionUID = 354465204015046586L;
        /**
         * promotion_id : 109519
         * scope : SINGLE
         * type : DISCOUNT
         * amount : 5
         * refund_amount : 100
         * goods_detail : {"merchant_goods_id":"1217752501201407033233368018","wechatpay_goods_id":"1001","goods_name":"iPhone6s 16G","unit_price":528800,"refund_amount":528800,"refund_quantity":1}
         */

        private String promotion_id;
        private String scope;
        private String type;
        private int amount;
        private int refund_amount;
        private GoodsDetailBean goods_detail;

        @Data
        public static class GoodsDetailBean implements Serializable {
            private static final long serialVersionUID = -5743565543422893278L;
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

    @Data
    public static class DetailBeanX implements Serializable {
        private static final long serialVersionUID = 3737508480804544301L;
        /**
         * detail : {"issue":"sign not match"}
         * field : signature
         * location : authorization
         * sign_information : {"method":"POST","sign_message_length":363,"truncated_sign_message":"POST\n/v3/refund/domestic/refunds\n1634317966\n6420bcb423290faac4feb09233b8dc32\n{\"transa\n","url":"/v3/refund/domestic/refunds"}
         */

        private DetailBean detail;
        private String field;
        private String location;
        private SignInformationBean sign_information;

        @Data
        public static class DetailBean implements Serializable {
            private static final long serialVersionUID = 7301876153866103366L;
            /**
             * issue : sign not match
             */

            private String issue;
        }

        @Data
        public static class SignInformationBean implements Serializable {
            private static final long serialVersionUID = 7121002419308165766L;
            /**
             * method : POST
             * sign_message_length : 363
             * truncated_sign_message : POST
             * /v3/refund/domestic/refunds
             * 1634317966
             * 6420bcb423290faac4feb09233b8dc32
             * {"transa
             * url : /v3/refund/domestic/refunds
             */

            private String method;
            private int sign_message_length;
            private String truncated_sign_message;
            private String url;
        }
    }

    @Data
    public static class DetailBean implements Serializable {
        private static final long serialVersionUID = 5706503530637367800L;
        /**
         * location : body
         * value : 36
         */

        private String location;
        private int value;
    }
}
