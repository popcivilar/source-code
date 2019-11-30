package com.popcivilar.code.spring;

import com.popcivilar.code.spring.ioc.ShopService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {



    @Test
    public void test1(){
        ClassPathXmlApplicationContext  applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");

        ShopService shopService1 = applicationContext.getBean("shopService", ShopService.class);
        System.out.println(shopService1.salePrice());
        applicationContext.close();
    }

    @Test
    public void test2(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ShopService shopService = applicationContext.getBean("shopService",ShopService.class);
        System.out.println(shopService.salePrice());
        applicationContext.close();
    }

}
