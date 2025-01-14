package com.wil.practice.algo.cp600.graph;

import java.util.*;

public class DetermineDAG {


    /**
     * Using Kahn's algo (Topological Sorting)
     * If one graph is a DAG, it should be sorted topologically
     * @param graph
     * @return boolean
     */
    private static boolean isDAG(List<List<Integer>> graph) {
        int v = graph.size();
        int count = 0;
        int[] inDegrees = new int[v];
        List<Integer> result = new LinkedList<>();
        // O(V+E)
        for (int i = 0; i < v; i++) {
            if (graph.get(i).size()==0) continue;
            for (int j = 0; j < graph.get(i).size(); j++) {
                inDegrees[graph.get(i).get(j)]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        // O(V+E)
        while(!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            count++;
            if (graph.get(node).size()==0) continue;
            for (int i = 0; i < graph.get(node).size(); i++) {
                int next = graph.get(node).get(i);
                inDegrees[next]--;
                if(inDegrees[next]==0) {
                    queue.add(next);
                }
            }
        }

        for(int i = 0; i < v; i++) {
            if(inDegrees[i]!=0) return false;
        }
        return count == v;
    }



    public static void main(String[] args) {
        List<List<Integer>> graph = new LinkedList<>();
        graph.add(new LinkedList<>(Arrays.asList(1)));
        graph.add(new LinkedList<>(Arrays.asList(2)));
        graph.add(new LinkedList<>(Arrays.asList(3)));
        graph.add(new LinkedList<>());
        System.out.println("Is DAG? "+isDAG(graph));

    }
}
