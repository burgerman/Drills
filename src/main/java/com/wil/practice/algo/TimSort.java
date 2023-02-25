package com.wil.practice.algo;

public class TimSort {
    private final static int MIN_RUN = 32;

    public static int minRunLength(int n) {
        if(n>0) {
            int r = 0;
            while (n>MIN_RUN) {
                r|= (n&1);
                n >>= 1;
            }
            return n+r;
        } else {
            return 0;
        }
    }

    public void mergeArray(int[] arr, int l, int r) {
        int m = l+(r-l)>>1;
        int size1 = m-l+1;
        int size2 = r-m;
        int leftArr[] = new int[size1];
        int rightArr[] = new int[size2];
        for (int x = 0; x<size1; x++) {
            leftArr[x] = arr[l+x];
        }
        for (int y = 0; y<size2; y++) {
            rightArr[y] = arr[m+1+y];
        }
        int i = 0, j = 0;
        int k = l;
        while(i<size1 && j<size2) {
            if(leftArr[i] <= rightArr[j]){
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i<size1) {
            arr[k++] = leftArr[i++];
        }
        while (j<size2) {
            arr[k++] = rightArr[j++];
        }
    }

    public void insertSort(int[] arr, int left, int right) {
        for(int i = left+1; i<=right; i++) {
            int tmp = arr[i];
            int j = i;
            while(j>left && tmp < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            if(j!=i) {
                arr[j] = tmp;
            }
        }
    }

    public void timSort(int[]arr, int left, int right) {
        if(left<right) {
            int minRun = minRunLength(arr.length);
            for(int i=0; i<arr.length; i+=minRun) {
                insertSort(arr, i, Math.min(i+minRun-1, arr.length-1));
            }

            for(int size = minRun; size<arr.length; size=size*2) {
                for(int l=0; l<arr.length; l+=2*size) {
                    int r = Math.min(l+2*size-1, arr.length-1);
                    int mid = l+(r-l)>>1;
                    if(mid<r) {
                        mergeArray(arr, l, r);
                    }
                }
            }
        }
    }
}
