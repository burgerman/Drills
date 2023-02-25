package com.wil.practice.algo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class DisjointSet {

    private static int[] usrArray;
    private static double[][] cordi;

    private static boolean hasContact(double d, double x1, double y1, double x2, double y2) {
        if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2))<=d) {
            return true;
        } else {
            return false;
        }
    }

    private static void union(int x, int y) {
        if(x<y) {
            usrArray[y] = x;
        } else {
            usrArray[x] = y;
        }
    }

    private static int find (int h) {
        int k, j, r;
        r = h;
        while(r != usrArray[h]) {
            r = usrArray[r];
        }

        // Path compression
        k = h;
        while (k != r) {
            j = usrArray[k];
            usrArray[k] = r;
            k = j;
        }
        return r;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double d = sc.nextDouble();
        int n = (int) sc.nextDouble();
        cordi = new double[n][2];
        usrArray = new int[n];
        for(int i =0; i<n; i++) {
            usrArray[i] = i;
        }
        for(int j =0; j<n; j++) {
            cordi[j][0] = sc.nextDouble();
            cordi[j][1] = sc.nextDouble();
        }
        sc = null;
        for(int k =0; k<n; k++) {
            for(int l=k+1; l<n; l++) {
                if(hasContact(d, cordi[k][0], cordi[k][1], cordi[l][0], cordi[l][1])) {
                    union(k, l);
                }
            }
        }
        HashMap<Integer, LinkedList<Integer>> table = new HashMap<>(n);
        LinkedList<Integer> list;
        for(int h=0; h<usrArray.length; h++) {
            if(usrArray[h] == h) {
                list = new LinkedList<>();
                list.offer(h);
                table.put(h, list);
            } else {
                list = table.get(find(h));
                if(list!=null) {
                    list.offer(h);
                    table.replace(usrArray[h], list);
                }

            }
        }
    }

}
