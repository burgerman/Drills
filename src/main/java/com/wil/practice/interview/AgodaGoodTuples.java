package com.wil.practice.interview;

public class AgodaGoodTuples {

    public static int countGoodTuples(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if ((nums[i] == nums[i+1] && nums[i] != nums[i+2]) ||
                    (nums[i] == nums[i+2] && nums[i] != nums[i+1]) ||
                    (nums[i+1] == nums[i+2] && nums[i+1] != nums[i])) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
       int count = countGoodTuples(new int[]{1, 1, 1, 2, 1, 3, 4});
        System.out.println(count);
    }
}
