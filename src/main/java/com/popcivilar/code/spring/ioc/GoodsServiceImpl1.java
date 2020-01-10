package com.popcivilar.code.spring.ioc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = "prototype")
public class GoodsServiceImpl1 implements GoodsService{



    public String price(){
        System.out.println(this.hashCode());
        return "GoodsServiceImpl1";
    }
}
