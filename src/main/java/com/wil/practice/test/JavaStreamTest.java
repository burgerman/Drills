package com.wil.practice.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class JavaStreamTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(16);
        char ch;
        for(int i=97, count=1; i<103;i++) {
            ch = (char)i;
            map.put(String.valueOf(ch), count);
            count++;
        }
        System.out.println("Origin: ");
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.forEach(System.out::println);
        System.out.println("======================================================================");
        System.out.println("Func1: Output those elements whose value is greater than 2");
        Consumer<Stream<Map.Entry<String, Integer>>> consumer = (element) -> outputtingList(returnAList(element.filter(n->n.getValue()>2)));
        consumer.accept(list.stream());
        System.out.println("======================================================================");
        System.out.println("Func2: Reverse the elements' order");
        Consumer<Stream<Map.Entry<String, Integer>>> consumer2 = (element) -> outputtingList(returnAList(element.sorted(((o1, o2) -> o2.getValue()-o1.getValue()))));
        consumer2.accept(list.stream());
    }

    private static List<Map.Entry<String, Integer>> returnAList(Stream<Map.Entry<String, Integer>> source) {
        return source.collect(Collectors.toList());
    }

    private static void outputtingList(List<Map.Entry<String, Integer>> list) {
        list.forEach(System.out::println);
    }


}
