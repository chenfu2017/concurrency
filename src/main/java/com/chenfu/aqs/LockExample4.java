package com.chenfu.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class LockExample4 {

    private final static StampedLock lock = new StampedLock();

    public static int threadTotal = 200;

    public static int clientTotal = 5000;

    public static int count = 0;
    
    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addCount();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count);
    }

    private static void addCount() {
        long stamp = lock.writeLock();
        try {
            count++;
        }finally {
            lock.unlock(stamp);
        }
    }
}
