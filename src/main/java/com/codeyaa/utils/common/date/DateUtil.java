package com.codeyaa.utils.common.date;


import com.codeyaa.utils.common.NumberUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Funtionalcode
 * @className DateUtil
 * @description 日期工具类
 * @date 2021/5/27 14:01
 */
public class DateUtil {
    /**
     * 24小时中国时区
     */
    public static final String CN_TWENTY_FOUR_FULL = "yyyy-MM-dd HH:mm:ss";
    /**
     * 12小时中国时区
     */
    public static final String CN_TWELVE_FULL = "yyyy-MM-dd hh:mm:ss";
    public static final String CN_YEAR = "yyyy";
    public static final String CN_DAY = "yyyy-MM-dd";
    public static final String CN_MONTH_DAY = "MM月dd日";
    public static final String CN_S = "HH:mm:ss";
    public static final String SPOT = "yyyy.MM.dd";
    public static final String RFC_1123 = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * @param localDate LocalDate
     * @Description: LocalDate 转换 Date
     * @return: Date
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/27 13:53
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * @param lowerBound 时间区间开始
     * @param upperBound 时间区间结束
     * @param createTime 待判断时间
     * @param dateFormat 时间格式化格式
     * @Description: 判断时间是否在指定时间区间内 jdk-1.8
     * @return: 是否在区间内
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/1 17:02
     */
    public static Boolean dateBetween(String lowerBound, String upperBound, String createTime, String dateFormat) {
        DateTimeFormatter dfFrontEnd = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime lower = LocalDateTime.parse(lowerBound, dfFrontEnd);
        LocalDateTime upper = LocalDateTime.parse(upperBound, dfFrontEnd);
        LocalDateTime creatT = LocalDateTime.parse(createTime, dfFrontEnd);
        return creatT.isAfter(lower) && creatT.isBefore(upper);
    }

    /**
     * @param startTime  开始时间 字符串
     * @param endTime    结束时间 字符串
     * @param nowTime    判断时间 字符串
     * @param dateFormat 转换格式 字符串
     * @Description: 时间转换指定格式后，判断时间是否在指定时间区间内 jdk-1.7
     * @return: 是否在区间内
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/11 11:52
     */
    public static Boolean dateBetweenOld(String startTime, String endTime, String nowTime, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date startNewTime = simpleDateFormat.parse(startTime);
            Date endNewTime = simpleDateFormat.parse(endTime);
            Date nowNewTime = simpleDateFormat.parse(nowTime);
            if (nowNewTime.getTime() == startNewTime.getTime()
                    || nowNewTime.getTime() == endNewTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(nowNewTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startNewTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endNewTime);

            return date.after(begin) && date.before(end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param source 减数
     * @param target 被减数
     * @Description: 两个时间相减
     * @return: 返回两个时间相差毫秒值
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/11 14:51
     */
    public static Long dateSubtract(Date source, Date target) {
        Calendar date1 = Calendar.getInstance();
        date1.setTime(source);
        Calendar date2 = Calendar.getInstance();
        date2.setTime(target);
        return date1.getTime().getTime() - date2.getTime().getTime();
    }

    /**
     * 获取现在时间
     *
     * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getRfc1123Time() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                RFC_1123, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * show 日期字符串转换 Date.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @Param dateStr  日期字符串 例：2021-01
     * @Param format
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/24 15:15
     */
    public static Date stringToDate(String dateStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long parseDay(long millis) {
        return millis / 1000 / 60 / 60 / 24;
    }

    public static long getStrTime(String dataStr, String regex) {
        String[] split = dataStr.split(regex);
        StringBuilder dataF = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            dataF.append(s.length() > 1 ? s + "." : "0" + s + ".");
            if (i == split.length - 1) {
                dataF = new StringBuilder(dataF.substring(0, dataF.length() - 1));
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(SPOT);
        Date parse = null;
        try {
            parse = dateFormat.parse(dataF.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert parse != null;
        return parse.getTime();
    }

    public static String getDateFormat(String dataStr, String regex, String section) {
        String[] split = dataStr.split(regex);
        StringBuilder dataF = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (i == 0) {
                dataF.append(s.length() > 1 ? s.substring(0, 4) + "." : "0" + s + ".");
            } else {
                dataF.append(s.length() > 1 ? s.substring(0, 2) + "." : "0" + s + ".");
            }
            if (i == split.length - 1) {
                dataF = new StringBuilder(dataF.substring(0, dataF.length() - 1));
            }
        }
        switch (section) {
            case "y":
                return dataF.substring(0, 4);
            case "m":
                return dataF.substring(5, 7);
            case "d":
                return dataF.substring(8, 10);
            case "ymd":
                return dataF.toString();
            default:
                return null;
        }
    }

    public static String getOldCalendar(String dateStr) {
        Calendar today = Calendar.getInstance();
        try {
            today.setTime(Lunar.smallFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 加载自定义日期
        Lunar lunar = new Lunar(today);
        return lunar.getOldList().stream().map(item -> {
            String s = String.valueOf(item);
            if (s.length() == 1) {
                s = "0" + s;
            }
            return s;
        }).collect(Collectors.joining(""));
    }

    public static Integer monthSub(String current, String minuend) {
        DateFormat dft = new SimpleDateFormat("MMdd");
        try {
            //开始时间
            Date start = dft.parse(current);
            //结束时间
            Date endDay = dft.parse(minuend);
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            long time1 = cal.getTimeInMillis();
            cal.setTime(endDay);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 1、普通年能被4整除且不能被100整除的为闰年。（如2004年就是闰年,1900年不是闰年）
     * 2、世纪年能被400整除的是闰年。(如2000年是闰年，1900年不是闰年)
     * 3、对于数值很大的年份,这年如果能整除3200，并且能整除172800则是闰年。
     */
    public static ArrayList<ArrayList<Integer>> isRun(int start, int end) {
        // 2.29
        ArrayList<Integer> runs = new ArrayList<Integer>();
        // 2.28
        ArrayList<Integer> pins = new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {
            if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0 || (i % 3200 == 0 && i % 172800 == 0)) {
                runs.add(i);
            } else {
                pins.add(i);
            }
        }
        ArrayList<ArrayList<Integer>> rs = new ArrayList<>();
        rs.add(runs);
        rs.add(pins);
        return rs;
    }

    public static String parseMilis(Long milis) {
        if (milis <= 1000) {
            return milis + "毫秒";
        } else if (milis <= 1000 * 60) {
            return milis / 1000 + "秒";
        } else if (milis <= 1000 * 60 * 60) {
            return milis / 1000 / 60 + "分钟";
        } else if (milis <= 1000 * 60 * 60 * 60) {
            return milis / 1000 / 60 / 60 + "小时";
        }
        return "";
    }

    public static String unitDate(Double date) {
        // 多少个零
        int num = (date.longValue() + "").length() - 1;
        if (num <= 2) {
            return String.format("%s %s", date, "毫秒");
        }
        date = date / 1000;
        ArrayList<String> resList = new ArrayList<>();
        Double hour = unitHour(date, 1, resList);

        unitDate(date.longValue(), 3, resList);
        return String.join("", resList);
    }

    private static Double unitHour(Double date, int index, List<String> resList) {
        List<String> unitNames = Arrays.asList("秒", "分钟", "小时");
        List<Long> unitConvert = Arrays.asList(60L, 60L);
        // 当前单位多少秒
        Long currentSecond = NumberUtil.recursionList(unitConvert, index);
        // 当前单位进制位
        Long currentConvert = unitConvert.get(index);
        double remainder = date % currentSecond;
        if (remainder != date) {
            double currentUnit = (date - remainder) / currentConvert;
            resList.add(String.format("%s%s", currentUnit, unitNames.get(index)));
        }
        if (index == 0) {
            return 0d;
        }
        date = date / currentConvert + date % currentConvert;
        return unitHour(date, index - 1, resList);
    }

    private static void unitDate(Long date, int index, List<String> resList) {
        List<String> unitNames = Arrays.asList("秒", "分钟", "小时", "天", "周");
        List<Long> unitConvert = Arrays.asList(60L, 60L, 24L, 7L);
        // 当前单位多少秒
        Long currentSecond = NumberUtil.recursionList(unitConvert, index);
        // 当前单位进制位
        Long currentConvert = unitConvert.get(index);
        if (date % currentSecond != date) {
            resList.add(String.format("%s%s", date / currentConvert, unitNames.get(index + 1)));
            return;
        }
        date = date / currentConvert + date % currentConvert;
        unitDate(date, index - 1, resList);
    }
}
