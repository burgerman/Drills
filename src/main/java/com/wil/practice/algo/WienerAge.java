package com.wil.practice.algo;

public class WienerAge {

    /**
     * set the range of his age
     * The important clue is that Wiener was very young at that time.
     * He went to college at the age of 11
     * So, we can assume his age is ranging from 11 to 30
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder s1;
        StringBuilder s2;
        for(int i = 11; i<30; i++) {
            int a = (int) Math.pow(i,3);
            s1=new StringBuilder().append(a);
            int b = (int) Math.pow(i,4);
            s2=new StringBuilder().append(b);
            if(s1.toString().length()!=4) {
                continue;
            }
            if(s2.toString().length()!=6) {
                continue;
            }
            if(!ifHasSame(s1.append(s2).toString())){
                System.out.println(i);
            }
        }
    }

    private static boolean ifHasSame(String source) {
        char[] arr = source.toCharArray();
        for(int i =0; i<arr.length-1; i++) {
            for(int j=i+1; j<arr.length;j++) {
                if(arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

}
