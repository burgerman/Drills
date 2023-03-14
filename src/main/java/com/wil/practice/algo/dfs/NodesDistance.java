package com.wil.practice.algo.dfs;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class NodesDistance {

    private TreeNode root;

    public int getDistance(TreeNode root, int n1, int n2) {
        int[] depths = new int[2];
        int lowestCommonAncestor = lowestCommonAncestorAndNodesDepths(root, n1, n2, 0, depths);
        int distance = depths[0] + depths[1] - 2 * lowestCommonAncestor;
        return distance;
    }


    private int lowestCommonAncestorAndNodesDepths(TreeNode root, int n1, int n2, int depth, int[] depths) {
        if(root == null) {
            return -1;
        } else if(root.val == n1) {
            depths[0] = depth;
            return depth;
        } else if(root.val == n2) {
            depths[1] = depth;
            return depth;
        } else {
            int leftDepth = lowestCommonAncestorAndNodesDepths(root.left, n1, n2, depth+1, depths);
            int rightDepth = lowestCommonAncestorAndNodesDepths(root.right, n1, n2, depth+1, depths);
            if(leftDepth != -1 && rightDepth != -1) {
                return depth;
            } else if (leftDepth!=-1) {
                return leftDepth;
            } else {
                return rightDepth;
            }
        }
    }

    public int getDistanceByMemoization(TreeNode root, int n1, int n2) {
        Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>(128);
        return distanceByMemo(root, n1, n2, memo);
    }

    private int distanceByMemo(TreeNode root, int n1, int n2, Map<Pair<Integer, Integer>, Integer> memo) {
        if(root == null) {
            return -1;
        } else if (root.val == n1 || root.val == n2) {
            return 0;
        }

        Pair<Integer, Integer> pair = new ImmutablePair(n1, n2);
        if(memo.containsKey(pair)) {
            return memo.get(pair);
        }
        int leftDist = distanceByMemo(root.left, n1, n2, memo);
        int rightDist = distanceByMemo(root.right, n1, n2, memo);
        int distance;
        if(leftDist>=0 && rightDist >= 0) {
            distance = leftDist+rightDist+2;
        } else {
            distance = leftDist>=0? leftDist+1:rightDist>=0?rightDist+1:-1;
        }
        memo.put(pair, distance);
        return distance;
    }

    public static void main(String[] args) {
        NodesDistance nodesDistance = new NodesDistance();
        nodesDistance.root = new TreeNode(1);
        nodesDistance.root.left = new TreeNode(2);
        nodesDistance.root.right = new TreeNode(3);
        nodesDistance.root.left.left = new TreeNode(4);
        nodesDistance.root.left.right = new TreeNode(5);
        nodesDistance.root.right.left = new TreeNode(6);
        nodesDistance.root.right.right = new TreeNode(7);
        System.out.println("Distance between 4 and 5 is: " + nodesDistance.getDistanceByMemoization(nodesDistance.root, 4, 6)  + " using DFS(recursive approach)");
        System.out.println("Distance between 4 and 5 is: " + nodesDistance.getDistance(nodesDistance.root, 4, 6) + " using dynamic programming memo");
        NodesDistance nodesDistance2 = new NodesDistance();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        nodesDistance2.root = root;
        System.out.println("Distance between 6 and 8 is: " + nodesDistance2.getDistanceByMemoization(nodesDistance2.root, 6, 8) + " using DFS(recursive approach)");
        System.out.println("Distance between 6 and 8 is: " + nodesDistance2.getDistance(nodesDistance2.root, 6, 8) + " using dynamic programming memo");

    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode (int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

final class MyImmutablePair<L, R> implements Map.Entry<L, R> {
    public final L left;
    public final R right;

    public MyImmutablePair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> MyImmutablePair<L, R> of(L left, R right) {
        return new MyImmutablePair(left, right);
    }
    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    @Override
    public L getKey() {
        return this.left;
    }

    @Override
    public R getValue() {
        return this.right;
    }

    @Override
    public R setValue(R value) {
        throw new UnsupportedOperationException();
    }
}