package com.wil.practice.asynchronous;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureDrill {

    private static final Logger log  = LoggerFactory.getLogger(CompletableFutureDrill.class);

    public List<String> asyncProcessingTextSearching(Path path, String keyWord) {
        String pathStr = path.toString();
        if(Files.exists(path)) {
            return CompletableFuture.supplyAsync(() -> {
                List<String> lines = new ArrayList<>(2048);
                try (BufferedReader br = new BufferedReader(new FileReader(pathStr))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (StringUtils.containsIgnoreCase(line, keyWord)) {
                            lines.add(line);
                        }
                    }
                } catch (IOException e) {
                    log.error("IO Error", e);
                }
                return lines.size() > 0 ? lines : null;
            }).exceptionally(ex->{
                log.error("Recording error", ex);
                return null;
            }).thenApplyAsync(strings->strings.parallelStream()
                    .map(this::dataNormalize)
                    .collect(Collectors.toList()))

                    .whenComplete((res, ex) -> {
                        if(ex!=null) {
                            log.error("Pipeline execution failed", ex);
                        } else {
                            log.info("The num of records: "+res.size(), res);
                        }
                    }).join();
        }
        return null;
    }


    private static final String[] illegalCharacters = new String[]{"@","$","&","#"};
    private static final String[] replacements = new String[]{"","","",""};
    private String dataNormalize(String txt) {
        return StringUtils.replaceEach(txt, illegalCharacters, replacements).strip();
    }


    public static void main(String[] args) {
        String keyWord = "MyKeyWord";
        Path path = Path.of("/Users/wilfried/Downloads/async_testing.txt");
        CompletableFutureDrill drill = new CompletableFutureDrill();
        try{
            drill.asyncProcessingTextSearching(path, keyWord).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
