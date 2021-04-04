package com.cg.others.niolearn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelLearnServer {
    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.socket().bind(new InetSocketAddress(9999));
            System.out.println("open socket channel");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                buffer.clear();
                channel.receive(buffer);
                System.out.println(buffer.remaining());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
