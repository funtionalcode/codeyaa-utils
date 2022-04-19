package com.codeyaa.bangsun;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RHZX implements Serializable {

    private static final long serialVersionUID = 4662828109150167905L;
    @SerializedName("@type")
    private String _$Type157; // FIXME check this code
    @SerializedName("customization")
    private CustomizationBean customization;
    @SerializedName("result")
    private Integer result;
    @SerializedName("retCode")
    private String retCode;
    @SerializedName("score")
    private Integer score;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("credits")
    private List<CreditsBean> credits;
    @SerializedName("items")
    private List<ItemsBean> items;

    @Data
    public static class CustomizationBean implements Serializable {
        private static final long serialVersionUID = 6663580584727194481L;
        @SerializedName("@type")
        private String _$Type106; // FIXME check this code
    }

    @Data
    public static class CreditsBean implements Serializable {
        private static final long serialVersionUID = -3017138084334158979L;

        @SerializedName("comments")
        private String comments;
        @SerializedName("createTime")
        private Long createTime;
        @SerializedName("customization")
        private CustomizationBeanX customization;
        @SerializedName("ruleName")
        private String ruleName;
        @SerializedName("rulePackageName")
        private String rulePackageName;
        @SerializedName("score")
        private Integer score;
        @SerializedName("uuid")
        private String uuid;
        @SerializedName("weight")
        private Integer weight;

        @Data
        public static class CustomizationBeanX implements Serializable {
            private static final long serialVersionUID = 1225378093969211026L;
            @SerializedName("@type")
            private String _$Type196; // FIXME check this code
            @SerializedName("评分卡类型")
            private String 评分卡类型;
            @SerializedName("规则类别")
            private String 规则类别;
            @SerializedName("规则包标签")
            private String 规则包标签;
        }
    }

    @Data
    public static class ItemsBean implements Serializable {
        private static final long serialVersionUID = -3646428760715763437L;
        @SerializedName("@type")
        private String _$Type122; // FIXME check this code
        @SerializedName("frms_glbank_xyk_averageAnnualDeposit")
        private Integer frmsGlbankXykAverageAnnualDeposit;
        @SerializedName("frms_id_bank_bank_midserious_overdueb")
        private String frmsIdBankBankMidseriousOverdueb;
        @SerializedName("frms_bill_addr_region")
        private String frmsBillAddrRegion;
        @SerializedName("frms_tl_1st_limit_verify")
        private Integer frmsTl1stLimitVerify;
        @SerializedName("frms_dwell_address")
        private String frmsDwellAddress;
        @SerializedName("frms_mt_cqBlack_xyk_phone")
        private String frmsMtCqBlackXykPhone;
        @SerializedName("loan_unpay_org_cnt")
        private Integer loanUnpayOrgCnt;
        @SerializedName("frms_id_nbank_all_m3_apply_orgnumb")
        private String frmsIdNbankAllM3ApplyOrgnumb;
        @SerializedName("frms_tl_1st_undetrmined_type")
        private Integer frmsTl1stUndetrminedType;
        @SerializedName("frms_mt_org")
        private String frmsMtOrg;
        @SerializedName("frms_mt_cqBlack_xyk_contact_phone")
        private String frmsMtCqBlackXykContactPhone;
        @SerializedName("frms_marital_status")
        private String frmsMaritalStatus;
        @SerializedName("inter_code")
        private String interCode;
        @SerializedName("frms_bill_addr_city")
        private String frmsBillAddrCity;
        @SerializedName("frms_home_add")
        private String frmsHomeAdd;
        @SerializedName("frms_bill_addr_spec")
        private String frmsBillAddrSpec;
        @SerializedName("frms_age")
        private Integer frmsAge;
        @SerializedName("frms_process_key")
        private String frmsProcessKey;
        @SerializedName("frms_cell_bank_bank_high_lostb")
        private String frmsCellBankBankHighLostb;
        @SerializedName("frms_emp_city")
        private String frmsEmpCity;
        @SerializedName("frms_cell_bank_bank_general_overdueb")
        private String frmsCellBankBankGeneralOverdueb;
        @SerializedName("frms_contact_2_name")
        private String frmsContact2Name;
        @SerializedName("frms_id_nbank_p2p_high_lostb")
        private String frmsIdNbankP2pHighLostb;
        @SerializedName("frms_id_no")
        private String frmsIdNo;
        @SerializedName("frms_id_bank_bank_high_lostb")
        private String frmsIdBankBankHighLostb;
        @SerializedName("frms_emp_add")
        private String frmsEmpAdd;
        @SerializedName("frms_id_nbank_all_m1_apply_orgnumb")
        private String frmsIdNbankAllM1ApplyOrgnumb;
        @SerializedName("frms_id_type")
        private String frmsIdType;
        @SerializedName("frms_mt_uuid")
        private String frmsMtUuid;
        @SerializedName("node_name")
        private String nodeName;
        @SerializedName("frms_pbocpath_name")
        private String frmsPbocpathName;
        @SerializedName("frms_order_id")
        private String frmsOrderId;
        @SerializedName("frms_tl_app_no")
        private String frmsTlAppNo;
        @SerializedName("frms_tl_1st_apply_result")
        private Integer frmsTl1stApplyResult;
        @SerializedName("frms_tl_1st_instant_use_right_tag")
        private String frmsTl1stInstantUseRightTag;
        @SerializedName("frms_channel")
        private String frmsChannel;
        @SerializedName("frms_contact_2_mobile")
        private String frmsContact2Mobile;
        @SerializedName("frms_cell_nbank_p2p_mid_serious_overdueb")
        private String frmsCellNbankP2pMidSeriousOverdueb;
        @SerializedName("frms_pboc2_filepath")
        private String frmsPboc2Filepath;
        @SerializedName("frms_id_bank_bank_low_refuseb")
        private String frmsIdBankBankLowRefuseb;
        @SerializedName("frms_phone_no")
        private String frmsPhoneNo;
        @SerializedName("frms_emp_province")
        private String frmsEmpProvince;
        @SerializedName("frms_home_city")
        private String frmsHomeCity;
        @SerializedName("frms_app_date")
        private String frmsAppDate;
        @SerializedName("frms_pboc_report_no")
        private String frmsPbocReportNo;
        @SerializedName("frms_mt_2nd_result")
        private Integer frmsMt2ndResult;
        @SerializedName("frms_org_name")
        private String frmsOrgName;
        @SerializedName("frms_bill_addr_prov")
        private String frmsBillAddrProv;
        @SerializedName("frms_id_bank_bank_poorcredit_fraudb")
        private String frmsIdBankBankPoorcreditFraudb;
        @SerializedName("frms_mt_external")
        private String frmsMtExternal;
        @SerializedName("frms_contacts_two_phone_no")
        private String frmsContactsTwoPhoneNo;
        @SerializedName("frms_mt_1st_reject_reason")
        private String frmsMt1stRejectReason;
        @SerializedName("frms_tl_1st_reject_reason")
        private String frmsTl1stRejectReason;
        @SerializedName("frms_org_address")
        private String frmsOrgAddress;
        @SerializedName("frms_mt_2nd_reject_reason")
        private String frmsMt2ndRejectReason;
        @SerializedName("frms_channel_no")
        private String frmsChannelNo;
        @SerializedName("frms_contacts_phone_no")
        private String frmsContactsPhoneNo;
        @SerializedName("frms_mt_1st_undetermined_type")
        private Integer frmsMt1stUndeterminedType;
        @SerializedName("frms_id_nbank_p2p_general_overdueb")
        private String frmsIdNbankP2pGeneralOverdueb;
        @SerializedName("frms_contact_1_type")
        private String frmsContact1Type;
        @SerializedName("frms_uuid")
        private String frmsUuid;
        @SerializedName("frms_contact_1_relation")
        private String frmsContact1Relation;
        @SerializedName("frms_emp_zone")
        private String frmsEmpZone;
        @SerializedName("frms_mt_cqBlack_xyk_df_uuid")
        private String frmsMtCqBlackXykDfUuid;
        @SerializedName("frms_cell_nbank_p2p_high_lostb")
        private String frmsCellNbankP2pHighLostb;
        @SerializedName("frms_biz_code")
        private String frmsBizCode;
        @SerializedName("query_l_org_12m")
        private Integer queryLOrg12m;
        @SerializedName("frms_customer_id")
        private String frmsCustomerId;
        @SerializedName("frms_educa")
        private String frmsEduca;
        @SerializedName("frms_tl_1st_instant_use_amt")
        private String frmsTl1stInstantUseAmt;
        @SerializedName("frms_contact_1_mobile")
        private String frmsContact1Mobile;
        @SerializedName("frms_apply_card_type")
        private String frmsApplyCardType;
        @SerializedName("frms_home_zone")
        private String frmsHomeZone;
        @SerializedName("frms_mtxyk_pboc_path")
        private String frmsMtxykPbocPath;
        @SerializedName("frms_approve_result_external")
        private String frmsApproveResultExternal;
        @SerializedName("frms_home_state")
        private String frmsHomeState;
        @SerializedName("frms_cust_group_type")
        private String frmsCustGroupType;
        @SerializedName("frms_mt_cqBlack_xyk_org_addr")
        private String frmsMtCqBlackXykOrgAddr;
        @SerializedName("query_c_org_12m")
        private Integer queryCOrg12m;
        @SerializedName("frms_cell_nbank_all_m1_apply_orgnumb")
        private String frmsCellNbankAllM1ApplyOrgnumb;
        @SerializedName("loan_house_clean_amt_r")
        private Double loanHouseCleanAmtR;
        @SerializedName("loan_mort_cnt")
        private Integer loanMortCnt;
        @SerializedName("frms_cell_bank_bank_midserious_overdueb")
        private String frmsCellBankBankMidseriousOverdueb;
        @SerializedName("frms_cell_bank_bank_low_refuseb")
        private String frmsCellBankBankLowRefuseb;
        @SerializedName("frms_call_type")
        private Integer frmsCallType;
        @SerializedName("frms_flow_id")
        private String frmsFlowId;
        @SerializedName("cc_cm_cnt_urt75")
        private Integer ccCmCntUrt75;
        @SerializedName("frms_pboc2_authid")
        private String frmsPboc2Authid;
        @SerializedName("frms_id_nbank_court_low_confusionb")
        private String frmsIdNbankCourtLowConfusionb;
        @SerializedName("frms_cell_nbank_all_d7_apply_orgnumb")
        private String frmsCellNbankAllD7ApplyOrgnumb;
        @SerializedName("frms_bank_org")
        private String frmsBankOrg;
        @SerializedName("frms_cell_bank_bank_poorcredit_fraudb")
        private String frmsCellBankBankPoorcreditFraudb;
        @SerializedName("frms_id_nbank_p2p_low_refuseb")
        private String frmsIdNbankP2pLowRefuseb;
        @SerializedName("frms_mt_2nd_undetermined_type")
        private Integer frmsMt2ndUndeterminedType;
        @SerializedName("frms_contact_2_type")
        private String frmsContact2Type;
        @SerializedName("frms_sex")
        private String frmsSex;
        @SerializedName("frms_target_grp")
        private Integer frmsTargetGrp;
        @SerializedName("frms_id_bank_bank_general_overdueb")
        private String frmsIdBankBankGeneralOverdueb;
        @SerializedName("frms_flow_uuid_now")
        private String frmsFlowUuidNow;
        @SerializedName("frms_mt_1st_undetermined_reason")
        private String frmsMt1stUndeterminedReason;
        @SerializedName("frms_product_cd")
        private String frmsProductCd;
        @SerializedName("frms_mt_flow_type")
        private String frmsMtFlowType;
        @SerializedName("frms_user_name")
        private String frmsUserName;
        @SerializedName("frms_mt_cqBlack_xyk_org_name")
        private String frmsMtCqBlackXykOrgName;
        @SerializedName("card_min_amt_all")
        private Integer cardMinAmtAll;
        @SerializedName("frms_id_bank_p2p_midserious_overdueb")
        private String frmsIdBankP2pMidseriousOverdueb;
        @SerializedName("loan_amt_3m_r")
        private Integer loanAmt3mR;
        @SerializedName("frms_cell_nbank_all_m3_apply_orgnumb")
        private String frmsCellNbankAllM3ApplyOrgnumb;
        @SerializedName("frms_bill_addr_type")
        private String frmsBillAddrType;
        @SerializedName("frms_tl_1st_instant_use_cont")
        private String frmsTl1stInstantUseCont;
        @SerializedName("frms_mt_1st_result")
        private Integer frmsMt1stResult;
        @SerializedName("frms_apply_city")
        private String frmsApplyCity;
        @SerializedName("frms_user_id")
        private String frmsUserId;
        @SerializedName("frms_occupation")
        private String frmsOccupation;
        @SerializedName("frms_id_nbank_all_d7_apply_orgnumb")
        private String frmsIdNbankAllD7ApplyOrgnumb;
        @SerializedName("frms_contact_2_relation")
        private String frmsContact2Relation;
        @SerializedName("frms_contact_1_name")
        private String frmsContact1Name;
        @SerializedName("card_open_month")
        private Integer cardOpenMonth;
        @SerializedName("query_latest_month")
        private Integer queryLatestMonth;
    }
}
