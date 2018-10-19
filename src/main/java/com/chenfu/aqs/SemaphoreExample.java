package com.chenfu.aqs;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample {
    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < 200; i++) {
            final int num = i;
            executorService.execute(() -> {
                try {
                    if(semaphore.tryAcquire(5,TimeUnit.SECONDS)){
                        add(num);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    log.error("exception:",e);
                }
            });
        }
        executorService.shutdown();

    }

    private static void add(int i) throws InterruptedException {
        Thread.sleep(2000);
        log.info("i:{}",i);
    }
}

