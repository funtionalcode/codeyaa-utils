package com.codeyaa.utils.common;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class RandomUtil {
    public static int randomInt(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    /**
     * Map 切割成 num 份
     *
     * @param map
     * @param num num 份
     * @return
     */
    public static <K, V> Map<Integer, List<Map<K, V>>> splitMap(Map<K, V> map, int num) {
        return splitMap(map, num, map.size() / num, new HashMap<>());
    }

    private static <K, V> Map<Integer, List<Map<K, V>>> splitMap(Map<K, V> map, int num, int size, Map<Integer, List<Map<K, V>>> res) {
        if (num == 1 || map.size() < size) {
            getMapNum(map, num, map.size(), res);
            return res;
        }
        getMapNum(map, num, size, res);
        return splitMap(map, num - 1, size, res);
    }

    private static <K, V> void getMapNum(Map<K, V> map, int num, int size, Map<Integer, List<Map<K, V>>> res) {
        List<Map<K, V>> maps = new ArrayList<>();
        List<K> ls = new ArrayList<>(map.keySet());
        for (int i = 0; i < size; i++) {
            HashMap<K, V> m = new HashMap<>();

            K key = ls.get(i);

            m.put(key, map.get(key));
            map.remove(key);

            maps.add(m);
        }
        res.put(num, maps);
    }

    /**
     * List 切割成 num 份
     *
     * @param list 目标 List
     * @param num  份数
     * @return 1 -> [1,2] 2 -> [3,4]
     */
    public static <T> Map<Integer, List<T>> splitList(List<T> list, int num) {
        Stack<T> ts = new Stack<>();
        ts.addAll(list);
        return splitList(ts, num, list.size() / num, new HashMap<>(num));
    }

    private static <T> Map<Integer, List<T>> splitList(Stack<T> list, int num, int size, HashMap<Integer, List<T>> res) {
        if (num == 1 || list.size() < size) {
            res.put(num, new ArrayList<>(list));
            return res;
        }
        List<T> currentObjs = IntStream.range(0, size)
                .mapToObj(row -> list.pop())
                .collect(Collectors.toList());
        res.put(num, currentObjs);
        return splitList(list, num - 1, size, res);
    }

    /**
     * List 切割成每份 num 个元素
     *
     * @param list 目标 List
     * @param num  元素个数
     * @return 1 -> [1,2] 2 -> [3,4]
     */
    public static <T> Map<Integer, List<T>> cutList(List<T> list, int num) {
        Stack<T> ts = new Stack<>();
        ts.addAll(list);
        return cutList(ts, 0, num, new HashMap<>());
    }

    private static <T> Map<Integer, List<T>> cutList(Stack<T> list, int index, int num, Map<Integer, List<T>> res) {
        if (list.isEmpty() || list.size() < num) {
            res.put(index, new ArrayList<>(list));
            return res;
        }
        List<T> currentObjs = IntStream.range(0, num)
                .mapToObj(row -> list.pop())
                .collect(Collectors.toList());
        res.put(index, currentObjs);
        return cutList(list, index + 1, num, res);
    }

    /**
     * number 切割为每份 size 长度
     *
     * @param number
     * @param size
     * @return
     */
    public static Map<Long, List<Long>> cutNumberByCode(long number, long size) {
        return cutNumberByCode(number, size, new HashMap<>());
    }

    private static Map<Long, List<Long>> cutNumberByCode(long number, long size, Map<Long, List<Long>> res) {
        long currentNum = number - size;
        if (currentNum <= 0) {
            res.put(number, Arrays.asList(0L, number));
            return res;
        }
        res.put(number, Arrays.asList(currentNum, number));
        return cutNumberByCode(currentNum, size, res);
    }

    public static Map<Long, List<Long>> cutNumberByStream(long number, long size) {
        return cutNumberByStream(number, size, new HashMap<>());
    }

    private static Map<Long, List<Long>> cutNumberByStream(long number, long size, Map<Long, List<Long>> res) {
        AtomicBoolean flag = new AtomicBoolean(true);
        LongStream.range(number, number + 1).filter(n -> n >= size).peek(n -> flag.set(false)).forEach(n -> res.put(n, Arrays.asList(n - size, n)));
        if (flag.get()) {
            res.put(number, Arrays.asList(0L, number));
            return res;
        }
        return cutNumberByStream(number - size, size, res);
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
