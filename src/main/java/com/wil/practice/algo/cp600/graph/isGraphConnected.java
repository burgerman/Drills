package com.wil.practice.algo.cp600.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class isGraphConnected {


    private static void nodeReach(List<List<Integer>> graph, int n, boolean[] visited) {
        visited[n] = true;
        for (int i = 0; i < graph.get(n).size(); i++) {
            if(!visited[graph.get(n).get(i)]) {
                nodeReach(graph, graph.get(n).get(i), visited);
            }
        }
    }

    private static boolean isConnected(List<List<Integer>> graph){
        int v = graph.size();
        int startVertex = 0;
        boolean[] visited = new boolean[v];
        // O(V+E)
        nodeReach(graph, startVertex, visited);
        for (int i = 0; i < v; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    private static void jointCheck(List<List<Integer>> graph, int n, int vertex, boolean[] visited) {
        if(n!=vertex) {
            visited[n] = true;
            for(int i = 0; i < graph.get(n).size(); i++) {
                if(!visited[graph.get(n).get(i)] && graph.get(n).get(i)!=vertex) {
                    jointCheck(graph, graph.get(n).get(i), vertex, visited);
                }
            }
        }
    }

    private static boolean hasJoint(List<List<Integer>> graph) {
        int v = graph.size();
        int [] edges = new int[v];

        //O(V+E)
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                edges[graph.get(i).get(j)]++;
            }
        }
        // Max Heap
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2)->n2.edgeNum-n1.edgeNum);
        for (int i = 0; i < v; i++) {
            queue.add(new Node(i, edges[i]));
        }

        //O((V+E)lgV)
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int vertex = node.vertex;
            if(graph.get(vertex).size()>0) {
                boolean[] visited = new boolean[v];
                int neighbor = graph.get(vertex).get(0);
                jointCheck(graph, neighbor, vertex, visited);
                for (int i = 0; i < v; i++) {
                    if (!visited[i] && i!=vertex) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
//        graph.add(new ArrayList<>(Arrays.asList(1)));
//        graph.add(new ArrayList<>(Arrays.asList(0,2)));
//        graph.add(new ArrayList<>(Arrays.asList(1,3)));
//        graph.add(new ArrayList<>(Arrays.asList(2, 4)));
//        graph.add(new ArrayList<>(Arrays.asList(3)));
        graph.add(new ArrayList<>(Arrays.asList(1, 4)));
        graph.add(new ArrayList<>(Arrays.asList(0, 2)));
        graph.add(new ArrayList<>(Arrays.asList(1, 3)));
        graph.add(new ArrayList<>(Arrays.asList(2, 4)));
        graph.add(new ArrayList<>(Arrays.asList(0, 3)));
        if(isConnected(graph)) {
            System.out.println("Graph connected");
            System.out.println("Graph has a joint vertex? "+hasJoint(graph));
        }

    }

    static class Node {
        int vertex;
        int edgeNum;
        public Node(int vertex, int edgeNum) {
            this.vertex = vertex;
            this.edgeNum = edgeNum;
        }
    }

}
