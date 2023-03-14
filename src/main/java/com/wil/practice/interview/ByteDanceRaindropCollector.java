package com.wil.practice.interview;

import java.util.HashMap;

public class ByteDanceRaindropCollector {

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(rainwaterCollector(arr));

    }

    public static int rainwaterCollector(int[] arr) {
        if(arr.length<1){
            return 0;
        }
        int n = arr.length;
        int left = 0, right = n-1;
        int num = 0;
        int leftMax = arr[left];
        int rightMax = arr[right];
        while (left<=right) {
            leftMax = leftMax>arr[left]? leftMax: arr[left];
            rightMax = rightMax>arr[right]? rightMax:arr[right];
            // 左右最短的一边同当前高度的差即为当前一边最多所盛雨水
            if(leftMax < rightMax) {
                num += leftMax - arr[left];
                left++;
            } else {
                num += rightMax - arr[right];
                right--;
            }
        }
        return num;
    }
}
