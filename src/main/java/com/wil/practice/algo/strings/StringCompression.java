package com.wil.practice.algo.strings;

public class StringCompression {

    private static int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        char current = chars[0];
        char c;
        int len = 0;
        int r;
        for (int j=0; j<chars.length; j++) {
            c = chars[j];
            if (c != current || j==chars.length-1) {
                sb.append(current);
                if (j==chars.length-1) len++;
                if(len/10!=0) {
                    r = len/10;
                    for (int i=0;i<r;i++) {
                        sb.append('1');
                    }
                    sb.append((char)len%10);
                } else if (len>1) {
                    sb.append((char)len%10);
                }
                current = c;
                len =1;
            } else {
                len++;
            }
        }
        char[] res = sb.toString().toCharArray();
        return res.length;
    }


    public static void main(String[] args) {
        char[] chars = new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(compress(chars));
    }
}
