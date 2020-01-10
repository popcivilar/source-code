package com.popcivilar.code.spring.mockaop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopBeanPostProcessor implements BeanPostProcessor , MethodInterceptor, InvocationHandler {

    private Object target;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //0.扫描得到需要被代理的类，否则return


        if(bean.getClass().getInterfaces().length > 0 || bean.getClass().isInterface()){
            this.target = bean;
            return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    this);
        }else{
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy before");
        methodProxy.invokeSuper(o,objects);
        System.out.println("cglib proxy after");
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy before");
        method.invoke(target,args);
        System.out.println("jdk proxy after");
        return proxy;
    }
}
