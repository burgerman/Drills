package com.wil.practice.algo.cp600.graph;

import java.util.ArrayList;
import java.util.List;

public class FixDisconnected {

    private static void dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        List<Integer> neighbors = graph.get(node);
        for(Integer neighbor : neighbors) {
            if(neighbor != node && !visited[neighbor]) dfs(graph, neighbor, visited);
        }
    }

    private static String fixGraph(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> components = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            if(!visited[i]) {
                components.add(i);
                dfs(graph, i, visited);
            }
        }
        int addedEdgeNum = components.size()-1;
        if (components.size()==1) return "CONNECTED";
        else {
            List<String> edgeList = new ArrayList<>();
            for (int i = 1; i < components.size(); i++) {
                edgeList.add(components.get(0) + "->" + components.get(i));
            }
            return edgeList.toString();
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        int n = 5;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(3).add(4);
        graph.get(4).add(3);
        System.out.println(fixGraph(graph));
    }


}
