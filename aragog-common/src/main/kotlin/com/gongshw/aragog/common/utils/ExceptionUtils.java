/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.utils;

/**
 * @author gongshiwei
 */
public class ExceptionUtils {
    public static void throwRuntimeException(Throwable throwable) throws RuntimeException {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        throw new RuntimeException(throwable);
    }
}
