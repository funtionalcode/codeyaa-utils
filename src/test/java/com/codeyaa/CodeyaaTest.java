package com.codeyaa;

import com.codeyaa.constant.CodeYaa;
import net.dongliu.commons.collection.Lists;

import java.util.stream.Collectors;

/**
 * @author by heyun
 * @date 2021/12/18
 */
public class CodeyaaTest {
    public static void main(String[] args) {
        ifGet1Test();
    }
    private static void ifGetTest(){
        String s = CodeYaa.iIfGet(true, String.class).get(() -> {
            if (false) {
                return "3";
            }
            return Lists.of(2).stream().collect(Collectors.toList()).toString();
        }, () -> "2");
        System.out.println("s = " + s);
    }
    private static void ifGet1Test(){
        String s = CodeYaa
                .iIfGet(true, String.class)
                .get(() -> "true1", () -> "false");
        System.out.println("s = " + s);
    }
}
