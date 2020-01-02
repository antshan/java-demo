package com.java.demo.collection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ListAndSetDemo {

    public static void main(String[] args) {

        //HashSet 并不能保证有序
        Set<String> values = new HashSet<>();
        //有些场景会让人产生误导
        //字母场景
        values.add("b");
        values.add("a");
        values.add("c");
        values.forEach(System.out::println);
        values.clear();

        //数字场景
        values.add("2");
        values.add("1");
        values.add("3");
        values.forEach(System.out::println);

        //以上用ASCII 码存储
        //HashSet 和 HashMap采用对象的hashcode
        // String 对象的hashcode 由char[]构建
        /**
         * public int hashCode() {
         *         int h = hash;
         *         if (h == 0 && value.length > 0) {
         *             char val[] = value;
         *
         *             for (int i = 0; i < value.length; i++) {
         *                 h = 31 * h + val[i];
         *             }
         *             hash = h;
         *         }
         *         return h;
         *     }
         */

        //Java中char占两个字节 相当于int 4字节

        List<String> linkedString = new LinkedList<>();
        linkedString.add("a");
        linkedString.add("b");
        linkedString.add("c");

        linkedString.get(0);

    }
}
