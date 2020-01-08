package com.java.demo.apt.compiler;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class CompilerDemo {

    public static void main(String[] args) throws Exception {

       JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
       StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null,null,null);

       Class<?> targetClass = CompilerDemo.class;

       String sourceFileRelativePath = targetClass.getName().replace(".","/").concat(".java");
       String sourceFilePath = System.getProperty("user.dir")+"/src/main/java/"+sourceFileRelativePath;

       File sourceFile = new File(sourceFilePath);
       javaFileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(getClassOutputDirectory()));

       Iterable<? extends JavaFileObject> compilationUnits = javaFileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));

       JavaCompiler.CompilationTask task = javaCompiler.getTask(new OutputStreamWriter(System.out),
               javaFileManager,null,null,null,compilationUnits);

       task.call();
    }

    public static File getClassOutputDirectory(){
        File classPath = new File(findClassPath());
        File targetDirectory = classPath.getParentFile();
        File classOutputDirectory = new File(targetDirectory, "new-classes");
        classOutputDirectory.mkdir();
        return classOutputDirectory;
    }

    public static String findClassPath(){
        String classPath = System.getProperty("java.class.path");
        return Arrays.stream(classPath.split(File.pathSeparator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canWrite)
                .filter(File::canRead)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }


}
