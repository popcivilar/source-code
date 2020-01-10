package com.popcivilar.code.spring.mockaop;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Apple implements Fruit {
    @Override
    public void eat() {
        System.out.println("apple");
    }
}
