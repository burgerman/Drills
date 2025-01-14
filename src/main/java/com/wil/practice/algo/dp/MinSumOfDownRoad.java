package com.wil.practice.algo.dp;

public class MinSumOfDownRoad {

    static int minSumPath(int[][] matrix) {
        int m = matrix.length, n  = matrix[0].length;
        if(m<=1) {
            int result = matrix[0][0];
            if(n>1) {
                for(int k=1; k<n; k++) {
                    result = matrix[m-1][k]>result?result:matrix[m-1][k];
                }
            }
            return result;
        }
        int min = Integer.MAX_VALUE;
        int[][] arr = new int[2][n];
        for(int k =0; k<n; k++) {
            arr[0][k] = matrix[0][k];
        }
        for (int i=1; i<m; i++) {
            for(int j =0; j<n; j++) {
                if(j<1) {
                    arr[i%2][j] = Math.min(arr[(i-1)%2][j], arr[(i-1)%2][j+1])+matrix[i][j];
                } else if(j>n-2) {
                    arr[i%2][j] = Math.min(arr[(i-1)%2][j], arr[(i-1)%2][j-1])+matrix[i][j];
                } else if(j>0) {
                    arr[i%2][j] = Math.min(arr[(i-1)%2][j], Math.min(arr[(i-1)%2][j-1], arr[(i-1)%2][j+1]))+matrix[i][j];
                }

                if(i==m-1) {
                    min = arr[i%2][j]>min?min:arr[i%2][j];
                }
            }
        }
        return min;
    }

    static int minSumPath2(int[][] matrix) {
        int m = matrix.length, n  = matrix[0].length;
        if(m<=1) {
            int result = matrix[0][0];
            if(n>1) {
                for(int k=1; k<n; k++) {
                    result = matrix[m-1][k]>result?result:matrix[m-1][k];
                }
            }
            return result;
        }
        int min = Integer.MAX_VALUE;
        for (int i=1; i<m; i++) {
            for(int j =0; j<n; j++) {
                if(j<1) {
                    matrix[i%2][j] = Math.min(matrix[(i-1)%2][j], matrix[(i-1)%2][j+1])+matrix[i][j];
                } else if(j>n-2) {
                    matrix[i%2][j] = Math.min(matrix[(i-1)%2][j], matrix[(i-1)%2][j-1])+matrix[i][j];
                } else if(j>0) {
                    matrix[i%2][j] = Math.min(matrix[(i-1)%2][j], Math.min(matrix[(i-1)%2][j-1], matrix[(i-1)%2][j+1]))+matrix[i][j];
                }

                if(i==m-1) {
                    min = matrix[i%2][j]>min?min:matrix[i%2][j];
                }
            }
        }
        return min;
    }

    static int minSumPathWithOffset2(int[][] matrix) {
        int m = matrix.length, n  = matrix[0].length;
        if(n<2) {
            return matrix[0][0];
        }
        if(m<2) {
            int result = matrix[0][0];
            if(n>1) {
                int minVal = Integer.MAX_VALUE;
                for(int k=1; k<n; k++) {
                    minVal = matrix[m-1][k]>minVal?minVal:matrix[m-1][k];
                }
                return result+minVal;
            }
            return result;
        }
        int[][] arr = new int[2][n];
        int lastRowMinIndex = -1, lastRowSecondaryMinIndex = -1;
        int min = Integer.MAX_VALUE;
        int max = min;
        for (int i=0; i<m; i++) {
            int currentRowMinIndex = -1, currentRowSecondaryMinIndex = -1;
            for(int j =0; j<n; j++) {
                int val = matrix[i][j];
                if(i<1) {
                    arr[i%2][j] = val;
                    if(val<(lastRowMinIndex == -1? max : arr[i%2][lastRowMinIndex])) {
                        lastRowSecondaryMinIndex = lastRowMinIndex;
                        lastRowMinIndex = j;
                    } else if (val < (lastRowSecondaryMinIndex == -1? max:arr[i%2][lastRowSecondaryMinIndex])) {
                        lastRowSecondaryMinIndex = j;
                    }
                } else {
                    arr[i%2][j] = max;
                    //相邻数字不能是来自同一列
                    //由此需要的是前一行的最小和次小值， 通过两个index var来存放其位置
                    if(j!=lastRowMinIndex) {
                        arr[i%2][j] = arr[(i-1)%2][lastRowMinIndex] + val;
                    } else {
                        arr[i%2][j] = arr[(i-1)%2][lastRowSecondaryMinIndex] + val;
                    }
                    if(i==m-1) {
                        min = arr[i%2][j]>min?min:arr[i%2][j];
                    }
                    if(arr[i%2][j]< (currentRowMinIndex == -1? max:arr[i%2][currentRowMinIndex])) {
                        currentRowSecondaryMinIndex = currentRowMinIndex;
                        currentRowMinIndex = j;
                    } else if(arr[i%2][j] < (currentRowSecondaryMinIndex==-1?max:arr[i%2][currentRowSecondaryMinIndex])) {
                        currentRowSecondaryMinIndex = j;
                    }
                }
            }
            if(i>0) {
                lastRowMinIndex = currentRowMinIndex;
                lastRowSecondaryMinIndex = currentRowSecondaryMinIndex;
            }
        }
        return min;
    }

    static int minSumPathWithOffset(int[][] matrix) {
        int m = matrix.length, n  = matrix[0].length;
        if(n<2) {
            return matrix[0][0];
        }
        if(m<2) {
            int result = matrix[0][0];
            if(n>1) {
                for(int k=1; k<n; k++) {
                    result = matrix[m-1][k]>result?result:matrix[m-1][k];
                }
            }
            return result;
        }
        int[][] arr = new int[m][n];
        int min = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            for(int j =0; j<n; j++) {
                if(i<1) {
                    arr[i][j] = matrix[i][j];
                    continue;
                } else {
                    arr[i][j] = Integer.MAX_VALUE;
                    for(int k=0; k<n; k++) {
                        if(k!=j) {
                            arr[i][j] = Math.min(arr[i][j], arr[i-1][k]+matrix[i][j]);
                        }
                    }
                }
                if(i==m-1) {
                    min = arr[i][j]>min?min:arr[i][j];
                }
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int[][] matrix = {{2,1,3}, {6,5,4}, {7,8,9}};
//        int[][] matrix = {{-48}};
//        System.out.println(minSumPath2(matrix));

        int[][] matrix2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(minSumPathWithOffset2(matrix2));
    }
}
