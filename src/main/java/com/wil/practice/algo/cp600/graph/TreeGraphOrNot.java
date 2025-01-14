package com.wil.practice.algo.cp600.graph;

import java.util.*;

public class TreeGraphOrNot {



    private static boolean isTree(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        Set<String> set = new HashSet<>(n-1, 1.0f);
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if(!visited[node]) {
                visited[node] = true;
                if(graph.get(node).size() > 0) {
                    for (int i = 0; i < graph.get(node).size(); i++) {
                        int u = graph.get(node).get(i)>node?node:graph.get(node).get(i);
                        int v = graph.get(node).get(i)>node?graph.get(node).get(i):node;
                        String link = u+"-"+v;
                        if(!visited[graph.get(node).get(i)]) {
                            queue.add(graph.get(node).get(i));
                            set.add(link);
                        } else {
                            if(!set.contains(link)) {
                                // detected a cycle in the graph
                                return false;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                // detected unreachable node in the graph
                return false;
            }
        }
        return true;
    }




    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 2));
        graph.add(Arrays.asList(0, 3, 4));
        graph.add(Arrays.asList(0, 5, 6));
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(2));
        graph.add(Arrays.asList(2));
        System.out.println(isTree(graph));
    }

}
