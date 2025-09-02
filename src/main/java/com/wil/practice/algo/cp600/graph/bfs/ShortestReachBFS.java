package com.wil.practice.algo.cp600.graph.bfs;

import java.util.*;

public class ShortestReachBFS {
    /**
     * classic BFS
     */
    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        int u, v;
        for (List<Integer> edge : edges) {
            u = edge.get(0);
            v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        int[] reaches = new int[n+1];
        visited[s] = true;
        queue.offer(s);
        int node;
        while(!queue.isEmpty()) {
            node = queue.pollFirst();
            if (graph.get(node).isEmpty()) continue;

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    reaches[neighbor] = reaches[node] + 6;
                    queue.add(neighbor);
                }
            }
        }

        for (int i=1; i<=n; i++) {
            if (i==s) continue;
            if(reaches[i]>0) res.add(reaches[i]);
            else res.add(-1);
        }
        return res;
    }



    public static void main(String[] args) {
        int n = 5;
        int m = 3;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(1, 2)));
        edges.add(new ArrayList<>(List.of(1, 3)));
        edges.add(new ArrayList<>(List.of(3, 4)));
        int s = 1;
        List<Integer> res = bfs(n, m, edges, s);
        System.out.println(res);
    }
}
