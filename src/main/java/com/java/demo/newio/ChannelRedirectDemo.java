package com.java.demo.newio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelRedirectDemo {

    public static void main(String[] args) throws IOException {
        try(ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out)){
            copy(readableByteChannel, writableByteChannel);
        }

    }

    private static void copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 * 1024);
        while (readableByteChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            if (byteBuffer.hasRemaining()){
                writableByteChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
    }
}
