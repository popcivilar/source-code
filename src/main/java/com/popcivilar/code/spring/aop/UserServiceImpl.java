package com.popcivilar.code.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(String name) {
        System.out.println(name);
    }
}
