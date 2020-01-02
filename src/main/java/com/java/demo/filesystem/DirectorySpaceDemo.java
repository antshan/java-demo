package com.java.demo.filesystem;

import java.io.File;
import java.io.FileFilter;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 求某个文件夹下文件大小之和
 */
public class DirectorySpaceDemo {

    private final File directory;

    private Predicate<File> filter;

    public DirectorySpaceDemo(File directory, Predicate<File> filter){
        this.directory = directory;
        this.filter = filter;
    }

    public long getSpace(){
        if (directory.isFile()){
            return directory.length();
        }else if (directory.isDirectory()){
            long space = 0L;
            File[] subDirectory = directory.listFiles();
            //获取当前目录文件的文件大小和
            space+= Stream.of(subDirectory)
                    .filter(File::isFile)
                    .filter(filter)
                    .map(File::length)
                    .reduce(Long::sum)
                    .orElse(0L);

            //获取当前目录所有文件夹下文件大小之和，采用递归
            space += Stream.of(subDirectory)
                    .filter(File::isDirectory)
                    .map(file->new DirectorySpaceDemo(file,filter))
                    .map(DirectorySpaceDemo::getSpace)
                    .reduce(Long::sum)
                    .orElse(0L);

            return space;
        }

        return -1L;
    }

    public static void main(String[] args) {
        System.out.println(new DirectorySpaceDemo(new File(System.getProperty("user.dir")), file->file.getName().endsWith(".xml")).getSpace()/1024);
        System.out.println(new DirectorySpaceDemo(new File(System.getProperty("user.home")+"\\logs"), file->file.getName().endsWith(".log")).getSpace()/1024);
    }
}
