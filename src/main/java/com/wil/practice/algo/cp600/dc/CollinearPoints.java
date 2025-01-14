package com.wil.practice.algo.cp600.dc;

import java.util.*;

public class CollinearPoints {

    private static void findCollinearPoints(int[][] points) {

        List<List<Integer>> collinearPoints = new ArrayList<>();
        List<Double> slopes = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (j==i) continue;
                for (int k = 0; k < points.length; k++) {
                    if(k==i || k ==j) continue;
                    int dx1 = points[j][0] - points[i][0], dy1 = points[j][1] - points[i][1];
                    int dx2 = points[k][0] - points[j][0], dy2 = points[k][1] - points[j][1];
                    int dx3 = points[k][0] - points[i][0], dy3 = points[k][1] - points[i][1];
                    double slope1 = dy1/dx1, slope2 = dy2/dx2, slope3 = dy3/dx3;
                    if (slope1==slope2 && slope1==slope3) {
                        List<Integer> cp = Arrays.asList(i, j, k);
                        if (!collinearPoints.contains(cp)) {
                            collinearPoints.add(index, cp);
                            slopes.add(index, slope1);
                            index++;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < collinearPoints.size(); j++) {
            List<Integer> cp = collinearPoints.get(j);
            for (int i = 0; i < points.length; i++) {
                if(!cp.contains(i)){
                    int dx = points[i][0] - points[cp.get(0)][0], dy = points[i][1] - points[cp.get(0)][1];
                    double slope = dy/dx;
                    if (slope == slopes.get(j)){
                        cp.add(i);
                        collinearPoints.set(j, cp);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{
                {2, 4},
                {4, 5},
                {1, 2},
                {-10, -20},
                {13, 25},
                {17, 34}
        };
       findCollinearPoints(points);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
