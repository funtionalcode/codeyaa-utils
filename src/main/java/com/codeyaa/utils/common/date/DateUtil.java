package com.codeyaa.utils.common.date;


import com.codeyaa.utils.common.NumberUtil;
import com.codeyaa.utils.common.StringUtils;

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
import java.util.stream.IntStream;

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
    public static final List<String> unitNames = Arrays.asList("毫秒", "秒", "分钟", "小时", "天", "月", "年");
    public static final List<Long> unitConvert = Arrays.asList(1000L, 60L, 60L, 24L, 30L, 12L);

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

    public static String unitDate(Long date) {
        return unitDate(date, true);
    }

    public static String unitDate(Long date, boolean enableYear) {
        ArrayList<String> resList = new ArrayList<>();

        List<Long> resConvert = new ArrayList<>(unitConvert.subList(unitConvert.size() - 2, unitConvert.size()));
        resConvert.add(1L);

        List<String> unitDates = unitDate(date, resList, unitNames, unitConvert, true);
        if (!enableYear) {
            return String.join("", unitDates);
        }
        boolean year = false;
        Long currentDateNum = 0L;
        for (String unitDate : unitDates) {
            if (year) {
                break;
            }
            currentDateNum = StringUtils.getRegexString("\\d+", unitDate).stream().map(Long::parseLong).collect(Collectors.toList()).get(0);
            year = unitDate.contains(unitNames.get(6)) && currentDateNum > 0;
        }

        if (year) {
            DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy");
            Long currentYear = Long.parseLong(LocalDate.now().format(inFormat));
            boolean run = isRun(currentYear);
            // 按每个月30天 按 2月31天 1年缺少6天
            // 闰年则需要加 6 - (31 - 29) = 4 天
            long addDay = run ? currentDateNum * 4 : currentDateNum * 3;

            addYearDay(addDay, resList, resConvert);
        } else {
            resList.clear();
            unitDates = unitDate(date, resList, unitNames, unitConvert, false);
        }


        return String.join("", unitDates);
    }

    private static void addYearDay(long addDay, List<String> resList, List<Long> unitConvert) {

        // 先进后出
        List<Long> addStack = resList.stream()
                .map(row -> StringUtils.getRegexString("\\d+", row).get(0))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        addStack = addStack.subList(0, addStack.size() - 4);

        List<Long> uppList = addYearDay(addDay, 2, false, addStack, unitConvert);
        for (int i = 0; i < uppList.size(); i++) {
            String currentUnit = resList.get(i);
            Long currentUpp = uppList.get(i);
            resList.set(i, currentUnit.replaceAll("\\d+", currentUpp + ""));
        }

    }

    private static List<Long> addYearDay(long addDay, int index, boolean upper, List<Long> addStack, List<Long> unitConvert) {
        if (index == -1) {
            return addStack;
        }
        Long pop = addStack.get(index);
        Long currentConvert = unitConvert.get(Math.abs(index - 2));
        // 进1
        // 增加缺少的天数
        long currentUnit = upper ? pop + addDay / currentConvert : index == 2 ? pop + addDay : pop;
        if (currentUnit < currentConvert) {
            addStack.set(index, currentUnit);
            return addYearDay(addDay, index - 1, false, addStack, unitConvert);
        }
        if (index == 0) {
            addStack.set(index, currentUnit);
        } else {
            // 超过当前单位赋值求余
            addStack.set(index, currentUnit % currentConvert);
        }
        return addYearDay(addDay / currentConvert, index - 1, true, addStack, unitConvert);
    }

    /**
     * 获取当前时间戳的最大单位
     *
     * @param date    时间戳/秒
     * @param resList
     * @return
     */
    private static List<String> unitDate(Long date, List<String> resList, List<String> unitNames, List<Long> unitConvert, boolean enYear) {
        return unitDate(date, unitConvert.size() - 1, resList, unitNames, unitConvert, enYear);
    }

    private static List<String> unitDate(Long date, int index, List<String> resList, List<String> unitNames, List<Long> unitConvert, boolean enYear) {
        // 当前单位多少秒
        Long currentSecond = NumberUtil.recursionList(unitConvert, index);
        // 当前单位求余
        double remainder = date % currentSecond;
        if (remainder != date) {
            long currentUnit = Double.valueOf((date - remainder) / currentSecond).longValue();
            // 获取当前单位
            resList.add(String.format("%s%s", currentUnit, unitNames.get(index + 1)));
            // 匹配到存在该单位 下一步
            date = date - currentUnit * currentSecond;
        } else if (enYear) {
            resList.add(String.format("%s%s", 0, unitNames.get(index + 1)));
        }
        if (index == 0) {
            // 最后一个单位直接赋值
            if (date > 0 || enYear) {
                resList.add(String.format("%s%s", date, unitNames.get(0)));
            }
            return resList;
        }
        return unitDate(date, index - 1, resList, unitNames, unitConvert, enYear);
    }

    public static long unitDateStr(String dateStr) {
        List<Long> nums = StringUtils.getRegexString("\\d+", dateStr).stream().map(Long::parseLong).collect(Collectors.toList());
        List<String> units = StringUtils.getRegexString("[^\\d]+", dateStr);

        List<Long> unitNums = units.stream()
                .map(unitNames::indexOf)
                .map(row -> row - 1)
                .map(row -> row == -1 ? 1 : NumberUtil.recursionList(unitConvert, row))
                .collect(Collectors.toList());

        return IntStream.range(0, nums.size()).mapToLong(i -> nums.get(i) * unitNums.get(i)).reduce(0, Long::sum);
    }

    public static boolean isRun(Long year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 || (year % 3200 == 0 && year % 172800 == 0);
    }

    /**
     * 1、普通年能被4整除且不能被100整除的为闰年。（如2004年就是闰年,1900年不是闰年）
     * 2、世纪年能被400整除的是闰年。(如2000年是闰年，1900年不是闰年)
     * 3、对于数值很大的年份,这年如果能整除3200，并且能整除172800则是闰年。
     */
    public static List<List<Integer>> isRun(int start, int end) {
        // 2.29
        List<Integer> runs = new ArrayList<>();
        // 2.28
        List<Integer> pins = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isRun(Long.parseLong(i + ""))) {
                runs.add(i);
            } else {
                pins.add(i);
            }
        }
        List<List<Integer>> rs = new ArrayList<>();
        rs.add(runs);
        rs.add(pins);
        return rs;
    }
}
