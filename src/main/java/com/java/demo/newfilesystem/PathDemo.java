package com.java.demo.newfilesystem;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.java.demo.newfilesystem.FileMetsDataDemo.USER_DIR;

public class PathDemo {

    public static void main(String[] args) {

        displayPathInfo();
        displayPathNormalize();
        displayPathConversation();

    }

    private static void displayPathConversation() {
        Path pathFromLocation = Paths.get(USER_DIR);
        File file = new File(USER_DIR);
        Path pathFromFile = file.toPath();
        Path pathFromURI = Paths.get(file.toURI());

        System.out.printf("pathFromLocation: %s\n", pathFromLocation);
        System.out.printf("pathFromFile: %s\n", pathFromFile);
        System.out.printf("pathFromURI: %s\n", pathFromURI);

        System.out.println("pathFromFile == pathFromLocation ? "+ pathFromFile.equals(pathFromLocation));
        System.out.println("pathFromURI == pathFromLocation ? "+ pathFromURI.equals(pathFromLocation));
        System.out.println("pathFromURI == pathFromFile ? "+ pathFromURI.equals(pathFromFile));

    }

    private static void displayPathNormalize() {

        Path path = Paths.get("D:\\repo\\repository\\..");

        System.out.println(path.normalize());
    }

    private static void displayPathInfo() {
        Path path = Paths.get(USER_DIR);
        System.out.printf("toString: %s\n",path);
        System.out.printf("pathName: %s\n", path.getFileName());

        int nameCount = path.getNameCount();
        for (int i =0;i<nameCount;i++){
            System.out.printf("getName(%s): %s\n",i,path.getName(i));
        }

        for (Path p : path){
            System.out.printf("For Each-%s\n",p);
        }

        System.out.printf("getParent: %s\n", path.getParent());
        System.out.printf("getRoot: %s\n", path.getRoot());

        FileSystems.getDefault().getRootDirectories().forEach(System.out::println);
    }
}
