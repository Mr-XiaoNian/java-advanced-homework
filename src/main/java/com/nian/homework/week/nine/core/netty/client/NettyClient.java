package com.nian.homework.week.nine.core.netty.client;

import com.nian.homework.week.nine.core.encoding.RpcDecoder;
import com.nian.homework.week.nine.core.encoding.RpcEncoder;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {


    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void startNettyHttpClient(int port) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //生成带有固定配置的基本instance
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class)  // 使用NioSocketChannel来作为连接用的channel类
                .handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("正在连接中...");
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new RpcEncoder(RpcfxRequest.class)); //编码request
                        pipeline.addLast(new RpcDecoder(RpcfxResponse.class)); //解码response
                        pipeline.addLast(NettyClientPool.clientHandler); //客户端处理类
                    }
                });

        ChannelFuture future = bootstrap.connect("127.0.0.1", port).sync();

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture arg0) throws Exception {
                if (future.isSuccess()) {
                    channel = future.channel();
                    System.out.println("连接服务器成功");

                } else {
                    System.out.println("连接服务器失败");
                    future.cause().printStackTrace();
                    workerGroup.shutdownGracefully(); //关闭线程组
                }
            }
        });

    }
}
