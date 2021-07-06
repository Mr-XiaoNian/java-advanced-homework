package com.nian.homework.week.nine.core.spi;

import com.nian.homework.week.nine.core.model.RpcfxRequest;

public interface Filter {
    boolean filter(RpcfxRequest request);
}
