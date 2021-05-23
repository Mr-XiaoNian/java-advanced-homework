package com.nian.homework.week.three.simple;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(1024 * 1024 * 1024))
                .addLast(new HttpHandler());
    }
}
