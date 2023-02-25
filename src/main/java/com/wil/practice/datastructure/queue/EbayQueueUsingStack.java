package com.wil.practice.datastructure.queue;

import java.util.Stack;

public class EbayQueueUsingStack {

    private Stack s1 = new Stack();
    private Stack s2 = new Stack();

    public void putVal(Object i) {
        if(i!=null) {
            s1.push(i);
        }
    }

    public Object takeVal() {
        if(!s2.isEmpty()) {
            return s2.pop();
        } else if (!s1.empty()) {
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
        System.out.println(ebayQueueUsingStack.takeVal());

    }
}
