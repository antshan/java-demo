package com.java.demo.reflection;

import java.lang.reflect.Modifier;
import java.util.AbstractList;
import java.util.Collection;

public class ClassObjectDemo {

    public static void main(String[] args) {

        System.out.println(!Modifier.isAbstract(String.class.getModifiers()));

        System.out.println(Modifier.isAbstract(AbstractList.class.getModifiers()));

        System.out.println(Modifier.isInterface(Collection.class.getModifiers()));

        System.out.println(Color.class.isEnum());

        System.out.println(Override.class.isAnnotation());

        System.out.println(int.class.isPrimitive());

        System.out.println(float[].class.isArray());
    }
}
