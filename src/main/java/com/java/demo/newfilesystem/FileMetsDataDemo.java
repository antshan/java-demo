package com.java.demo.newfilesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

public class FileMetsDataDemo {

    public static String USER_DIR = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(USER_DIR);
        System.out.println(path);

        UserPrincipal userPrincipal = Files.getOwner(path);
        System.out.printf("Path[%s]'s owner is %s\n",path,userPrincipal);
    }
}
