package com.wil.practice.datastructure.stack;

import java.util.PriorityQueue;
import java.util.Stack;

public class BalancedParentheses {


    private static Stack<Character> stack = new Stack<>();

    private static boolean isMatch(char currentBracket) {
        if (stack.isEmpty()) return false;
        char topBracket = stack.pop();
        if (currentBracket == ')' && topBracket == '(') return true;
        else if (currentBracket == ']' && topBracket == '[') return true;
        else if (currentBracket == '}' && topBracket == '{') return true;
        else return false;
    }

    private static boolean isBalanced(String s) {
        if(s.length()%2 !=0) return false;
        char[] brackets = s.toCharArray();
        for (char b : brackets) {
            if (b == '(' || b == '[' || b == '{') stack.push(b);
            else if (stack.isEmpty() || !isMatch(b)) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{{[[(())]]}}";
        System.out.println(isBalanced(s));
    }

}
