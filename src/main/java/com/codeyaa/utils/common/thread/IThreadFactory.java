package com.codeyaa.utils.common.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class IThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程组名称，在使用 jstack 来排查线程问题时，非常有帮助
    public IThreadFactory(String whatFeatureOfGroup) {
        this.namePrefix = whatFeatureOfGroup + "-";
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = this.namePrefix + nextId.getAndIncrement();
        return new Thread(null, runnable, name, 0);
    }
}
