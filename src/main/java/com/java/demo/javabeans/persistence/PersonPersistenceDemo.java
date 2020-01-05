package com.java.demo.javabeans.persistence;

import com.java.demo.javabeans.properties.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;

public class PersonPersistenceDemo {

    public static void main(String[] args) throws Exception {
        Person person = new Person();

        person.setId(1L);
        person.setName("ะกร๗");
        person.setAge(18);
        person.setUpdateTime(new Date());

        String classPath = findClassPath();

        FileOutputStream outputStream = new FileOutputStream(new File(classPath,"person.temp"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(person);

    }

    private static String findClassPath(){
        String classPath = System.getProperty("java.class.path");
        return Arrays.stream(classPath.split(File.pathSeparator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canRead)
                .filter(File::canWrite)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }
}
