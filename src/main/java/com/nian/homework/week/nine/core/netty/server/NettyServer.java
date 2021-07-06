package com.nian.homework.week.nine.core.netty.server;

import com.nian.homework.week.nine.core.encoding.RpcDecoder;
import com.nian.homework.week.nine.core.encoding.RpcEncoder;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public void startNettyHttpServer(int port, int leaderNum, int workerNum) throws InterruptedException {
        EventLoopGroup leaderGroup = new NioEventLoopGroup(leaderNum);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerNum);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(leaderGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128) //初始化服务端可连接队列,指定了队列的大小128
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //保持长连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {  // 绑定客户端连接时候触发操作
                        @Override
                        protected void initChannel(SocketChannel sh) throws Exception {
                            sh.pipeline()
                                    .addLast(new RpcDecoder(RpcfxRequest.class)) //解码request
                                    .addLast(new RpcEncoder(RpcfxResponse.class)) //编码response
                                    .addLast(new NettyServerHandler()); //使用ServerHandler类来处理接收到的消息
                        }
                    });

            ChannelFuture future = b.bind("127.0.0.1", port).sync();

            if (future.isSuccess()) {
                System.out.println("服务端启动成功");
            } else {
                System.out.println("服务端启动失败");
                workerGroup.shutdownGracefully();
            }
            future.channel().closeFuture().sync();

        } finally {
            leaderGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
