package com.nian.homework.week.three.complex.router;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Random;

public class HttpEndpointRouter {


    public static JSONObject getRandomEndpoint(List<String> proxyServers) {
        Random random = new Random();
        JSONObject result = new JSONObject();
        int instanceFrOM = random.nextInt(proxyServers.size());
        result.put("url", proxyServers.get(instanceFrOM));
        result.put("instanceFrom", instanceFrOM);
        return result;
    }

}
