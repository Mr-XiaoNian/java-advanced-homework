package com.nian.homework.week.three.complex.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HttpResponseFilter {

    public void responseFilter(FullHttpResponse response) {
        response.headers().set("MyResponseFilter", "ResponseFilter add this header");

    }
}
