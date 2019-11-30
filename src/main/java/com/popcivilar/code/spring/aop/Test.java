package com.popcivilar.code.spring.aop;

import com.popcivilar.code.spring.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class Test {



    @org.junit.Test
    public void test1(){

        UserService userService = new UserServiceImpl();
        PerformanceHandler performanceHandler = new PerformanceHandler(userService);

        UserService obj = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), performanceHandler);
        obj.printUser("123");
    }

    @org.junit.Test
    public void test2(){

        CjlibHandler cjlibHandler = new CjlibHandler();
        UserService proxy = (UserService)cjlibHandler.getProxyInstance(UserServiceImpl.class);
        proxy.printUser("22");
    }


}
