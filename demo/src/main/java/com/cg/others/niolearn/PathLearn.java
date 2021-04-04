package com.cg.others.niolearn;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathLearn {
    public static void main(String[] args) {
        Path path = Paths.get("demo/src/main/resources/nio/notes.txt");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(path.toAbsolutePath());
        path = Paths.get("C:/Users/MSI/Desktop/src", "querydsl_spring_demo");
        System.out.println(path.toFile().exists());
        Path currentDir = Paths.get("demo/src/.");
        System.out.println(currentDir.toAbsolutePath());
        System.out.println(currentDir.toAbsolutePath().normalize());
        Path parentDir = Paths.get("demo/src/..");
        System.out.println(parentDir.toAbsolutePath());
        System.out.println(parentDir.toAbsolutePath().normalize());
    }
}
