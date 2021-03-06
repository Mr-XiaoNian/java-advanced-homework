package com.nian.homework.week.three.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyHttpServer {

    public static void startNettyHttpServer(int port, int leaderNum, int workerNum) throws InterruptedException {
        EventLoopGroup leaderGroup = new NioEventLoopGroup(leaderNum);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerNum);
        try {
            ServerBootstrap b = new ServerBootstrap();
            //根据要求生成对应的leader组合worker组
            //生成带有固定配置的基本instance
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.group(leaderGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitializer());
            Channel ch = b.bind(port).sync().channel();
            ch.closeFuture().sync();
        } finally {
            leaderGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
