package com.popcivilar.code.webservice.pk1;


import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * 它是一个注解，用在类上指定将此类发布成一个ws.
 *
 * 类中所有非静态方法都会被发布；
 *
 * 静态方法和final方法不能被发布；
 *
 */
@WebService(
        serviceName="helloService",//【对外发布的服务名 】：需要见名知意
        targetNamespace="http://pk1.webservice.popcivilar.com",//【名称空间】：【实现类包名的反缀】
        endpointInterface = "com.popcivilar.code.webservice.pk1.HelloWebService")//【服务接口全路径】
public class HelloWebService {

    @WebMethod
    public @WebResult String HelloWord(String name){
        return "hello "+name;
    }




}
