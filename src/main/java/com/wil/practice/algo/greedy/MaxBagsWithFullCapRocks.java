package com.wil.practice.algo.greedy;

import java.util.Arrays;

public class MaxBagsWithFullCapRocks {

    public static int maxBagsWithFullCapacityOfRocks(int[] capacity, int[] rocks, int addiRocks) {
        int n = capacity.length;
        int[] records = new int[n];
        for (int i=0; i<n; i++) {
            records[i] = capacity[i] - rocks[i];
        }
//        Arrays.sort(records);
        records = Arrays.stream(records).sorted().toArray();
        int res = 0;
        for (int i = 0; i<n; i++) {
            addiRocks = addiRocks - records[i];
            if(addiRocks<0) {
                break;
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] cap = new int[]{1, 5, 3, 6};
        int[] rocks = new int[]{1, 2, 3, 4};
        System.out.println(maxBagsWithFullCapacityOfRocks(cap, rocks, 2));

    }


}
