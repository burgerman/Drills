package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {

    private static void printResults(List<List<TreeNode>> solutions) {
        for (List<TreeNode> solution : solutions) {
            solution.forEach(node->System.out.print(node.val+" "));
            System.out.println();
        }
    }

    private static void pathSum(TreeNode root, int targetSum) {
        if(root == null) return;
        List<List<TreeNode>> solutions = new ArrayList<>();
        List<TreeNode> path = new ArrayList<>();
        dfs(solutions, path, root, targetSum);
        printResults(solutions);
    }

    private static void dfs(List<List<TreeNode>> solutions, List<TreeNode> path, TreeNode root, int remainingSum) {
        if (root == null) return;
        path.add(root);
        if (remainingSum-root.val == 0) {
            solutions.add(new ArrayList<>(path));
        }
        dfs(solutions, path, root.left, remainingSum - root.val);
        dfs(solutions, path, root.right, remainingSum - root.val);
        path.remove(path.size()-1);
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode tn1 = new TreeNode(4);
        TreeNode tn2 = new TreeNode(8);
        TreeNode tn3 = new TreeNode(13);
        TreeNode tn4 = new TreeNode(4);
        root.setLeft(tn1);
        root.setRight(tn2);
        tn2.setLeft(tn3);
        tn2.setRight(tn4);

        pathSum(root, 17);
    }

}
