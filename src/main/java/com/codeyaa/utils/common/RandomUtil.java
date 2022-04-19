package com.codeyaa.utils.common;

import com.google.gson.Gson;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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
    public static Map<Integer, List<Map<Object, Object>>> split(Map map, int num) {
        Gson gson = new Gson();
        Map<Integer, List<Map<Object, Object>>> res = new HashMap<>();
        // 每份的容量
        int len = map.size() / num;
        // 标识第几份
        int flag = 1;
        ArrayList<Map<Object, Object>> maps = new ArrayList<>();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            HashMap<Object, Object> tmp = new HashMap<>();
            tmp.put(key, value);
            maps.add(tmp);

            // 最后一份不限容量
            if (flag == num) {
                res.put(flag, gson.fromJson(gson.toJson(maps), List.class));
            }
            // 达到每份容量
            else if (maps.size() - 1 == len) {
                res.put(flag++, gson.fromJson(gson.toJson(maps), List.class));
                maps.clear();
            }
        }
        return res;
    }

    /**
     * List 切割成 num 份
     *
     * @param list 目标 List
     * @param num  份数
     * @return 1 -> [1,2] 2 -> [3,4]
     */
    public static Map<Integer, List<Object>> splitList(List list, int num) {
        HashMap<Integer, List<Object>> map = new HashMap<>();
        for (int i = 1; i <= num; i++) {
            int size = list.size();
            if (i == 1) {
                List<Object> first = list.subList(0, size / num / i);
                map.put(i, first);
            }
            if (i > 1 && i < num) {
                List<Object> mid = list.subList(size / num * (i - 1), size / num * i);
                map.put(i, mid);
            }
            if (i == num) {
                List<Object> end = list.subList(size / num * (i - 1), size);
                map.put(i, end);
            }
        }
        return map;
    }

    /**
     * List 切割成每份 num 个元素
     *
     * @param list 目标 List
     * @param num  元素个数
     * @return 1 -> [1,2] 2 -> [3,4]
     */
    public static Map<Integer, List<Object>> cutList(List list, int num) {
        HashMap<Integer, List<Object>> map = new HashMap<>();
        int size = list.size();
        for (int i = 1; i <= size; i++) {
            int toIndex = i * num;
            if (toIndex >= size) {
                map.put(i, list.subList(5 * (i - 1), size));
                break;
            } else {
                map.put(i, list.subList(5 * (i - 1), toIndex));
            }
        }
        return map;
    }

    /**
     * number 切割为每份 size 长度
     *
     * @param number
     * @param size
     * @return
     */
    public static void cutNumberByCode(long number, long size, Map<Long, List<Long>> res) {
        if (number - size <= 0) {
            res.put(number, Arrays.asList(0L, number));
            return;
        }
        res.put(number, Arrays.asList(number - size, number));
        cutNumberByCode(number - size, size, res);
    }

    public static void cutNumberByStream(long number, long size, Map<Long, List<Long>> res) {
        AtomicBoolean flag = new AtomicBoolean(true);
        LongStream.range(number, number + 1).filter(n -> n >= size).peek(n -> flag.set(false)).forEach(n -> res.put(n, Arrays.asList(n - size, n)));
        if (flag.get()) {
            res.put(number, Arrays.asList(0L, number));
            return;
        }
        cutNumberByStream(number - size, size, res);
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
