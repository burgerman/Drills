package com.wil.practice.algo.twopointers;

public class DeleteMiddleNodeOfLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static ListNode deleteMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        if (slow.next == null) return null;
        while (fast!=null) {
            if (fast.next == null) break;
            else fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return head;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,4,7,1,2,6};
        int[] nums2 = new int[] {1,2,3,4};
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

        node = deleteMiddle(head);
        System.out.printf("%d ", node.val);
        while (node.next!=null) {
            node = node.next;
            System.out.printf("%d ", node.val);
        }
        System.out.println();
        node = deleteMiddle(head2);
        System.out.printf("%d ", node.val);
        while (node.next!=null) {
            node = node.next;
            System.out.printf("%d ", node.val);
        }

    }


}
