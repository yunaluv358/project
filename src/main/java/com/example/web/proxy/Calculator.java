package com.example.web.proxy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component @Lazy

public class Calculator {
    @Autowired
    Box<Integer> box;
    public int sum(int a, int b) {
        box.put("result", a + b);
        return box.get("result");
    }
}