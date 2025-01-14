package com.wil.practice.algo.cp600.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendsOfFriend {

    private static void dfs(List<List<Integer>> G, List<Integer> friendList, int node, int hop) {
        if (hop == 2) return;
        if(!friendList.contains(node))friendList.add(node);
        for (int n : G.get(node)) {
            dfs(G, friendList, n, hop + 1);
        }
    }


    private static List<List<Integer>> buildFriendOfFriendNetwork(List<List<Integer>> G) {
        if (G.isEmpty()) return null;
        if (G.size() == 1) return G;
        List<List<Integer>> G_prime = new ArrayList<>();
        for(int i = 0; i < G.size(); i++) {
            List<Integer> tmpList = new ArrayList<>();
            tmpList.add(i);
            G_prime.add(i, tmpList);
        }

        for (int i = 0; i < G.size(); i++) {
            int hop=0;
            List<Integer> friendList = G_prime.get(i);
            for (int j = 0; j < G.get(i).size(); j++) {
                dfs(G, friendList, G.get(i).get(j), hop);
            }
            G_prime.set(i, friendList);
        }
        return G_prime;
    }

    public static void main(String[] args) {
        List<List<Integer>> G = new ArrayList<>();
        G.add(Arrays.asList(1, 2, 3));
        G.add(Arrays.asList(0, 6, 7));
        G.add(Arrays.asList(0, 7, 8, 9, 10));
        G.add(Arrays.asList(0, 4, 5));
        G.add(Arrays.asList(3));
        G.add(Arrays.asList(3));
        G.add(Arrays.asList(1));
        G.add(Arrays.asList(1, 2));
        G.add(Arrays.asList(2));
        G.add(Arrays.asList(2));
        G.add(Arrays.asList(2));
        List<List<Integer>> G_prime = buildFriendOfFriendNetwork(G);
        for (List<Integer> friendList : G_prime) {
            System.out.println(Arrays.toString(friendList.toArray()));
        }
    }
}
