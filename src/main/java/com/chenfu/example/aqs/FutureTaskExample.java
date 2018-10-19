package com.chenfu.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new FutureExample.MyCallble());
        new Thread(futureTask).start();
        log.info("do else in main");
        Thread.sleep(1000);
        String s = futureTask.get();
        log.info("result:{}",s);
    }
}
