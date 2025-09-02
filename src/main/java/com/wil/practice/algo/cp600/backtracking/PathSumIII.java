package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSumIII {


    private static int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        return dfs(root, prefixSum, 0, targetSum);
    }

    private static int dfs (TreeNode root, Map<Integer, Integer> prefixSum, int currentSum, int targetSum) {
        if (root == null) return 0;
        currentSum += root.val;
        // counting paths starting from the root, but from any ancestor
        // target: currentSum - prefixSum[i] = targetSum ->  prefixSum[i] = currentSum - targetSum
        // when prefixSum[i] exists means the path of targetSum found
        int sum = prefixSum.getOrDefault(currentSum-targetSum, 0);
        int pathCount = prefixSum.getOrDefault(currentSum, 0);
        prefixSum.put(currentSum, pathCount + 1);
        sum += dfs(root.left, prefixSum, currentSum, targetSum);
        sum += dfs(root.right, prefixSum, currentSum, targetSum);
        prefixSum.put(currentSum, prefixSum.get(currentSum)-1);
        return sum;
    }




    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        TreeNode tn1 = new TreeNode(5);
        TreeNode tn2 = new TreeNode(-3);
        TreeNode tn3 = new TreeNode(3);
        TreeNode tn4 = new TreeNode(2);
        TreeNode tn5 = new TreeNode(11);
        root.setLeft(tn1);
        root.setRight(tn2);
        tn1.setLeft(tn3);
        tn1.setRight(tn4);
        tn2.setRight(tn5);

        System.out.println(pathSum(root, 8));
    }



}
