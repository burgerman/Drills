package com.wil.practice.algo;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KMPdemo {

    private static Pattern pattern = Pattern.compile("(?i)[a-z]+");
    private static Pattern pattern2 = Pattern.compile("[^\\f\\n\\r\\t\\v]");


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext(pattern)) {
            String pattern = scanner.nextLine();
            String source = scanner.nextLine();

            int[] table = kmpMatchTable(pattern);
            int pos = kmpSearch(source, pattern, table);
            System.out.println(pos);
            break;
        }
    }

    private static int kmpSearch(String source, String pattern, int[] table) {
        for(int i=0, j=0; i<source.length(); i++) {
            while(j>0 && source.charAt(i) != pattern.charAt(j)) {
                j = table[j-1];
            }

            if(source.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if(j==pattern.length()) {
                return i-j+1;
            }
        }
        return -1;
    }

    private static int[] kmpMatchTable(String source) {
        int[] table = new int[source.length()];
        table[0] = 0;
        for(int i=1, len=0; i< table.length;i++) {
            while(len>0 && source.charAt(i) != source.charAt(len)) {
                len = table[len-1];
            }
            if(source.charAt(i) == source.charAt(len)){
                len++;
            }
            table[i] = len;
        }
        return table;
    }

}
