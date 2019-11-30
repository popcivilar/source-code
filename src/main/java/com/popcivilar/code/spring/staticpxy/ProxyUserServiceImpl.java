package com.popcivilar.code.spring.staticpxy;

import com.popcivilar.code.spring.aop.UserServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 静态代理-继承方式
 */
@Service
public class ProxyUserServiceImpl extends UserServiceImpl {
    @Override
    public void printUser(String name) {
        System.out.println("before");
        super.printUser(name);
        System.out.println("end");
    }
}
