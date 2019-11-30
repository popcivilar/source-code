package com.popcivilar.code.spring.dynamicpxy;

import java.lang.reflect.Method;

public interface CoustomInvocationHandler {

    public Object invoke(Method method);
}
