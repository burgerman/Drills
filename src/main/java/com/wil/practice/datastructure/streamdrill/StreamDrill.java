package com.wil.practice.datastructure.streamdrill;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StreamDrill {
    public static JSONObject fetchJsonData(Map<String, String> mapData) {
        JSONObject json;
        if(mapData.entrySet().size()>0) {
             json = new JSONObject(mapData.entrySet().stream()
                            .filter(e -> StringUtils.containsIgnoreCase(e.getKey(), "corr_id_") || StringUtils.containsIgnoreCase(e.getKey(), "rucs_corr_id"))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
             return json;
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("CORR_ID_", "adqwe1234");
        map.put("rucs_corr_id", "adqwe1234");
        map.put("account_id", "1234xl");
        map.put("customer_id", "adqwe");
        map.put("trans_id", "7788453548946581c");
        System.out.println( fetchJsonData(map));
        map.forEach((key,value)->{
            CompletableFuture.supplyAsync(()-> new StringJoiner("_").add(value).add("id").toString())
                    .thenApply(res->map.put(key, res));});
    }

}
