package com.codeyaa.bangsun;

import com.codeyaa.utils.common.FileUtil;
import com.google.gson.Gson;

import java.io.InputStream;

public class BangsunTest {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        toBangsunJson();
    }

    public static void toBangsunJson() {
        InputStream resourceAsStream = BangsunTest.class.getClassLoader().getResourceAsStream("RHZX.txt");
        System.out.println(BangsunTest.class.getClassLoader().getResource("RHZX.txt").getPath());
        String s = FileUtil.readStream(resourceAsStream);
        RHZX rhzx = gson.fromJson(s, RHZX.class);
        RHZX.ItemsBean itemsBean = rhzx.getItems().get(0);
        Integer ccCmCntUrt75 = itemsBean.getCcCmCntUrt75();
        Integer queryCOrg12m = itemsBean.getQueryCOrg12m();
        Integer cardOpenMonth = itemsBean.getCardOpenMonth();
        Double loanHouseCleanAmtR = itemsBean.getLoanHouseCleanAmtR();
        Integer queryLatestMonth = itemsBean.getQueryLatestMonth();
        Integer cardMinAmtAll = itemsBean.getCardMinAmtAll();
        Integer loanUnpayOrgCnt = itemsBean.getLoanUnpayOrgCnt();
        Integer loanMortCnt = itemsBean.getLoanMortCnt();
        Integer queryLOrg12m = itemsBean.getQueryLOrg12m();
        Integer loanAmt3mR = itemsBean.getLoanAmt3mR();
        StringBuilder sb = new StringBuilder();
        sb.append("cc_cm_cnt_urt75 （额度使用率>75%的账户数）：").append(ccCmCntUrt75).append("\r\n").
                append("query_l_org_12m（最近12个月（360天）“贷款审批”查询机构数）：").append(queryCOrg12m).append("\r\n").
                append("card_open_month（首张（已激活未销户RMB贷记卡）开卡时间距报告日期月份）：").append(cardOpenMonth).append("\r\n").
                append("loan_house_clean_amt_r（个人住房贷已还比例（包括公积金）：").append(loanHouseCleanAmtR).append("\r\n").
                append("query_latest_month（最近一次查询日期距报告日期月份数（天数/30)，1-24，如无查询则25）：").append(queryLatestMonth).append("\r\n").
                append("card_min_amt_all（单家行最低授信额）：").append(cardMinAmtAll).append("\r\n").
                append("loan_unpay_org_cnt（未结清贷款机构数）：").append(loanUnpayOrgCnt).append("\r\n").
                append("loan_mort_cnt （抵押贷款笔数）：").append(loanMortCnt).append("\r\n").
                append("query_c_org_12m （最近12个月（360天）“信用卡审批”查询机构数）：").append(queryLOrg12m).append("\r\n").
                append(" loan_amt_3m_r （近3个月新增贷款笔数占比）：").append(loanAmt3mR).append("\r\n");
        System.out.println(sb);
    }
}
