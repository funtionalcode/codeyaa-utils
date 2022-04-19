package com.codeyaa.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class UserThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程组名称，在使用 jstack 来排查线程问题时，非常有帮助
    public UserThreadFactory(String whatFeatureOfGroup) {

        this.namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = this.namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, runnable, name, 0);
        return thread;
    }
}
