package com.wil.practice.polymorphism;

public abstract class DataProcessor {
    private int num;
    private String processorType;

    public DataProcessor(int num, String processorType) {
        this.num = num;
        this.processorType = processorType;
    }

    public String process() {
        return null;
    }
}
