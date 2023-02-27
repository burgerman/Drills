package com.wil.practice.datastructure.array;

import java.util.Arrays;

public class MatrixRotate {

    public int[][] rotateArrBy90DegreeInClockwise(int[][] arr) {
        for(int i = 0; i<arr.length; i++) {
            for(int j = i+1; j<arr.length; j++) {
                transposeMatrix(arr, i, j);
            }
        }
        for(int i = 0; i<arr.length; i++) {
            int left = 0, right = arr.length-1;
            while(left<right) {
                mirrorByRow(arr, i, left, right);
                left++;
                right--;
            }
        }
        return arr;
    }

    public int[][] rotateArrBy90DegreeInCounterclockwise(int[][] arr) {
        for(int i = 0; i<arr.length; i++) {
            for(int j = i+1; j<arr.length; j++) {
                transposeMatrix(arr, i, j);
            }
        }
        for(int i = 0; i<arr.length; i++) {
            int left = 0, right = arr.length-1;
            while(left<right) {
                mirrorByCol(arr, i, left, right);
                left++;
                right--;
            }
        }
        return arr;
    }

    /**
     * Switching rows and columns
     * Interchange rows and columns of the matrix
     * @param arr
     * @param i
     * @param j
     */
    public void transposeMatrix(int[][] arr, int i, int j) {
        arr[i][j] = arr[i][j] ^ arr[j][i];
        arr[j][i] = arr[i][j] ^ arr[j][i];
        arr[i][j] = arr[i][j] ^ arr[j][i];
    }

    public void mirrorByRow(int[][] arr, int rowIndex, int l, int r) {
        arr[rowIndex][l] = arr[rowIndex][l] ^ arr[rowIndex][r];
        arr[rowIndex][r] = arr[rowIndex][l] ^ arr[rowIndex][r];
        arr[rowIndex][l] = arr[rowIndex][l] ^ arr[rowIndex][r];
    }

    public void mirrorByCol(int[][] arr, int colIndex, int l, int r) {
        arr[l][colIndex] = arr[l][colIndex] ^ arr[r][colIndex];
        arr[r][colIndex] = arr[l][colIndex] ^ arr[r][colIndex];
        arr[l][colIndex] = arr[l][colIndex] ^ arr[r][colIndex];
    }

    public void rotateArray90Degree (int[][] arr) {
        int dimension = arr.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = dimension-1; j >=0; j--) {
                System.out.print(arr[j][i] +", ");
            }
            System.out.println();
        }
    }

    public void rotateArray180Degree (int[][] arr) {
        int dimension = arr.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = dimension-1; j >=0; j--) {
                System.out.print(arr[i][j] +", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int [][] maxtrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        int [][] maxtrix2 = {{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};
        MatrixRotate mr = new MatrixRotate();
        System.out.println(Arrays.deepToString(maxtrix));
        System.out.println("-----------------Rotate matrix by 90 degree in clockwise-----------------------");
        System.out.println("Origin:");
        for (int[] row : maxtrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Rotated by 90 degree in clockwise:");
//        mr.rotateArray90Degree(maxtrix);
        int[][] rotatedArr = mr.rotateArrBy90DegreeInClockwise(maxtrix);
        for (int[] row : rotatedArr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("----------------------------------------");
        System.out.println(Arrays.deepToString(maxtrix2));
        System.out.println("Origin:");
        for (int[] row : maxtrix2) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Rotated:");
        int[][] rotatedArr2 = mr.rotateArrBy90DegreeInCounterclockwise(maxtrix2);
        for (int[] row : rotatedArr2) {
            System.out.println(Arrays.toString(row));
        }
//        mr.rotateArray90Degree(maxtrix2);
//        System.out.println("-----------------Rotate 180 degree-----------------------");
//        mr.rotateArray180Degree(maxtrix);
//        System.out.println("----------------------------------------");
//        mr.rotateArray180Degree(maxtrix2);
    }

}
