package com.wil.practice.polymorphism;


import com.wil.practice.bean.XmlObject;

public class XmlDataProcessor extends DataProcessor{
    private XmlObject data;
    public XmlDataProcessor(int num, String processorType, XmlObject data) {
        super(num, processorType);
        setData(data);
    }

    public XmlObject getData() {
        return data;
    }

    public void setData(XmlObject data) {
        this.data = data;
    }

    @Override
    public String process() {
        return "Processing XML data";
    }
}
