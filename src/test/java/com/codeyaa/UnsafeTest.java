package com.codeyaa;

import com.codeyaa.utils.common.date.Lunar;
import com.codeyaa.utils.common.reflection.BeanUtil;
import com.codeyaa.utils.common.reflection.UnSafeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UnsafeTest {
    @EqualsAndHashCode(callSuper = true)
    @Data
    static class A extends C {
        BigDecimal a;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @ToString(callSuper = true)
    public static class B extends C {
        BigDecimal a;
    }

    @Data
    static class C {
        BigDecimal c;
    }

    public static void main(String[] args) {
        toMapTest();
//        mapCopyTest();
    }
    private static void toMapTest() {
        A a = new A();
        a.a = BigDecimal.valueOf(2);
        a.c = BigDecimal.valueOf(3);
        B clone = BeanUtil.clone(a, B.class);
        System.out.println(clone);
        Map<Object, Object> toMap = UnSafeUtil.toMap(clone);
        System.out.println(toMap);
    }
}
