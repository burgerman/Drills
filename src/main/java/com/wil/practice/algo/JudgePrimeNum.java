package com.wil.practice.algo;

public class JudgePrimeNum {

    final static int minPrime = 2;

    public static boolean ifPrime(int n) {

        if(n<minPrime) {
            return false;
        }
        int i = 2;
        while(i<=Math.sqrt(n)) {
            if(n%i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ifPrime(17));
    }

}
