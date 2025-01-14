package com.wil.practice.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Stack;

public class EbayQueueUsingSingleStack {

    public static int popLast(Stack<Integer> stack) {
        int currentLast = stack.pop();
        if(stack.empty()) {
            return currentLast;
        }
        int last = popLast(stack);
        stack.push(currentLast);
        return last;
    }

    public static void popOut (Stack<Integer> stack) {
        if(stack.size()<1) {
            return;
        }
        //将最下方元素置顶
        int last = popLast(stack);
        popOut(stack);
        stack.push(last);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        popOut(stack);
        while(!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

}
