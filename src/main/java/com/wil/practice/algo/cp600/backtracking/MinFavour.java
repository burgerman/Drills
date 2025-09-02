package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MinFavour {

    private static int getSum(int[] input) {
        return Arrays.stream(input).sum();
    }

    private static int getSum(List<Integer> inputList) {
        return inputList.stream().mapToInt(i -> i.intValue()).sum();
    }

    private static int getMaxElement(int[] input) {
        return Arrays.stream(input).max().getAsInt();
    }

    private static void printDivisions(List<List<Integer>> result) {
        int max = Integer.MIN_VALUE;
        for (List<Integer> l : result) {
            int sum = 0;
            int i = 0;
            for (Integer e : l) {
                sum += e;
                if (i == l.size()-1)  System.out.print(e);
                else System.out.print(e + " + ");
                i++;
            }
            max = Math.max(max, sum);
            System.out.print(" = "+sum);
            System.out.println();
        }
        System.out.println("Favour = " + max);
    }

    private static void getDivisions(List<List<List<Integer>>> partitions, List<List<Integer>> tmpPartition, int[] arr, int k, int count) {
        if (count == arr.length) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                res.add(new ArrayList<>(tmpPartition.get(i)));
            }
            partitions.add(res);
            return;
        }
        for (int i = 0; i < k; i++) {
            List<Integer> tmp = tmpPartition.get(i);
            tmp.add(arr[count]);
            tmpPartition.set(i, tmp);
            getDivisions(partitions, tmpPartition, arr, k, count+1);
            tmpPartition.get(i).remove((Integer)arr[count]);
        }
    }


    private static void getMinFavour(int[] arr, int k) {
        List<List<List<Integer>>> partitions = new ArrayList<>();
        List<List<Integer>> tmpPartition = new ArrayList<>();
        List<List<Integer>> result = null;
        for (int i = 0; i < k; i++) tmpPartition.add(new ArrayList<>());
        getDivisions(partitions, tmpPartition, arr, k, 0);

        int minFavour = Integer.MAX_VALUE;
        int pSum;
        for (List<List<Integer>> partition : partitions) {
            int maxSum = Integer.MIN_VALUE;
            for (List<Integer> p : partition) {
                pSum = getSum(p);
                maxSum = Math.max(maxSum, pSum);
            }
            if(maxSum < minFavour) {
                result = new ArrayList<>(partition);
                minFavour = maxSum;
            }
        }
        if (result != null) printDivisions(result);
    }


    private static void getDivisionsWithHeuristics(List<List<List<Integer>>> partitions, List<List<Integer>> tmpPartition, int[] arr, int k, int count, int lowBound, int upBound) {
        if (count < 0) {
            List<List<Integer>> res = new ArrayList<>();
            int tmpMinFavour = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                if (tmpPartition.get(i).size()==0) return;
                if (lowBound<upBound && tmpPartition.get(i).size()==1) return;
                tmpMinFavour = Math.min(tmpMinFavour, getSum(tmpPartition.get(i)));
                res.add(new ArrayList<>(tmpPartition.get(i)));
            }
            if (tmpMinFavour> lowBound && tmpMinFavour <= upBound) {
                partitions.add(res);
            }
            return;
        }

        for (int i = 0; i < k; i++) {
            List<Integer> tmp = tmpPartition.get(i);
            int tmpSum = getSum(tmp)+arr[count];
            if(tmpSum>upBound) continue;
            tmp.add(arr[count]);
            tmpPartition.set(i, tmp);
            getDivisionsWithHeuristics(partitions, tmpPartition, arr, k, count-1, lowBound, upBound);
            int lastIndex = tmpPartition.get(i).size()-1;
            tmpPartition.get(i).remove(lastIndex);
        }
    }



    private static void getMinFavourWithHeuristics(int[] arr, int k) {
        Arrays.sort(arr);
        int total = getSum(arr);
        int lowerBound = arr[arr.length-1];
        int upperBound = (total/k) + (total%k);
        if (upperBound <= lowerBound) {
            System.out.print("Favour = " + lowerBound);
            return;
        }
        List<List<List<Integer>>> partitions = new ArrayList<>();
        List<List<Integer>> tmpPartition = new ArrayList<>();
        List<List<Integer>> result = null;
        for (int i = 0; i < k; i++) tmpPartition.add(new ArrayList<>());
        int minFavour = Integer.MAX_VALUE;
        getDivisionsWithHeuristics(partitions, tmpPartition, arr, k, arr.length-1, lowerBound, upperBound);
        int pSum;
        for (List<List<Integer>> partition : partitions) {
            int maxSum = Integer.MIN_VALUE;
            for (List<Integer> p : partition) {
                pSum = getSum(p);
                maxSum = Math.max(maxSum, pSum);
            }
            if(maxSum <= minFavour) {
                result = new ArrayList<>(partition);
                minFavour = maxSum;
            }
        }
        if (result != null) printDivisions(result);
    }

    public static void main(String[] args) {
        int n = 8;
        int[] arr = new int[]{6, 1, 3, 2, 2, 4, 2, 2};
        int k = 3;
        long startTime, endTime;
        startTime = System.nanoTime();
        getMinFavour(arr, k);
        endTime = System.nanoTime();
        System.out.println("Brutal Force Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");
        startTime = System.nanoTime();
        getMinFavourWithHeuristics(arr, k);
        endTime = System.nanoTime();
        System.out.println("Heuristic Approach Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");
    }

}
