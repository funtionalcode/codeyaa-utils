package com.codeyaa.utils.common.reflection;

import com.codeyaa.utils.common.StringUtils;
import com.codeyaa.utils.common.date.DateUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanUtil {

    public static List<String> getBeanNames(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        ArrayList<String> res = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            res.add(declaredField.getName());
        }
        return res;
    }

    public static Object getValue(Object obj, String key) {
        Class<?> clazz = obj.getClass();
        //得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(key)) {
                try {
                    return field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static Object getValue(Class<?> clazz, String key) {
        //得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(key)) {
                try {
                    return field.get(clazz);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void setValue(Object obj, String key, Object value) {
        Class<?> clazz = obj.getClass();
        //得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(key)) {
                try {
                    field.set(obj, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> T mapClone(Map map, Class<T> clazz) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.CN_TWELVE_FULL);
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            // 使用newInstance来创建对象
            obj = clazz.newInstance();
            // 获取类中的所有字段
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                // 判断是拥有某个修饰符
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                // 当字段使用private修饰时，需要加上
                field.setAccessible(true);
                // 获取参数类型名字
                String filedTypeName = field.getType().getName();
                // 判断是否为时间类型，使用equalsIgnoreCase比较字符串，不区分大小写
                // 给obj的属性赋值
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = (String) map.get(field.getName());
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, sdf.parse(datetimestamp));
                    }
                } else if ("java.lang.Integer".equals(filedTypeName)) {
                    putIntegerValue(map, obj, field);
                } else if ("java.lang.String".equals(filedTypeName)) {
                    putStringValue(map, obj, field);
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();

        }
        return obj;
    }

    public static <T> T clone(Object object, Class<T> clazz) {
        T res = null;
        try {
            // unsafe实例化对象
            res = (T) clazz.newInstance();
            // 获取目标类中的所有字段
            Field[] fields = getAllFields(res.getClass());
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                // 字段时候在当前类
                if (UnSafeUtil.fieldInTarget(fieldName, object.getClass())) {
                    // 当前类的属性
                    Field declaredField = object.getClass().getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    // 当前类的属性值
                    Object fieldValue = getValue(object, fieldName);
                    // 赋值到目标类
                    setValue(res, fieldName, fieldValue);
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private static <T> void putIntegerValue(Map map, T obj, Field field) throws IllegalAccessException {
        String o = map.get(field.getName()) + "";
        // 避免 Double `.`
        if (StringUtils.isNotBlank(o) && o.contains(".")) {
            field.set(obj, Integer.parseInt(o.substring(0, o.indexOf("."))));
        } else {
            field.set(obj, map.get(field.getName()));
        }
    }

    private static <T> void putStringValue(Map map, T obj, Field field) throws IllegalAccessException {
        String o = map.get(field.getName()) + "";
        // 避免 Double `.`
        if (StringUtils.isNotBlank(o) && o.contains(".")) {
            field.set(obj, o.substring(0, o.indexOf(".")));
        } else {
            field.set(obj, map.get(field.getName()));
        }
    }

    public static String toString(Object obj) {
        List<String> beanNames = getBeanNames(obj.getClass());
        ArrayList<String> res = beanNames.stream()
                .map(row -> (String) getValue(obj, row))
                .collect(Collectors.toCollection(ArrayList::new));
        return String.join(",", res);
    }

    public static <T> T newInstance(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 获取本类及其父类的属性的方法
     *
     * @param clazz 当前类对象
     * @return 字段数组
     */
    public static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }
}
