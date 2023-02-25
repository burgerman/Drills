package com.wil.practice.algo.dfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;

public class FileSearchDFS {
    private static final Logger log = LoggerFactory.getLogger(FileSearchDFS.class);

    public static void searchFileUsingDFS(Path rootDir, String targetFile) {
        try{
            Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException{
                    if(file.getFileName().toString().equals(targetFile)) {
                        System.out.println("File Found: " +file.toAbsolutePath());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            log.error("IO Exception",e);
        }
    }

    public static void main(String[] args) {

    }

}
