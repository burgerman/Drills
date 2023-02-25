package com.wil.practice.http;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

/**
 * post json data to the remote server
 */
public class HttpPostTest implements Callable<String> {

    String uri;
    String jsonPath;

    public HttpPostTest(String uri, String jsonPath) {
        this.uri = uri;
        this.jsonPath = jsonPath;
    }

    @Override
    public String call() throws Exception {
        return doPost();
    }

    public String doPost() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        String result = "";
        CloseableHttpResponse response = null;
        File file = new File(jsonPath);
        try{
            StringEntity s = new StringEntity(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
            s.setContentType(MediaType.APPLICATION_JSON);
            httpPost.setEntity(s);
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                 result = EntityUtils.toString(entity);

                System.out.println(result);
            }
            response.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return result;
    }

}
