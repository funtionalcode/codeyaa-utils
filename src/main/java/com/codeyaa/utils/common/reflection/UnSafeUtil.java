package com.codeyaa.utils.common.reflection;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeyaa.utils.common.reflection.BeanUtil.getAllFields;

public class UnSafeUtil {
    private static Unsafe unSafe;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unSafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T allocateInstance(Class<T> clazz) {
        try {
            return (T) unSafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T clone(Object object, Class<T> clazz) {
        return clone(object, allocateInstance(clazz), clazz);
    }

    private static <T> T clone(Object object, T target, Class<T> clazz) {
        try {
            if (Objects.isNull(object) || Objects.isNull(clazz)) {
                return target;
            }
            Class<?> targetClazz = object.getClass();
            // 获取目标类中的所有字段
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                // 字段在当前类
                if (fieldInTarget(field, targetClazz)) {
                    Object fieldValue = getFieldValue(object, fieldName);
                    // 赋值到目标类
                    unSafe.putObject(target, unSafe.objectFieldOffset(field), fieldValue);
                }
            }
            return (T) clone(object, target, clazz.getSuperclass());
        } catch (
                Exception e) {
            e.printStackTrace();
            return target;
        }
    }

    /**
     * 校验字段是否在目标字段中
     * 校验字段名称、字段数据类型
     *
     * @param sourceField 源字段
     * @param targetClazz 目标类型
     * @return
     */
    public static boolean fieldInTarget(Field sourceField, Class<?> targetClazz) {
        String sourceName = sourceField.getName();
        Class<?> sourceType = sourceField.getType();
        List<String> declaredFields = getAllFields(targetClazz)
                .stream()
                .peek(row -> row.setAccessible(true))
                .filter(row -> row.getName().equals(sourceName))
                .filter(row -> row.getType().equals(sourceType))
                .map(Field::getName)
                .collect(Collectors.toList());
        return declaredFields.contains(sourceName);
    }
    public static boolean fieldInTarget(String sourceName, Class<?> targetClazz) {
        List<String> declaredFields = getAllFields(targetClazz)
                .stream()
                .peek(row -> row.setAccessible(true))
                .filter(row -> Objects.equals(row.getName(), sourceName))
                .map(Field::getName)
                .collect(Collectors.toList());
        return declaredFields.contains(sourceName);
    }

    public static <T> T mapClone(Map map, Class<T> clazz) {
        T res = null;
        try {
            // unsafe实例化对象
            res = (T) unSafe.allocateInstance(clazz);
            // 获取类中的所有字段
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                if (fieldInTarget(field, clazz)) {
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
     * 转换List的时间类型并返回秒数
     *
     * @param list          待转换 list
     * @param dateFieldName 日期名称
     * @param clazz         转换对象
     * @return
     */
    @SuppressWarnings("all")
    public static <T> List<T> convertListSecond(List list, List<String> dateFieldNames, Class<T> clazz) {
        List<T> records = (List) list.stream().map(row -> {
            T clone = clone(row, allocateInstance(clazz), clazz);
            for (String dateFieldName : dateFieldNames) {
                Object timeObj = UnSafeUtil.getFieldValue(row, dateFieldName);
                if (timeObj instanceof LocalDate) {
                    LocalDate rowPaymentDate = (LocalDate) timeObj;
                    long epochSecond = rowPaymentDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();
                    UnSafeUtil.setFieldValue(clone, dateFieldName, epochSecond);
                }
                if (timeObj instanceof LocalDateTime) {
                    LocalDateTime rowPaymentDate = (LocalDateTime) timeObj;
                    long epochSecond = rowPaymentDate.toEpochSecond(ZoneOffset.ofHours(8));
                    UnSafeUtil.setFieldValue(clone, dateFieldName, epochSecond);
                }
            }
            return clone;
        }).collect(Collectors.toList());
        return records;
    }

    /**
     * 转换List的时间类型并返回毫秒数
     *
     * @param list          待转换 list
     * @param dateFieldName 日期名称
     * @param clazz         转换对象
     * @return
     */
    @SuppressWarnings("all")
    public static <T> List<T> convertListMilli(List list, List<String> dateFieldNames, Class<T> clazz) {
        List records = (List) list.stream().map(row -> {
            T clone = clone(row, allocateInstance(clazz), clazz);
            for (String dateFieldName : dateFieldNames) {
                Object timeObj = UnSafeUtil.getFieldValue(row, dateFieldName);
                if (timeObj instanceof LocalDate) {
                    LocalDate rowPaymentDate = (LocalDate) timeObj;
                    long epochSecond = rowPaymentDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();
                    UnSafeUtil.setFieldValue(clone, dateFieldName, epochSecond * 1000);
                }
                if (timeObj instanceof LocalDateTime) {
                    LocalDateTime rowPaymentDate = (LocalDateTime) timeObj;
                    long epochSecond = rowPaymentDate.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
                    UnSafeUtil.setFieldValue(clone, dateFieldName, epochSecond);
                }
            }
            return clone;
        }).collect(Collectors.toList());
        return records;
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
    public static Object convertDate(Object keyObj, Object valueObj) {
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
        List<Field> fields = getAllFields(objClass);
        Field field = fields.stream()
                .peek(row -> row.setAccessible(true))
                .filter(row -> fieldName.equals(row.getName()))
                .collect(Collectors.toList()).get(0);
        return unSafe.getObject(obj, unSafe.objectFieldOffset(field));
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
