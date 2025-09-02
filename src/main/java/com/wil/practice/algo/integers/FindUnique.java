package com.wil.practice.algo.integers;

import java.util.List;

public class FindUnique {

    public static int findUnique(List<Integer> nums) {
        int result = 0;
        for (int num : nums) {
            // XOR op-get identity(a ^ 0 = a) where all other numbers appear exactly twice b ^ b = 0
            result ^= num;
        }
        return result;
    }


}
