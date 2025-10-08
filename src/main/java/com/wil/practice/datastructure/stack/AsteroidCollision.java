package com.wil.practice.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

public class AsteroidCollision {

    private static int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> output = new ArrayDeque<>(asteroids.length);
        boolean alive;
        int prev;
        for (int asteroid : asteroids) {
            alive = true;
            while (!output.isEmpty() && asteroid<0 && output.peek()>0) {
                prev = output.peek();
                if (Math.abs(prev) > Math.abs(asteroid)) {
                    alive = false;
                    break;
                } else if (Math.abs(prev) == Math.abs(asteroid)) {
                    output.pop();
                    alive = false;
                    break;
                } else {
                    output.pop();
                    continue;
                }
            }
            if (alive) output.push(asteroid);
        }

        int[] res = new int[output.size()];
        for (int i=res.length-1; i>=0; i--) {
            res[i] = output.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {5,10,-5};
        int[] nums2 = new int[] {10,2,-5};
        int[] nums3 = new int[] {8, -8};
        System.out.printf(" %s%n", Arrays.toString(asteroidCollision(nums)));
        System.out.printf(" %s%n", Arrays.toString(asteroidCollision(nums2)));
        System.out.printf(" %s%n", Arrays.toString(asteroidCollision(nums3)));
    }

}
