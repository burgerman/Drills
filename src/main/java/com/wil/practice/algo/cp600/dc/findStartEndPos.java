package com.wil.practice.algo.cp600.dc;

import java.util.Arrays;

public class findStartEndPos {

    private static int binarySearch(int[] arr, int l, int r, int target) {
        int m = l+(r-l)/2;
        if (arr[m]==target) return m;
        if (l==r) return -1;
        if(arr[m]<target) return binarySearch(arr, m+1, r, target);
        else return binarySearch(arr, l, m, target);
    }

    private static int searchLeftEndPos(int[]arr, int l, int r, int target, int pivotPos, int left) {
        int m = l+(r-l)/2;
        if ( m==pivotPos-1 || m==0) {
            if (arr[m]==target) left = Math.min(left, m);
            return left;
        }
        if (arr[m]!=target) {
            return searchLeftEndPos(arr,m+1, r, target, pivotPos,left);
        } else {
            left = Math.min(left, m );
            return searchLeftEndPos(arr, l, m, target, pivotPos,left);
        }
    }

    private static int searchRightEndPos(int[]arr, int l, int r, int target, int pivotPos, int right) {
        int m = l+(r-l)/2;
        if (m== arr.length-1 || m==pivotPos+1) {
           if(arr[m]==target) right = Math.max(right, m);
           return right;
        }
        if (arr[m]!=target) {
            return searchRightEndPos(arr,l, m-1, target, pivotPos, right);
        } else {
            right = Math.max(right,m);
            return searchRightEndPos(arr, m+1, r, target, pivotPos, right);
        }
    }

    private static int[] getStartEndPos(int[]arr, int target) {
        int pos = binarySearch(arr, 0, arr.length-1, target);
        if(pos==-1) return new int[]{-1, -1};
        int left = Integer.MAX_VALUE; int right = Integer.MIN_VALUE;
        int tmpLeft = left, tmpRight = right;
        if (pos != 0 && pos!=arr.length-1) {
            tmpLeft = searchLeftEndPos(arr, 0, pos-1, target, pos, left);
            tmpRight = searchRightEndPos(arr, pos+1, arr.length-1, target, pos, right);
        }
        else if(pos == 0) tmpRight = searchRightEndPos(arr, pos+1, arr.length-1, target, pos, right);
        else tmpLeft = searchLeftEndPos(arr, 0, pos-1, target, pos, left);
        
        if (left!=tmpLeft && right!=tmpRight ) return new int[]{tmpLeft, tmpRight};
        else if(left!=tmpLeft) return new int[]{tmpLeft, pos};
        else if(right!=tmpRight) return new int[]{pos, tmpRight};
        else return new int[]{pos, pos};
    }


    public static void main(String[] args) {
        int[] arr = new int[] {1,1,3,4,4,4,5,7,7};
        int target = 4;
        System.out.print(Arrays.toString(getStartEndPos(arr, target)));
    }
}
