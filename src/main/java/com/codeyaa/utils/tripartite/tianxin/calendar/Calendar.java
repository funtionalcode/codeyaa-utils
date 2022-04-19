package com.codeyaa.utils.tripartite.tianxin.calendar;

import com.codeyaa.model.calendar.dto.TXYearRequest;
import com.codeyaa.model.calendar.vo.BDCalendarVo;
import com.codeyaa.model.calendar.vo.TXYearVo;

public interface Calendar {
    /**
     * show 方法的简述.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param date 年月，yyyy-MM格式
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/24 1:03
     */
    BDCalendarVo getMonthCalendar(String date);

    @Deprecated
    /***
     * show 方打包后运行空指针 建议基于 Requests 的方法
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     * @param txYearRequest
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/24 11:43
     */
    TXYearVo txGetMonthYear(TXYearRequest txYearRequest);

    TXYearVo txGetMonthYearRequests(TXYearRequest txYearRequest);
}
