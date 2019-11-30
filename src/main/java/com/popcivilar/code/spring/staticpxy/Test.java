package com.popcivilar.code.spring.staticpxy;


import com.popcivilar.code.spring.AppConfig;
import com.popcivilar.code.spring.aop.UserService;
import com.popcivilar.code.spring.aop.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {


    @org.junit.Test
    public void test1(){
        ProxyUserServiceImpl proxyUserService = new ProxyUserServiceImpl();
        proxyUserService.printUser("继承方式-业务逻辑");
    }
    @org.junit.Test
    public void test2(){
        UserService proxyUserService = new ProxyUserServiceImpl2(new UserServiceImpl());
        proxyUserService.printUser("聚合方式-业务逻辑");
    }
}
