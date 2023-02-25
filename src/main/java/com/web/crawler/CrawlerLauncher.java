package com.web.crawler;

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class CrawlerLauncher {

    public static void main(String[] args) {
        String url = "https://www.paypal-community.com/t5/PayPal-Community/ct-p/en";

        String outputPath = "D:\\SpringBootPractice\\src\\main\\resources\\outputDir";
        List<String> parentPageLinks = Crawler.urlCrawler(url);
        List<String> childPageLinks = new LinkedList<>();
        parentPageLinks.forEach(lk->childPageLinks.addAll(Crawler.urlCrawler(lk)));
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(childPageLinks.size());
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(8, childPageLinks.size(), 20L, TimeUnit.SECONDS, blockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());
        Map<String, String> dict = new HashMap<>(32, 1.0f);
        for(String childPageLink : childPageLinks) {
            int begin = childPageLink.lastIndexOf("t5/")+3;
            String folderName = childPageLink.substring(begin, childPageLink.lastIndexOf("/"))
                    .replaceAll("/\\w+", "")
                    .replaceAll("(-p)$", "");
            File outputFolder = FileProcessor.createFolder(folderName, Paths.get(outputPath));
            dict.put(childPageLink, outputFolder.getAbsolutePath());
        }
        dict.entrySet().parallelStream().forEach(e->pool.execute(new CrawlingJob(e.getKey(), e.getValue())));
        pool.shutdown();
    }
}
