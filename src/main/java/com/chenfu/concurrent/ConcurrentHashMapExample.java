package com.chenfu.concurrent;

import com.chenfu.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {
    public static int threadTotal = 200;

    public static int clientTotal = 5000;

    public static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static Map<Integer, Integer> map2 = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("map1:{}",map.size());
        log.info("map2:{}",map.size());
    }

    private  static void update(int i) {
        map.put(i,i);
        map2.put(i, i);
    }
}
