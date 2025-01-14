package com.wil.practice.algo.cp600.dc;

public class Search2DMatrix {


    /**
     * Binary Search Solution
     * O(lg^2(n))
     */
    private static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return search(matrix, target, 0, 0, matrix[0].length - 1, matrix.length - 1);
    }

    private static int searchRecursive(int[][] matrix, int target, int l, int up, int r, int down) {
        if(l>r || up > down) return -1;
        if(target < matrix[up][l] || target > matrix[down][r]) return -1;

        int mid = l+(r-l)/2;
        int row = up;
        while(row<=down && matrix[row][mid] <= target) {
            if(matrix[row][mid] == target) return matrix[row][mid];
            row++;
        }
        int leftBot = searchRecursive(matrix, target, l, row, mid-1, down);
        int rightTop = searchRecursive(matrix, target, mid+1, up, r, row-1);
        if(leftBot == -1) return rightTop;
        else return leftBot;
    }


//    private static boolean search (int[][] matrix, int target, int l, int up, int r, int down) {
//        if(l>r || up > down) return false;
//        if(target < matrix[up][l] || target > matrix[down][r]) return false;
//
//        int m1 = up + (down-up)/2;
//        int m2 = l + (r-l)/2;
//        if (target == matrix[m1][m2]) return true;
//        if (target < matrix[m1][m2]) {
//            // top left, top right, bot left
//            return search(matrix, target, l, up, m2-1, m1) || search(matrix, target, m2, up, r, m1-1) || search(matrix, target, l, m1+1, m2-1, down);
//        } else {
//            // bot right, bot left, top right
//            return search(matrix, target, m2+1, m1+1, r, down) || search(matrix, target, l, m1+1, m2, down) || search(matrix, target, m2+1, up, r, m1);
//        }
//    }

    private static boolean search (int[][] matrix, int target, int l, int up, int r, int down) {
        if(l>r || up > down) return false;
        if(target < matrix[up][l] || target > matrix[down][r]) return false;

        int m1 = up + (down-up)/2;
        int m2 = l + (r-l)/2;
        if (target == matrix[m1][m2]) return true;
        if (target < matrix[m1][m2]) {
            // top half, left half
            return search(matrix, target, l, up, r, m1-1) || search(matrix, target, l, m1, m2-1, down);
        } else {
            // right half, bot half
            return search(matrix, target, m2+1, up, r, down) || search(matrix, target, l, m1+1, m2, down);
        }
    }



    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        System.out.println(searchMatrix(matrix, 20));

    }
}
