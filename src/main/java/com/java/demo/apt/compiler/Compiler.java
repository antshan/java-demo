package com.java.demo.apt.compiler;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Compiler {

    private File sourceDirectory;

    private File targetDirectory;

    private JavaCompiler javaCompiler;

    public Compiler(File sourceDirectory,File targetDirectory){
        this.sourceDirectory = sourceDirectory;
        this.targetDirectory = targetDirectory;
        this.javaCompiler = ToolProvider.getSystemJavaCompiler();
    }

    public void compile(String... classNames) throws IOException {
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null,null,null);

        javaFileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(targetDirectory));

        List<File> sourceFiles = Arrays.stream(classNames).map(name-> name.replace(".","/").concat(".java"))
                .map(name -> sourceDirectory+ File.separator+name)
                .map(File::new)
                .collect(Collectors.toList());

        Iterable<? extends JavaFileObject> javaFileObjects = javaFileManager.getJavaFileObjectsFromFiles(sourceFiles);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(new OutputStreamWriter(System.out),javaFileManager,null,null,null,javaFileObjects);

        task.call();
    }
}
