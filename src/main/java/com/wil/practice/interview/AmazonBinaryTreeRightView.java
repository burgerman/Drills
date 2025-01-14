package com.wil.practice.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AmazonBinaryTreeRightView {

    private static List<Integer> dfs(List<Integer> views, TreeNode node, int depth) {
        if(node == null) {
            return views;
        }
        if(depth == views.size()) {
            views.add(node.val);
        }
        depth++;
        views = dfs(views, node.right, depth);
        views = dfs(views, node.left, depth);
        return views;
    }


    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> views = new ArrayList<>();
        views = dfs(views, root, 0);
        return views;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,new TreeNode(2, null, null), null);
        System.out.println(rightSideView(root));
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}