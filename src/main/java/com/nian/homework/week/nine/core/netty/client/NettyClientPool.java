package com.nian.homework.week.nine.core.netty.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyClientPool {

    public static ExecutorService executor = Executors.newFixedThreadPool(4);

    public static NettyClient nettyClient = new NettyClient();

    public static NettyClientHandler clientHandler = new NettyClientHandler();

}
