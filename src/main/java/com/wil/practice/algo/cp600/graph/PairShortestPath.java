package com.wil.practice.algo.cp600.graph;

import java.util.Arrays;

public class PairShortestPath {

    private static int findMinDistance(int[][] distance, boolean[] visited, boolean[][] reachable, int u) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;
        for (int i =0; i<distance.length; i++) {
            if(visited[i] || !reachable[u][i]) continue;
            if(distance[u][i] < minDistance) {
                minDistance = distance[u][i];
                minVertex = i;
            }
        }
        return minVertex;
    }


    private static void reachable(int[][] graph, boolean[][] reachable, boolean[]visited, int source, int u, int v) {
        if(visited[v]) return;
        if (graph[u][v]==0) {
            reachable[u][v] = false;
            return;
        }
        visited[v] = true;
        reachable[source][v] = true;
        for (int i = 0; i < graph.length; i++) {
            if (i!=source) reachable(graph, reachable, visited, source, v, i);
        }

    }

    private static int pairShortestPath (int[][] graph, int from, int to) {
        int n = graph.length;
        int[][] pairDistance = new int[n][n];
        boolean[][] reachable = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(pairDistance[i], Integer.MAX_VALUE);
            Arrays.fill(reachable[i], false);
            reachable[i][i] = true;
        }

        for (int src = 0; src <n; src++) {
            boolean[] visited = new boolean[n];
            for (int des = 0; des < n; des++) {
                if(src==des) continue;
                reachable(graph, reachable, visited, src, src, des);
            }
        }

        if(!reachable[from][to]) return Integer.MAX_VALUE;
        for (int u = from; u <= from; u++) {
            boolean[] visited  = new boolean[n];
            pairDistance[u][u] = 0;
            for (int t=0; t<n; t++) {
                int v = findMinDistance(pairDistance, visited, reachable, u);
                if (v<0) continue;
                visited[v] = true;
                for (int i=0; i<n; i++) {
                    if (visited[i] || graph[v][i]==0) continue;
                    if(pairDistance[u][v]+graph[v][i]<pairDistance[u][i]) {
                        pairDistance[u][i] = pairDistance[u][v]+graph[v][i];
                    }
                }
            }
        }
        return pairDistance[from][to];
    }


    public static void main(String[] args) {

        int graph[][] = new int[][] {
                { 0, 4, 4, 0, 0, 0},
                { 0, 0, 2, 0, 0, 0},
                { 0, 0, 0, 1, 3, 0},
                { 0, 0, 0, 0, 0, 3},
                { 0, 0, 0, 0, 0, 2},
                { 0, 0, 6, 0, 0, 0}
        };
        int u = 0;
        int v = 5;
        int uvDistance = pairShortestPath(graph, u, v);
        System.out.println(uvDistance);

    }


}
