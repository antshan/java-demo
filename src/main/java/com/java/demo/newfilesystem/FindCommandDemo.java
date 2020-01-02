package com.java.demo.newfilesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.java.demo.newfilesystem.FileMetsDataDemo.USER_DIR;

public class FindCommandDemo {

    public static void main(String[] args) throws IOException {
        String pattern = "D[a-zA-Z]*.java";
        FindVisitor findVisitor = new FindVisitor(pattern);
        Files.walkFileTree(Paths.get(USER_DIR),findVisitor);
        System.out.printf("Found Count: %s\n",findVisitor.getFoundCount());
    }
}
