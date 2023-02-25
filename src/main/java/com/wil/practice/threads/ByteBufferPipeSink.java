package com.wil.practice.threads;

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.*;

public class ByteBufferPipeSink {

    public static void main(String[] args) {

        try{
            Pipe pipe = Pipe.open();
            Pipe.SinkChannel sinkChannel = pipe.sink();

            String data = "Write sth. into disk";
            ByteBuffer byteBuffer = ByteBuffer.allocate(data.getBytes().length);
            byteBuffer.clear();
            byteBuffer.put(data.getBytes());
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()) {
                sinkChannel.write(byteBuffer);
            }
            BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(5);
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Demo-pool-%d").build();
            ExecutorService pool = new ThreadPoolExecutor(2, 5, 0L, TimeUnit.MILLISECONDS, blockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());
            pool.execute(()->System.out.println(Thread.currentThread().getName()));
            ByteBufferPipeSource pipeSource = new ByteBufferPipeSource(pipe, data.getBytes().length);
            FutureTask<String> futureTask = new FutureTask<>(pipeSource);
            pool.submit(futureTask);
            String result = futureTask.get();
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        } catch (IOException | InterruptedException | ExecutionException e) {
           if(e instanceof IOException) {
               //record IOException
           } else if (e instanceof InterruptedException) {
               //record InterruptedException
           } else {
               // record ExecutionException
           }
        }
    }

}
