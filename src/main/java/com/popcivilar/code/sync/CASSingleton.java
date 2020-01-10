package com.popcivilar.code.sync;

import java.util.concurrent.atomic.AtomicReference;

public class CASSingleton {
    private static final AtomicReference<CASSingleton> reference = new AtomicReference<>();
    public static CASSingleton getInstance(){
        for(;;){
            CASSingleton singleton = reference.get();
            if(singleton != null){
                return singleton;
            }
            singleton = new CASSingleton();
            if (reference.compareAndSet(null,singleton)) {
                return singleton;
            }
        }
    }
}

