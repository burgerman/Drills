package com.wil.practice.algo.twopointers;

import java.util.Arrays;

public class StringCompression {

    private static int compressTwoPointer(char[] chars) {
        int write = 0;
        int read = 0;
        char c;
        int count;
        while (read < chars.length) {
            c = chars[read];
            count = 0;

            while (read < chars.length && chars[read] == c) {
                read++;
                count++;
            }
            chars[write] = c;
            write++;

            if (count > 1) {
                for (char digit : String.valueOf(count).toCharArray()){
                    chars[write] = digit;
                    write++;
                }
            }
        }
        System.out.printf("%s%n", Arrays.toString(chars));
        return write;
    }


    private static int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        int len;
        while (r<chars.length) {
            if (r==chars.length-1) {
                sb.append(chars[l]);
                if (chars[l] == chars[r]) len = r-l+1;
                else len = (r-1)-l+1;

                if (len>10) {
                    for (int i=0; i<(len/10); i++) {
                        sb.append('1');
                    }
                    sb.append((char)((len%10)+'0'));
                } else if (len==10) {
                    sb.append('1');
                } else if ((len%10)>1) {
                    sb.append((char)((len%10)+'0'));
                }

                if (l!=r && chars[l] != chars[r]) sb.append(chars[r]);
                break;
            }

            if (chars[l] != chars[r]) {
                sb.append(chars[l]);
                len = (r-1)-l+1;
                if (len>10) {
                    for (int i=0; i<len/10; i++) {
                        sb.append('1');
                    }
                    sb.append((char)((len%10)+'0'));
                } else if (len==10) sb.append('1');
                else if ((len%10)>1) sb.append((char)((len%10)+'0'));
                l = r;
            }
            r++;
        }
        System.out.printf("%s%n", sb.toString());
        return sb.length();
    }

    public static void main(String[] args) {
        char[] chars = new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] chars2 = new char[] {'a'};
        char[] chars3 = new char[] {'a','a','b','b','c','c','c'};
        System.out.printf("Returned length: %d%n", compress(chars));
        System.out.printf("Returned length: %d%n", compress(chars2));
        System.out.printf("Returned length: %d%n", compress(chars3));

        System.out.printf("Returned length: %d%n", compressTwoPointer(chars));
        System.out.printf("Returned length: %d%n", compressTwoPointer(chars2));
        System.out.printf("Returned length: %d%n", compressTwoPointer(chars3));
    }

}
