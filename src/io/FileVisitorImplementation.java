package io;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

//NOTE: "FileVisitor" interface can be used to traverse a file system using any of the tree traversal methods
//NOTE: Any class that implements "FileVisitor" should be used as follows
//Files.walkFileTree(Paths.get("D:\\Money Heist"), new FileVisitorImplementation());
public class FileVisitorImplementation implements FileVisitor<Path> {
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException ioe) {
        System.out.println("Couldn't visit file: " + file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path directory, IOException ioe) {
        return FileVisitResult.CONTINUE;
    }
}