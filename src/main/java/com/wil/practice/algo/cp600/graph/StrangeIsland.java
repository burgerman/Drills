package com.wil.practice.algo.cp600.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrangeIsland {

    // traverse original and reversed graph using DFS to find safe destination
    // boolean [i] reachableFromS == boolean [i] reachableToS
    private static void findRoute(List<List<Integer>>graph, int S) {
        int v = graph.size();
        boolean[] reachableFromS = new boolean[v];
        boolean[] reachableToS = new boolean[v];
        dfs(graph, S, reachableFromS);
        List<List<Integer>> reversedGraph = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            reversedGraph.add(new ArrayList<>());
        }
        // reverse roads
        for (int i = 0; i < v; i++) {
            if(graph.get(i).size()>0) {
                for (int j = 0; j < graph.get(i).size(); j++) {
                    reversedGraph.get(graph.get(i).get(j)).add(i);
                }
            }
        }
        dfs(reversedGraph, S, reachableToS);
        for (int i = 0; i < v; i++) {
            if(reachableFromS[i] && reachableToS[i]) {
                System.out.println("Safe Destination: "+i);
            }
        }
    }

    private static void dfs(List<List<Integer>> graph, int d, boolean[]visited) {
        visited[d] = true;
        if(graph.get(d).size()>0) {
            for (int i = 0; i < graph.get(d).size(); i++) {
                if(!visited[graph.get(d).get(i)]) {
                    dfs(graph, graph.get(d).get(i), visited);
                }
            }
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(2, 3));
//        graph.add(Arrays.asList(5));
        graph.add(Arrays.asList(4));
        graph.add(Arrays.asList(2,4));
        graph.add(Arrays.asList(0));
//        graph.add(new ArrayList<>());
        findRoute(graph, 0);
    }


}


