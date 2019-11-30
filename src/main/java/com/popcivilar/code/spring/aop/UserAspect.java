package com.popcivilar.code.spring.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {


    @Pointcut("execution(* com.popcivilar.code.spring.aop.*.*(..))")
    public void pointCut_1(){}

    @Pointcut("within(com.popcivilar.code.spring.aop.*)")
    public void pointCut_2(){}

    @Pointcut("args(java.lang.Integer)")
    public void pointCut_3(){}


    @Before("com.popcivilar.code.spring.aop.UserAspect.pointCut_3()")
    public void beforeAdvice(){
        System.out.println("before");
    }
}
