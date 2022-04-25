package com.codeyaa.utils.common;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2022/2/14.
 *
 * @author haogege
 */
public class NumberUtil {
    public static String unitHashRate(Double data) {
        List<String> unitNames = Arrays.asList("MH/s", "GH/s", "TH/s", "PH/s", "EH/s", "ZH/s");
        // 多少个零
        int num = (data.longValue() + "").length() - 1;
        // 10*index次方
        double pow = Math.pow(10, num);
        // 单位下标
        int index = Double.valueOf(Math.floor(Math.log10(pow) / 3) + "").intValue();
        // 单位
        String unit = index <= 1 ? "H/s" : unitNames.get(index - 2);
        return String.format("%s %s", data / Math.pow(10, (index == 0 ? 1 : index) * 3), unit);
    }

    /**
     * logx y
     *
     * @param x
     * @param y
     * @return
     */
    public static double log(double x, double y) {
        return Math.log(y) / Math.log(x);
    }
    public static Long recursionList(List<Long> list, int index) {
        if (index == 0) {
            return list.get(index);
        }
        return recursionList(list, index - 1) * list.get(index);
    }
}