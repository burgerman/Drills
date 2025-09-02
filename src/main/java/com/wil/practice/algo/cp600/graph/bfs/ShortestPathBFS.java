package com.wil.practice.algo.cp600.graph.bfs;

import java.util.*;

public class ShortestPathBFS {

    /**
     * classic BFS
     * @return
     */
    private static int bfsShortestFind(List<List<Integer>> graph, int src, int dest) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] distances = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        queue.offer(src);
        visited[src] = true;
        distances[src] = 0;
        int current;

        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current == dest) return distances[current];
            if (graph.get(current).isEmpty()) continue;

            for (int neighbor: graph.get(current)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[current]+1;
                    queue.offer(neighbor);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        // Example graph: adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        graph.add(new ArrayList<>(Arrays.asList(1, 2)));
        graph.add(new ArrayList<>(Arrays.asList(0, 3)));
        graph.add(new ArrayList<>(Arrays.asList(0, 3)));
        graph.add(new ArrayList<>(Arrays.asList(1, 2, 4)));
        graph.add(new ArrayList<>(Arrays.asList(3)));
        int result = bfsShortestFind(graph, 0, 4);
        System.out.println("Shortest path length: " + result); // Output: 3
        System.out.printf("Shortest path length: %d%n", result);
    }

}
