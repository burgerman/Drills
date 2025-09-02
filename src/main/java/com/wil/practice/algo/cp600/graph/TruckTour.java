package com.wil.practice.algo.cp600.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TruckTour {

    private static class Node{
        public int petrol;
        public int distance;
        public Node (int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    private static int truckTour(List<List<Integer>> petrolpumps) {
        // Write your code here
        int len = petrolpumps.size();
        int minIndex = -1;
        int leftPetrol = 0;
        Node[] nodes = new Node[len];
        List<Integer> startNodes = new ArrayList<>();
        Node n;
        for (int i = 0; i<len; i++) {
            n = new Node(petrolpumps.get(i).get(0), petrolpumps.get(i).get(1));
            if(n.petrol>=n.distance) startNodes.add(i);
            nodes[i] = n;
        }

        startNodes.sort((o1, o2) -> o1-o2);

        int count;
        int index;
        boolean[] visited;
        for (int j = 0; j < startNodes.size(); j++) {
            visited = new boolean[len];
            index = startNodes.get(j);
            count = dfs(nodes, visited, index, leftPetrol);
            if(count == len) {
                minIndex = index;
                break;
            }
        }
        return minIndex;
    }

    private static int dfs (Node[] nodes, boolean[] visited, int nodeIndex, int leftPetrol) {
        visited[nodeIndex] = true;
        leftPetrol -= nodes[nodeIndex].distance;
        leftPetrol += nodes[nodeIndex].petrol;
        int count = 1;
        for (int i = 0; i < nodes.length; i++) {
            if(!visited[i] && i != nodeIndex) {
                if(leftPetrol+nodes[i].petrol >= nodes[i].distance) count += dfs(nodes, visited, i, leftPetrol);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int N = 3;
        List<List<Integer>> petrolpumps = new ArrayList<>();
        petrolpumps.add(new ArrayList<>(List.of(1, 5)));
        petrolpumps.add(new ArrayList<>(List.of(10, 3)));
        petrolpumps.add(new ArrayList<>(List.of(3, 4)));
        int min = truckTour(petrolpumps);
        System.out.println(min);
    }
}
