package com.wil.practice.algo.dp;

public class MaxProfit {

    /**
     * 每天可进行2次买卖
     * @param prices
     * @return
     */
    public static int bestBuyAndSellStockIII(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int buy1 = - prices[0], sell1 = 0;
        int buy2 = - prices[0], sell2 = 0;

        // max profit can be made by selling on any day
        //assuming that the transactions are on separate days
        for (int price : prices) {
            //get min cost of buying 1st stock
            //base case for buy: - price[0]
            buy1 = Math.max(buy1, -price);
            //get max profit of selling 1st stock
            sell1 = Math.max(sell1, buy1 + price);
            // get min cost of buying 2nd stock
            buy2 = Math.max(buy2, sell1 - price);
            // get max profit by selling 2nd stock
            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }

    /**
     * 每天可进行2次买卖
     * @param prices
     * @return
     */
    public static int DPTabulationIII(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        // can process at most 2 transactions

        // max profit obtained by completing 2 buy transactions up to and including day n
        int[][] buy = new int[n][3];
        // max profit obtained by completing 2 sell transactions up to and including day n
        int[][] sell = new int[n][3];
        buy[0][0] = Integer.MIN_VALUE;
        // first day
        buy[0][1] = buy[0][2] = - prices[0];
        sell[0][0] = 0;

        for(int i =1; i<n; i++) {
            for(int j=1; j<3; j++) {
                buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j-1] - prices[i]);
                sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j]+prices[i]);

            }
        }
        return sell[n-1][2];
    }


    /**
     * 每天可进行K次买卖
     * @param prices
     * @param k
     * @return
     */
    public static int DPTabulationIV(int[] prices, int k) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        // can process at most 2 transactions

        // max profit obtained by completing 2 buy transactions up to and including day n
        int[][] buy = new int[n][k+1];
        // max profit obtained by completing 2 sell transactions up to and including day n
        int[][] sell = new int[n][k+1];
        buy[0][0] = Integer.MIN_VALUE;
        // first day
        for(int i=1; i<k+1; i++) {
            buy[0][i] = - prices[0];
        }
        sell[0][0] = 0;

        for(int i =1; i<n; i++) {
            for(int j=1; j<k+1; j++) {
                buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j-1] - prices[i]);
                sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j]+prices[i]);
            }
        }
        return sell[n-1][k];
    }


    /**
     * 有限天数内，每天均可进行一次买卖
     * @param prices
     * @return
     */
    public static int maxProfitII(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int buy1 = - prices[0], sell1 = 0;
        // max profit can be made by selling on any day
        //assuming that the transactions are on separate days
        for (int price : prices) {
            //get min cost of buying 1st stock
            //base case for buy: - price[0]
            buy1 = Math.max(buy1, sell1-price);
            //get max profit of selling 1st stock
            sell1 = Math.max(sell1, buy1 + price);
        }
        return sell1;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        System.out.println(bestBuyAndSellStockIII(prices));
        System.out.println((DPTabulationIII(prices)));

        int[] prices2 = new int[]{3,2,6,5,0,3};
        System.out.println((DPTabulationIV(prices2, 2)));
    }

}
