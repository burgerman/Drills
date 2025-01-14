package com.wil.practice.interview;

public class AmazonOverlap1401 {

    private static boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int minDistanceY = (y1 <= y_center && y_center <= y2) ? 0 : Math.min(Math.abs(y1 - y_center), Math.abs(y2 - y_center));
        int minDistanceX = (x1 <= x_center && x_center <= x2) ? 0 : Math.min(Math.abs(x1 - x_center), Math.abs(x2 - x_center));
        return minDistanceX * minDistanceX + minDistanceY * minDistanceY <= radius * radius;
    }

    public static void main(String[] args) {
        int x1=1, x2=3, y1=-2, y2=0;
        int radius = 1;
        int xCenter=0, yCenter = 0;
        System.out.println(checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2));
    }
}
