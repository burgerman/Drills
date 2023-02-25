package com.wil.practice.utils;

public class SwapUtils {

    public static void swapByXOR(Integer[]arr, Integer i, Integer j) {
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }

}
