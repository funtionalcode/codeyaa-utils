package com.codeyaa.utils.common;

import com.qiniu.common.Constants;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Funtionalcode
 * @className StringUtils
 * @description 字符串校验工具类
 * @date 2021/5/27 12:11
 */
public class StringUtils {
    /**
     * @description 中文正则
     */
    public static String cnRegex = "[\\u4E00-\\u9FA5]";
    public static final String NUM_REGEX = "[0-9]+";

    public static boolean isBlank(String str) {
        return null == str || str.isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !(null == str || str.isEmpty());
    }

    /**
     * @param regex   正则表达式
     * @param content 匹配的文本
     * @Description: 匹配正则内容
     * @return: 返回匹配上的文本列表
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/31 16:30
     */
    public static List<String> getRegexString(String regex, String content) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        ArrayList<String> regexs = new ArrayList<>();
        //此处find（）每次被调用后，会偏移到下一个匹配
        while (m.find()) {
            regexs.add(m.group());
        }
        return regexs;
    }

    public static byte[] utf8Bytes(String data) {
        return data.getBytes(Constants.UTF_8);
    }

    public static boolean inStringArray(String s, String[] array) {
        String[] var2 = array;
        int var3 = array.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String x = var2[var4];
            if (x.equals(s)) {
                return true;
            }
        }

        return false;
    }

    public static double getNumber(Object obj) {
        //double item = -1D;  getNumber(A)+getNumber(B)相加时可能出现 1+（-1）=0
        double item = 0D;
        if (obj instanceof Number) {
            item = Double.parseDouble(String.valueOf(obj));
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.matches("^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?")) {
                item = Double.parseDouble(str);
            }
        }
        return item;
    }

    public static Long chineseNumberToInt(String chineseNumber) {
        Deque<Character> deque = new ArrayDeque<>();
        char[] chars = chineseNumber.toCharArray();
        // 构建先进先出队列
        for (int i = chars.length - 1; i >= 0; i--) {
            deque.push(chars[i]);
        }
        return chineseNumberToInt(deque, new ArrayDeque<>(), 0L);
    }

    private static Long chineseNumberToInt(Deque<Character> chineseNumbers, Deque<Integer> numberQueue, Long number) {
        String val = "零一二三四五六七八九";
        String valChineseUnit = "十百千万亿";
        int[] valNumberUnit = {10, 100, 1000, 10000, 100000000};
        Character chineseNumber = chineseNumbers.poll();
        if (Objects.isNull(chineseNumber) || Objects.equals("零", chineseNumber)) {
            // 出队列求和
            return number + numberQueue.stream().filter(Objects::nonNull).mapToLong(Long::valueOf).sum();
        }
        // 个位数入队列
        if (valChineseUnit.indexOf(chineseNumber) == -1) {
            numberQueue.push(val.indexOf(chineseNumber));
        }
        int valNumberIndex = valChineseUnit.indexOf(chineseNumber);
        // 十 单位以上 *= 入队列
        if (valNumberIndex != -1) {
            int currentNumberUnit = valNumberUnit[valNumberIndex];
            // 队列为空直接入队列
            int currentNumberQueue = Optional.ofNullable(numberQueue.peek()).orElse(-1);
            if (currentNumberQueue < currentNumberUnit) {
                // 队列为空默认值 1 用于 *=
                int currentVal = Optional.ofNullable(numberQueue.poll()).orElse(1);
                // 队列不为空 *= 再入队列
                numberQueue.push(currentVal * currentNumberUnit);
            }
        }
        return chineseNumberToInt(chineseNumbers, numberQueue, number);
    }

    public static List<String> sortChapterNovel(List<String> chineseNumbers) {
        return chineseNumbers.stream().sorted((a, b) -> {
            // 中文数字排序
            if (getRegexString(NUM_REGEX, a).isEmpty()) {
                String aChina = getNovelNumberString(a);
                String bChina = getNovelNumberString(b);
                return chineseNumberToInt(aChina).compareTo(chineseNumberToInt(bChina));
            }
            // 阿拉伯数字排序
            Integer aInt = Integer.valueOf(getRegexString(NUM_REGEX, a).get(0));
            Integer bInt = Integer.valueOf(getRegexString(NUM_REGEX, b).get(0));
            return aInt.compareTo(bInt);
        }).collect(Collectors.toList());
    }

    private static String getNovelNumberString(String str) {
        return str.substring(1, str.indexOf("章"));
    }
}
