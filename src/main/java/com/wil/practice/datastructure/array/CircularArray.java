package com.wil.practice.datastructure.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class CircularArray {


    private static int[] findNextGreaterNum(int[] arr) {
        int size = arr.length;
        int[] res = new int[size];
        Arrays.fill(res, -1);
        // 记录当前非最大数的索引
        Deque<Integer> stack = new ArrayDeque<>();
        //假设将数组扩展为原来的2倍
        for(int i = 0; i<2*size-1; i++) {
            // 对i进行取模计算还原为原数组索引
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i%size]) {
                // 将大的数存入res
                res[stack.pop()] = arr[i%size];
            }
            //将arr元素放入
            stack.push(i%size);
        }
        return res;
    }



    public static void main(String[] args) {
        int[] arr = {2,1,2,4,3};
        System.out.println(Arrays.toString(findNextGreaterNum(arr)));

    }
}
