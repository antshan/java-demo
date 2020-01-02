package com.java.demo.io;

import java.util.Formatter;

public class FormatterDemo {
    public static void main(String[] args) {
        System.out.printf("Hello,%s\n","World!");
        System.out.println(String.format("Hello,%s","World!"));
        System.out.println(new Formatter().format("Hello,%s","World!"));
    }
}
