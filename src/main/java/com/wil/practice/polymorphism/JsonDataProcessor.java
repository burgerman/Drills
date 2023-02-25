package com.wil.practice.polymorphism;


import org.json.JSONObject;

public class JsonDataProcessor extends DataProcessor {
    private JSONObject data;

    public JsonDataProcessor(int num, String processorType, JSONObject data) {
        super(num, processorType);
        setData(data);
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @Override
    public String process() {
        return "Processing JSON data";
    }

}
