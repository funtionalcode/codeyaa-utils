package com.codeyaa.utils.common.reflection;

import com.codeyaa.utils.common.StringUtils;
import com.codeyaa.utils.common.date.DateUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeyaa.utils.common.reflection.UnSafeUtil.fieldInTarget;

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

    public static Object getReadValue(Object obj, String key) {
        try {
            Class<?> clazz = obj.getClass();
            //使用符合JavaBean规范的属性访问器
            PropertyDescriptor pd = new PropertyDescriptor(key, clazz);
            //调用setter
            Method readMethod = pd.getReadMethod();
            readMethod.setAccessible(true);
            return readMethod.invoke(obj);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Object getValue(Object obj, String key) {
        try {
            Class<?> clazz = obj.getClass();
            //使用符合JavaBean规范的属性访问器
            List<Field> allFields = getAllFields(clazz).stream().filter(Objects::nonNull).peek(row -> row.setAccessible(true)).collect(Collectors.toList());
            if (allFields.isEmpty()) {
                return "";
            }
            return getField(obj, key).get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setReadValue(Object obj, String key, Object value) {
        try {
            //使用符合JavaBean规范的属性访问器
            PropertyDescriptor pd = new PropertyDescriptor(key, obj.getClass());
            //调用setter
            Method writeMethod = pd.getWriteMethod();
            writeMethod.setAccessible(true);
            writeMethod.invoke(obj, value);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setValue(Object obj, String key, Object value) {
        try {
            Field field = getField(obj, key);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethod(Object obj, String key, Class<?>... parameterTypes) {
        try {
            Method md = obj.getClass().getDeclaredMethod(key, parameterTypes);
            md.setAccessible(true);
            return md;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> T clone(Object source, Class<T> clazz) {
        try {
            return clone(source, clazz.newInstance(), clazz);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> T clone(Object source, T target, Class<T> clazz) {
        if (Objects.isNull(source) || Objects.isNull(clazz)) {
            return target;
        }
        Class<?> sourceClazz = source.getClass();
        // 获取目标类中的所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            // 字段在当前类
            if (fieldInTarget(field, sourceClazz)) {
                // 当前类的属性值
                Object fieldValue = getValue(source, fieldName);
                setValue(target, fieldName, fieldValue);
            }
        }
        return (T) clone(source, target, clazz.getSuperclass());
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
                .map(row -> (String) getReadValue(obj, row))
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
     * 获取本类及其父类的属性
     *
     * @param clazz 当前类对象
     * @return 字段数组
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        return getAllFields(clazz.getSuperclass(), new ArrayList<>());
    }

    private static List<Field> getAllFields(Class<?> clazz, List<Field> fields) {
        if (Objects.isNull(clazz)) {
            return fields;
        }
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return getAllFields(clazz.getSuperclass(), fields);
    }

    public static Field getField(Object obj, String key) {
        if (Objects.isNull(obj)) {
            return null;
        }
        Class<?> clazz = obj.getClass();
        List<Field> allFields = getAllFields(clazz);
        return allFields.stream()
                .filter(row -> Objects.equals(row.getName(), key))
                .peek(row -> row.setAccessible(true))
                .findFirst()
                .get();
    }
}
