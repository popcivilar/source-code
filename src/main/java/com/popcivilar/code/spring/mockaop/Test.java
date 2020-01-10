package com.popcivilar.code.spring.mockaop;

import com.popcivilar.code.spring.AppConfig;
import com.popcivilar.code.spring.aop.UserServiceImpl;
import com.popcivilar.code.spring.importConfig.UserDao;
import com.popcivilar.code.spring.importConfig.UserDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

public class Test {

@PostConstruct
    public static void main(String[] args) {
    Log log = LogFactory.getLog("");
    AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();
//        Object appConfig = applicationContext.getBean(AppConfig.class);
        Object appConfig = applicationContext.getBean(UserDao2.class);

//        UserDao2 o = (UserDao2) applicationContext.getBean("userDao2");
//        o.list();
//        Fruit apple = (Fruit) applicationContext.getBean("apple");
//        apple.eat();

    }
}
