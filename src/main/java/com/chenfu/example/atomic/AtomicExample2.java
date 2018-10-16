package com.chenfu.example.atomic;

import com.chenfu.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample2 {

    private static AtomicIntegerFieldUpdater<AtomicExample2> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample2.class,"count");
    @Getter
    private volatile int count = 0;

    public static void main(String[] args) {
        AtomicExample2 example = new AtomicExample2();
        if (updater.compareAndSet(example, 0, 2)) {
            log.info("count1:{}",example.getCount());
        }
        if (updater.compareAndSet(example, 0, 3)) {
            log.info("count2:{}",example.getCount());
        } else {
            log.error("update failed,{}",example.getCount());
        }
    }
}
