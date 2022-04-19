package com.codeyaa;

import com.codeyaa.utils.common.RandomUtil;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RandomTest {
    public static void main(String[] args) {
        regexTest();
    }

    private static void random() {
        int i = RandomUtil.randomInt(0, 10);
        System.out.println("i = " + i);
    }
    private static void regexTest() {
        System.out.println(Pattern.matches(".*0x5F5eA98b58220294e689487483b147A25Bfa0e22.*","0x5F5eA98b58220294e689487483b147A25Bfa0e22"));
    }
    private static void equalsTest(){
        HashMap<Object, Object> obj1= new HashMap<>();
        HashMap<Object, Object> obj2= new HashMap<>();
        obj1.put(1,Integer.valueOf(1));
        obj2.put(1,1);
        System.out.println(obj1.equals(obj2));
    }
}
