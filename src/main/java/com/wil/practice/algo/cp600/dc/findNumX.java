package com.wil.practice.algo.cp600.dc;

public class findNumX {

    private static int findXPos (int[] arr, int X){
        if(arr[0]==-1) return -1;
        int k = 1;
        while (k<arr.length && arr[k]!=-1 && arr[k]<X) {
            k *=2;
        }
        k = Math.min(k, arr.length-1);
        int l = k/2, r=k;
        int mid;
        while (l<=r) {
            mid = l+(r-l)/2;
            if (arr[mid]<X && arr[mid]!=-1) {
                l = mid+1;
            } else if(arr[mid]>X || arr[mid]==-1) {
                r = mid-1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2,4,7,9,11,15,18,20,21,23,24,25,27,29,31,33,34,35,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        System.out.println(findXPos(arr,35));
    }

}
