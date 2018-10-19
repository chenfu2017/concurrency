package com.chenfu.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(num);
                } catch (Exception e) {
                    log.error("exception :{}",e);
                }
            });
        }
        executorService.shutdown();
    }

    public static void race(int i) throws Exception {
        Thread.sleep(1000);
        log.info("{}is ready!",i);
        cyclicBarrier.await();
        log.info("{} continue",i);

    }
}
