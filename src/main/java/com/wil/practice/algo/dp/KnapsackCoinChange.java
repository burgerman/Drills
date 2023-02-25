package com.wil.practice.algo.dp;

public class KnapsackCoinChange {

    public static void main(String[] args) {
        int amount = 5;
        int amount2 = 3;
        int amount3 = 10;
        int[] coins = {1, 2, 5};
        int[] coins2 = {2};
        int[] coins3 = {10};
        System.out.println(findNumOfCombination(amount, coins));
        System.out.println(optimizedDPSolution(amount, coins));
        System.out.println(findNumOfCombination(amount2, coins2));
        System.out.println(optimizedDPSolution(amount2, coins2));
        System.out.println(findNumOfCombination(amount3, coins3));
        System.out.println(optimizedDPSolution(amount3, coins3));

    }


    private static int findNumOfCombination(int amount, int[] coins) {
        if(coins.length<2 && coins[0]<amount && coins[0]*2>amount) {
            return 0;
        } else if( coins.length==1 && coins[0] == amount) {
            return 1;
        }

        int[][] arr = new int[coins.length+1][amount+1];
        arr[0][0] = 1;
        for(int i = 1; i<=coins.length; i++) {
            for(int j = 0; j<=amount; j++) {
                if(j-coins[i-1]>=0) {
                    arr[i][j] = arr[i-1][j] + arr[i][j-coins[i-1]];
                } else {
                    arr[i][j] = arr[i-1][j];
                }
            }
        }
        return arr[coins.length][amount];
    }

    private static int optimizedDPSolution(int amount, int[] coins) {
        if(coins.length<2 && coins[0]<amount && coins[0]*2>amount) {
            return 0;
        } else if( coins.length==1 && coins[0] == amount) {
            return 1;
        }
        int[] arr = new int[amount+1];
        arr[0] = 1;
        for(int i = 1; i<=coins.length; i++) {
            for(int j = coins[i-1]; j<=amount; j++) {
                arr[j] = arr[j] + arr[j-coins[i-1]];
            }
        }
        return arr[amount];
    }

}
