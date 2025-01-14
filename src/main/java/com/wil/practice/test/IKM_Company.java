package com.wil.practice.test;

public interface IKM_Company {
    public String getAddress(String companyName);
    default public String getPhoneNum () {
        return "555-1212";
    }

}
