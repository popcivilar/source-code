package com.popcivilar.code.spring.ioc;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟Spring IOC
 * XML文件
 */
public class MockIoc {


    private Map<String, Object> beanMap = new HashMap<>();

    public Object getBean(String name) {
        System.out.println(beanMap);
        return beanMap.get(name);
    }

    public MockIoc(String xmlPath) throws Exception {

        //0.dom4j 读取 xml
        File file = new File(MockIoc.class.getClassLoader().getResource(xmlPath).getFile());
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();


        List<String> injectInstanceNullList = new ArrayList<>(); // 实例化顺序 设置依赖时为null的bean的记录

        boolean autoWiredTypeFlag = false;  //模拟自动注入 按type类型的注入
        if (root.attribute("auto-wired") != null) {
            Attribute attribute = root.attribute("auto-wired");
            if("type".equals(attribute.getValue())){
                autoWiredTypeFlag = true;
            }
        }

        //1.循环读取bean标签
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Object instance = null;
            Element element = it.next();

            Attribute bean = element.attribute("class");
            String classVal = bean.getValue();
            if (element.element("constructor-arg") == null) {
                //没有constructor-arg 标签  可直接实例化
                Constructor<?> constructor = Class.forName(classVal).getDeclaredConstructors()[0];
                if (constructor.getParameterTypes().length == 0) {
                    System.out.println("无默认构造函数");
                    instance = Class.forName(classVal).newInstance();
                }
            } else {
                //实例化构造函数
                Element constructorElement = element.element("constructor-arg");
                String constructorRefName = "";
                for (Attribute attribute : constructorElement.attributes()) {
                    if (attribute.getName().equals("ref")) {
                        constructorRefName = attribute.getValue();
                    }
                    Object constuctorInjectInstance = beanMap.get(constructorRefName);
                    if(constuctorInjectInstance == null){
                        injectInstanceNullList.add(element.attribute("id").getValue());
                    }else{
                        instance = Class.forName(classVal).getConstructor(constuctorInjectInstance.getClass()
                                .getInterfaces()[0]).newInstance(constuctorInjectInstance);
                        break;
                    }
                }

            }

            if (element.element("property") != null) {
                Object injectInstance = null;
                Field injectField = null;
                Element property = element.element("property");
                for (Attribute attribute : property.attributes()) {
                    if ("ref".equals(attribute.getName())) {
                        injectInstance = beanMap.get(attribute.getValue());
                        if(injectInstance == null){
                            injectInstanceNullList.add(element.attribute("id").getValue());
                        }
                    }
                    if ("name".equals(attribute.getName())) {
                        injectField = instance.getClass().getDeclaredField(attribute.getValue());
                    }
                }
                injectField.set(instance, injectInstance);

            }


            //自动注入 按type
            if (autoWiredTypeFlag) {
                for (Attribute attribute : element.attributes()) {
                    if("class".equals(attribute.getName())){
                        Field[] fieldArr = Class.forName(attribute.getValue()).getDeclaredFields();
                        final Object obj = instance;
                        final AtomicInteger injectCount = new AtomicInteger(0);
                        if(fieldArr.length > 0 && beanMap.isEmpty()){
                            injectInstanceNullList.add(element.attribute("id").getValue());
                        }
                        for (Field field : fieldArr) {
                            beanMap.values().forEach(v->{
                                Class<?>[] interfaces = v.getClass().getInterfaces();

                                for (Class<?> anInterface : interfaces) {
                                    if (anInterface.getName().equals(field.getType().getName())) {
                                        injectCount.getAndIncrement();
                                        try {
                                            field.set(obj,v);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                            if(injectCount.get() > 1){
                                throw new RuntimeException("excepted 1 bean ,but found 2 bean");
                            }
                        }
                    }
                }





            }

            beanMap.put(element.attribute("id").getValue(), instance);

        }
        //2.处理依赖实例化顺序 null再赋值
        injectInstanceNullList.forEach(injectK->{
            beanMap.forEach((k,v)->{
                if(k.equals(injectK)){
                    Field[] declaredFields = v.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        try {
                            if (declaredField.get(v) == null) {
                                beanMap.forEach((k1,v1)->{
                                    if(k1.equals(declaredField.getName())){
                                        try {
                                            declaredField.set(v,v1);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        });
    }

}
