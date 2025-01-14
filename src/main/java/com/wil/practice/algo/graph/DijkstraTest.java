package com.wil.practice.algo.graph;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import java.util.List;

public class DijkstraTest {

    public static void main(String[] args) {
        DirectedAcyclicGraph<String, DefaultEdge> directedGraph
                = new DirectedAcyclicGraph<>(DefaultEdge.class);
        directedGraph.addVertex("v1");
        directedGraph.addVertex("v2");
        directedGraph.addVertex("v3");
        directedGraph.addVertex("v4");
        directedGraph.addEdge("v1", "v2");
        directedGraph.addEdge("v2", "v4");
        directedGraph.addEdge("v4", "v3");
        directedGraph.addEdge("v3", "v1");
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(directedGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath("v1", "v4").getVertexList();
        System.out.println(shortestPath);

    }

}
