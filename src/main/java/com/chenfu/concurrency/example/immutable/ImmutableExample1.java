package com.chenfu.concurrency.example.immutable;

import com.chenfu.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
@ThreadSafe
public class ImmutableExample1 {

    private  static Map<Integer,Integer> map = Maps.newHashMap();
    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(4, 5);
        map=Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        for (Integer i:map.keySet()
             ) {
            System.out.println(i);
        }
    }

}
