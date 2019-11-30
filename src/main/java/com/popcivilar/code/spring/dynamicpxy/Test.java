package com.popcivilar.code.spring.dynamicpxy;


import com.popcivilar.code.spring.aop.UserService;
import com.popcivilar.code.spring.aop.UserServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {



    @org.junit.Test
    public void test1() throws Exception {
        UserService userService = new UserServiceImpl();
        CustomProxy proxy = new CustomProxy(userService);
        Object o = proxy.newInstance();
        System.out.println(o);
    }


}
