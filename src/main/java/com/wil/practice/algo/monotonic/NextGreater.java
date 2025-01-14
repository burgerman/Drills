package com.wil.practice.algo.monotonic;

import java.util.*;

public class NextGreater {

    private static Deque<Integer> findNextGreater(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        for(int i=0; i<arr.length; i++) {
            while(!stack.isEmpty() && arr[i] < stack.peek()) {
                stack2.push(stack.pop());
            }
            stack.push(arr[i]);
            while(!stack2.isEmpty()) {
                stack.push(stack2.pop());
            }
        }
        return stack;
    }

    public static void main(String[] args) {
        Deque<Integer> q = findNextGreater(new int[] {1,2,4,2,0,3});
        while(!q.isEmpty()) {
            System.out.println(q.pop());
        }
    }


}
