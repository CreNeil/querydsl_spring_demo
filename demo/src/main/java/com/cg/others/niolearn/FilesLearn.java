package com.cg.others.niolearn;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesLearn {

    private static void deleteRecursively(Path rootPath) {
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("delete file: " + file.toString());
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    System.out.println("delete dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("demo/src/main/resources/nio/notes.txt");
        boolean pathExists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(pathExists);
        try {
            Path newDir = Files.createDirectory(
                    Paths.get("demo/src/main/resources/nio/new"));
            Path newFile = newDir.resolve("a.txt");
            Files.copy(path, newFile, StandardCopyOption.REPLACE_EXISTING);
            deleteRecursively(newDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
