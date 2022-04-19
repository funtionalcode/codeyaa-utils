package com.codeyaa;

import com.codeyaa.utils.common.IpUtil;
import com.codeyaa.utils.common.RegexEnum;

public class StrTest {
    public static void main(String[] args) {
        strTest();
    }

    private static void replayA() {
        String name = "重生之套路之王1.txt";
        System.out.println(name.replaceAll(RegexEnum.chStr + "|txt", ""));
    }

    private static void domainTest() {
        System.out.println(IpUtil.getDomain("https://213123/aaa"));
    }

    private static void strTest(){
        System.out.println(Long.valueOf("1").compareTo(null));
    }
}
