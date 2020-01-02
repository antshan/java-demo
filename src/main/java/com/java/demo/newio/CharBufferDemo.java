package com.java.demo.newio;

import java.nio.CharBuffer;

import static com.java.demo.newio.BufferDemo.displayBufferMetaData;

public class CharBufferDemo {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(8);

        String message = "Hello";

        for (char c : message.toCharArray()){
            charBuffer.put(c);
        }

        displayBufferMetaData(charBuffer);

        charBuffer.flip();

        displayBufferMetaData(charBuffer);

        while (charBuffer.hasRemaining()){
            System.out.print(charBuffer.get());
        }
        System.out.println();

        displayBufferMetaData(charBuffer);

        charBuffer.clear();

        displayBufferMetaData(charBuffer);
    }
}
