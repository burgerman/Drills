package com.wil.practice.algo.cp600.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringDifference {


    private static int[] getRemovableIndices(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        // 1. Find the first mismatch from the left.
        int left = 0;
        while (left < n2 && str1.charAt(left) == str2.charAt(left)) {
            left++;
        }
        // 2. Find the first mismatch from the right.
        int right1 = n1 - 1;
        int right2 = n2 - 1;
        while (right2 >= 0 && str1.charAt(right1) == str2.charAt(right2)) {
            right1--;
            right2--;
        }
        // 3. The valid indices are between the two mismatch points.
        // If right1 > left, it means the mismatches "crossed", which implies more
        // than one character is different, so no solution exists.
        List<Integer> result = new ArrayList<>();
        if (left >= right1) {
            for (int k = right1; k <= left; k++) {
                // To be a valid removable index k, the prefix str1[0..k-1] must match str2[0..k-1]
                // AND the suffix str1[k+1..] must match str2[k..].
                // Our left and right1 pointers already guarantee this for any k in the range.
                result.add(k);
            }
        }

        // 4. Format the output as specified.
        if (result.isEmpty()) {
            return new int[]{-1};
        } else {
            // Convert List<Integer> to int[]
            int[] resultArray = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                resultArray[i] = result.get(i);
            }
            return resultArray;
        }
    }


    public static void main(String[] args) {

        // There's exactly one character in the s1 more than s2
        String s1 = "aabbb";
        String s2 = "aabb";
        System.out.println(Arrays.toString(getRemovableIndices(s1,s2)));
    }
}
