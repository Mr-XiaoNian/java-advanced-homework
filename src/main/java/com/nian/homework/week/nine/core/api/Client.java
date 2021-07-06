package com.nian.homework.week.nine.core.api;

import com.alibaba.fastjson.JSON;
import com.nian.homework.week.nine.core.exception.ExceptionHandler;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import com.nian.homework.week.nine.core.netty.client.NettyClientPool;
import com.nian.homework.week.nine.core.spi.Filter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutionException;

@Service
public class Client {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {
        // 0. 替换动态代理 -> AOP
        return (T) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));
    }


    public static class RpcfxInvocationHandler implements InvocationHandler {

        private final Class<?> serviceClass;
        private final String url;
        private final Filter[] filters;

        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url, Filter... filters) {
            this.serviceClass = serviceClass;
            this.url = url;
            this.filters = filters;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

            //封装request
            RpcfxRequest request = new RpcfxRequest(this.serviceClass.getName(), method.getName(), params);

            //处理filter
            boolean filterFlag = handlerFilter(request, filters);
            if (!filterFlag) {
                return null;
            }

            //去拿response
            RpcfxResponse response = post(request, url);

            //处理返回结果
            if (response != null) {
                if (response.isFlag()) {
                    //成功获取后返回result
                    return JSON.parse(response.getResult().toString());
                } else {
                    //根据exception的情况进行返回
                    return ExceptionHandler.handler(response.getException());
                }
            }

            //请求失败
            throw new Exception("请求失败！");
        }

    }

    private static boolean handlerFilter(RpcfxRequest request, Filter... filters) {
        if (null != filters) {
            for (Filter filter : filters) {
                if (!filter.filter(request)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private static RpcfxResponse post(RpcfxRequest req, String url) throws IOException, ExecutionException, InterruptedException {

        //使用httpClient
//        String reqJson = JSON.toJSONString(req);
//        System.out.println("req json: " + reqJson);
//        // 1.可以复用client
//        // 2.尝试使用httpclient或者netty client
//        HttpPost clientRequest = new HttpPost(url);
//        clientRequest.setEntity(new StringEntity(reqJson, ContentType.APPLICATION_JSON));
//        HttpResponse response = httpClient.execute(clientRequest);
//        if (response != null && response.getStatusLine().getStatusCode() == 200) {
//            String result = EntityUtils.toString(response.getEntity());
//            return JSON.parseObject(result, RpcfxResponse.class);
//        }



        //使用netty
        NettyClientPool.clientHandler.setRpcRequest(req);
        RpcfxResponse result = (RpcfxResponse) NettyClientPool.executor.submit(NettyClientPool.clientHandler).get();
        System.out.println(result);
        return result;
    }

}
