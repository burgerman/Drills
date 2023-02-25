package com.wil.practice.algo.dp;

import java.util.Arrays;

public class GetNumOfPrime {

    private static int countPrimes(int num) {
        boolean[] isPrim = new boolean[num];
        Arrays.fill(isPrim, true);
        isPrim[0] = false;
        isPrim[1] = false;
        // 大于sqrt(num)后会出现因子镜像 如 2*6, 3*4, sqrt(12)*sqrt(12), 4*3, 6*2
        // 所以在遍历时只需要遍历到[2,sqrt(num)]这个集合元素即可
        for (int i=2; i*i <= num; i++) {
            if(isPrim[i]) {
                for(int j = i*i; j<num; j+=i) {
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for(int i=2; i<num; i++) {
            if(isPrim[i]) {
                count++;
            }
        }
        System.out.println(Arrays.toString(isPrim));
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(14));

    }
}
