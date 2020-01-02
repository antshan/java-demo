package com.java.demo.collection;

public class CollectionDemo {

    public static void main(String[] args) {

        int value = Integer.MAX_VALUE;

        System.out.println(value + 1);
        System.out.println(value + 1 == Integer.MIN_VALUE);
        System.out.println(value + 2 == Integer.MIN_VALUE+1);

        //long 和 double 是非线程安全的，两个4字节， 高和低位
        //java中默认没有正整数(无符号整数)
    }
}
