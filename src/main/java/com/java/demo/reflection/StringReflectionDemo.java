package com.java.demo.reflection;

import java.lang.reflect.Field;

public class StringReflectionDemo {
    public static void main(String[] args) throws Exception {
        String a = "Hello";
        String b = "World";

        System.out.println("修改前的值:"+a);
        Class aClazz = a.getClass();
        Field valueField = aClazz.getDeclaredField("value");
        valueField.setAccessible(true);

        valueField.set(a, b.toCharArray());

        System.out.println("修改后的值:"+a);
    }
}
