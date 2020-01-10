package com.popcivilar.code.spring;


import com.popcivilar.code.spring.mockaop.Apple;
import com.popcivilar.code.spring.mockaop.EnableMockAop;
import com.popcivilar.code.spring.mockaop.Fruit;
import com.popcivilar.code.spring.mockaop.UserDao2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.popcivilar.code.spring.mockaop")
public class AppConfig {


    @Bean
    public UserDao2 userDao2(){
        fruit();
        return new UserDao2();
    }


    @Bean
    public Fruit fruit(){
//        userDao2();
        return new Apple();
    }
}
