package com.wil.practice.threads;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.Callable;

public class ByteBufferPipeSource implements Callable<String> {

    private Pipe pipeAttr;
    private int cap;

    public ByteBufferPipeSource(Pipe pipeAttr, int cap) {
        this.pipeAttr = pipeAttr;
        this.cap = cap;
    }


    @Override
    public String call() throws Exception {
        Pipe.SourceChannel sourceChannel = pipeAttr.source();

        // If the allocated size < source size, it would cause BufferUnderflowException
        ByteBuffer buffer = ByteBuffer.allocate(cap);
        StringBuilder sb = new StringBuilder();
        while(sourceChannel.read(buffer)>0) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                char ch = (char) buffer.get();
                sb.append(ch);
            }
        }
        buffer.clear();
        System.out.println(Thread.currentThread().getName());
        return sb.toString();
    }
}
