package com.codeyaa.model.tianxin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllegoricalResult implements Serializable {
    /**
     * code : 200
     * msg : success
     * newslist : [{"quest":"掉进陷阱里的野猪","result":"张牙舞爪；死路一条"}]
     */

    private Integer code;
    private String msg;
    private List<NewslistBean> newslist;

    @Data
    public static class NewslistBean implements Serializable {
        /**
         * quest : 掉进陷阱里的野猪
         * result : 张牙舞爪；死路一条
         */

        private String quest;
        private String result;
    }
}
