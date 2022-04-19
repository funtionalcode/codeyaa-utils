package com.codeyaa.utils.tripartite.shopee.v1;

import com.codeyaa.model.shopee.v1.dto.PreOrderRequest;
import com.codeyaa.model.shopee.v1.dto.ShopeeRequest;
import com.codeyaa.model.shopee.v1.vo.OrderResult;
import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dongliu.requests.Header;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Funtionalcode
 * @className ShopeeUtil
 * @description Shopee快递代发工具类
 * @date 2021/6/3 17:56
 */
@Data
@NoArgsConstructor
public class ShopeeUtil {
    private static final Gson GSON = new Gson();
    private static final Session REQUESTS = Requests.session();
    private String parentId;
    private String shopeeApiKey;
    /**
     * 获取 READY_TO_SHIP 和 RETRY_SHIP 状态的面单
     */
    private final static String GET_AIRWAY_BILL_URL = "https://partner.shopeemobile.com/api/v1/logistics/airway_bill/get_mass";

    public ShopeeUtil(String shopeeApiKey) {
        this.shopeeApiKey = shopeeApiKey;
    }

    public OrderResult getOrder(PreOrderRequest preOrderRequest) {

        ShopeeRequest shopeeRequest = new ShopeeRequest();
        shopeeRequest.setPartner_id(Long.parseLong(parentId));
        shopeeRequest.setShopid(preOrderRequest.getShopId());
        shopeeRequest.setTimestamp(System.currentTimeMillis() / 1000);

        ArrayList<String> orderIds = new ArrayList<>();
        String[] orderIdTmp = preOrderRequest.getOrderId().split("/");
        for (String s : orderIdTmp) {
            orderIds.add(s);
        }
        shopeeRequest.setOrdersn_list(orderIds);

        String content = GSON.toJson(shopeeRequest);
        String url = "https://partner.shopeemobile.com/api/v1/orders/detail";
        ArrayList<Header> headers = new ArrayList<>();
        headers.add(new Header("Content-Type", "application/json"));
        headers.add(new Header("Authorization", getAuthorization(url, content)));
        String body = REQUESTS.post(url).headers(headers).body(content).send().readToText();
        OrderResult res = GSON.fromJson(body, OrderResult.class);
        return res;
    }

    public String lookBillPdf(String url) {
        return Requests.session().get(url).send().readToText();
    }

    private String getAuthorization(String url, String requestBody) {
        try {
            String baseStr = url + "|" + requestBody;
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(shopeeApiKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String result = Hex.encodeHexString(sha256_HMAC.doFinal(baseStr.getBytes(StandardCharsets.UTF_8)));
            return result;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }
}
