package com.codeyaa;

import com.codeyaa.utils.common.reflection.UnSafeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;

public class UnsafeTest {
    @EqualsAndHashCode(callSuper = true)
    @Data
    static class A extends C {
        String a;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @ToString(callSuper = true)
    static class B extends C {
        String a;
    }

    @Data
    static class C {
        String c;
    }

    public static void main(String[] args) {
        copyTest();
//        mapCopyTest();
    }

    private static void copyTest() {
        A a = new A();
        a.a = "2";
        a.c = "3";
        B clone = UnSafeUtil.clone(a, B.class);
        System.out.println(clone);
    }

    private static void mapCopyTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "2");
        B b = UnSafeUtil.mapClone(map, B.class);
        System.out.println("b = " + b);
    }
}
