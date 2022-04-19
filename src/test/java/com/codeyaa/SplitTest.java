package com.codeyaa;

import com.codeyaa.utils.common.RandomUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by xin11.xin
 * @date 2022/1/2
 */
public class SplitTest {
    public static void main(String[] args) {
        splitNumber();
    }

    private static void mapSplit() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 19; i++) {
            map.put(i, i);
        }
        Map<Integer, List<Map<Object, Object>>> split = RandomUtil.split(map, 5);
        split.forEach((k, v) -> System.out.println(k + ":" + v + "-->" + v.size()));
    }

    private static void listSplit() {
        List list = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            list.add(i);
        }
        Map<Integer, List<Object>> split = RandomUtil.splitList(list, 5);
        System.out.println("splitList = " + split);
    }

    private static void cutList() {
        List list = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            list.add(i);
        }
        Map<Integer, List<Object>> split = RandomUtil.cutList(list, 5);
        System.out.println("cutList = " + split);
    }

    private static void skipList() {
        List list = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            list.add(i);
        }
        list = Collections.singletonList(list.stream().skip(3).map(Object::toString).collect(Collectors.toList()));
        System.out.println("cutList = " + list);
    }

    private static void formatTest() {
        System.out.println(String.format("%.4s", 1.111111d));
    }

    private static void splitNumber() {
        Map<Long, List<Long>> res = new HashMap<>();
        RandomUtil.cutNumberByStream(29200009L, 100000L, res);
        res.forEach((k, v) -> System.out.println(String.format("%s:%s", k, v)));
        System.out.printf("总次数:%s",res.size());
    }
}
