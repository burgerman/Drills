package com.wil.practice.interview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FindSeat {

    private List<Integer> occupiedSeats = new LinkedList<>();
    private int maxDistance = 0;

    public int FindFarthestDistanceFromClosestStranger(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            if(arr[i] > 0){
                occupiedSeats.add(i);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(!occupiedSeats.contains(i)) {
                CompareAndSwap(i);
            }
        }
        return maxDistance;
    }

    private void CompareAndSwap(int currentSeat) {
        for (int i = 0; i < occupiedSeats.size(); i++) {
            if(ifClosest(currentSeat, occupiedSeats.get(i))) {
                int tmpDistance = Math.abs(currentSeat-occupiedSeats.get(i));
                maxDistance = tmpDistance>maxDistance?tmpDistance:maxDistance;
            }
        }
    }

    private boolean ifClosest(int a, int b) {
        if(a==b) {
            return false;
        }
        int left = (a>b)?b:a;
        int right = (a>b)?a:b;
        for(Integer i : occupiedSeats){
            if(left<i && i<right) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] seats = {1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1};
        FindSeat fs = new FindSeat();
        int farthestDistance = fs.FindFarthestDistanceFromClosestStranger(seats);
        System.out.println(farthestDistance);
    }
}
