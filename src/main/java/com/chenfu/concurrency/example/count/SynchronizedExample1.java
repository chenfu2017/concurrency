package com.chenfu.concurrency.example.count;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1-{}",i);
            }
        }
    }

    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2-{}",i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample1 example1 = new SynchronizedExample1();
        executorService.execute(()->{
            example1.test1();
        });
        executorService.execute(()->{
            example1.test2();
        });
        executorService.shutdown();
    }
}
