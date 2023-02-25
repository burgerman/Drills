package com.wil.practice.test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestHotKey {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        Stream<String> stream = list.stream();
        stream.collect(Collectors.joining());


    }

}
