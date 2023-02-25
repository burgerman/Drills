package com.wil.practice.interview;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SortingWorkerSalary {

    private static HashMap<Integer, Integer> map = new HashMap<>(16);

    public static void main(String[] args) {
        List<String> workLedger = new ArrayList<>(16);
        workLedger.add("1, 200");
        workLedger.add("5, 400");
        workLedger.add("2, 500");
        workLedger.add("1, 150");
        workLedger.add("3, 400");
        workLedger.add("4, 800");
//        sortSalary(workerList);
        workLedger.stream()
                .collect(Collectors.groupingBy(v->Integer.valueOf(String.valueOf(v.split(",\\s+")[0])),
                        Collectors.summingInt(v->Integer.valueOf(v.split(",\\s+")[1]))))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> o2-o1))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void sortSalary(List<String> workerList) {
        ArrayList<String> list = new ArrayList<>(workerList);
        String[] arr;
        int id, salary;
        for(String str: list) {
            arr = str.split(",");
            id = Integer.valueOf(arr[0]);
            salary = Integer.valueOf(arr[1]);
            if(map.get(id)!=null) {
                map.replace(id, map.get(id), map.get(id)+salary);
            } else {
                map.put(id,salary);
            }
        }
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        List<Map.Entry<Integer,Integer>> res = new ArrayList<>(set);
        res.sort((o1, o2) -> o2.getValue()-o1.getValue());
        Iterator<Map.Entry<Integer,Integer>> it = res.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,Integer> entry = it.next();
            id = entry.getKey();
            salary = entry.getValue();
            System.out.println("id"+id+", salary:" +salary);
        }
    }


}
