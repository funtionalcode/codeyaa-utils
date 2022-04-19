package com.codeyaa.utils.tripartite.workwechat;

import com.codeyaa.model.workwechat.dto.ApplicationTextMessage;
import com.codeyaa.model.workwechat.dto.Mobile;
import com.codeyaa.model.workwechat.vo.*;
import com.google.gson.Gson;
import lombok.Data;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @Classname WorkWechat
 * @Description 企业微信工具类
 * @Date 2021/7/21 14:20
 * @Author Funtionalcode
 */
@Data
public class WorkWechatUtil {
    /**
     * 企业 ID
     */
    private String corpId;
    /**
     * 企业应用密钥
     */
    private String corpSecret;
    /**
     * 企业应用 ID
     */
    private Integer agentId;
    private static final Session REQUESTS = Requests.session();
    private static final Gson GSON = new Gson();

    public WorkWechatUtil(String corpId, String corpSecret, Integer agentId) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
        this.agentId = agentId;
    }

    public AccessTokenResult getAccessToken() {
        String apiUrl = String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpId, corpSecret);
        RawResponse response = REQUESTS.get(apiUrl)
                .charset(StandardCharsets.UTF_8)
                .send();
        return GSON.fromJson(response.readToText(), AccessTokenResult.class);
    }

    public UserIdByMobileResult getUserIdByMobileId(String accessToken, String mobileStr) {
        String apiUrl = String.format("https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=%s&debug=1", accessToken);
        Mobile mobile = new Mobile();
        mobile.setMobile(mobileStr);

        RawResponse response = REQUESTS.post(apiUrl)
                .jsonBody(mobile)
                .charset(StandardCharsets.UTF_8)
                .send();
        return GSON.fromJson(response.readToText(), UserIdByMobileResult.class);
    }

    public Boolean sendAppText(String accessToken, String text, String toUser) {
        String apiUrl = String.format("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s&debug=1", accessToken);
        ApplicationTextMessage applicationTextMessage = new ApplicationTextMessage();
        applicationTextMessage.setTouser(null == toUser ? "@all" : toUser);
        applicationTextMessage.setMsgtype("text");
        applicationTextMessage.setAgentid(agentId);
        ApplicationTextMessage.TextBean textBean = new ApplicationTextMessage.TextBean();
        textBean.setContent(text);

        applicationTextMessage.setText(textBean);
        applicationTextMessage.setSafe(0);

        RawResponse response = REQUESTS.post(apiUrl)
                .jsonBody(applicationTextMessage)
                .charset(StandardCharsets.UTF_8)
                .send();
        SendAPPTextResult result = GSON.fromJson(response.readToText(), SendAPPTextResult.class);
        return Optional.ofNullable(result).map(res -> res.getErrcode() != 0).orElse(false);
    }

    public OrgListResult getOrgList(String accessToken) {
        String apiUrl = String.format("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s&debug=1", accessToken);

        RawResponse response = REQUESTS.get(apiUrl)
                .charset(StandardCharsets.UTF_8)
                .send();
        return GSON.fromJson(response.readToText(), OrgListResult.class);
    }

    public OrgUserDetailsResult getOrgUserDetails(String accessToken, Integer orgId) {
        Integer fetch_child = 1;
        String apiUrl = String.format("https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=%s&department_id=%s&fetch_child=%s&debug=1", accessToken, orgId, fetch_child);

        RawResponse response = Requests.session().get(apiUrl)
                .charset(StandardCharsets.UTF_8)
                .send();
        return GSON.fromJson(response.readToText(), OrgUserDetailsResult.class);
    }
}
