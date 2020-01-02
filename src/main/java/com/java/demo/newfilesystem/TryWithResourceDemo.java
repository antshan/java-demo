package com.java.demo.newfilesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.java.demo.newfilesystem.FileMetsDataDemo.USER_DIR;

public class TryWithResourceDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(USER_DIR, "pom.xml");
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(path, charset)){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }

        //try-resource写法可省略这段代码
//        finally {
//            if (reader != null){
//                reader.close();
//            }
//        }
    }
}
