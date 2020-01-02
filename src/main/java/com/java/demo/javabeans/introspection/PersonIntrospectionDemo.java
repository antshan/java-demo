package com.java.demo.javabeans.introspection;

import com.java.demo.javabeans.properties.Person;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;

public class PersonIntrospectionDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();

        System.out.println("Person ��� BeanDescriptor: "+ beanDescriptor);

        System.out.println("==========");

        Arrays.stream(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println("Person ��� PropertyDescriptor: "+propertyDescriptor);
                });

        System.out.println("==========");

        Arrays.stream(beanInfo.getMethodDescriptors())
                .forEach(methodDescriptor -> {
                    System.out.println("Person ��� MethodDescriptor: "+ methodDescriptor);
                });

        System.out.println("==========");

        Arrays.stream(beanInfo.getEventSetDescriptors())
                .forEach(eventSetDescriptor -> {
                    System.out.println("Person��� EventSetDescriptor: "+eventSetDescriptor);
                });
    }
}
