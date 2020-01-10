package com.popcivilar.code.spring.importEnable;

import com.popcivilar.code.spring.AppConfig;
import com.popcivilar.code.spring.importConfig.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileWriter;

public class Test {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AppConfig.class);

        applicationContext.refresh();

//        UserDao userDao = (UserDao) applicationContext.getBean(UserDao.class);
//
//        userDao.list();

    }
}
