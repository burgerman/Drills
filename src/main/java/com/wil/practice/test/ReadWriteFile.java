package com.wil.practice.test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadWriteFile {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of the read file");
        while(sc.hasNext()) {
            String readPath = sc.nextLine();
            directRead(readPath);
//            List<String> list = doRead(readPath);
//            System.out.println("Enter the path of the output file");
//            String writePath = sc.nextLine();
//            doWrite(writePath, list);
            break;
        }

    }

    private static List<String> doRead (String path) {
        if(path!=null && path.length()>0) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                List<String> lines = br.lines().collect(Collectors.toList());
                return lines;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void doWrite(String path, List<String> source) {
        if(source!=null && source.size()>0 && path!=null && path.length()>0) {
            Iterator<String> iterator = source.iterator();
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
                while (iterator.hasNext()) {
                    bw.write(iterator.next());
                    bw.newLine();
                }
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * JAVA NIO
     * Zero-copy: reading file
     * @param path
     */
    private static void directRead(String path) {
        // DirectByteBuffer - Using the direct memory (zero-copy between user status and core status)
        try (RandomAccessFile file = new RandomAccessFile(path, "r")){
            try(FileChannel channel = file.getChannel()) {
                ByteBuffer bb = ByteBuffer.allocateDirect(1024);
                int bytes = channel.read(bb);
                System.out.println(bytes);
                bb.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
