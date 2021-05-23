package com.nian.homework.week.three.complex.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel>  {

    private final List<String> backends;

    public HttpInboundInitializer(List<String> backends){
        this.backends = backends;
    }
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(1024 * 1024 * 1024))
                .addLast(new HttpInboundHandler(this.backends));
    }
}
