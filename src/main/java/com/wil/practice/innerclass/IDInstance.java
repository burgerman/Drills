package com.wil.practice.innerclass;

import lombok.Data;

@Data
public class IDInstance {
    private String id;

    public IDInstance(String id) {
        this.id = id;
    }
}
