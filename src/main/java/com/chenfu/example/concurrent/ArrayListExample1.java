package com.chenfu.example.concurrent;

import com.chenfu.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ArrayListExample1 {
    public static int threadTotal = 200;

    public static int clientTotal = 5000;

    public static List<Integer> arrayList = new CopyOnWriteArrayList<>();


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
        System.out.println("size:" + arrayList.size());
    }

    private  static void update(int i) {
        arrayList.add(i);
    }
}