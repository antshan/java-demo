package com.java.demo.newfilesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.java.demo.newfilesystem.FileMetsDataDemo.USER_DIR;

public class FileOperationsDemo {

    public static void main(String[] args) throws IOException {
        displayFileExists();
        displayFileAccessibility();
        displayFileEquals();
    }

    private static void displayFileEquals() throws IOException {
        Path path = Paths.get(USER_DIR);
        Path path2 = Paths.get(USER_DIR);
        System.out.println("path == path2 ? "+Files.isSameFile(path,path2));
    }

    private static void displayFileAccessibility() {
        Path path = Paths.get(USER_DIR);
        System.out.printf("${user.dir}: %s, Readable=%s, Writable=%s, Executable=%s\n",
                path,
                Files.isReadable(path),
                Files.isWritable(path),
                Files.isExecutable(path));
    }

    private static void displayFileExists() {
        Path path = Paths.get(USER_DIR);
        System.out.printf("${user.dir}: %s is exists %s\n",path, Files.exists(path));
    }
}
