package com.wil.practice.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TriangleShortestPath {
    public static int minimumTotal(List<List<Integer>>triangle) {
        int r = triangle.size();
        if(r <=1) {
            return triangle.get(0).get(0);
        }
        int min = Integer.MAX_VALUE;
        int ans = min;
        int[][] arr = new int[2][r];
        arr[0][0] = triangle.get(0).get(0);
        for (int i=1; i<r; i++) {
                for(int j=0; j<i+1; j++) {
                    int currentVal = triangle.get(i).get(j);
                    arr[i & 1][j] = min;
                    if(j!=0) {
                        arr[i & 1][j] = Math.min(arr[i & 1][j], currentVal+arr[(i-1)&1][j-1]);
                    }
                    if (j!=i) {
                        arr[i & 1][j] = Math.min(arr[i & 1][j], currentVal+arr[(i-1)&1][j]);
                    }
                    if(i==r-1) {
                        ans = Math.min(arr[i & 1][j], ans);
                    }
                }
        }
        return ans;
    }


    public static int minimumTotal2(List<List<Integer>>triangle) {
        int r = triangle.size();
        if(r <=1) {
            return triangle.get(0).get(0);
        }
        int min = Integer.MAX_VALUE;
        int ans = min;
        for (int i=1; i<r; i++) {
            // first element coming from right top only
            triangle.get(i).set(0, triangle.get(i-1).get(0) + triangle.get(i).get(0));
            if(i==r-1) {
                ans = Math.min(triangle.get(i).get(0), ans);
            }
            // elements between the head and the tail
            for(int j=1; j<i; j++) {
                triangle.get(i).set(j, Math.min(triangle.get(i-1).get(j-1), triangle.get(i-1).get(j)) + triangle.get(i).get(j));
                if(i==r-1) {
                    ans = Math.min(triangle.get(i).get(j), ans);
                }
            }
            // last element coming from top left
            triangle.get(i).set(i, triangle.get(i-1).get(i-1) + triangle.get(i).get(i));
            if(i==r-1) {
                ans = Math.min(triangle.get(i).get(i), ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][]arr = {{-1},{2,3},{1,-1,-3}};
        List<List<Integer>> list = new ArrayList<>();
        for (int[] row: arr) {
            list.add(IntStream.of(row).boxed().collect(Collectors.toList()));
        }
        System.out.println(minimumTotal2(list));
        System.out.println(list);

    }
}
