package com.wil.practice.algo.cp600.dc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Skyline {

    private static List<Integer> getBuilding(int[][] buildings, int pos) {
        List<Integer> builds = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            if(buildings[i][1]<pos || buildings[i][0]>pos) continue;
            if(buildings[i][0]<=pos && buildings[i][1]>=pos) {
                builds.add(i);
            }
        }
        // right end with no an overlapped building
        if (builds.size()==1) {
            if(buildings[builds.get(0)][1]==pos) return Arrays.asList(pos, 0);
            else if (buildings[builds.get(0)][0]==pos) return Arrays.asList(pos, buildings[builds.get(0)][2]);
        }
        int maxHeight = -1, tmpHeight = -1;
        int buildLeft = -1, buildRight = -1;
        for (int x : builds) {
            int[] build = buildings[x];
            if (build[0]==pos) {
                buildLeft = x;
            } else if (build[1]==pos) {
                buildRight = x;
            }
            if(x!=buildRight) {
                tmpHeight = Math.max(tmpHeight, build[2]);
            }
        }

        if ((buildLeft>=0 && buildings[buildLeft][2]>=tmpHeight) || (buildRight>=0 && buildings[buildRight][2]>=tmpHeight)) {
            maxHeight = tmpHeight;
        }

        if (maxHeight >0) {
            return Arrays.asList(pos, maxHeight);
        }
        return null;
    }

    private static List<List<Integer>> skylineView(int[][] buildings,  List<Integer> boundaries, int l, int r) {
        List<List<Integer>> outputs = new ArrayList<>();
        if (l==r) {
            List<Integer> build = getBuilding(buildings, boundaries.get(l));
            if(build==null || build.isEmpty()) return null;
            outputs.add(build);
            return outputs;
        }
        int m = l+(r-l)/2;
        List<List<Integer>> leftRes = skylineView(buildings, boundaries, l, m);
        List<List<Integer>> rightRes = skylineView(buildings, boundaries,m+1, r);
        if (leftRes!=null) outputs.addAll(leftRes);
        if (rightRes!=null) outputs.addAll(rightRes);
        if (outputs.isEmpty()) return null;
        return outputs;
    }



    public static void main(String[] args) {
        int[][] buildings = new int[][] {
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        };
        List<Integer> boundaries = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            boundaries.add(buildings[i][0]);
            boundaries.add(buildings[i][1]);
        }
        Collections.sort(boundaries);
        List<List<Integer>> outputs = skylineView(buildings, boundaries, 0, boundaries.size()-1);
        for (List<Integer> output : outputs) {
            System.out.println(output.get(0)+", "+output.get(1));
        }
    }
}
