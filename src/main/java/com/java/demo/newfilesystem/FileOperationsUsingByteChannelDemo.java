package com.java.demo.newfilesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.java.demo.newfilesystem.FileMetsDataDemo.USER_DIR;

public class FileOperationsUsingByteChannelDemo {

    public static void main(String[] args) throws IOException {

        Path pomXmlPath = Paths.get(USER_DIR, "pom.xml");
        Path pomXmlCopyPath = Paths.get(USER_DIR, "pom-copy.xml");
        try (SeekableByteChannel sourceByteChannel = Files.newByteChannel(pomXmlPath);
             SeekableByteChannel targetByteChannel = Files.newByteChannel(pomXmlCopyPath, StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE)){

            ByteBuffer byteBuffer = ByteBuffer.allocate(8);
            while (sourceByteChannel.read(byteBuffer)>0){
                byteBuffer.clear();
                targetByteChannel.write(byteBuffer);
                byteBuffer.flip();
            }
        }
    }
}
