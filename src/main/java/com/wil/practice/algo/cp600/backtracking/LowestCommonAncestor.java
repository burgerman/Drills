package com.wil.practice.algo.cp600.backtracking;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);
        TreeNode tn4 = new TreeNode(5);
        TreeNode tn5 = new TreeNode(6);
        TreeNode tn6 = new TreeNode(7);
        root.setLeft(tn2);
        root.setRight(tn5);
        tn2.setLeft(tn1);
        tn2.setRight(tn3);
        tn5.setLeft(tn4);
        tn5.setRight(tn6);

        System.out.println(commonAncestor(root, tn4, tn6).val);
    }

    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = commonAncestor(root.left, p, q);
        TreeNode right = commonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }



}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
}
