package com.wil.practice.interview;

import java.util.*;
import java.util.stream.Collectors;

public class PinDuoDuoBuySellStock {

    private final static KeySortFunc sortFunc = new KeySortFunc();

    public static ArrayDeque<Integer> insertionSort(ArrayDeque<Integer> buyIn) {
        int index = 0;
        Object[] tmpArr = buyIn.toArray();
        Integer[] arr = new Integer[buyIn.size()];
        while (index<buyIn.size()) {
            arr[index] = Integer.parseInt(tmpArr[index].toString());
            index++;
        }
        int i = 0;
        for (int k = 1; k<arr.length; k++) {
            int indicator = arr[k];
            int j = k;
            while(j>0 && indicator < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            if(k!=j) {
                arr[j] = indicator;
            }
        }
        return new ArrayDeque<>(Arrays.asList(arr));
    }


    public static Integer maxYield(List<Integer> priceHistory) {
        List<Node> ledger = new ArrayList<>();
        for (int i =0; i<priceHistory.size(); i++) {
            Node node = new Node(i, priceHistory.get(i));
            ledger.add(node);
        }
        int maxDiff = 0;
        for (int j = 0; j<ledger.size(); j++) {
            for (int k = j+1; k<ledger.size(); k++) {
                if(sortFunc.compare(ledger.get(j), ledger.get(k))<0) {
                    Integer boughtPrice = (Integer) ledger.get(j).getValue();
                    Integer soldPrice = (Integer) ledger.get(k).getValue();
                    maxDiff = soldPrice-boughtPrice>maxDiff?soldPrice-boughtPrice:maxDiff;
                }
            }
        }
        return maxDiff;
    }



    public static Integer maxYield2(List<Integer> priceHistory) {
        Integer[] ph = (Integer[]) priceHistory.toArray();
        List<Integer> buyIn = new ArrayList<>();
        int maxDiff = 0;
        for(int i=0; i<ph.length; i++) {
            int currentPrice = ph[i];
            if(buyIn.size()<1) {
                buyIn.add(currentPrice);
                continue;
            } else if(buyIn.size() == 1) {
                maxDiff = currentPrice - buyIn.get(0);
                buyIn.add(currentPrice);
            } else {
//                buyIn = insertionSort(buyIn);
                // Default - applying TimSort Algo
                buyIn.sort(Comparator.naturalOrder());
                maxDiff = (currentPrice-buyIn.get(0)) > maxDiff? (currentPrice-buyIn.get(0)):maxDiff;
                buyIn.add(currentPrice);
            }
        }
        return maxDiff;
    }

    public static Integer maxYield3(List<Integer> priceHistory, int l, int r) {
        int maxProfit=0;
        int mid = l+(r-l)>>1;
        int left = l;
        int right = r;
        int left_low_index = l;
        int left_high_index = l;
        int right_low_index = r;
        int right_high_index = r;
        while (left<=mid) {
            if(priceHistory.get(left)<priceHistory.get(left_low_index) && left>left_low_index){
                left_low_index = left;
            }
            if(priceHistory.get(left)>priceHistory.get(left_high_index) && left>left_high_index) {
                left_high_index = left;
            }
            left++;
        }
        while (right>mid) {
            if(priceHistory.get(right)>priceHistory.get(right_high_index) && right<right_high_index) {
                right_high_index = right;
            }
            if(priceHistory.get(right)<priceHistory.get(right_low_index) && right<right_low_index) {
                right_low_index = right;
            }
            right--;
        }
        List<Integer> ad = new ArrayList<>(Arrays.asList(left_low_index, left_high_index, right_low_index, right_high_index));
        ad.sort(Comparator.naturalOrder());
        for(int i=0; i<ad.size(); i++) {
            for(int j=i+1; j<ad.size(); j++){
                maxProfit = priceHistory.get(j)-priceHistory.get(i)>maxProfit? priceHistory.get(j)-priceHistory.get(i):maxProfit;
            }
        }
        return maxProfit;
    }

    /**
     * 有限天数内只能进行1次买卖
     * @param prices
     * @return
     */
    static int maxProfit(int[] prices) {
        int[] profits = new int[prices.length];
        profits[0] = 0;
        int minPrice = Integer.MAX_VALUE;
        for(int i=1; i<prices.length; i++) {
            if(prices[i]>prices[i-1]) {
                minPrice = minPrice>prices[i-1]? prices[i-1]:minPrice;
                profits[i] = (prices[i] - minPrice)>profits[i-1]? (prices[i] - minPrice):profits[i-1];
            }
        }
        Arrays.sort(profits);
        return  profits[prices.length-1];
//        return Arrays.stream(profits).max().getAsInt();
    }

    /**
     * 有限天数内，每天均可进行一次买卖
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
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


    static class Node<K,V> implements Map.Entry<K,V> {
        K index;
        V price;

        public Node(K index, V price) {
            this.index = index;
            this.price = price;
        }

        @Override
        public final K getKey() {
            return index;
        }

        @Override
        public final V getValue() {
            return price;
        }

        @Override
        public final V setValue(V newValue) {
            return null;
        }

    }

    static class KeySortFunc implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return Integer.parseInt(o1.getKey().toString())-Integer.parseInt(o2.getKey().toString());
        }
    }


    public static void main(String[] args) {
        List<Integer> priceRecord = Arrays.asList(1, 5, 9, 8, 6, 3, 7, 2);
        long initTime = System.currentTimeMillis();
        System.out.println(maxYield(priceRecord));
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-initTime)+"ms");

        long initTime2 = System.currentTimeMillis();
        System.out.println(maxYield2(priceRecord));
        long endTime2 = System.currentTimeMillis();
        System.out.println((endTime2-initTime2) + "ms");

        long initTime3 = System.currentTimeMillis();
        System.out.println(maxYield3(priceRecord, 0, priceRecord.size()-1));
        long endTime3 = System.currentTimeMillis();
        System.out.println((endTime3-initTime3)+"ms");

        int[] prices = new int[]{1, 5, 9, 8, 6, 3, 7, 2};
        long initTime4 = System.currentTimeMillis();
        System.out.println(maxProfit(prices));
        long endTime4 = System.currentTimeMillis();
        System.out.println((endTime4-initTime4)+"ms");


        int[] prices2 = new int[]{7,1,5,3,6,4};
        long initTime5 = System.currentTimeMillis();
        System.out.println(maxProfit2(prices2));
        long endTime5 = System.currentTimeMillis();
        System.out.println((endTime5-initTime5)+"ms");
    }
}

