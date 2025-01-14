package com.wil.practice.algo.cp600.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class SetSplitting {

    private static int getSum(Set<Integer> inputSet) {
        int sum = 0;
        for (Integer i : inputSet) {
            sum += i;
        }
        return sum;
    }

    private static void printSet(Set<Integer> inputSet) {
        int i =0;
        for (Integer e : inputSet) {
            if(i==inputSet.size()-1){
                System.out.print(e);
            } else {
                System.out.print(e + " + ");
            }
            i++;
        }
    }

    private static void recursion(int[] set, Set<Integer> setA, Set<Integer> setB,  Set<Set<Integer>> allSet, int count) {
        if((setA.size()>0 && setB.size()>0) && getSum(setA)==getSum(setB)){
                Set<Integer> tmpSet = new HashSet<>();
                tmpSet.addAll(setA);
                tmpSet.addAll(setB);
                if(!allSet.contains(tmpSet)) {
                    allSet.add(tmpSet);
                    printSet(setA);
                    System.out.print(" = ");
                    printSet(setB);
                    System.out.println();
                }
        }

        if (count < 0) return;

        if (!setB.contains(set[count])) setA.add(set[count]);
        recursion(set, setA, setB, allSet, count - 1);

        setA.remove(set[count]);
        if (!setA.contains(set[count])) setB.add(set[count]);
        recursion(set, setA, setB, allSet, count - 1);

        setB.remove(set[count]);
        recursion(set, setA, setB, allSet, count - 1);
    }


    private static void recursionWithHeuristics(int[] set, TreeSet<Integer> setA, TreeSet<Integer> setB,  Set<Set<Integer>> allSet, int count, int sum) {
        int sumA = getSum(setA);
        int sumB = getSum(setB);
        if((setA.size()>0 && setB.size()>0) && sumA==sumB) {
            Set<Integer> tmpSet = new HashSet<>();
            tmpSet.addAll(setA);
            tmpSet.addAll(setB);
            if(!allSet.contains(tmpSet)) {
                allSet.add(tmpSet);
                printSet(setA);
                System.out.print(" = ");
                printSet(setB);
                System.out.println();
            }
        }

        if (count < 0) return;
        int remainSumA = sum - sumA;
        int remainSumB = sum - sumB;
        // pruning, cut impossible branches
        if (sumA>remainSumA || sumB>remainSumB || sumA > sum/2 || sumB >sum/2) return;

        if (sumA<sumB) {
            if (!setB.contains(set[count])) setA.add(set[count]);
            recursionWithHeuristics(set, setA, setB, allSet, count - 1,sum);

            setA.remove(set[count]);
        } else {
            if (!setA.contains(set[count])) setB.add(set[count]);
            recursionWithHeuristics(set, setA, setB, allSet, count - 1,sum);

            setB.remove(set[count]);
        }
        recursionWithHeuristics(set, setA, setB, allSet, count - 1,sum);
    }


    private static void brutalForceSplit(int[] set) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        Set<Set<Integer>> allSet = new HashSet<>();
        Arrays.sort(set);
        recursion(set, setA, setB, allSet, set.length-1);
    }

    private static void heuristicsSplit(int[] set) {
        TreeSet<Integer> setA = new TreeSet<>();
        TreeSet<Integer> setB = new TreeSet<>();
        Set<Set<Integer>> allSet = new HashSet<>();
        Arrays.sort(set);
        int sum = Arrays.stream(set).sum();
        recursionWithHeuristics(set, setA, setB, allSet, set.length-1, sum);
    }


    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        long startTime, endTime;
        startTime = System.nanoTime();
        brutalForceSplit(input);
        endTime = System.nanoTime();
        System.out.println("Brutal Force Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");
        startTime = System.nanoTime();
        heuristicsSplit(input);
        endTime = System.nanoTime();
        System.out.println("Heuristic Approach Time Cost: " + TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)+ " milliseconds");
    }
}
