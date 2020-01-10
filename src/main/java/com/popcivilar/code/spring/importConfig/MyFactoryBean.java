package com.popcivilar.code.spring.importConfig;

import org.springframework.beans.factory.FactoryBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean,InvocationHandler {
    private Class clazz;
    public MyFactoryBean(Class clazz){
        this.clazz = clazz;
    }
    @Override
    public Object getObject() throws Exception {
        Object object = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz},this) ;
        return object;
    }
    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method method1 = proxy.getClass().getInterfaces()[0].getMethod(method.getName());
        //此处可根据args的值,类型设置getDeclaredAnnotation的类型参数
        MockSelect declaredAnnotation = method1.getDeclaredAnnotation(MockSelect.class);
        //打印 select * from user
        System.out.println(declaredAnnotation.value());
        return null;
    }
}
