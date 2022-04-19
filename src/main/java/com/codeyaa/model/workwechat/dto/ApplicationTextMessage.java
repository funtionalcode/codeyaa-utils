package com.codeyaa.model.workwechat.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname TextMessage
 * @Description 企业微信应用发送信息请求体
 * @Date 2021/7/21 14:44
 * @Author Funtionalcode
 */
@Data
public class ApplicationTextMessage implements Serializable {

    private static final long serialVersionUID = 1304887619562329588L;
    /**
     * touser : ["userid1","userid2","CorpId1/userid1","CorpId2/userid2"]
     * toparty : ["partyid1","partyid2","LinkedId1/partyid1","LinkedId2/partyid2"]
     * totag : ["tagid1","tagid2"]
     * toall : 0
     * msgtype : text
     * agentid : 1
     * text : {"content":"你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况<\/a>，聪明避开排队。"}
     * safe : 0
     */

    private Integer toall;
    private String msgtype;
    private Integer agentid;
    private TextBean text;
    private Integer safe;
    private String touser;
    private List<String> toparty;
    private List<String> totag;

    @Data
    public static class TextBean implements Serializable {
        private static final long serialVersionUID = -642303514721330789L;
        /**
         * content : 你的快递已到，请携带工卡前往邮件中心领取。
         * 出发前可查看<a href="http://work.weixin.qq.com">邮件中心视频实况</a>，聪明避开排队。
         */

        private String content;
        /**
         * touser : UserID1|UserID2|UserID3
         * toparty : PartyID1|PartyID2
         * totag : TagID1 | TagID2
         * msgtype : text
         * agentid : 1
         * text : {"content":"你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况<\/a>，聪明避开排队。"}
         * safe : 0
         * enable_id_trans : 0
         * enable_duplicate_check : 0
         * duplicate_check_interval : 1800
         */

        private String touser;
        private String toparty;
        private String totag;
        private String msgtype;
        private Integer agentid;
        private TextBean text;
        private Integer safe;
        private Integer enable_id_trans;
        private Integer enable_duplicate_check;
        private Integer duplicate_check_interval;
    }
}
