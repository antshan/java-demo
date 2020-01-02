package com.java.demo.io;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class ScanningDemo {

    public static void main(String[] args) throws IOException {

        StringReader stringReader = new StringReader("a b 3 4 5");

        //默认分隔符 \n
        Scanner scanner = new Scanner(stringReader);
        //修改分隔符
        scanner.useDelimiter("\\t");

        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }

        scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }
}
