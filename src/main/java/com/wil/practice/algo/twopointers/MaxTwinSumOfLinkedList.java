package com.wil.practice.algo.twopointers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MaxTwinSumOfLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static int reverseSolution(ListNode head) {
        int max = 0;
        ListNode fast = head, slow = head;
        while (fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode prev = null, tmp;
        ListNode current = slow;
        while (current!=null) {
            tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        ListNode left = head, right = prev;

        while (right!=null) {
            max = Math.max(max, left.val+right.val);
            left = left.next;
            right = right.next;
        }
        return max;
    }

    private static int listSolution(ListNode head) {
        int max = 0;
        List<ListNode> list = new ArrayList<>();
        ListNode current;
        current = head;
        while (current!=null){
            list.add(current);
            current = current.next;
        }
        int i=0, j = list.size()-1;
        while (i<j) {
            max = Math.max(max, list.get(i).val + list.get(j).val);
            i++;
            j--;
        }
        list = null;
        return max;
    }

    private static int pairSum(ListNode head) {
        int max = 0;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode fast = head, slow = head;
        while (fast!=null && fast.next != null) {
            fast = fast.next.next;
            stack.push(slow);
            slow = slow.next;
        }
        ListNode pairNode1;
        while (slow!=null && !stack.isEmpty()) {
            pairNode1 = stack.pop();
            max = Math.max(max, pairNode1.val+slow.val);
            slow = slow.next;
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {5,4,2,1};
        int[] nums2 = new int[] {1,100000};
        int max;
        ListNode node;
        ListNode head = new ListNode(nums[0]);
        ListNode head2 = new ListNode(nums2[0]);
        node = head;
        for (int i = 1; i<nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }

        node = head2;
        for (int i = 1; i<nums2.length; i++) {
            node.next = new ListNode(nums2[i]);
            node = node.next;
        }

        max = pairSum(head);
        System.out.printf("%d%n", max);

        max = pairSum(head2);
        System.out.printf("%d%n", max);

        max = reverseSolution(head);
        System.out.printf("%d%n", max);
        max = reverseSolution(head2);
        System.out.printf("%d%n", max);
    }

}
