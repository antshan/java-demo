package com.java.demo.newio;

import com.sun.xml.internal.stream.XMLBufferListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelServerDemo {

    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();){
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.socket().bind(new InetSocketAddress(8080));

            System.out.printf("服务器地址是: %s\n", serverSocketChannel.socket().getInetAddress());
            String message = "Hello,World!";
            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());

            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null){
                    System.out.printf("接受[%s]连接...\n",socketChannel.getRemoteAddress());
                    byteBuffer.clear();
                    socketChannel.write(byteBuffer);
                }else {
                    Thread.sleep(100);
                }
            }

        }
    }
}
