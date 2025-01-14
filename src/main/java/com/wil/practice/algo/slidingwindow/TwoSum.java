package com.wil.practice.algo.slidingwindow;

import java.util.Arrays;

public class TwoSum {
    // int[] numbers - A sorted ascending array with positive integers
    // Using two-pointer approach
    public static int[] twoSum(int[] numbers, int target) {
        int index1=0, index2=numbers.length-1;
        while(index1<index2) {
            if(numbers[index1] + numbers[index2] == target) {
                return new int[]{index1+1, index2+1};
            } else if(numbers[index1] + numbers[index2] > target) {
                index2--;
            } else {
                index1++;
            }
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(twoSum(new int[]{3,24,50,79,88,150,345}, 200)));

    }

}
