package com.nian.homework.week.four;

import com.nian.homework.week.four.thread.CallableThread;
import com.nian.homework.week.four.thread.RunnableThread;

import java.util.concurrent.*;

public class GetResultBeforeMain {

    public static void main(String[] args) {

        //num用来计数
        int num = 1;
        String result;


        result = getResultMethodOne();
        System.out.println(result + num);
        num++;

        result = getResultMethodTwo();
        System.out.println(result + num);
        num++;

        result = getResultMethodThree();
        System.out.println(result + num);
        num++;

        result = getResultMethodFour();
        System.out.println(result + num);

        System.out.println("then main method will done!");
    }


    //callable返回结果
    private static String getResultMethodOne() {
        FutureTask<String> future = new FutureTask<String>(new CallableThread());
        try {
            new Thread(future).start();
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    //线程组跑callable返回结果
    private static String getResultMethodTwo() {
        FutureTask<String> future = new FutureTask<String>(new CallableThread());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(future);
        executor.shutdown();
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    //CompletableFuture返回结果
    private static String getResultMethodThree() {
        //supplyAsync异步操作，整个线程在join阻塞，等待结果
        return CompletableFuture.supplyAsync(() -> {
            return "hey, you catch the result ! this is time ";
        }).join();
    }

    //runnable线程修改自己的内部变量，调用get返回
    private static String getResultMethodFour() {
        RunnableThread threadTwo =new RunnableThread();
        Thread thread = new Thread(threadTwo);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return threadTwo.getResult();
    }



}
