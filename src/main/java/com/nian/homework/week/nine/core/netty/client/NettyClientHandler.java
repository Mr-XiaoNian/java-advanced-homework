package com.nian.homework.week.nine.core.netty.client;

import com.alibaba.fastjson.JSON;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.ReferenceCountUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyClientHandler extends SimpleChannelInboundHandler<RpcfxResponse> implements Callable {

    public ChannelHandlerContext ctx;
    private RpcfxResponse rpcResponse;
    private RpcfxRequest rpcRequest;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端建立连接..");
        this.ctx = ctx;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected synchronized  void channelRead0(ChannelHandlerContext ctx, RpcfxResponse rpcResponse) throws Exception {
        System.out.println("服务器返回数据..." + rpcResponse);
        this.rpcResponse = rpcResponse;
        notify();
    }

    public void setRpcRequest(RpcfxRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    @Override
    public synchronized RpcfxResponse call() throws Exception {
        System.out.println("call 方法执行..");
        System.out.println(rpcRequest);
        ctx.writeAndFlush(rpcRequest);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rpcResponse;
    }
}
