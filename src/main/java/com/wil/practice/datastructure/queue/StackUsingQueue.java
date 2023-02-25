package com.wil.practice.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    private Queue queue1 = new LinkedList();
    private Queue queue2 = new LinkedList();

    public void pushVal(Object o) {
        queue1.offer(o);
        while(queue2.size()>0) {
            queue1.offer(queue2.poll());
        }
        Queue tmpQueue = queue1;
        queue1 = queue2;
        queue2 = tmpQueue;
    }

    public Object popVal() {
        if(queue2.size()>0) {
            return queue2.poll();
        } else {
            throw new IllegalStateException("Queue empty");
        }
    }

    public static void main(String[] args) {
        StackUsingQueue suq = new StackUsingQueue();
        suq.pushVal(5);
        suq.pushVal(4);
        suq.pushVal(3);
        suq.pushVal(2);
        suq.pushVal(1);
        System.out.println(suq.popVal());
        System.out.println(suq.popVal());
        System.out.println(suq.popVal());
        System.out.println(suq.popVal());
        System.out.println(suq.popVal());
    }
}
