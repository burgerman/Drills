package com.wil.practice.datastructure.array;

import org.apache.commons.lang3.StringUtils;

public class WordReverse {


    public static void main(String[] args) {

        String words = "  a good   example ";
        String trimmedWords = words.strip();
        if (trimmedWords.length()<=1) System.out.printf("%s%n", trimmedWords);
        else {
            int l = trimmedWords.length()-1, r = trimmedWords.length()-1;
            StringBuilder sb = new StringBuilder();
            while (l>=0 && r>=0) {
                if (trimmedWords.charAt(l)==' ' || l==0) {
                    if (l!=0 && trimmedWords.charAt(l+1)!=' ') {
                        sb.append(trimmedWords, l+1, r+1);
                        if (l!=0) sb.append(' ');
                    } else if (l==0 && trimmedWords.charAt(l+1) == ' ') {
                        r=l;
                        sb.append(trimmedWords, l, r+1);
                    } else if (l==0) {
                        sb.append(trimmedWords, l, r+1);
                    }
                } else {
                    if (l<trimmedWords.length()-1 && trimmedWords.charAt(l+1)==' ') {
                        r = l;
                    }
                }
                l--;
            }
            String reversedWords = sb.toString();
            System.out.println(reversedWords);
        }
    }
}
