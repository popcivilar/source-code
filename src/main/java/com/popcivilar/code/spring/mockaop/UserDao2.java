package com.popcivilar.code.spring.mockaop;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserDao2 {

    public UserDao2() {
        System.out.println("构造函数");
    }

    @PostConstruct
    public void hhh(){
        System.out.println("1111");
    }

    public void list(){
        System.out.println("UserDao2");
    }
}
