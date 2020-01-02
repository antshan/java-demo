package com.java.demo.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectStreamsDemo {
    public static void main(String[] args) throws Exception{
        List<String> strings = new ArrayList<>(Arrays.asList("Hello","SF","World","Tencent"));

        File file = new File("strings.txt");
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(strings);
            outputStream.flush();
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
            List<String> copyList = (List<String>) inputStream.readObject();
            System.out.println(copyList);
        }

    }
}
