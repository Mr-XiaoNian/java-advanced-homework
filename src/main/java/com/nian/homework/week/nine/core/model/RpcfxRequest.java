package com.nian.homework.week.nine.core.model;

import lombok.Data;

@Data
public class RpcfxRequest {

  public RpcfxRequest() {
  }

  public RpcfxRequest(String serviceClass, String method, Object[] params) {
    this.serviceClass = serviceClass;
    this.method = method;
    this.params = params;
  }

  private String serviceClass;
  private String method;
  private Object[] params;
}
