package com.wil.practice.algo.cp600.dc;

public class FixedPoint {

    private static int fixedPoint(int[] arr) {
        int l = 0, r = arr.length-1;
        while (l < r) {
            int m = l+(r-l)/2;
            if(arr[m] > m) {
                r=m-1;
            } else if (arr[m] < m) {
                l=m+1;
            } else {
                return m;
            }
        }
        if(arr[l] == l) return l;
        else return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {-10,-5,0,3,7};
        int[] arr2 = {0,2,5,8,17};
        int[] arr3 = {-10,-5,3,4,7,9};
        System.out.println(fixedPoint(arr));
        System.out.println(fixedPoint(arr2));
        System.out.println(fixedPoint(arr3));
    }
}
