package com.wd.play.support.util;

@FunctionalInterface
public interface CheckedFunction<T,R> {
    R apply(T t) throws Exception;
}

