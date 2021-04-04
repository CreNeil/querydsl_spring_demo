package com.cg.others.niolearn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsyncFileLearn {

    private static void write1(AsynchronousFileChannel fileChannel, ByteBuffer buffer, long position) throws IOException {
        Path path = Paths.get("demo/src/main/resources/nio/test-write1.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        buffer.put("test data".getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();

        while (!operation.isDone()) ;

        System.out.println("Write done");
    }

    private static void write2(AsynchronousFileChannel fileChannel, ByteBuffer buffer, long position) throws IOException {
        Path path = Paths.get("demo/src/main/resources/nio/test-write2.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        buffer.put("test data".getBytes());
        buffer.flip();

        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Write failed");
                exc.printStackTrace();
            }
        });
    }

    private static void read1(AsynchronousFileChannel fileChannel, ByteBuffer buffer, long position) {
        Future<Integer> operation = fileChannel.read(buffer, position);

        while (!operation.isDone()) {
            System.out.println("not ok yet");
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();

    }

    private static void read2(AsynchronousFileChannel fileChannel, ByteBuffer buffer, long position) {
        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("demo/src/main/resources/nio/notes.txt");
        System.out.println("==============================read==============================");
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        System.out.println("=============================read 1==============================");
        read1(fileChannel, buffer, position);

        System.out.println("==============================read 2==============================");
        read2(fileChannel, buffer, position);
        Thread.sleep(100);

        System.out.println("==============================write==============================");
        AsynchronousFileChannel fileChannelW =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("==============================write 1==============================");
        write1(fileChannelW, buffer, position);
        System.out.println("==============================write 2==============================");
        write2(fileChannelW, buffer, position);
    }
}
