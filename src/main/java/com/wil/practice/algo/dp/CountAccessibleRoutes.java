package com.wil.practice.algo.dp;

import java.util.Arrays;

public class CountAccessibleRoutes {
    int[][] arr;
    private static final int valMod = (int)Math.pow(10, 9) + 7;

    public int countRoutes(int[] locations, int start, int end, int fuel) {
        if(fuel<1) {
            return 0;
        }
        arr = new int[locations.length][fuel+1];
        for (int i=0; i<locations.length; i++) {
            Arrays.fill(arr[i], -1);
        }
        return dfs(locations, start, end, fuel);
    }

    public int dfs(int[] locations, int start, int end, int fuel) {
        if(arr[start][fuel] != -1) {
            return arr[start][fuel];
        }
        if(fuel< Math.abs(locations[start]-locations[end])) {
            arr[start][fuel] = 0;
            return 0;
        }
        int m = locations.length;
        int routes = start==end? 1:0;
        for(int i =0; i<m; i++) {
            if (i!=start) {
                int cost = Math.abs(locations[i]-locations[start]);
                if(cost<=fuel) {
                    int leftFuel = fuel-cost;
                    //arr[i][fuel] = arr[i][fuel] + arr[j][fuel-cost]
                    routes += dfs(locations, i, end, leftFuel);
                    routes %= valMod;
                }
            }
        }
        arr[start][fuel] = routes;
        return routes;
    }


    public int countRoutesDPSolution(int[] locations, int start, int end, int fuel) {
        int m = locations.length;
        int [][] cache = new int[m][fuel+1];
        for (int i=0; i<=fuel; i++) {
            cache[end][i] = 1;
        }
        for(int cursor=0; cursor<=fuel; cursor++) {
            for(int i=0; i<m; i++) {
                for(int j=0; j<m; j++) {
                    if(i!=j) {
                        int cost = Math.abs(locations[i]-locations[j]);
                        if(cursor>=cost) {
                            cache[i][cursor] += cache[j][cursor-cost];
                            cache[i][cursor] %= valMod;
                        }
                    }
                }
            }
        }
        return cache[start][fuel];
    }

    public static void main(String[] args){
        int[] locations = {2,3,6,8,4};
        int start = 1, end = 3, fuel = 5;
        CountAccessibleRoutes car = new CountAccessibleRoutes();
        System.out.println(car.countRoutes(locations, start, end, fuel));
        System.out.println(car.countRoutesDPSolution(locations, start, end, fuel));
    }
}
