package com.java.demo.collection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeepCloneDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        System.out.println("Shallow Clone==============");
        List<String> shallowClone = (List<String>) strings.clone();
        displayDiff(strings, shallowClone);

        System.out.println("Deep Clone Easy==============");
        List<String> clones = deepCloneEasy(strings);
        displayDiff(strings, clones);

        System.out.println("Clone By Serialization==============");
        List<String> clonesBySerialization = deepCloneSerialization(strings);
        displayDiff(strings, clonesBySerialization);

        Object[] obj = new Object[]{};
        System.out.println(22>>1);
    }

    public static void displayDiff(List<String> strings, List<String> clones){
        for (int i =0; i<strings.size(); i++){
            System.out.printf("Objects equals: %s\n", Objects.equals(strings.get(i), clones.get(i)));
            System.out.printf("Object ==: %s\n", strings.get(i) == clones.get(i));
        }
    }

    public static List<String> deepCloneEasy(List<String> strings){
        List<String> result = new ArrayList<>();
        for (String s : strings){
            result.add(new String(s));
        }
        return result;
    }

    public static List<String> deepCloneSerialization(List<String> strings) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(strings);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<String> clones = (List<String>) objectInputStream.readObject();
        return clones;
    }
}
