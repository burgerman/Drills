package com.wil.practice.threads;

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {

        BlockingQueue<Runnable> bq = new LinkedBlockingQueue<>(5);
        ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("Demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(1, 5, 0L, TimeUnit.MILLISECONDS, bq, tf, new ThreadPoolExecutor.AbortPolicy());
        pool.execute(()->System.out.println(Thread.currentThread().getName()));
        pool.shutdown();


    }

}
