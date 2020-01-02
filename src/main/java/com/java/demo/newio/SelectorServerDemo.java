package com.java.demo.newio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServerDemo {
    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.socket().bind(new InetSocketAddress(8090));

            System.out.printf("服务器地址是:%s\n",serverSocketChannel.socket().getLocalSocketAddress());

            while (true){
                Selector selector = Selector.open();

                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                int i = selector.select();
                if (i == 0){
                    continue;
                }
                Set<SelectionKey> keys =  selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = channel.accept();
                        if (socketChannel == null){
                            continue;
                        }
                        System.out.printf("地址:%s\n",socketChannel.getRemoteAddress());
                        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
                        byteBuffer.putLong(System.currentTimeMillis());
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()){
                            socketChannel.write(byteBuffer);
                        }
                        byteBuffer.clear();
                    }
                    iterator.remove();
                }
            }
        }
    }
}
