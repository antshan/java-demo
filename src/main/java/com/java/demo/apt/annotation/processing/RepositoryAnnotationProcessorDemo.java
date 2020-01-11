package com.java.demo.apt.annotation.processing;

import com.java.demo.apt.compiler.Compiler;

import java.io.File;
import java.io.IOException;

import static com.java.demo.apt.compiler.CompilerDemo.getClassOutputDirectory;

public class RepositoryAnnotationProcessorDemo {

    public static void main(String[] args) throws IOException {
        File sourceDir = new File(System.getProperty("user.dir"),"/src/main/java/");
        File targetDir = getClassOutputDirectory();

        Compiler compiler = new Compiler(sourceDir,targetDir);
        compiler.setProcessors(new RepositoryAnnotationProcessor());

        compiler.compile("com.java.demo.apt.annotation.example.CrudRepository",
                "com.java.demo.apt.annotation.example.Repository",
                "com.java.demo.apt.annotation.example.User",
                "com.java.demo.apt.annotation.example.UserRepository");
    }
}
