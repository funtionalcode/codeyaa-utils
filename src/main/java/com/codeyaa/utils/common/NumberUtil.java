package com.codeyaa.utils.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public static String unitFileRate(Double fileSize) {
        List<String> unitNames = Arrays.asList("K", "M", "G", "T", "P", "E", "ZH");
        // 单位下标
        int index = Double.valueOf(log(1024D, fileSize) + "").intValue();
        // 单位
        String unit = unitNames.get(index - 1);
        BigDecimal finalFileSize = BigDecimal.valueOf(fileSize / Math.pow(1024, index)).setScale(2, RoundingMode.DOWN);
        return String.format("%s %s", finalFileSize, unit);
    }


    /**
     * logx y
     *
     * @param x
     * @param y
     * @return
     */
    public static Double log(double x, double y) {
        return Math.log(y) / Math.log(x);
    }

    public static Long recursionList(List<Long> list) {
        return recursionList(list, list.size() - 1);
    }

    /**
     * 阶乘
     *
     * @param list  列表
     * @param index 阶乘下标截止
     * @return
     */
    public static Long recursionList(List<Long> list, int index) {
        if (index <= 0) {
            return list.get(index);
        }
        return recursionList(list, index - 1) * list.get(index);
    }
}
