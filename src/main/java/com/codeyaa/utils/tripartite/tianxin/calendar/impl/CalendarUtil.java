package com.codeyaa.utils.tripartite.tianxin.calendar.impl;

import com.codeyaa.model.calendar.dto.TXYearRequest;
import com.codeyaa.model.calendar.vo.BDCalendarVo;
import com.codeyaa.model.calendar.vo.TXYearVo;
import com.codeyaa.utils.common.client.CommonClient;
import com.codeyaa.utils.tripartite.tianxin.calendar.Calendar;
import com.codeyaa.utils.tripartite.tianxin.calendar.client.CalendarClient;


public class CalendarUtil implements Calendar {
    private static final String BAIDU_MONTH_URL = "http://www.autmone.com/openapi/icalendar/queryMonth?date=";
    private static final String TIANXING_YEAR_URL = "http://api.tianapi.com/txapi/jiejiari/index";

    /**
     * show 方法的简述.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param date 年月，yyyy-MM格式
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/24 1:03
     */
    @Override
    public BDCalendarVo getMonthCalendar(String date) {
        return CalendarClient.queryMonth(BAIDU_MONTH_URL, date);
    }

    @Override
    public TXYearVo txGetMonthYear(TXYearRequest txYearRequest) {
        txYearRequest.setUrl(TIANXING_YEAR_URL);
        return CommonClient.GSON.fromJson(CalendarClient.queryYear(txYearRequest), TXYearVo.class);
    }

    @Override
    public TXYearVo txGetMonthYearRequests(TXYearRequest txYearRequest) {
        String apiUrl = String.format("%s?key=%s&type=%s&date=%s",
                TIANXING_YEAR_URL,txYearRequest.getKey(),txYearRequest.getType(),txYearRequest.getDate());
        String toText = CommonClient.REQUESTS.get(apiUrl).send().readToText()
                .replaceAll("\"vacation\":\"\"","\"vacation\":[]")
                .replaceAll("\"remark\":\"\"","\"remark\":[]");
        return CommonClient.GSON.fromJson(toText, TXYearVo.class);
    }
}
