package com.codeyaa.constant;

/**
 * @author by xin11.xin
 * @date 2021/12/18
 */
public class CodeYaa {
    public static BranchHandle iIf(boolean b) {
        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    public static <T> BranchGetHandle<T> iIfGet(boolean b, Class<T> clazz) {
        return (consumer, runnable) -> {
            if (b) {
                return clazz.cast(consumer.get());
            }
            return clazz.cast(runnable.get());
        };
    }
}
