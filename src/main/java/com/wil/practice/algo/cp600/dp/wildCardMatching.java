package com.wil.practice.algo.cp600.dp;

public class wildCardMatching {


    public static boolean isMatch(String s, String p) {
        if (s.length()== 0 || p.length()==0) return false;
        int i=0, j = 0;
        int star = -1, index = -1;

        while (i<s.length() && j<p.length()) {
            if (s.charAt(i) == p.charAt(j) || p.charAt(j)=='?') {
                i++;
                j++;
            } else if (j<p.length() && p.charAt(j)=='*') {
                star = j;
                index = i;
                j++;
            } else if(star!=-1) {
                j = star+1;
                index++;
                i = index;
            } else {
                return false;
            }
        }
        if (i!=s.length()) return false;
        while (j<p.length()) {
            if(p.charAt(j) == '*') j++;
            else break;
        }
        boolean res = i==s.length() && j==p.length();
        return res;
    }


    public static void main(String[] args) {
        String s = "acdcb";
        String p = "a*c?b";
        System.out.println(isMatch(s, p));
    }
}
