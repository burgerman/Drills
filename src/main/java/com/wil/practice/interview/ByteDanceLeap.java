package com.wil.practice.interview;

public class ByteDanceLeap {

    public static boolean canBeReached (int[] arr, int currentIndex, int target) {
        if(arr[currentIndex]<1) {
            return false;
        }
        int distance = target-currentIndex;

        if(arr[currentIndex] >= distance) {
            return true;
        } else {
            boolean result = false;
            for (int i =1; i<=arr[currentIndex]; i++) {
                if(currentIndex+i<arr.length) {
                    result |= canBeReached(arr, currentIndex+i, target);
                }
            }
            return result;
        }
    }


    public static boolean canBeReachedDP (int[] arr, int currentIndex, int target) {
        if(currentIndex==target) {
            return true;
        }
        if(arr[currentIndex]<1) {
            return false;
        }
        int distance = target-currentIndex;

        if(arr[currentIndex] >= distance) {
            return true;
        } else {
            boolean[] result = new boolean[arr.length];
            result[currentIndex] = true;
            for(int i=0; i<arr.length; i++) {
                int distant = target-i;
                for(int j=1; j<=arr[i]; j++) {
                    if(i+j< result.length && i+j >= distant) {
                        result[i+j] = true;
                    }
                }
            }
            return result[target];
        }
    }

    public static void main(String[] args) {
//        int[] arr = {2,3,1,1,4};
        int[] arr = {3,2,1,0,4};
        boolean result = canBeReached(arr, 0, arr.length-1);
        boolean result2 = canBeReachedDP(arr, 0, arr.length-1);
        System.out.println(result);
        System.out.println(result2);
    }
}
