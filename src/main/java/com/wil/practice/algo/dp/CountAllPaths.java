package com.wil.practice.algo.dp;

public class CountAllPaths {

    public static int countAllPaths(boolean[][] mapArr) {
        int m = mapArr.length, n = mapArr[0].length;
        if(!mapArr[m-1][n-1] || !mapArr[0][0]){
            return 0;
        }
        int[][] pathCounts = new int[m][n];
        pathCounts[m-1][n-1] = 1;
        for (int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(!mapArr[i][j]) {
                    pathCounts[i][j] = 0;
                    continue;
                } else if(i<m-1 && j<n-1) {
                    pathCounts[i][j] = pathCounts[i][j+1]+pathCounts[i+1][j];
                } else if (i<m-1) {
                    pathCounts[i][j] = pathCounts[i+1][j];
                } else if(j<n-1) {
                    pathCounts[i][j] = pathCounts[i][j+1];
                } else {
                    continue;
                }
            }
        }
        return pathCounts[0][0];
    }


    public static void main(String[] args) {
        boolean[][] arr = {
                {true, true, true, true, true, true, true, true},
                {true, true, false, true, true, true, false, true},
                {true, true, true, true, false, true, true, true},
                {false, true, false, true, true, false, true, true},
                {true, true, false, true, true, true, true, true},
                {true, true, true, false, false, true, false, true},
                {true, false, true, true, true, false, true, true},
                {true, true, true, true, true, true, true, true}};

        int allPaths = countAllPaths(arr);
        System.out.println(allPaths);

        int[][] arr2 = {{1}};
        System.out.println(arr2[0].length);
    }
}
