package com.youxiang.netty.chapter07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Rivers
 * @date: 2018/4/14
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.schedule(() -> System.out.println("60 seconds later"), 60, TimeUnit.SECONDS);

        Thread.sleep(100);
        executorService.shutdown();
    }
}
