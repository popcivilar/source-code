package com.popcivilar.code.sync;

/**
 * 测试 volitale 与 sync 变量可见性
 * 结果证明  ，两者均可 保证
 * 结论： volitale是低配版的sync
 */
public class VisibleDemo {

    private static int a = -1;

    public static synchronized void test1(){
        try {
            System.out.println(Thread.currentThread().getName()+"线程1阻塞2s");
            Thread.sleep(2000);
            a = 1;
        }catch (Exception e){

        }
    }

    public static synchronized void test2(){
        try {
            while(a<0){

            }
            System.out.println(Thread.currentThread().getName()+"线程2感知到了");;
        }catch (Exception e){

        }
    }


    public static void main(String[] args) throws Exception {

        System.out.println("主线程开始");

        new Thread(()->{
            test1();
        }).start();

        new Thread(()->{
            test2();
        }).start();
        System.out.println("主线程结束");


    }

}
