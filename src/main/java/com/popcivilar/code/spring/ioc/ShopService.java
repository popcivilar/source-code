package com.popcivilar.code.spring.ioc;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
@Lazy
public class ShopService implements InitializingBean, DisposableBean {

    @Autowired
    public GoodsService goodsService;

    public String salePrice(){
       return (goodsService.price());
    }

    public void init(){
        System.out.println("生命周期钩子函数init");

    }
    public void destroy(){
        System.out.println("生命周期钩子函数dsestroy");
    }

    public ShopService() {
        System.out.println("构造函数");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
