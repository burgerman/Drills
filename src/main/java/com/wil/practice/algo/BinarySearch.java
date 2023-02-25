package com.wil.practice.algo;

import com.wil.practice.annotations.AnnotationUtil;
import com.wil.practice.annotations.Params;

import java.security.Key;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
@AnnotationUtil(comment = "Binary Search", params = {})
public class BinarySearch {

    private class Node {
        private String key;
        private String value;
        private Node left, right;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node root;
    private int nodeNum;

    public void preOrder(Node node) {
        if(node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(Node node) {
        if(node!=null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if(node!=null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    public void levelOrder(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.offer(node);
        while(!que.isEmpty()){
            Node n = que.poll();
            System.out.println(n.key);
            if(n.left!=null) {
                que.offer(n.left);
            }
            if(n.right!=null) {
                que.offer(n.right);
            }
        }
    }

    public void insert(String key, String value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, String key, String value) {
        if (node != null) {
            if(key.compareTo(node.key) == 0) {
                node.value = value;
            } else if (key.compareTo(node.key) < 0) {
                node.left = insert(node.left, key, value);
            } else{
                node.right = insert(node.right, key, value);
            }
            return node;
        } else{
            nodeNum++;
            return new Node(key, value);
        }
    }

    private boolean containNode(Node node, String key) {
        if( node!=null && key!=null) {
            if(key.compareTo(node.key) == 0) {
                return true;
            } else if (key.compareTo(node.key)<0 && node.left!=null) {
                return containNode(node.left, key);
            } else if (key.compareTo(node.key)>0 && node.right!=null) {
                return containNode(node.right, key);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String search(Node node, String key) {
        if (node == null || key == null) {
            return null;
        } else {
            if(key.compareTo(node.key) == 0) {
                return node.value;
            } else if(key.compareTo(node.key)<0 && node.left!=null) {
                return search(node.left, key);
            } else if (key.compareTo(node.key)>0 && node.right!=null) {
                return search(node.right, key);
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        bs.insert("1", "p1");
        bs.insert("3", "p3");
        bs.insert("5", "p5");
        bs.insert("4", "p4");
        bs.insert("7", "p7");
        bs.insert("2", "p2");
        bs.insert("6", "p6");
        bs.insert("8", "p8");
        bs.preOrder(bs.root);
        System.out.println("-------------------------------------");
        bs.inOrder(bs.root);
        System.out.println("-------------------------------------");
        bs.postOrder(bs.root);
        System.out.println("-------------------------------------");
        System.out.println(bs.containNode(bs.root, "5"));
        System.out.println(bs.search(bs.root, "5"));
        System.out.println("-------------------------------------");
        bs.levelOrder(bs.root);
    }
}
