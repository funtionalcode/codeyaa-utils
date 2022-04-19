package com.codeyaa.utils.common.reflection;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class UnSafeUtil {
    private static Unsafe unSafe;

    static {
        Field f;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unSafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T allocateInstance(Class<T> clazz) {
        T t = null;
        try {
            t = (T) unSafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T clone(Object object, Class<T> clazz) {
        T res = null;
        try {
            // unsafe实例化对象
            res = allocateInstance(clazz);
            if (Objects.isNull(object)) {
                return res;
            }
            // 获取目标类中的所有字段
            Field[] fields = BeanUtil.getAllFields(res.getClass());
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                // 字段在当前类
                if (fieldInTarget(fieldName, object.getClass())) {
                    // 当前类的属性
                    Field declaredField = object.getClass().getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    // 当前类的属性值
                    Object fieldValue = unSafe.getObject(object, unSafe.objectFieldOffset(declaredField));
                    // 赋值到目标类
                    unSafe.putObject(res, unSafe.objectFieldOffset(field), fieldValue);
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean fieldInTarget(String field, Class clazz) {
        Field[] declaredFields = BeanUtil.getAllFields(clazz);
        boolean flag = false;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();
            if (name.equals(field)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static <T> T mapClone(Map map, Class<T> clazz) {
        T res = null;
        try {
            // unsafe实例化对象
            res = (T) unSafe.allocateInstance(clazz);
            // 获取类中的所有字段
            Field[] fields = BeanUtil.getAllFields(res.getClass());
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                if (fieldInTarget(name, clazz)) {
                    // 当前map的属性值
                    Object fieldValue = map.get(name);
                    // 赋值到目标类
                    unSafe.putObject(res, unSafe.objectFieldOffset(field), fieldValue);
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 判断key是否包含 `Date` ,是否为时间戳，格式化返回
     * 1646150401 2022-03-02 秒转换
     * 1646384748279 2022-03-02 00:00:01 毫秒转换
     *
     * @param keyObj
     * @param valueObj
     * @return
     */
    public static Object convertParamDate(Object keyObj, Object valueObj) {
        if (Objects.isNull(keyObj) || !keyObj.toString().contains("Date")) {
            return valueObj;
        }
        String value = valueObj.toString();
        if (Pattern.matches("[0-9]{10}", value)) {
            Instant instant = Instant.ofEpochSecond(Long.parseLong(value));
            LocalDate localDate = ZonedDateTime.ofInstant(instant, ZoneOffset.ofHours(8)).toLocalDate();
            DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return localDate.format(inFormat);
        }
        if (Pattern.matches("[0-9]{13}", value)) {
            Instant instant = Instant.ofEpochMilli(Long.parseLong(value));
            LocalDateTime localDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.ofHours(8)).toLocalDateTime();
            DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return localDateTime.format(inFormat);
        }
        return valueObj;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Class<?> objClass = obj.getClass();
        try {
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return unSafe.getObject(obj, unSafe.objectFieldOffset(field));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setField(Object obj, Field field, Object fieldValue) {
        Class<?> objClass = obj.getClass();
        field.setAccessible(true);
        unSafe.putObject(obj, unSafe.objectFieldOffset(field), fieldValue);
    }

    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
        Class<?> objClass = obj.getClass();
        try {
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            unSafe.putObject(obj, unSafe.objectFieldOffset(field), fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
