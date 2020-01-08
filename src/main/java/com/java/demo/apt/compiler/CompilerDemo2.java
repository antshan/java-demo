package com.java.demo.apt.compiler;

import com.java.demo.collection.CollectionDemo;
import com.java.demo.filesystem.DirCommandDemo;
import com.java.demo.io.InputStreamToReaderDemo;
import com.java.demo.javabeans.introspection.PersonIntrospectionDemo;
import com.java.demo.newio.SelectorServerDemo;
import com.java.demo.reflection.JavaClassMemberDemo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CompilerDemo2 {

    public static void main(String[] args) throws IOException {
        File sourceDirectory = new File(System.getProperty("user.dir"),"/src/main/java");

        Compiler compiler = new Compiler(sourceDirectory,getTargetDirectory());

        compiler.compile(JavaClassMemberDemo.class.getName(),
                SelectorServerDemo.class.getName(),
                PersonIntrospectionDemo.class.getName(),
                InputStreamToReaderDemo.class.getName(),
                CollectionDemo.class.getName());
    }

    public static File getTargetDirectory(){
        File classPath = new File(findClassPath());
        File targetDirectory = classPath.getParentFile();
        File classOutputDirectory = new File(targetDirectory,"new-classes");
        classOutputDirectory.mkdir();
        return classOutputDirectory;
    }

    public static String  findClassPath(){
        String classPath = System.getProperty("java.class.path");
        return Arrays.stream(classPath.split(File.pathSeparator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canRead)
                .filter(File::canWrite)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }
}
