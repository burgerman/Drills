package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {

    private static void printResults(List<List<Integer>> solutions) {
        for (List<Integer> solution : solutions) {
            solution.forEach(v->System.out.print(v+" "));
            System.out.println();
        }
    }

    private static List<List<Integer>> combinationSum3(int k, int n) {
        if (k==0 || n==0) return null;
        List<List<Integer>> solutions = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        dfs(solutions, combination, k, n, 1);
        return solutions;
    }


    private static void dfs (List<List<Integer>> solutions, List<Integer> combination, int k, int remainingSum, int startNum) {
        if (k==combination.size() && remainingSum == 0) {
            solutions.add(new ArrayList<>(combination));
            return;
        }
        if (k==combination.size() || remainingSum <= 0) return;

        for (int i=startNum; i<=9; i++) {
            if (i>remainingSum) break;
            combination.add(i);
            dfs(solutions, combination, k, remainingSum-i, i+1);
            combination.remove(combination.size()-1);
        }
    }


    public static void main(String[] args) {
       printResults(combinationSum3(3, 7));
    }
}
