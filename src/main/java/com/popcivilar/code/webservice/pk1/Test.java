package com.popcivilar.code.webservice.pk1;


import javax.xml.ws.Endpoint;

public class Test {

    public static void main(String[] args) {
        Endpoint.publish("http://10.190.127.71:457/HelloWebService",new HelloWebService());

    }
}
