package com.wil.practice.innerclass;

import lombok.Data;

@Data
public class EmailInstance {
    private String address;

    public EmailInstance(String address) {
        this.address = address;
    }
}
