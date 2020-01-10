package com.popcivilar.code.spring.ioc;

import com.popcivilar.code.spring.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {


    @org.junit.Test
    public void testMock1() throws Exception{


        MockIoc mockIoc = new MockIoc("mockSpring.xml");
        String shopService = ((ShopService) mockIoc.getBean("shopService")).salePrice();
        System.out.println(shopService);

        ApplicationContext a = null;
        a.getBean("1");
    }
    @org.junit.Test
    public void test2() throws Exception{

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        ShopService shopServie1 = (ShopService) applicationContext.getBean("shopService");
        ShopService shopServie2 = (ShopService) applicationContext.getBean("shopService");
        System.out.println(shopServie1.salePrice());
        System.out.println(shopServie2.salePrice());
    }

    @org.junit.Test
    public void test3() throws Exception{

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();
        ShopService shopServie1 = (ShopService) applicationContext.getBean("shopService");
        ShopService shopServie2 = (ShopService) applicationContext.getBean("shopService");
        System.out.println(shopServie1.salePrice());
        System.out.println(shopServie2.salePrice());
    }

}


