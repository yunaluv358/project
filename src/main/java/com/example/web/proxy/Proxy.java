package com.example.web.proxy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

@Component("pxy")
@Lazy
public class Proxy {
    public String string(Object param){
        Function<Object, String> f = String :: valueOf;
        return f.apply(param);
    }

    public Integer integer(String param){
        Function<String, Integer> f = Integer :: parseInt;
        return f.apply(param);
    }

    public Boolean equals(String param1, String param2){
        BiPredicate<String, String> f = String :: equals;
        return f.test(param1, param2);
    }

    public void print(Object param){
        Consumer<Object> f = System.out::println;
        f.accept(param);
    }
}