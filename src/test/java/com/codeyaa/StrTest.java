package com.codeyaa;

import com.codeyaa.utils.common.IpUtil;

public class StrTest {
    public static void main(String[] args) {
        replayA();
    }

    private static void replayA() {
        System.out.println((int) 'a' +" "+ (int) 'A');
    }

    private static void domainTest() {
        System.out.println(IpUtil.getDomain("https://213123/aaa"));
    }

    private static void strTest() {
        System.out.println(Long.valueOf("1").compareTo(null));
    }
}
