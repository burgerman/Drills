package com.wil.practice.algo.twopointers;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class flipNums {

    private static final Pattern invalidNumPattern = Pattern.compile("[23457]");
    private static final Pattern flipNumPattern = Pattern.compile("[69]");
    private static final Pattern endZeroes = Pattern.compile("\\d+0$");
    private static Matcher matcher;

    private static String regexSolution(int num) {
        String strNum = String.valueOf(num);
        matcher = invalidNumPattern.matcher(strNum);
        if (matcher.find()) return null;
        matcher = endZeroes.matcher(strNum);
        if (matcher.find()) return null;
        matcher = flipNumPattern.matcher(strNum);
        StringBuilder sb = new StringBuilder(matcher.replaceAll(match->{
            if(match.group(0).equals("6")){
                return "9";
            } else {
                return "6";
            }
        }));
        String res = sb.reverse().toString();
        if (res.equals(strNum) || Integer.parseInt(res) > MAX_NUM) return null;
        return res;
    }



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
            String res = regexSolution(i);
            if(res!=null) System.out.printf("%d%n",i);
        }

    }

}
