package com.cg.others.niolearn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelLearnClient {
    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.socket().bind(new InetSocketAddress(9998));
            String newData = "New String to write to file..sdasdfgsdfgffsdasdfadfasdf" +
                    "'sa."
                    + System.currentTimeMillis();

            ByteBuffer buf = ByteBuffer.allocate(128);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            int bytesSent = channel.send(buf, new InetSocketAddress("localhost", 9999));
            System.out.println(bytesSent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
