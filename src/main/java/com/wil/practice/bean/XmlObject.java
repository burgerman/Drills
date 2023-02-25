package com.wil.practice.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class XmlObject {

    private String tag;
    private String subTag;
    private String value;

    public XmlObject(String tag, String subTag, String value) {
        this.tag = tag;
        this.subTag = subTag;
        this.value = value;
    }
}
