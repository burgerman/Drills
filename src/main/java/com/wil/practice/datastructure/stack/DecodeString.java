package com.wil.practice.datastructure.stack;

import java.util.ArrayDeque;

public class DecodeString {

    private static String decodeString(String s) {
        ArrayDeque<Integer> countStack = new ArrayDeque<>();
        ArrayDeque<StringBuilder> stringStack = new ArrayDeque<>();

        StringBuilder currentDecode = new StringBuilder();
        StringBuilder prevDecode;
        int count = 0;
        int reps;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                currentDecode.append(c);
            } else if (Character.isDigit(c)) {
                count = count * 10 + (c-'0');
            } else if (c == '[') {
                countStack.push(count);
                stringStack.push(currentDecode);
                count = 0;
                currentDecode = new StringBuilder();
            } else if (c == ']') {
                reps = countStack.pop();
                prevDecode = stringStack.pop();
                prevDecode.append(currentDecode.toString().repeat(reps));
                currentDecode = prevDecode;
            }

        }

        return currentDecode.toString();
    }

    private static String decodeStringWithStack(String s) {
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>(s.length());
        StringBuilder res = new StringBuilder();
        StringBuilder reps = new StringBuilder();
        String cluster;
        int level = 0;
        for (char c : s.toCharArray()) stack.push(c);
        char c, top;
        while (!stack.isEmpty()) {
            c = stack.pop();
            if (c == '[') {
                level--;
            } else if(c == ']') {
                level++;
                if (sb.length()>0)  {
                    res.insert(0, sb);
                    sb = new StringBuilder();
                }
            } else if (c>='a' && c<='z') {
                sb.insert(0, c);
            } else if (c>='0' && c<='9') {
                reps.insert(0, c);
                while (!stack.isEmpty()) {
                    top = stack.peek();
                    if (top>='0' && top<='9') {
                        reps.insert(0, top);
                        stack.pop();
                    } else {
                        break;
                    }
                }
                cluster = sb.toString().repeat(Integer.parseInt(reps.toString()));
                reps = new StringBuilder();
                sb = new StringBuilder(cluster);
                if (level==0) {
                    res.insert(0, sb);
                    sb = new StringBuilder();
                }
            }
        }
        if (sb.length()>0) res.insert(0, sb);

        return res.toString();
    }


    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.printf(" %s%n", decodeStringWithStack(s1));
        System.out.printf(" %s%n", decodeStringWithStack(s2));
        System.out.printf(" %s%n", decodeStringWithStack(s3));
        System.out.printf(" %s%n", decodeStringWithStack(s4));

        System.out.printf(" %s%n", decodeString(s1));
        System.out.printf(" %s%n", decodeString(s2));
        System.out.printf(" %s%n", decodeString(s3));
        System.out.printf(" %s%n", decodeString(s4));
    }

}
