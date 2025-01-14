package com.wil.practice.algo.cp600.dc;

public class FindNumInMatrix {

    private static boolean binaryFind(int[] subMatrix, int target) {
        int l = 0, r = subMatrix.length-1;

        while (l<=r) {
            int mid = l+(r-l)/2;
            if(subMatrix[mid] == target) {
                System.out.println("column: "+mid);
                return true;
            } else if(subMatrix[mid] < target) {
                l = mid+1;
            } else {
                r = mid -1;
            }
        }
        return false;
    }


    private static boolean findT (int[][] matrix, int n, int target) {
        int top = 0;
        int bottom = n-1;
        while (top <= bottom) {
            int mid = top + (bottom - top)/2;
            if(target>=matrix[mid][0] && target<=matrix[mid][n-1]) {
                if(binaryFind(matrix[mid], target)) {
                    System.out.println("row: "+mid);
                    return true;
                } else {
                    return false;
                }
            } else if(target<matrix[mid][0]) {
                bottom = mid -1;
            } else {
                top = mid +1;
            }
        }
        return false;
    }



    public static void main(String[] args) {

        int[][] matrix = {
                {1, 3, 5},
                {10, 11, 16},
                {23, 30, 34}
        };
        int target = 3;
        int n = matrix.length;
        boolean res = findT(matrix, n, target);
        System.out.println(res);
    }



}
