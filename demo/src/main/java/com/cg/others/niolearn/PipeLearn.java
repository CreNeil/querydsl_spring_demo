package com.cg.others.niolearn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeLearn {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        try (Pipe.SinkChannel sinkChannel = pipe.sink()) {
            String newData = "This String has 24 bytes";

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            System.out.println(newData.getBytes().length);

            buf.flip();

            while (buf.hasRemaining()) {
                sinkChannel.write(buf);
            }
            buf.clear();
        }

        try (Pipe.SourceChannel sourceChannel = pipe.source()) {
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = sourceChannel.read(buf);
            System.out.println(bytesRead);
        }
        System.out.println(pipe);

    }
}
