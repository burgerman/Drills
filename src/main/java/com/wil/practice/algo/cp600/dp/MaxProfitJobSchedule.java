package com.wil.practice.algo.cp600.dp;

import java.util.ArrayList;
import java.util.List;

public class MaxProfitJobSchedule {

    private static class Job {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }


    private static int binarySearch(List<Job> jobList, int job) {
        int l = 0, r = job-1;
        while (l<=r) {
            int mid = l + (r-l)/2;
            if(jobList.get(mid).endTime <= jobList.get(job).startTime) {
                if(jobList.get(mid+1).endTime<=jobList.get(job).startTime){
                    l = mid +1;
                } else {
                    return mid;
                }
            } else {
                r = mid -1;
            }
        }
        return -1;
    }


    private static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[] dp = new int[profit.length+1];
        List<Job> jobList = new ArrayList<>();
        for (int i=0; i<startTime.length; i++) {
            jobList.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        jobList.sort((j1, j2)-> j1.endTime - j2.endTime);

        for (int i = 1; i<= jobList.size(); i++) {
            if(i==1) {
                dp[i] = jobList.get(0).profit;
                continue;
            }
            int latestJob = binarySearch(jobList, i-1);
            int latestJobMaxProfit = latestJob >= 0? dp[latestJob+1] : 0;
            int currentJobMaxProfit = jobList.get(i-1).profit + latestJobMaxProfit;
            dp[i] = Math.max(dp[i-1], currentJobMaxProfit);
        }

        return dp[profit.length];
    }



    public static void main(String[] args) {

        int[] startTime = {1,2,3,3};
        int[] endTime = {3,4,5,6};
        int[] profit = {50,10,40,70};
        System.out.println(jobScheduling(startTime, endTime, profit));

        int[] startTime1 = {1,2,3,4,6};
        int[] endTime1 = {3,5,10,6,9};
        int[] profit1 = {20,20,100,70,60};
        System.out.println(jobScheduling(startTime1, endTime1, profit1));
    }

}
