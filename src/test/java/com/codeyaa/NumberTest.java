package com.codeyaa;

import com.codeyaa.utils.common.NumberUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class NumberTest {
    public static void main(String[] args) {
        unitTest();
    }

    private static void bigTest() {
        BigDecimal bigDecimal = new BigDecimal("0");
        System.out.println(bigDecimal.equals(BigDecimal.ZERO));
    }

    private static void systemTest() {
        Properties properties = System.getProperties();
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            System.out.printf("%s= %s\n", key, properties.getProperty(key));
        }
    }

    private static void formatTest() {
        double log = NumberUtil.log(60, 4000);
        System.out.println("log = " + log);
    }

    private static void unitTest() {
        System.out.println(NumberUtil.unitHashRate(204871500d));
    }

    private static void recursionListTest() {
        List<Long> longs = Arrays.asList(2L, 2L, 3L, 4L);
        System.out.println(NumberUtil.recursionList(longs, 2));
    }
}
