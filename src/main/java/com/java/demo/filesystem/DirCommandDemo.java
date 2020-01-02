package com.java.demo.filesystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class DirCommandDemo {

    private final File rootDirectory;

    public DirCommandDemo(File rootDirectory){
        this.rootDirectory = rootDirectory;
    }

    public void excute(){
        Stream.of(rootDirectory.listFiles())
                .map(file -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    Long lastModified = file.lastModified();
                    String dir = file.isDirectory()?"<DIR>":"      ";
                    String length = file.isDirectory()?"   ":String.valueOf(file.length());
                    String fileName = file.getName();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = simpleDateFormat.format(new Date(lastModified));
                    return stringBuilder.append(dateString)
                            .append(" ")
                            .append(dir)
                            .append(" ")
                            .append(length)
                            .append(" ")
                            .append(fileName)
                            .toString();

                }).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new DirCommandDemo(new File(System.getProperty("user.home"))).excute();
        new DirCommandDemo(new File(System.getProperty("user.dir"))).excute();

        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
    }
}
