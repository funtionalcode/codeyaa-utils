package com.codeyaa.model.workwechat.vo;

import lombok.Data;

import java.util.List;

/**
 * @Classname OrgUserDetailsResult
 * @Description 企业微信部门所有用户详情响应实体类
 * @Date 2021/7/21 15:55
 * @Author Funtionalcode
 */
@Data
public class OrgUserDetailsResult {
    /**
     * errcode : 0
     * errmsg : ok
     * userlist : [{"userid":"ZhengYaGe","name":"爱唯逸","department":[1],"position":"","mobile":"18376198461","gender":"1","email":"","avatar":"http://wework.qpic.cn/bizmail/qs0ibSxocnjl9sQN3rUtl1u8ia10pQ5vuEMvkUgdFUZQRPLx9OTVqtJA/0","status":1,"enable":1,"isleader":0,"extattr":{"attrs":[]},"hide_mobile":0,"telephone":"","order":[0],"main_department":1,"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc29d746a52c2862e8","alias":"爱唯逸","is_leader_in_dept":[0],"thumb_avatar":"http://wework.qpic.cn/bizmail/qs0ibSxocnjl9sQN3rUtl1u8ia10pQ5vuEMvkUgdFUZQRPLx9OTVqtJA/100"},{"userid":"WangCai","name":"小七","department":[1],"position":"","mobile":"","gender":"2","email":"","avatar":"https://wework.qpic.cn/wwhead/duc2TvpEgSTPk74IwG7Bsic8uuTMFqaUboT7eykn6PiaVRMAib4E0gcJwetyiayf2HdKd6tG5cxnRibk/0","status":1,"enable":1,"isleader":0,"extattr":{"attrs":[]},"hide_mobile":0,"telephone":"","order":[0],"main_department":1,"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc1a9ae36d5ae74a86","alias":"","is_leader_in_dept":[0],"thumb_avatar":"https://wework.qpic.cn/wwhead/duc2TvpEgSTPk74IwG7Bsic8uuTMFqaUboT7eykn6PiaVRMAib4E0gcJwetyiayf2HdKd6tG5cxnRibk/100"},{"userid":"BuFan","name":"张智洋","department":[1],"position":"","mobile":"18439351926","gender":"1","email":"","avatar":"http://wework.qpic.cn/bizmail/MdMPtSRh9ia7byRm0rRqX07PiawIEppZJwHml8sIUUR9L5ib2TtZ7PKfQ/0","status":1,"enable":1,"isleader":0,"extattr":{"attrs":[]},"hide_mobile":0,"telephone":"","order":[0],"main_department":1,"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc26d1b96fb73945e0","alias":"","is_leader_in_dept":[0],"thumb_avatar":"http://wework.qpic.cn/bizmail/MdMPtSRh9ia7byRm0rRqX07PiawIEppZJwHml8sIUUR9L5ib2TtZ7PKfQ/100"},{"userid":"66212429190d0aed2f735d073eaac690","name":"。","department":[1],"position":"","mobile":"","gender":"1","email":"","avatar":"http://wework.qpic.cn/bizmail/WP1840BDdGMbkg8I7XicW3IK12lesRdA7n9gePYiaibDVNYwtfZ22ojvg/0","status":1,"enable":1,"isleader":0,"extattr":{"attrs":[]},"hide_mobile":0,"telephone":"","order":[0],"main_department":1,"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vcee971f88488c68d9","alias":"","is_leader_in_dept":[0],"thumb_avatar":"http://wework.qpic.cn/bizmail/WP1840BDdGMbkg8I7XicW3IK12lesRdA7n9gePYiaibDVNYwtfZ22ojvg/100"},{"userid":"LianYu","name":"煉獄","department":[1],"position":"","mobile":"","gender":"1","email":"","avatar":"http://wework.qpic.cn/bizmail/hpROqAELgy9zoN8W7NTXOicR6amqdQMAhC0iaHsqCGudl8gfSicRGaicWA/","status":1,"enable":1,"isleader":0,"extattr":{"attrs":[]},"hide_mobile":0,"telephone":"","order":[0],"main_department":1,"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc154de5556291ddc0","alias":"","is_leader_in_dept":[0],"thumb_avatar":"http://wework.qpic.cn/bizmail/hpROqAELgy9zoN8W7NTXOicR6amqdQMAhC0iaHsqCGudl8gfSicRGaicWA/100"},{"userid":"MoCheng","name":"陌城","department":[1],"position":"","mobile":"18736702293","gender":"1","email":"","avatar":"http://wework.qpic.cn/bizmail/fIib5picZ5qhKMwbWGe1qBPEWAmiacNyZRF9KRttu8cz2QiclH5U8E9Cow/0","status":1,"enable":1,"isleader":0,"extattr":{"attrs":[]},"hide_mobile":0,"telephone":"","order":[0],"main_department":1,"qr_code":"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc761aaaa8ddd5e688","alias":"","is_leader_in_dept":[0],"thumb_avatar":"http://wework.qpic.cn/bizmail/fIib5picZ5qhKMwbWGe1qBPEWAmiacNyZRF9KRttu8cz2QiclH5U8E9Cow/100"}]
     */

    private Integer errcode;
    private String errmsg;
    private List<UserlistBean> userlist;

    @Data
    public static class UserlistBean {

        private String userid;
        private String name;
        private String position;
        private String mobile;
        private String gender;
        private String email;
        private String avatar;
        private Integer status;
        private Integer enable;
        private Integer isleader;
        private ExtattrBean extattr;
        private Integer hide_mobile;
        private String telephone;
        private Integer main_department;
        private String qr_code;
        private String alias;
        private String thumb_avatar;
        private List<Integer> department;
        private List<Integer> order;
        private List<Integer> is_leader_in_dept;

        @Data
        public static class ExtattrBean {
            private List<String> attrs;
        }
    }
}
