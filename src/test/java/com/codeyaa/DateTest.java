package com.codeyaa;

import com.codeyaa.utils.common.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTest {
    public static void main(String[] args) {
        unitTest();
    }

    private static void dateTest() {
        String oldCalendar = DateUtil.getOldCalendar("20211001");
        Integer integer = DateUtil.monthSub("0901", "1001");
        System.out.println(integer);
    }

    private static void dateMTest() {
        String oldCalendar = DateUtil.getOldCalendar("20211003");
        System.out.println("oldCalendar = " + oldCalendar);
    }

    private static void smallDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.SPOT);
        String dateS = "2021.01.02231";
        try {
            System.out.println(dateFormat.parse(dateS));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void localDateTimeTest() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = LocalDateTime.now().minusHours(2).format(dateTimeFormatter);
        System.out.println("dateStr = " + dateStr);
    }

    private static void dateTimeToLocalDateTest() {
        long time = 1644989400;
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.ofHours(8));
        DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = inFormat.format(dateTime);
        System.out.println("dateStr = " + format);
        System.out.println(System.currentTimeMillis() / 1000);
        long epochSecond = LocalDateTime.now().minusHours(2).toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println("epochSecond = " + epochSecond);
    }

    private static void unitTest() {
        String unitDate = DateUtil.unitDate((7200 + 1) * 1000d);
        System.out.println("unitDate = " + unitDate);
    }

    private static void localDateTest() {
        long i = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
        System.out.println("i = " + i);
    }

    private static void parseLongTest() {
        System.out.println((Long) null);
    }
}
