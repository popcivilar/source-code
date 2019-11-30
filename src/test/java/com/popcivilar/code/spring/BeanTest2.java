package com.popcivilar.code.spring;

import com.popcivilar.code.spring.ioc.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//指定单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(classes=AppConfig.class)
//@ContextConfiguration(locations={"/spring.xml"})
public class BeanTest2 {


   @Autowired
   ShopService shopService;

   @Test
    public void test(){
       System.out.println(shopService.salePrice());
   }

}
