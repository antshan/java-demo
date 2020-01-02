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
                        //createDirectories可以创建多级不存在的目录，createDirectory则不可以
                        //例如D:\test存在，D:\test\1不存在，使用createDirectories可以创建D:\test\1\2
                        //createDirectory只能创建D:\test\1这种目录
                        Path p = Files.createDirectories(newDir);
                        System.out.printf("新目录[%s]已被创建!\n",p);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
