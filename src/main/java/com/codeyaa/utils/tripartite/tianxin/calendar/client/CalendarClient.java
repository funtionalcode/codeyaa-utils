package com.codeyaa.utils.tripartite.tianxin.calendar.client;

import com.codeyaa.model.calendar.dto.TXYearRequest;
import com.codeyaa.model.calendar.vo.BDCalendarVo;
import com.codeyaa.utils.common.client.CommonClient;

public class CalendarClient extends CommonClient {
    public static BDCalendarVo queryMonth(String url, String month) {
        return REQUESTS.get(url + month).send().readToJson(BDCalendarVo.class);
    }

    public static String queryYear(TXYearRequest txYearRequest) {
        String url = txYearRequest.getUrl();
        String key = txYearRequest.getKey();
        String type = txYearRequest.getType();
        String date = txYearRequest.getDate();
        String get = String.format("%s?key=%s&type=%s&date=%s", url, key, type, date);
        return REQUESTS.get(get).send().readToText();
    }
}
