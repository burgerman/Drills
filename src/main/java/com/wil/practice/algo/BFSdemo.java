package com.wil.practice.algo;

import java.util.*;

public class BFSdemo {

    static Queue q = new ArrayDeque<>();

    public static boolean hasPathBFS(Node source, Node dest) {
        ArrayDeque<Node> nextToVisit = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();
        nextToVisit.offerLast(source);
        while(!nextToVisit.isEmpty()) {
            Node node = nextToVisit.pollLast();
            if(node == dest) {
                q.addAll(visited);
                return true;
            }
            if(visited.contains(node)) {
                continue;
            }
            visited.add(node);
           if(node.getAdjacent().size()>0) {
               for(Node child : node.getAdjacent()) {
                   nextToVisit.offerLast(child);
               }
           }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node0.adjacent.add(node1);
        node1.adjacent.add(node2);
        node3.adjacent.add(node4);
        node3.adjacent.add(node5);
        node3.adjacent.add(node6);
        node4.adjacent.add(node5);
        node4.adjacent.add(node6);
        node5.adjacent.add(node6);

        System.out.println(hasPathBFS(node3, node5));
        q.stream().forEach(System.out::println);
    }


}


class Node {
    int val;
    List<Node> adjacent = new ArrayList<>();

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public List<Node> getAdjacent() {
        return adjacent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", adjacent=" + adjacent +
                '}';
    }
}
