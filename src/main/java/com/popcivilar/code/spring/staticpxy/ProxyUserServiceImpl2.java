package com.popcivilar.code.spring.staticpxy;

import com.popcivilar.code.spring.aop.UserService;
import com.popcivilar.code.spring.aop.UserServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 静态代理-聚合方式
 */
//@Service("px2")
public class ProxyUserServiceImpl2 implements UserService {

    private UserService userService;
    public ProxyUserServiceImpl2(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void printUser(String name) {
        System.out.println("before");
        userService.printUser(name);
        System.out.println("end");
    }
}
