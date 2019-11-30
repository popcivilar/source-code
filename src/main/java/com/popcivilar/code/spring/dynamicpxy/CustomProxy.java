package com.popcivilar.code.spring.dynamicpxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义实现动态代理
 */
public class CustomProxy {
    //目标对象
    private Object target;

    public CustomProxy(Object target) {
        this.target = target;
    }


    public Object newInstance() throws Exception {

        Class<?>[] interfaces = target.getClass().getInterfaces();

        //1.生成增强类 .java
        String newLine = "\n";
        String tab = "\t";
        String packageContent = "package com.popcivilar;" + newLine;

        String fistLineContent = "public class $Proxy {"+newLine;

        String filedContent = tab+"private Object target;"+newLine;

        String constructorContent = tab+"public $Proxy(Object target){"+newLine
                +tab+tab+"this.target = target;"+newLine
                +tab+"}"+newLine;


        Method[] declaredMethodArr = target.getClass().getDeclaredMethods();

        String methodContent = "";
        for (Method method : declaredMethodArr) {
            String returnTypeName = method.getReturnType().getName();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            String argsContent = "";
            for (int i = 0; i < parameterTypes.length; i++) {
                argsContent += parameterTypes[i].getTypeName()+" arg"+i+",";
            }
            if(argsContent.length() > 0){
                argsContent = argsContent.substring(0,argsContent.length()-1);
            }
            String returnVoid = returnTypeName.equals("void")?"":"return ";

            methodContent += tab+"public "+returnTypeName+" "+methodName+"("+argsContent+") throws Exception{"+newLine
                          +tab+tab+"System.out.println(\"before-custonProxy\");"+newLine
                          +tab+tab+"java.lang.reflect.Method method = Class.forName(target.getClass().getName()).getDeclaredMethod(\""+methodName+"\");"+newLine
                          + tab+tab+returnVoid+"method.invoke(target);"+newLine
                          +tab+"}"+newLine;

        }


        String content = packageContent + fistLineContent + filedContent + constructorContent + methodContent + newLine+"}";


        File file = new File("d:\\com\\popcivilar\\$Proxy.java");

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();



        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(file);

        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();

//
        URL[] urls = new URL[]{new URL("file:D:\\\\")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class clazz = urlClassLoader.loadClass("com.popcivilar.$Proxy");

        Constructor constructor = clazz.getConstructor(CoustomInvocationHandler.class);
        CoustomInvocationHandler coustomInvocationHandler = new CoustomInvocationHandler() {
            @Override
            public Object invoke(Method method) {
                return null;
            }
        };
        return  constructor.newInstance(coustomInvocationHandler);
    }
}
