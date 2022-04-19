package com.codeyaa.constant;

/**
 * 分支处理接口
 *
 * @author by xin11.xin
 * @date 2021/12/18
 */
@FunctionalInterface
public interface BranchHandle {
    /**
     * 分支操作
     *
     * @param trueHandle  为true时要进行的操作
     * @param falseHandle 为false时要进行的操作
     * @return void
     **/
    void iTrue(Runnable trueHandle, Runnable falseHandle);
}
