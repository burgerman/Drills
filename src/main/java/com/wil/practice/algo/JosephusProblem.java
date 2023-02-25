package com.wil.practice.algo;

import java.util.Arrays;

public class JosephusProblem {

    public static void main(String[] args) {

        int n = 5;
        int[] arr = new int[n+1];
        arr[0] = 0;
        int removedNum = 0,
                count = 0,
                start = 0,
                index = 0;
        double condition = 0;

        for(int i=1;i<arr.length; i++) {
            arr[i] = i;
        }
        System.out.println("Array: "+Arrays.toString(arr));
        outer: for (;;) {
            if(removedNum == n-1) {
                break outer;
            }
            inner: for(int round =1; round<n; round++) {
                int offset = 0;
                condition = Math.pow(2, round);
                start = index +1;
                index += 1;
                // if the current object has been kicked out
                while(arr[(start%n)>0? (start%n):n] == -1) {
                    start+=1;
                    offset+=1;
                }
                index+=offset;
                count = 1;
                while(count!=condition) {
                    count+=1;
                    index+=1;
                }
                if(count == condition) {
                    arr[(index%n) == 0? n:index%n] = -1;
                    removedNum +=1;
                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }
}
