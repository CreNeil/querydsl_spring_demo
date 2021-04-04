package com.cg.others.niolearn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class SocketChannelLearnServer {
    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 7070));
            //open non-blocking
//            serverSocketChannel.configureBlocking(false);
            System.out.println("open socket channel");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                System.out.println("receiving data...");
                SocketChannel socketChannel = serverSocketChannel.accept();
                // should check socketChannel is null or not
                if (Objects.nonNull(socketChannel)) {
                    int reads = socketChannel.read(buffer);
                    while (reads != -1) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            System.out.println((char) buffer.get());
                        }
                        buffer.clear();
                        reads = socketChannel.read(buffer);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
