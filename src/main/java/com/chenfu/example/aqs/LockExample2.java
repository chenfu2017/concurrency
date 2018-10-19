package com.chenfu.example.aqs;

import com.chenfu.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.tree.Tree;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class LockExample2 {
    private final Map<String, Data> map = new TreeMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final static Lock readLock = lock.readLock();

    private final static Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public void put(String key, Data data) {
        writeLock.lock();
        try {
            map.put(key, data);
        }finally {
            writeLock.unlock();
        }
    }

    public Set<String> getKeys(){
        readLock.lock();
        try {
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    class Data{

    }
}
