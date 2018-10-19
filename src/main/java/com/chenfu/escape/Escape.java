package com.chenfu.escape;

import com.chenfu.annoations.NotCommend;
import com.chenfu.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotCommend
public class Escape {

    private int escapeCount=0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("escapeCount:{}",Escape.this.escapeCount);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
