package com.wil.practice.algo.cp600.backtracking;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.matching.blossom.v5.KolmogorovWeightedPerfectMatching;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TravelSalesman {

    private static int minWeight = Integer.MAX_VALUE;
    private static int minWeight2 = Integer.MAX_VALUE;
    private static int minWeight_lower = Integer.MAX_VALUE;
    private static int minWeight_upper = Integer.MAX_VALUE;
    private static int baselineMinWeight = Integer.MAX_VALUE;
    private static int size = 0;

    public static List<Edge> loadInput(String filePath) {
        File dictFile = new File(filePath);
        if(dictFile.exists()) {
            List<Edge> input = new ArrayList<>();
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(dictFile))) {
                int lineNum = (int) Files.lines(Paths.get(dictFile.getAbsolutePath())).count();
                size = Integer.parseInt(bufferedReader.readLine());
                String edgeLine;
                for (int i = 1; i < lineNum; i++) {
                    edgeLine=bufferedReader.readLine();
                    String[] edgeStr = edgeLine.split("\\s+");
                    input.add(new Edge(Integer.parseInt(edgeStr[0]), Integer.parseInt(edgeStr[1]), Integer.parseInt(edgeStr[2])));
                }
                return input;
            } catch (IOException io) {
            }
        }
        return null;
    }

    public static int[][] getGraph(List<Edge> inputEdges) {
        int[][] graph = new int[size][size];
        for (Edge e : inputEdges) {
            graph[e.u][e.v] = e.weight;
            graph[e.v][e.u] = e.weight;
        }
        return graph;
    }


    private static void backtracking(boolean[] visited, int[][] graph, int source, int current, int cost, int count) {
        if(count==graph.length-1 && graph[current][source]!=0) {
            minWeight = Math.min(minWeight, cost+graph[current][source]);
            return;
        }
        for (int loc = 0; loc < graph.length; loc++) {
            if(visited[loc]) continue;
            if(graph[current][loc]!=0) {
                visited[loc] = true;
                backtracking(visited, graph, source, loc,cost+graph[current][loc], count+1);
                visited[loc] = false;
            }
        }
    }

    private static int tspInBacktracking(int[][] graph, int startLoc) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[startLoc] = true;
        backtracking(visited, graph, startLoc,  startLoc, 0, 0);
        return minWeight;
    }

    private static List<Edge> getPrimMST(int[][] graph) {
        int n = graph.length;
        List<Edge> edges = new ArrayList<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (edges.size() < n-1) {
            int min = Integer.MAX_VALUE;
            int u = 0, v = 0;
            for(int i = 0; i < n; i++) {
                if (visited[i]) {
                    for(int j = 0; j < n; j++) {
                        if(!visited[j] && graph[i][j]!=0) {
                            if(graph[i][j]<min) {
                                min = graph[i][j];
                                u = i;
                                v = j;
                            }
                        }
                    }
                }
            }
            edges.add(new Edge(u, v, min));
            visited[v] = true;
        }
        return edges;
    }

    private static List<Integer> getOddDegrees(List<Edge> mst, int n) {
        if (mst.size()<1) return null;
        List<Integer> oddDegrees = new ArrayList<>();
        int[] degrees = new int[n];
        for (int i = 0; i < mst.size(); i++) {
            Edge edge = mst.get(i);
            degrees[edge.u]++;
            degrees[edge.v]++;
        }

        for (int i = 0; i < n; i++) {
            if(degrees[i]%2!=0) oddDegrees.add(i);
        }
        return oddDegrees;
    }

    private static List<Edge> findPerfectMatching(int[][] graph, List<Integer> oddNodes) {
        List<Edge> matching = new ArrayList<>();

        // check all pairs of locations
        boolean[] checked = new boolean[graph.length];
        for (int i = 0; i < oddNodes.size(); i++) {
            int locA = oddNodes.get(i);
            if (checked[locA]) continue;
            int minWeight = Integer.MAX_VALUE;
            int nextDest = -1;

            // Find the minimum weight edge from current location to another unmatched location
            for (int j = i + 1; j < oddNodes.size(); j++) {
                int locB = oddNodes.get(j);
                if(graph[locA][locB]==0 || checked[locB]) continue;
                if (graph[locA][locB] < minWeight) {
                    minWeight = graph[locA][locB];
                    nextDest = locB;
                }
            }

            // Add matching edge
            if (nextDest != -1) {
                checked[locA] = true;
                checked[nextDest] = true;
                matching.add(new Edge(locA, nextDest, minWeight));
            }
        }
        return matching;
    }

    private static List<Integer> getEulerian(List<Edge> subGraph, int n, int startLoc) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (Edge edge : subGraph) {
            edges.get(edge.u).add(edge.v);
            edges.get(edge.v).add(edge.u);
        }

        List<Integer> eulerianCircuit = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startLoc);

        while (!stack.isEmpty()) {
            int loc = stack.peek();
            if (edges.get(loc).isEmpty()) {
                // Add to circuit
                int l = stack.pop();
                if(!eulerianCircuit.contains(l)) eulerianCircuit.add(l);
            } else {
                // explore neighbor locations
                int neighbor = edges.get(loc).remove(0);
                // Remove dup edge
                edges.get(neighbor).remove((Integer) loc);
                stack.push(neighbor);
            }
        }

        return eulerianCircuit;
    }

    private static int tspUsingMST(int[][] graph, int startLoc) {
        List<Edge> mst = getPrimMST(graph);
        List<Integer> oddNodes = getOddDegrees(mst, graph.length);
        List<Edge> matching = findPerfectMatching(graph, oddNodes);
        mst.addAll(matching);

        List<Integer> eulerian = getEulerian(mst, graph.length, startLoc);

        boolean[] visited = new boolean[graph.length];

        int prevLoc = eulerian.get(0);
        visited[prevLoc] = true;
        int weight = 0;
        for (int i = 1; i < eulerian.size(); i++) {
            int loc = eulerian.get(i);
            if(visited[loc]) continue;
            visited[loc] = true;
            weight += graph[prevLoc][loc];
            prevLoc = loc;
        }

        weight += graph[prevLoc][eulerian.get(0)];

        return weight;
    }


    /**
     * Twice-Around-the-Tree
     * @param graph
     * @return
     */
    public static int tspUsingMST2(int[][] graph) {
        int n = graph.length;

        // Step 1: Build the MST using Prim's Algorithm
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        int[] key = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Step 2: Perform a double traversal on the MST using DFS
        List<Integer> preorderTraversal = new ArrayList<>();
        boolean[] dfsVisited = new boolean[n];
        dfs(0, parent, preorderTraversal, dfsVisited);

        // Step 3: Convert the Eulerian circuit to a Hamiltonian cycle by shortcutting
        Set<Integer> visitedNodes = new HashSet<>();
        List<Integer> tspTour = new ArrayList<>();
        for (int node : preorderTraversal) {
            if (!visitedNodes.contains(node)) {
                tspTour.add(node);
                visitedNodes.add(node);
            }
        }
        tspTour.add(0); // Return to the starting node

        // Step 4: Compute the cost of the TSP tour
        int tspCost = 0;
        for (int i = 0; i < tspTour.size() - 1; i++) {
            tspCost += graph[tspTour.get(i)][tspTour.get(i + 1)];
        }

        return tspCost;
    }

    // Find the vertex with the minimum key value
    private static int minKey(int[] key, boolean[] visited, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Perform a DFS traversal on the MST to generate the Eulerian circuit
    private static void dfs(int node, int[] parent, List<Integer> preorder, boolean[] visited) {
        visited[node] = true;
        preorder.add(node);
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == node && !visited[i]) {
                dfs(i, parent, preorder, visited);
            }
        }
    }


//    private static int estimateTwoWeight(boolean[] visited, List<List<Edge>> edges, int current) {
//        int weight = 0;
//        int min = Integer.MAX_VALUE;
//        // use priority queue to find two smallest edges
//        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)->e1.weight-e2.weight);
//        for (Edge e : edges.get(current)) {
//            if(visited[e.v]) continue;
//            min = Math.min(min, e.weight);
//        }
//        weight += min;
//        for (int i = 0; i < visited.length; i++) {
//            if(visited[i]) continue;
//            for (Edge e: edges.get(i)){
//                if(visited[e.v])continue;
//                pq.add(e);
//            }
//        }
//        if(pq.size()!=0) {
//            Edge firstMin = pq.poll();
//            weight += firstMin.weight;
//            Edge secondMin = pq.poll();
//            if (secondMin != null) weight += secondMin.weight;
//        }
//
//        // compute the average of the two smallest edges for unvisited
//        return weight/2;
//    }

    public static boolean triangleInequalityChecker(int[][] graph) {
        int n = graph.length;

        // Iterate over all triplets of nodes (u, v, w)
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                for (int w = 0; w < n; w++) {
                    if (u != v && v != w && u != w) { // Ensure distinct nodes
                        if(graph[u][v]==0 || graph[u][w] ==0 || graph[w][v]==0) continue;
                        int directDistance = graph[u][v];
                        int throughThirdNodeDistance = graph[u][w] + graph[w][v];

                        // Check if the triangle inequality is violated
                        if (directDistance > throughThirdNodeDistance) {
                            System.out.printf(
                                    "Triangle inequality violated: d(%d, %d) > d(%d, %d) + d(%d, %d)\n",
                                    u, v, u, w, w, v
                            );
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }



    private static void degreePreComputing(List<List<Edge>> edges, PriorityQueue<Node> degrees) {
        for (int i = 0; i < edges.size(); i++) {
            degrees.add(new Node(i, edges.get(i).size()));
        }
    }

    private static int estimateTwoWeight(boolean[] visited, List<List<Edge>> edges, int current) {
        int weight = 0;
        // use priority queue to find two smallest edges
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)->e1.weight-e2.weight);
        for (Edge e : edges.get(current)) {
            if(visited[e.v]) continue;
            pq.offer(e);
        }
        if (pq.isEmpty()) return weight;
        if(pq.size()>1) {
            Edge e1 = pq.poll();
            Edge e2 = pq.poll();
            weight += e1.weight;
            weight += e2.weight;
            return weight/2;
        }
        return pq.poll().weight;
    }


    private static void baseLineBacktracking(boolean[] visited, int[][] graph, List<List<Edge>> edges, int source, int current, int cost, int count) {
        if(count==graph.length-1) {
            if(graph[current][source]!=0 && cost+graph[current][source] < baselineMinWeight) baselineMinWeight = cost+graph[current][source];
            return;
        }
//        int esWeight = estimateTwoWeight(visited, edges, current);
        for (int loc = 0; loc < graph.length; loc++) {
            if(visited[loc] || loc == current) continue;
            Edge currentEdge = null;
            for (Edge e : edges.get(current)) {
                if(e.v == loc) currentEdge = e;
            }
            int esWeight = lookAheadWeight(visited, edges, currentEdge);
//            if (cost + graph[current][loc] + esWeight >= baselineMinWeight) continue;
            if (cost + esWeight >= baselineMinWeight) continue;

            if(graph[current][loc]!=0) {
                visited[loc] = true;
                baseLineBacktracking(visited, graph, edges, source, loc,cost+graph[current][loc], count+1);
                visited[loc] = false;
            }
        }
    }

    private static int tspBaseLine (int[][] graph, List<List<Edge>> edges, int startLoc) {
        boolean[] visited = new boolean[size];
        visited[startLoc] = true;
        baseLineBacktracking(visited, graph, edges, startLoc,  startLoc, 0, 0);
        return baselineMinWeight;
    }

    public static List<List<Edge>> constructEdges(List<Edge> inputEdges) {
        List<List<Edge>> edges = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            edges.add(new ArrayList<>());
        }
        for (Edge e : inputEdges) {
            edges.get(e.u).add(e);
            edges.get(e.v).add(new Edge(e.v, e.u, e.weight));
        }
        return edges;
    }

    private static int lookAheadWeight(boolean[] visited, List<List<Edge>> edges, Edge currentEdge) {
        if (currentEdge==null) return 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2) -> e1.weight - e2.weight);
        boolean[] visited2 = Arrays.copyOf(visited, visited.length);
        int weight = 0;
        pq.add(currentEdge);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.v;
            if (visited2[v]) continue;
            visited2[v] = true;
            weight += e.weight;
            for (int i = 0; i < edges.get(v).size(); i++) {
                if(visited2[edges.get(v).get(i).v]) continue;
                pq.add(edges.get(v).get(i));
            }
        }
        return weight;
    }

    private static void backtrackingWithHeuristic(boolean[] visited, int[][] graph, List<List<Edge>> edges, int source, int current, int cost, int count) {
        if(count==graph.length-1) {
            int currentCost = cost+graph[current][source];
            if(graph[current][source]!=0 && currentCost >= minWeight_lower && currentCost < minWeight_upper) {
                minWeight2 = Math.min(minWeight2, currentCost);
                minWeight_upper = minWeight2;
            }
            return;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        for (int i = 0; i < edges.get(current).size(); i++) {
            int neighbor = edges.get(current).get(i).v;
            if (!visited[neighbor]) queue.add(edges.get(current).get(i));
        }
        while (!queue.isEmpty()) {
            Edge neighbor = queue.poll();
            int dest = neighbor.v;
            int weight = neighbor.weight;
            int lookAheadWeight = lookAheadWeight(visited, edges,neighbor);
            if(cost+lookAheadWeight >= minWeight_upper) continue;
            visited[dest] = true;
            backtrackingWithHeuristic(visited, graph, edges, source, dest, cost+weight, count+1);
            visited[dest] = false;
        }

    }

    private static int tspWithHeuristic(int[][] graph, List<List<Edge>> edges, int startLoc) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[startLoc] = true;
        backtrackingWithHeuristic(visited, graph, edges, startLoc,  startLoc, 0, 0);
        return minWeight2;
    }


    private static int[] getInitialMinMaxCostByNearest(int[][] graph, List<List<Edge>> edges, int vertex) {
        int n = graph.length;
        int max = 0;
        int maxBackEdge = Integer.MIN_VALUE;
        int min = 0;
        int minBackEdge = Integer.MAX_VALUE;
        boolean[] visited1 = new boolean[n];
        boolean[] visited2 = new boolean[n];
        PriorityQueue<Edge> queueMin;
        PriorityQueue<Edge> queueMax;
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).size()==0 || visited1[i] || visited2[i]) continue;
            if(i!=0 && graph[i][vertex]!=0) {
                minBackEdge = Math.min(minBackEdge, graph[i][vertex]);
                maxBackEdge = Math.max(maxBackEdge, graph[i][vertex]);
            }
            queueMax = new PriorityQueue<>((e1, e2) -> e2.weight - e1.weight);
            queueMin = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
            queueMax.addAll(edges.get(i));
            queueMin.addAll(edges.get(i));
            Edge eMax = queueMax.poll();
            Edge eMin = queueMin.poll();
            if (visited1[eMax.v]) {
                while (!queueMax.isEmpty()) {
                    eMax = queueMax.poll();
                    if(!visited1[eMax.v]) {
                        max+=eMax.weight;
                        visited1[i]=true;
                        break;
                    }
                }
            } else {
                max+=eMax.weight;
                visited1[i]=true;
            }

            if (visited2[eMin.v]) {
                while (!queueMin.isEmpty()) {
                    eMin = queueMin.poll();
                    if(!visited2[eMin.v]) {
                        min+=eMin.weight;
                        visited2[i]=true;
                        break;
                    }
                }
            } else {
                min+=eMin.weight;
                visited2[i]=true;
            }
        }
        min += minBackEdge;
        max += maxBackEdge;
        return new int[]{min, max};
    }

    private static int getInitialMinCostByNearest(int[][] graph, List<List<Edge>> edges, int vertex) {
        int n = graph.length;
        int min = 0;
        int minBackEdge = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> queue;
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).size()==0 || visited[i]) continue;
            if(i!=0 && graph[i][vertex]!=0) minBackEdge = Math.min(minBackEdge, graph[i][vertex]);
            queue = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
            queue.addAll(edges.get(i));
            Edge e = queue.poll();
//            if(i!=0 && e.v==0) minBackEdge = Math.min(minBackEdge, e.weight);
            if (visited[e.v]) {
                while (!queue.isEmpty()) {
                    e = queue.poll();
                    if(!visited[e.v]) {
                        min+=e.weight;
                        visited[i]=true;
                        break;
                    }
                }
            } else {
                min+=e.weight;
                visited[i]=true;
            }
        }
        min += minBackEdge;
        return min;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{
                {0, 4, 3, 1},
                {4, 0, 1, 2},
                {3, 1, 0, 5},
                {1, 2, 5, 0}
        };
        String classPath = TravelSalesman.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String filePath = classPath+"/input.txt";
        List<Edge> inputEdges = loadInput("/Users/wilfried/IdeaProjects/Drills/src/main/java/com/wil/practice/algo/cp600/backtracking/input.txt");
        List<List<Edge>> edges = constructEdges(inputEdges);
        int[][] graph = getGraph(inputEdges);
        int startLoc = 0;
        long startTime, endTime;
        tspInBacktracking(input, startLoc);
        System.out.println("Min Weight: "+minWeight);

        System.out.println(triangleInequalityChecker(graph));

        Graph<Integer, DefaultWeightedEdge> g_prime = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (int i = 0; i < 32; i++) {
            g_prime.addVertex(i);
        }
        for (Edge edge : inputEdges) {
            g_prime.setEdgeWeight(g_prime.addEdge(edge.u, edge.v), edge.weight);
        }

        startTime = System.nanoTime();
        System.out.println("Approximation using MST - Min Weight: "+tspUsingMST(graph, startLoc));
        endTime = System.nanoTime();
        System.out.println("Approximation using MST Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");

        PriorityQueue<Node> pq;
        Node startNode;
        startTime = System.nanoTime();
        pq = new PriorityQueue<>((n1,n2)-> n1.degree-n2.degree); // min-heap
        degreePreComputing(edges, pq);
        startNode = pq.poll();
        tspBaseLine(graph, edges, startNode.vertex);
        System.out.println("Baseline - Min Weight: "+baselineMinWeight);
        endTime = System.nanoTime();
        System.out.println("Baseline Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");


        startTime = System.nanoTime();
        pq = new PriorityQueue<>((n1,n2)-> n1.degree-n2.degree); // min-heap
        degreePreComputing(edges, pq);
        startNode = pq.poll();
        int[] initialMinMax = getInitialMinMaxCostByNearest(graph, edges, startNode.vertex);
//        int initialMinWeight = getInitialMinCostByNearest(graph, edges, startNode.vertex);
//        int initialMaxWeight = getInitialMaxCostByNearest(graph, edges, startNode.vertex);
        minWeight_lower = initialMinMax[0];
        minWeight_upper = initialMinMax[0] + (initialMinMax[1]-initialMinMax[0])/2;
        System.out.println("Backtracking with heuristics - Min Weight: "+tspWithHeuristic(graph, edges, startNode.vertex));
        endTime = System.nanoTime();
        System.out.println("Backtracking with heuristics Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");
    }



    static class Edge{
        int u;
        int v;
        int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Node{
        int vertex;
        int degree;

        public Node(int vertex, int degree) {
            this.vertex = vertex;
            this.degree = degree;
        }
    }
}
