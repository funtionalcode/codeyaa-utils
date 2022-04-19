package com.codeyaa.constant;

import java.util.function.Supplier;

/**
 * @author by xin11.xin
 * @date 2021/12/18
 */
public interface BranchGetHandle<T> {
    T get(Supplier<? super T> trueHandle, Supplier<? super T> falseHandle);
}
