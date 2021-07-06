package com.nian.homework.week.nine.core.model;

import lombok.Data;

@Data
public class RpcfxResponse {

    public RpcfxResponse() {
    }

    public RpcfxResponse(Object result, boolean flag, Exception exception) {
        this.result = result;
        this.flag = flag;
        this.exception = exception;
    }

    private Object result;
    private boolean flag;
    private Exception exception;
}
