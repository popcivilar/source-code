package com.popcivilar.code.spring.ioc;

import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl1 implements GoodsService{



    public String price(){
        return "GoodsServiceImpl1";
    }
}
