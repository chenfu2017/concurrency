package com.chenfu.example.immutable;

import com.chenfu.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

@ThreadSafe
public class ImmutableExample2 {

    private  final static ImmutableList list = ImmutableList.of(1,2,3);

    private  final static ImmutableSet set = ImmutableSet.copyOf(list);

    private  final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);

    public static void main(String[] args) {
        list.add(4);
        map.put(3,4);
    }
}
