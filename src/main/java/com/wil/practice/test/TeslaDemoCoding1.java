package com.wil.practice.test;

import java.util.concurrent.ForkJoinPool;

public class TeslaDemoCoding1 {

    /**
     * given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
     * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
     * Given A = [1, 2, 3], the function should return 4.
     * Given A = [−1, −3], the function should return 1.
     * Write an efficient algorithm for the following assumptions:
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−1,000,000..1,000,000].
     * @param A
     * @return
     */
    private static int solution(int[] A) {
        // Implement your solution here
        boolean[] table = new boolean[1000001];
        int result = Integer.MIN_VALUE;
        for (int i=0; i<A.length; i++) {
            if(A[i]>=0 && table[A[i]]!=true) {
                table[A[i]] = true;
            }
        }
        for(int j=1; j<table.length; j++) {
            if(table[j]==false) {
                result = j;
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 9999, 3, 4, 1, 2};
        int[] arr2 = {-1, -3, 1, 2, 3};
        System.out.println(solution(arr1));
        System.out.println(solution(arr2));
    }

}
