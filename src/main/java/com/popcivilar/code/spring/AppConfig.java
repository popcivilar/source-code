package com.popcivilar.code.spring;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.popcivilar.code.spring")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {
}
