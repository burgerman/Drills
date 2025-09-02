package com.wil.practice.algo.twopointers;

import java.util.HashSet;
import java.util.Set;

public class flipNums {


    private static int MAX_NUM = 650;

    private static Set<Integer> flippableNums;
    private static Set<Integer> specialNums;

    private static boolean printOut(int num) {
        String str = String.valueOf(num);
        char[] charArr = str.toCharArray();
        int left = 0, right = charArr.length-1;
        if (charArr[right]-'0'==0) return false;
        if (left == right && specialNums.contains(charArr[left]-'0')) return false;
        char tmp;

        while (left<=right) {
            if (!flippableNums.contains(charArr[left]-'0') || !flippableNums.contains(charArr[right]-'0')) {
                return false;
            }

            if (charArr[left] == '6') {
                charArr[left] = '9';
            } else if (charArr[left] == '9') {
                charArr[left] = '6';
            }

            if (left != right) {
                if (charArr[right] == '6') {
                    charArr[right] = '9';
                } else if (charArr[right] == '9') {
                    charArr[right] = '6';
                }
            }

            tmp = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = tmp;
            left++;
            right--;
        }
        String newStr = new String(charArr);
        if(str.equals(newStr)) return false;

        return Integer.valueOf(newStr) <= MAX_NUM;
    }


    public static void main(String[] args) {

        flippableNums = new HashSet<>();
        flippableNums.add(0);
        flippableNums.add(1);
        flippableNums.add(6);
        flippableNums.add(8);
        flippableNums.add(9);

        specialNums = new HashSet<>();
        specialNums.add(0);
        specialNums.add(1);
        specialNums.add(8);

        for (int i = 1; i<=MAX_NUM; i++) {
            if(printOut(i)) System.out.println(i);
        }

    }

}
