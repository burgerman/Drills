package com.wil.practice.datastructure.array;

import java.util.*;
import java.util.stream.Collectors;

public class EqualRowColumnPairs {
    private static int equalPairs(int[][] grid) {
        int res = 0;
        int n = grid.length;
        Set<List<Integer>> cache = new HashSet<>(n, 1.0f);
        Map<List<Integer>, Integer> dupCount = new HashMap<>(n, 1.0f);
        List<Integer> list;

        for (int[] row : grid) {
            list = Arrays.stream(row)
                    .boxed()
                    .collect(Collectors.toList());
            if (!cache.add(list)) {
                dupCount.put(list, dupCount.get(list)+1);
            } else {
                dupCount.put(list, 1);
            }
        }

        for (int i=0; i<n; i++) {
            list = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                list.add(grid[j][i]);
            }
            if (!cache.add(list)) res += dupCount.get(list);
        }
        return res;
    }


    public static void main(String[] args) {

        int[][] grid1 = new int[][]{{3,2,1},{1,7,6},{2,7,7}};
        int[][] grid2 = new int[][]{{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}};
        System.out.printf(" %d%n", equalPairs(grid1));
        System.out.printf(" %d%n", equalPairs(grid2));
    }
}
