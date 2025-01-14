package com.wil.practice.algo.cp600.dc;

public class FindDominantChar {

    private static char getDominant(char[] arr, int l, int r) {
        if (r-l==1) {
            if (arr[l] == arr[r]) return arr[l];
            else return '\0';
        }
        int mid = l + (r-l)/2;
        char leftDominant = getDominant(arr, l, mid);
        char rightDominant = getDominant(arr, mid+1, r);
        return getCrossDominant(arr, leftDominant, rightDominant, l, r);
    }

    private static char getCrossDominant(char[] arr, char leftDominant, char rightDominant, int l, int r) {
        if (leftDominant==rightDominant) return leftDominant;
        int i = l;
        int count1 =0, count2 = 0;
        while (i <= r) {
            if(arr[i] == leftDominant) count1++;
            if(arr[i] == rightDominant) count2++;
            i++;
        }
        int threshold = (r-l) /2;
        if(count1>threshold) return leftDominant;
        else if(count2>threshold) return rightDominant;
        else return '\0';
    }


    public static void main(String[] args) {
        char[] A = {'a', 'b', 'a', 'a', 'a', 'b', 'a','c'};
        char x = getDominant(A, 0, A.length-1);
        if (x!='\0') System.out.println("Dominant: "+x);
        else System.out.println("FAIL");
    }

}
