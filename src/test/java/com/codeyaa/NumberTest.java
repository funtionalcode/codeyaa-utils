package com.codeyaa;

import com.codeyaa.utils.common.NumberUtil;

import java.math.BigDecimal;
import java.util.*;

public class NumberTest {
    public static void main(String[] args) {
        bigTest();
    }

    private static void bigTest() {
        System.out.println(Objects.equals(Byte.valueOf("1"),(byte)1));
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
        System.out.println(NumberUtil.unitHashRate(6031083396d));
    }
    private static void unitFileTest() {
        System.out.println(NumberUtil.unitFileRate(514333d*1024));
    }

    private static void recursionListTest() {
        List<Long> longs = Arrays.asList(2L, 2L, 3L, 4L);
        System.out.println(NumberUtil.recursionList(longs, 2));
    }
}
