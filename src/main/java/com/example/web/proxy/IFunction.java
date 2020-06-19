package com.example.web.proxy;

import java.util.HashMap;

@FunctionalInterface
public interface IFunction<T,R> {
    public R apply(T t);

}
