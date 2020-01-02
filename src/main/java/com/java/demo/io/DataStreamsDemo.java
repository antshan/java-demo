package com.java.demo.io;

import java.io.DataOutputStream;
import java.io.IOException;

public class DataStreamsDemo {


    public static void main(String[] args) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(System.out);
        dataOutputStream.write(12);
        dataOutputStream.writeByte(65);
        dataOutputStream.writeChar(99);
        dataOutputStream.writeUTF("Hello,World!");
        dataOutputStream.flush();
    }
}
