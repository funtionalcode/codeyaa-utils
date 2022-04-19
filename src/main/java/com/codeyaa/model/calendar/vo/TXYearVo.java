package com.codeyaa.model.calendar.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TXYearVo implements Serializable {
    private Integer code;
    private String msg;
    private List<NewslistBean> newslist;

    @Data
    public static class NewslistBean implements Serializable {
        private String date;
        private Integer daycode;
        private Integer weekday;
        private String cnweekday;
        private String lunaryear;
        private String lunarmonth;
        private String lunarday;
        private String info;
        private String start;
        private String now;
        private String end;
        private String holiday;
        private String name;
        private String enname;
        private Integer isnotwork;
        private List<String> vacation;
        private List<String> remark;
        private String wage;
        private String tip;
        private String rest;
    }
}
