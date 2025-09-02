package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    private static void printResults(List<List<Integer>> solutions) {
        for (List<Integer> solution : solutions) {
            solution.forEach(v->System.out.print(v+" "));
            System.out.println();
        }
    }

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> solutions = new ArrayList<>();
        if (candidates[0]>target) return solutions;
        List<Integer> solution = new ArrayList<>();
        dfs(candidates, target, 0, solutions, solution);
        return solutions;
    }

    private static void dfs (int[] candidates, int target, int index, List<List<Integer>> solutions, List<Integer> solution) {
        if (target == 0) {
            solutions.add(new ArrayList<>(solution));
            return;
        }
        if (target<0 || index == candidates.length) return;

        for (int i=index; i<candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > target) break;
            solution.add(candidates[i]);
            dfs(candidates, target-candidates[i], i+1, solutions, solution);
            solution.remove(solution.size()-1);
        }
    }




    public static void main(String[] args) {
        int[] nums1 = {10,1,2,7,6,1,5};
        int[] nums2 = {2,5,2,1,2};
        int target1 = 8;
        int target2 = 5;
        printResults(combinationSum2(nums1, target1));
        printResults(combinationSum2(nums2, target2));
    }
}
