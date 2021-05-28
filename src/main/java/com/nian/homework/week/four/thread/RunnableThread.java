package com.nian.homework.week.four.thread;


public class RunnableThread implements Runnable {
    private String result;
    @Override
    public void run() {
        result = "hey, you catch the result ! this is time ";
    }

    public String getResult() {
        return result;
    }
}
