package com.popcivilar.code.map;


import java.util.concurrent.*;

/**
 * map测试
 */
public class MapTest {


    public static void main(String[] args) throws InterruptedException {

        FakedHashMap<String,String> fakedHashMap = new FakedHashMap<>();

        for(int i = 0;i<345;i++){
            final String index = i+"";
            new Thread(()->{
                fakedHashMap.put(index,index);
            }).start();
        }


        //《阿里巴巴Java开发手册》中强制线程池不允许使用 Executors 去创建
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                6,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue());

        TimeUnit.SECONDS.sleep(2);

        System.out.println(fakedHashMap.size());


    }
}
