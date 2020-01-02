package com.java.demo.newio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class SelectorClientDemo {
    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()){
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(8090));
            while(!socketChannel.finishConnect()){
                System.out.println("等待连接中...");
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(8);
            while (socketChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println(new Date(byteBuffer.getLong()));
                }
                byteBuffer.clear();
            }
        }
    }
}
