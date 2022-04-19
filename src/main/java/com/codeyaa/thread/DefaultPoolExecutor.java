package com.codeyaa.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultPoolExecutor {
    public static ThreadPoolExecutor newInstance(Integer length, String threadGroupName) {
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(length);
        UserThreadFactory userThreadFactory = new UserThreadFactory(threadGroupName);
        /**
         * ThreadPoolExecutor.AbortPolicy() 抛出java.util.concurrent.RejectedExecutionException异常
         * ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务，他会自动重复调用execute()方法
         * ThreadPoolExecutor.DiscardOldestPolicy() 抛弃旧的任务
         * ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务
         */
        ThreadPoolExecutor.CallerRunsPolicy policy = new ThreadPoolExecutor.CallerRunsPolicy();
        /**
         * corePoolSize    核心线程池大小
         * maximumPoolSize 最大线程池大小
         * keepAliveTime   线程最大空闲时间
         * unitHashRate 	       时间单位
         * workQueue       线程等待队列
         * threadFactory   线程创建工厂
         * handler 	       拒绝策略
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (length, length + 1, 10L, TimeUnit.MINUTES,
                        arrayBlockingQueue, userThreadFactory, policy);
        return threadPoolExecutor;
    }

}
