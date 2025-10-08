package com.wil.practice.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

public class RemoveStarsFromString {

    private static String removeStars(String s) {
        int count=0;
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        char c;
        while (!stack.isEmpty()) {
            c = stack.pop();
            if(c!='*') {
                if(count>0) count--;
                else sb.append(c);
            } else {
                count++;
            }
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {

       String s1 = "leet**cod*e";
       String s2 = "erase*****";
        System.out.printf(" %s%n", removeStars(s1));
        System.out.printf(" %s%n", removeStars(s2));
    }

}
