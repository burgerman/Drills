package com.wil.practice.test;

import com.wil.practice.bean.XmlObject;
import com.wil.practice.polymorphism.DataProcessor;
import com.wil.practice.polymorphism.JsonDataProcessor;
import com.wil.practice.polymorphism.XmlDataProcessor;
import org.json.JSONObject;


public class ProcessorDispatcher {

    public String process (JSONObject jsonData) {
        DataProcessor dp = new JsonDataProcessor(1, JsonDataProcessor.class.getName(), jsonData);
        return dp.process();
    }

    public String process (XmlObject xmlData) {
        DataProcessor dp = new XmlDataProcessor(1, XmlDataProcessor.class.getName(), xmlData);
        return dp.process();
    }

    public static void main(String[] args) {
        JSONObject jo = new JSONObject();
        jo.put("time",20200514);
        jo.put("type","JSON");
        ProcessorDispatcher pd = new ProcessorDispatcher();
        System.out.println(pd.process(jo));
        XmlObject xo = XmlObject.builder().tag("Tag").subTag("SubTag").value("Val").build();
        System.out.println(pd.process(xo));
    }

}
