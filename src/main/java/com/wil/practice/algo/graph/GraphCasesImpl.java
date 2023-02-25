package com.wil.practice.algo.graph;

import java.util.*;

public class GraphCasesImpl {

}

class Graph{
    private int vertices;
    private List<List<Integer>> graph;
    private boolean[] visited;

    Graph(int vertices) {
        this.vertices = vertices;
        this.graph = new ArrayList<>();
        this.visited = new boolean[vertices];
        int i = 0;
        while (i++<vertices) {
            this.graph.add(i, new ArrayList<>());
        }
    }

    void addEdge (int v1, int v2) {
         this.graph.get(v1).add(v2);
         this.graph.get(v2).add(v1);
    }

    void DFS_Query (int vertexSrc, int vertexDest) {

    }

    void DFS(int v) {

    }

    public static void main(String[] args) {
        Graph g = new Graph(5);


    }


}
