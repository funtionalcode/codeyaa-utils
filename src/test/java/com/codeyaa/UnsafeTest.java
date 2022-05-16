package com.codeyaa;

import com.codeyaa.utils.common.date.Lunar;
import com.codeyaa.utils.common.reflection.BeanUtil;
import com.codeyaa.utils.common.reflection.UnSafeUtil;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UnsafeTest {
    @EqualsAndHashCode(callSuper = true)
    @Data
    static class A extends C {
        BigDecimal a;
        Dogo dogo;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @ToString(callSuper = true)
    public static class B extends C {
        BigDecimal a;
        Dogo dogo;
    }

    @Data
    static class C {
        BigDecimal c;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Dogo {
        BigDecimal dog;
    }

    public static void main(String[] args) {
        toMapTest();
//        mapCopyTest();
    }
    private static void toMapTest() {
        A a = new A();
        a.a = BigDecimal.valueOf(2);
        a.c = BigDecimal.valueOf(3);
        a.dogo = Dogo.builder().dog(BigDecimal.TEN).build();
        B clone = BeanUtil.clone(a, B.class);
        System.out.println(clone);
        Map<Object, Object> toMap = UnSafeUtil.toMap(clone);
        System.out.println(toMap);
    }
}
