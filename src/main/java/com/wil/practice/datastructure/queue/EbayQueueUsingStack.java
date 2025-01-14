package com.wil.practice.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class EbayQueueUsingStack {

    private Deque<Object> s1 = new ArrayDeque<>();
    private Deque<Object> s2 = new ArrayDeque<>();

    /**
     * 压入元素只固定压入其中一个stack
     * @param i
     */
    public void putVal(Object i) {
        if(i!=null) {
            s1.push(i);
        }
    }

    /**
     * 先进先出，永远先从另一个stack中取，
     * 如果另一个stack为空，则将当前stack的元素压入其栈，再取
     * @return
     */
    public Object takeVal() {
        if(!s2.isEmpty()) {
            return s2.pop();
        } else if (!s1.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        } else {
            throw new IllegalStateException("Stacks empty");
        }
    }

    public static void main(String[] args) {
        EbayQueueUsingStack ebayQueueUsingStack = new EbayQueueUsingStack();
        ebayQueueUsingStack.putVal(5);
        ebayQueueUsingStack.putVal(4);
        ebayQueueUsingStack.putVal(3);
        System.out.println(ebayQueueUsingStack.takeVal());
        ebayQueueUsingStack.putVal(2);
        ebayQueueUsingStack.putVal(1);
        System.out.println(ebayQueueUsingStack.takeVal());
        System.out.println(ebayQueueUsingStack.takeVal());
        System.out.println(ebayQueueUsingStack.takeVal());
        System.out.println(ebayQueueUsingStack.takeVal());

    }
}
