package com.codeyaa;

import com.codeyaa.utils.common.reflection.UnSafeUtil;

import java.util.HashMap;

public class UnsafeTest {
    static class A {
        String a;
    }

    static class B {
        String a;
    }

    public static void main(String[] args) {
//        copyTest();
        mapCopyTest();
    }

    private static void copyTest() {
        A a = new A();
        a.a = "2";
        B b = UnSafeUtil.clone(a, B.class);
        System.out.println(b.a);
    }

    private static void mapCopyTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "2");
        B b = UnSafeUtil.mapClone(map, B.class);
        System.out.println("b = " + b);
    }
}
