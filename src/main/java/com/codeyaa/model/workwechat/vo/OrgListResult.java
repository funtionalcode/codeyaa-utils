package com.codeyaa.model.workwechat.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname OrgListResult
 * @Description 企业微信部门响应实体类
 * @Date 2021/7/21 15:39
 * @Author Funtionalcode
 */
@Data
public class OrgListResult implements Serializable {
    private static final long serialVersionUID = 7465934988306831610L;
    /**
     * errcode : 0
     * errmsg : ok
     * department : [{"id":1,"name":"111","parentid":0,"order":100000000}]
     */

    private Integer errcode;
    private String errmsg;
    private List<DepartmentBean> department;

    @Data
    public static class DepartmentBean implements Serializable {
        private static final long serialVersionUID = -8416010069591908918L;
        /**
         * id : 1
         * name :  111
         * parentid : 0
         * order : 100000000
         */

        private Integer id;
        private String name;
        private Integer parentid;
        private Integer order;
    }
}
