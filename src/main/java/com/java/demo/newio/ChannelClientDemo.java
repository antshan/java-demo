package com.java.demo.newio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelClientDemo {

    public static void main(String[] args) throws Exception {
        try (SocketChannel socketChannel = SocketChannel.open();){
            socketChannel.configureBlocking(false);

            socketChannel.connect(new InetSocketAddress(8080));
            ByteBuffer byteBuffer = ByteBuffer.allocate(8);
            while (!socketChannel.finishConnect()){
                System.out.println("µÈ´ýÁ¬½Ó...");
            }
            while (socketChannel.read(byteBuffer)!=-1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.print((char) byteBuffer.get());
                }
                byteBuffer.clear();
            }
        }
    }
}
