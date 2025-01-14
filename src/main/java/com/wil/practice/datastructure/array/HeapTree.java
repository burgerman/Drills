package com.wil.practice.datastructure.array;

import com.wil.practice.annotations.AnnotationUtil;

import java.util.Queue;


@AnnotationUtil(comment = "Heap Tree", params = {})
public class HeapTree {

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

    public HeapTree() {
        this.nodeNum = 0;
    }

    public void maxInsert(String key, String value) {
        root = maxInsert(root, key, value);
    }

    private Node maxInsert(Node node, String key, String value) {
        if (node != null) {
            if(value.compareTo(node.value) > 0) {
                node.value = value;
            } else if (node.left != null && node.right == null) {
                node.right = maxInsert(node.right, key, value);
            } else{
                node.left = maxInsert(node.left, key, value);
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
        HeapTree ht = new HeapTree();
        ht.maxInsert("1", "10");
        ht.maxInsert("2", "5");
        ht.maxInsert("3", "3");
        ht.maxInsert("4", "2");
        ht.maxInsert("5", "4");
        ht.maxInsert("6", "15");
    }
}
