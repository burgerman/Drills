package com.wil.practice.algo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class prim {

    /**
     * Leverage Priority Queue to reduce the complexity to O(E LogV)
     * @param graph
     * @param V
     * @return
     */
    private static int buildMST(List<List<Edge>> graph, int V) {
        boolean[] visited = new boolean[V];
        int mstWeight = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)-> e1.weight - e2.weight);
        pq.add(new Edge(0, 0));
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int w = e.weight;
            int u = e.vertex;
            if(visited[u]) continue;
            visited[u] = true;
            if(e.weight!=-1) {
                mstWeight += w;
            }
            for (Edge e1 : graph.get(u)) {
                if(visited[e1.vertex] || e1.weight==-1) continue;
                pq.add(e1);
            }
        }
        return mstWeight;
    }



    public static void main(String[] args){
        int V = 4;
        List<List<Edge>> graph = new ArrayList<>();
        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(2, 2));
        graph.get(0).add(new Edge(3, 20));
        graph.get(1).add(new Edge(0, 10));
        graph.get(1).add(new Edge(2, 17));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(0, 2));
        graph.get(2).add(new Edge(1, 17));
        graph.get(2).add(new Edge(3, 3));
        graph.get(3).add(new Edge(0, 20));
        graph.get(3).add(new Edge(1, 5));
        graph.get(3).add(new Edge(2, 3));
        System.out.println("MST Weight: "+ buildMST(graph, V));
    }


    static class Edge {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
