package com.java.demo.io;

import java.io.Console;
import java.io.IOException;

public class CommandLineDemo {


    public static void main(String[] args) throws IOException {
        //Windows下运行时需要放在src/main/java的根目录下(代码中不能包含package)
        // 并且要在控制台下运行，不能在IDEA下运行
        //运行时先编译 javac CommandLineDemo.java
        //再运行 java CommandLineDemo
        System.in.read();
        Console console = System.console();

        while (true){

            String s = console.readLine();
            if ("exit".equals(s)){
                break;
            }else {
                System.out.println(s);
            }
        }
    }

}
