package com.nian.homework.week.nine.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import com.nian.homework.week.nine.core.spi.RpcfxResolver;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Server {

    private RpcfxResolver resolver;

    public Server(RpcfxResolver resolver){
        this.resolver = resolver;
    }


    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        //拿到类
        Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);

        try {
            //拿到对应方法
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setFlag(true);
            return response;
        } catch ( Exception e) {
            // 3.Xstream
            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            response.setException(e);
            response.setFlag(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
