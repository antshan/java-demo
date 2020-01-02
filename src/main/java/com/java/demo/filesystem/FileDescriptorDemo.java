package com.java.demo.filesystem;

import java.io.*;
import java.lang.reflect.Field;

public class FileDescriptorDemo {

    public static File file = new File("file.txt");

    public static void main(String[] args) throws Exception {
        displayDescriptorField(FileDescriptor.in);
        displayDescriptorField(FileDescriptor.out);
        displayDescriptorField(FileDescriptor.err);

        FileOutputStream fos = new FileOutputStream(FileDescriptor.out);
        fos.write("通过FD在控制台输出\n".getBytes());

        testWrite();
        testRead();
    }

    private static void testRead() throws Exception {
        FileInputStream inputStream = new FileInputStream(file);

        FileDescriptor fileDescriptor = inputStream.getFD();
        FileInputStream fileInputStream = new FileInputStream(fileDescriptor);

        System.out.println("inputStream read(): "+ (char)inputStream.read());
        System.out.println("fileInputStream read(): "+ (char)fileInputStream.read());
    }

    private static void testWrite() throws Exception {

        FileOutputStream outputStream = new FileOutputStream(file);
        
        FileDescriptor fileDescriptor = outputStream.getFD();
        
        FileOutputStream fileOutputStream = new FileOutputStream(fileDescriptor);
        
        outputStream.write("a".getBytes());
        fileOutputStream.write("b".getBytes());
        outputStream.close();
        fileOutputStream.close();
    }

    private static void displayDescriptorField(FileDescriptor fileDescriptor) throws Exception {
        Integer fd = getFieldValue(fileDescriptor,"fd");
        Long handle = getFieldValue(fileDescriptor,"handle");
        Boolean closed = getFieldValue(fileDescriptor,"closed");
        System.out.printf("FileDescriptor [ fd:%s, handle:%s, closed:%s]\n",fd,handle,closed);
    }

    private static <T> T getFieldValue(FileDescriptor fileDescriptor, String fieldName) throws Exception {
        Class clazz = FileDescriptor.class;
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(fileDescriptor);
    }
}
