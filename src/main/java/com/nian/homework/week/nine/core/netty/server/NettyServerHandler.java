package com.nian.homework.week.nine.core.netty.server;

import com.alibaba.fastjson.JSONObject;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //请求的基本url
    String targetBaseUrl = "http://127.0.0.1:8802/rpcFxProvider";
    //定义的httpClint
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("come in netty server");
        System.out.println(msg);
        try {
            RpcfxRequest request = (RpcfxRequest)msg;
            String result = getResponseResult(request);
            //返回response
            System.out.println(result);
            ctx.writeAndFlush(JSONObject.toJavaObject(JSONObject.parseObject(result), RpcfxResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    private String getResponseResult(RpcfxRequest request) {
        HttpPost httpPost = new HttpPost(targetBaseUrl);
        String content = JSONObject.toJSONString(request);
        System.out.println(content);
        try {
            httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
            // 执行请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
