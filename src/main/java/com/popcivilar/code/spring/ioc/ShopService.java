package com.popcivilar.code.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope(scopeName = "singleton")
public abstract class ShopService implements InitializingBean, DisposableBean{



    public GoodsService goodsService;


    @Lookup
    public abstract GoodsService injectGoodsService();

//    public void setGoodsService(GoodsService goodsService) {
//        this.goodsService = goodsService;
//    }


    //    public ShopService(GoodsService goodsService) {
//        this.goodsService = goodsService;
//    }

    public String salePrice(){
       return (injectGoodsService().price());
    }

    public void init(){
        System.out.println("生命周期钩子函数init");

    }
    public void destroy(){
        System.out.println("生命周期钩子函数dsestroy");
    }

//    public ShopService(GoodsService goodsService) {
//        this.goodsService = goodsService;
//        System.out.println("构造函数");
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

}
