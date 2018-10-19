package com.chenfu.example.aqs;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;
@Slf4j
public class CountDownLatchExample {

    public static int clientTotal = 500;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int num = i;
            executorService.execute(() -> {
                try {
                    add(num);
                } catch (Exception e) {
                    log.error("exception:", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10,TimeUnit.MICROSECONDS);
        log.info("finish");
    }

    private static void add(int i) throws InterruptedException {
        Thread.sleep(100);
        log.info("i:{}",i);
    }
}

