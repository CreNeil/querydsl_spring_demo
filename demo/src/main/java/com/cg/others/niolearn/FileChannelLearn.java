package com.cg.others.niolearn;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelLearn {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("nio/notes.txt");
        try (FileChannel fileChannel = new FileInputStream(resource.getFile()).getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int byteReads = fileChannel.read(byteBuffer);

            while (byteReads != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer.get());
                }
                byteBuffer.clear();
                byteReads = fileChannel.read(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("demo/src/main/resources/nio/notes.txt", "rw")) {
            FileChannel fileChannel1 = randomAccessFile.getChannel();
            String data = "hello, world!";
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.clear();
            byteBuffer.put(data.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                fileChannel1.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
