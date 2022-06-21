package com.codeyaa.constant;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * Created on 2022/5/30.
 *
 * @author zyg
 */
@FunctionalInterface
public interface FunctionModelName<T> extends Serializable {
    Object get(T source);

    default SerializedLambda getSerializedLambda() throws Exception {
        //writeReplace改了好像会报异常
        Method write = this.getClass().getDeclaredMethod("writeReplace");
        write.setAccessible(true);
        return (SerializedLambda) write.invoke(this);
    }

    default String getClassName() {
        try {
            return getSerializedLambda().getImplClass();
        } catch (Exception e) {
            return null;
        }
    }

    default String getMethodName() {
        try {
            return getSerializedLambda().getImplMethodName();
        } catch (Exception e) {
            return null;
        }
    }

    default String getFieldName() {
        try {
            String implMethodName = getMethodName();
            if (!implMethodName.contains("get")) {
                return null;
            }
            String preFieldName = implMethodName.substring(3);
            if (Character.isLowerCase(preFieldName.charAt(0))) {
                return preFieldName;
            }
            char[] cs = preFieldName.toCharArray();
            cs[0] += 32;
            return String.valueOf(cs);
        } catch (Exception e) {
            return null;
        }
    }
}
