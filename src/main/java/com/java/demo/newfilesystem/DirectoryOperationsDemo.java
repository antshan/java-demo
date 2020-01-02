package com.java.demo.newfilesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DirectoryOperationsDemo {

    public static void main(String[] args) {
        String classpath = System.getProperty("user.dir");
        System.out.println(classpath);
        Stream.of(classpath.split(File.pathSeparator))
                .map(Paths::get)
                .filter(Files::isDirectory)
                .filter(Files::isWritable)
                .filter(Files::isReadable)
                .map(Path::toString)
                .map(dir-> Paths.get(dir,"parent-dir"))
                .forEach(newDir->{
                    try {
                        //createDirectories���Դ����༶�����ڵ�Ŀ¼��createDirectory�򲻿���
                        //����D:\test���ڣ�D:\test\1�����ڣ�ʹ��createDirectories���Դ���D:\test\1\2
                        //createDirectoryֻ�ܴ���D:\test\1����Ŀ¼
                        Path p = Files.createDirectories(newDir);
                        System.out.printf("��Ŀ¼[%s]�ѱ�����!\n",p);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
