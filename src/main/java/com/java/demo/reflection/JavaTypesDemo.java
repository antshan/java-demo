package com.java.demo.reflection;

import java.beans.ConstructorProperties;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaTypesDemo {

    @ConstructorProperties("v")
    public JavaTypesDemo(String v){

    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>(Arrays.asList(1,2,3));

        System.out.println(values.getClass().toGenericString());

        System.out.println(StringList.class.getGenericSuperclass());

        Arrays.stream(Color.values()).forEach(System.out::println);
    }

    class StringList extends ArrayList<String>{

    }
}
