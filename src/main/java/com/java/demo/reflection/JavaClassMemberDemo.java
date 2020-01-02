package com.java.demo.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class JavaClassMemberDemo {

    public JavaClassMemberDemo(){}

    public static void main(String[] args) {
        List<Field> allDeclaredFields = getAllDeclaredField(ExtendData.class);
        Field[] declaredField = ExtendData.class.getDeclaredFields();
        Field[] fields = ExtendData.class.getFields();
        System.out.printf("ExtendData 类及所有父类的声明属性：%s\n", allDeclaredFields.stream().map(Field::getName).collect(Collectors.joining(",")));
        System.out.printf("ExtendData 类的所有声明属性:%s\n", Stream.of(declaredField).map(Field::getName).collect(Collectors.joining(",")));
        System.out.printf("ExtendData 类的所有 public 属性:%s\n", Stream.of(fields).map(Field::getName).collect(Collectors.joining(",")));
    }

    private static List<Field> getAllDeclaredField(Class<?> type) {
        if (type.equals(Object.class)){
            return Collections.emptyList();
        }
        List<Field> allDeclaredFields = new LinkedList<>();
        Field[] fields = type.getDeclaredFields();
        allDeclaredFields.addAll(asList(fields));
        Class<?> superClass = type.getSuperclass();
        while (true){
            if (superClass == null || superClass.equals(Object.class)){
                break;
            }
            allDeclaredFields.addAll(getAllDeclaredField(superClass));
            superClass = superClass.getSuperclass();
        }
        return allDeclaredFields;
    }

    class Data{
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        private int value;
    }

    class ExtendData extends Data{
        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }
}
