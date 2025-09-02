package com.wil.practice.algo.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class NodeTraversal {


    private static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }


    private static void inOrder (Node root) {
        if (root == null) return;

        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    private static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }



    public void preOrder(Node root) {
        if (root == null) return;

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);  // Same as addFirst()

        while (!stack.isEmpty()) {
            Node current = stack.pop();  // Same as removeFirst()

            // Process current node
            System.out.print(current.data + " ");

            // Push right child first (so left is processed first)
            if (current.right != null) {
                stack.push(current.right);
            }

            // Push left child second (so it's on top)
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n4.left = n2;
        n4.right = n5;
        n2.left = n1;
        n2.right = n3;
        inOrder(n4);
        System.out.println();
        Node n6 = new Node(1);
        Node n7 = new Node(2);
        Node n8 = new Node(3);
        Node n9 = new Node(4);
        Node n10 = new Node(5);
        n10.left = n8;
        n10.right = n9;
        n8.left = n6;
        n8.right = n7;
        postOrder(n10);
    }


}
