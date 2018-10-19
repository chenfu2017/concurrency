package com.chenfu.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.schedule(()->{
            log.info("running....");
        },3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(()->{
            log.info("running....");
        },1,3,TimeUnit.SECONDS);
//        executorService.shutdown();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer running....");
            }
        }, new Date(),5000);
    }
}
