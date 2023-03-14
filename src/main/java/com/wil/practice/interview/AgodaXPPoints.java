package com.wil.practice.interview;

import java.util.*;
import java.util.stream.Collectors;

public class AgodaXPPoints {
    /**
     * Recruit - 0-999
     * Warrior - 1000 - 4999
     * Knight - 5000 - 9999
     * Ninja - 10000+
     * @param xps
     * @return
     */
    static String[] xpPointers (int[] xps) {
        if(xps.length<1) {
            return new String[]{};
        }
        Map<String, Integer> xpsMap = new HashMap<>(xps.length);
        xpsMap.put("Recruit", 0);
        xpsMap.put("Warrior", 0);
        xpsMap.put("Knight", 0);
        xpsMap.put("Ninja", 0);
        for(int i=0; i<xps.length; i++) {
            if(xps[i]<1000) {
                xpsMap.compute("Recruit", (k,v)->v+=1);
                continue;
            } else if(xps[i]<5000) {
                xpsMap.compute("Warrior", (k,v)->v+=1);
                continue;
            } else if(xps[i]<10000) {
                xpsMap.compute("Knight", (k,v)->v+=1);
                continue;
            } else if(xps[i]>9999) {
                xpsMap.compute("Ninja", (k,v)->v+=1);
                continue;
            } else {
                continue;
            }
        }
        xpsMap.forEach((k,v)->System.out.println(k+"-"+v.toString()));
        List<String> levels = Arrays.asList("Ninja", "Knight", "Warrior", "Recruit");
        Comparator<Map.Entry<String,Integer>> customizedComparator = (o1, o2) -> {
            int result = o2.getValue().compareTo(o1.getValue());
            if(result!=0) {
                return result;
            } else {
                int index1 = levels.indexOf(o1.getKey());
                int index2 = levels.indexOf(o2.getKey());
                return index1-index2;
            }
        };
        List<Map.Entry<String, Integer>> resList = xpsMap.entrySet().stream().parallel().filter(e->e.getValue()>0).sorted(customizedComparator).collect(Collectors.toList());
        String[] res = new String[resList.size()];
        StringJoiner sj;
        for(int i = 0; i<res.length; i++) {
            sj = new StringJoiner(" - ");
            sj.add(resList.get(i).getKey());
            sj.add(resList.get(i).getValue().toString());
            res[i] = sj.toString();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] xps = new int[]{0, 1500, 3500, 10000};
        System.out.println(Arrays.toString(xpPointers(xps)));
        int[] xps2 = new int[]{0, 1500, 10000};
        System.out.println(Arrays.toString(xpPointers(xps2)));
    }
}
