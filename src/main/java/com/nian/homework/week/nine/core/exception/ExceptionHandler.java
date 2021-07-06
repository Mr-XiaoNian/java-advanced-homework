package com.nian.homework.week.nine.core.exception;

import org.apache.commons.lang3.StringUtils;

public class ExceptionHandler {
    public static String handler(Exception e){
        if (StringUtils.isNoneBlank(e.getMessage())) {
            return e.getMessage();
        } else {
            return e.getClass().toString();
        }
    }
}
