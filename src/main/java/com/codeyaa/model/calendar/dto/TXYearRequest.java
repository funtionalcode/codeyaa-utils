package com.codeyaa.model.calendar.dto;

import lombok.Data;

@Data
public class TXYearRequest {
    private String url;
    private String key;
    /**
     * 查询类型，0批量、1按年、2按月、3范围 默认0
     */
    private String type;
    /**
     * 工作模式，为1返回当天全部相关节日信息 默认0
     */
    private String mode;
    private String date;
}
