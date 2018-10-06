package com.chenfu.concurrency.example;

import com.google.common.collect.Maps;

import java.util.Map;

public class ImmutableExample1 {

    private final static Map<Integer,Integer> map = Maps.newHashMap();
    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(4, 5);
    }

    public static void main(String[] args) {
        for (Integer i:map.keySet()
             ) {
            System.out.println(i);
        }
    }

}
