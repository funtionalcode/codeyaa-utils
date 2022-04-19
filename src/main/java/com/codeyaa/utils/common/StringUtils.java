package com.codeyaa.utils.common;

import com.qiniu.common.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

}
