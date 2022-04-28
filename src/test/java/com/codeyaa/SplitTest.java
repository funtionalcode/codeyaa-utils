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
        Map<Integer, List<Map<Integer, Integer>>> split = RandomUtil.splitMap(map, 6);
        split.forEach((k, v) -> System.out.println(k + ":" + v + "-->" + v.size()));
    }

    private static void listSplit() {
        Stack<Integer> list = new Stack<>();
        for (int i = 0; i < 19; i++) {
            list.add(i);
        }
        Map<Integer, List<Integer>> split = RandomUtil.splitList(list, 10);
        System.out.println("splitList = " + split);
    }

    private static void cutList() {
        Stack<Integer> list = new Stack<>();
        for (int i = 0; i < 39; i++) {
            list.add(i);
        }
        Map<Integer, List<Integer>> split = RandomUtil.cutList(list, 5);
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
        Map<Long, List<Long>> res = RandomUtil.cutNumberByCode(29200009L, 100000L);
        res.forEach((k, v) -> System.out.println(String.format("%s:%s", k, v)));
        System.out.printf("总次数:%s", res.size());
    }
}
