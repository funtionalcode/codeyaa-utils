package com.codeyaa;

import com.codeyaa.utils.common.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Funtionalcode
 * @className CalendarTest
 * @description TODO(用一句话描述该文件做什么)
 * @date 2021/6/1 17:23
 */
public class CalendarTest {
    public static void main(String[] args) {
        getTimeRegx();
    }

    private static void calculation() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        /*// 从一个 Calendar 对象中获取 Date 对象
        Date date = calendar.getTime();
        //使用给定的 Date 设置此 Calendar 的时间
        calendar.setTime(date);*/
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        System.out.println("当前时间日设置为8后,时间是:" + dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.HOUR, 2);
        System.out.println("当前时间加2小时后,时间是:" + dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, -2);
        System.out.println("当前日期减2个月后,时间是:" + dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, -15);
        System.out.println("当前日期减15天后,时间是:" + dateFormat.format(calendar.getTime()));
        System.out.println("年：" + calendar.get(Calendar.YEAR));
        System.out.println("月：" + calendar.get(Calendar.MONTH) + 1);

        System.out.println(((String) null) instanceof String);
    }

    private static void currentDay() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("week = " + week);
    }

    private static void getTimeTest() {
        String string = "2021.01.02";
        System.out.println(DateUtil.getStrTime(string, "\\."));
    }

    private static void getTimeRegx() {
        String string = "2018.05.1414:05:04";
        String m1 = DateUtil.getDateFormat(string, "\\.", "m");
        String string1 = "2018.06.1414:05:04";
        String m2 = DateUtil.getDateFormat(string1, "\\.", "m");
        int i = Integer.parseInt(m2) - Integer.parseInt(m1);
        System.out.println("i = " + i);
    }
}
