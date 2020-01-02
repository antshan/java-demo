package com.java.demo.newfilesystem;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FindVisitor extends SimpleFileVisitor<Path> {

    private final PathMatcher pathMatcher;

    private int foundCount;

    public FindVisitor(String pattern){
        this.pathMatcher = FileSystems.getDefault().getPathMatcher("glob:"+pattern);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        matchFile(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        matchFile(file);
        return FileVisitResult.CONTINUE;
    }

    private void matchFile(Path file){
        Path name = file.getFileName();
        if (name != null && pathMatcher.matches(name) ){
            foundCount++;
            System.out.printf("Found File: %s\n",file);
        }

    }

    public int getFoundCount() {
        return foundCount;
    }
}
