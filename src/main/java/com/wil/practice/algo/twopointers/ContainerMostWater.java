package com.wil.practice.algo.twopointers;

public class ContainerMostWater {

    private static int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int max = 0;
        int minBar, amount;
        while (l<r) {
            minBar = Math.min(height[l], height[r]);
            amount = minBar * (r-l);
            max = Math.max(amount, max);
            if (height[l]>height[r]) r--;
            else l++;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] heights = new int[] {1,8,6,2,5,4,8,3,7};
        int[] heights2 = new int[] {1,1};
        System.out.printf("Max Water: %d%n", maxArea(heights));
        System.out.printf("Max Water: %d%n", maxArea(heights2));
    }

}
