package com.wil.practice.algo.cp600.dc;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SuperPow {

    private static int getModResult(int a, int power, int mod) {
        long res = 1;
        long base = a % mod;
        while (power>0) {
            if(power%2!=0) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            power /= 2;
        }
        return (int) res;
    }

    private static int mergeSort(int a, int[]b, int l, int r) {
        if (l==r) {
            return (int) (b[l]* Math.pow(10, b.length-1-l));
        }
        int m = l+(r-l)/2;
        int left = mergeSort(a, b, l, m);
        int right = mergeSort(a, b, m+1, r);
        return left + right;
    }

    private static int superPow(int a, int[] b) {
        if (a==1) return 1;
        if (a==0) return 0;
        if (b.length==1) return (int) Math.pow(a, b[0]);
        int n = b.length;
        int power = mergeSort(a, b, 0, n-1);
        return getModResult(a, power, 1337);
    }

    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        int[] b = new int[] {2, 0, 0};
        System.out.println(superPow(a, b));
    }
}
