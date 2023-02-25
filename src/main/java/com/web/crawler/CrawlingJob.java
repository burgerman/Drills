package com.web.crawler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrawlingJob implements Runnable{

    private String url;
    private String outputPath;

    public CrawlingJob(String url, String outputPath) {
        this.url = url;
        this.outputPath = outputPath;
    }

    @Override
    public void run(){
        Map<String, JSONObject> postsMap = new HashMap<>(16384);
        Map<String, JSONObject> zeroReplyPostsMap = new HashMap<>(16384);
        Crawler crawler = new Crawler(postsMap, zeroReplyPostsMap);
        crawler.postUriCrawler(url);
        crawler.repliesFinder(outputPath);
        crawler.zeroReplyFinder(outputPath);
    }
}
