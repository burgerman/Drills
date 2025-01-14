package com.wil.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EuclideanFindHCF {

    /**
     * The greatest common divisor (CCD), also called highest common factor
     * (HCF) of N numbers is the largest positive integer that divides all numbers without giving a remainder.
     * Write an algorithm to determine the CD of N positive integers.
     * Input
     * The input to the function/method consists of two arguments - num, an integer representing the number of positive integers (N). arr, a list of positive integers.
     * Output
     * Return an integer representing the CD of the given positive integers.
     * Example
     * Input:
     * num = 5
     * arr = [2, 4, 6, 8, 10]
     * Output:
     * 2
     * @param num
     * @param arr
     * @return
     */

    public static int findHCF(int num, List<Integer> arr) {
        int intermediate = arr.get(0);
        for(int i=1; i<num; i++) {
            intermediate = findHCF(intermediate, arr.get(i));
        }
        int hcf = intermediate;
        return hcf;
    }

    private static int findHCF(int num1, int num2) {
        if(num2==0) {
            return num1;
        }
        return findHCF(num2, num1%num2);
    }


    public static void main(String[] args) {
        List<Integer> givenList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        int num = 5;
        System.out.println(findHCF(num, givenList));
    }


}
