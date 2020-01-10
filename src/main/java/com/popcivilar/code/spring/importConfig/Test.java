package com.popcivilar.code.spring.importConfig;

import com.popcivilar.code.spring.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {


    public static void main1(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AppConfig.class);

        applicationContext.refresh();

        UserDao userDao = (UserDao) applicationContext.getBean(UserDao.class);

        userDao.list();
//            applicationContext.getBean(UserService.class).a();

    }
    public static void main(String[] args) throws Exception{

        String filePath = "d:\\abc.txt";
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("11111");
        fileWriter.flush();
        fileWriter.close();

    }
}
