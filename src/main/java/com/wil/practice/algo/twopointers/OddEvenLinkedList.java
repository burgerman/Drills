package com.wil.practice.algo.twopointers;

public class OddEvenLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    private static ListNode oddEvenList(ListNode head) {
        ListNode prevOdd = head;
        if (prevOdd == null || head.next == null) return head;
        ListNode prevEven = head.next;
        ListNode evenHead = head.next;
        while (prevOdd.next!=null && prevEven.next!=null) {
            prevOdd.next = prevEven.next;
            prevOdd = prevEven.next;
            prevEven.next = prevOdd.next;
            prevEven = prevOdd.next;
        }
        prevOdd.next = evenHead;
        return head;
    }
}
