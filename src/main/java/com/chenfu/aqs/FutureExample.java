package com.chenfu.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {

    static class MyCallble implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do else in callble");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallble());
        log.info("do else in main");
        Thread.sleep(1000);
        String s = future.get();
        log.info("result:{}",s);
    }
}
