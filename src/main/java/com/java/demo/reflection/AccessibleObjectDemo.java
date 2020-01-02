package com.java.demo.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccessibleObjectDemo {
    public static void main(String[] args) throws Exception {
        String s = "Hello,World";

        execute(()->s.toString());

        Method method = String.class.getMethod("toString");

        execute(()->{
            try {
                method.invoke(s);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        execute(()->{
            method.setAccessible(true);
            try {
                method.invoke(s);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        execute(()->{
            method.setAccessible(false);
            try {
                method.invoke(s);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

    private static void execute(Runnable runnable){
        int times = 10000000;
        long startTime = System.currentTimeMillis();
        for (int i=0;i<times;i++){
            runnable.run();
        }
        long costTime = System.currentTimeMillis()-startTime;
        System.out.printf("执行 %d 次，消耗时间 %d ms\n", times,costTime);
    }
}
