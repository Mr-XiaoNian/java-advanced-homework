package com.nian.homework.week.four.thread;

import java.util.concurrent.Callable;

public class CallableThread implements Callable {
    @Override
    public String call() throws Exception {
        return "hey, you catch the result ! this is time ";
    }
}
