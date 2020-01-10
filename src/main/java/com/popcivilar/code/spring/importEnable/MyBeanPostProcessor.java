package com.popcivilar.code.spring.importEnable;

import com.popcivilar.code.spring.aop.PerformanceHandler;
import com.popcivilar.code.spring.importConfig.UserDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userDaoImpl".equals(beanName)){
            bean = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                    new Class[]{UserDao.class},new PerformanceHandler(bean));
        }
        return bean;
    }

}
