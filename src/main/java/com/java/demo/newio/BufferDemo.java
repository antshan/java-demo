package com.java.demo.newio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        displayBufferMetaData(byteBuffer);
        byteBuffer.put((byte) 2);
        displayBufferMetaData(byteBuffer);

        byteBuffer.rewind();
        displayBufferMetaData(byteBuffer);
        System.out.println(byteBuffer.get());
        displayBufferMetaData(byteBuffer);
    }

    public static void displayBufferMetaData(Buffer byteBuffer) {
        System.out.printf("ByteBuffer [%s]'s  MetaData[position=%s,limit=%s,capacity=%s]\n",
                byteBuffer.getClass().getSimpleName(),
                byteBuffer.position(),
                byteBuffer.limit(),
                byteBuffer.capacity());
    }
}
