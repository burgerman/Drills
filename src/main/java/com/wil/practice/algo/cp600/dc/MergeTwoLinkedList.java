package com.wil.practice.algo.cp600.dc;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoLinkedList {

    private static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
    }

    private static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        List<SinglyLinkedListNode> res = new ArrayList<>();
        if (head1 == null || head2 == null) throw new IllegalStateException("At least one list is null");
        SinglyLinkedListNode p1 = head1;
        SinglyLinkedListNode p2 = head2;

        while (p1!=null && p2!=null) {
            if (p1.data != p2.data) {
                if (p1.data<p2.data) {
                    res.add(p1);
                    p1 = p1.next;
                } else {
                    res.add(p2);
                    p2 = p2.next;
                }
            } else {
                res.add(p1);
                res.add(p2);
                p1 = p1.next;
                p2 = p2.next;
            }
        }

        while (p1!=null) {
            res.add(p1);
            p1 = p1.next;
        }

        while (p2!=null) {
            res.add(p2);
            p2 = p2.next;
        }

        for (int i=res.size()-2; i>=0; i--) res.get(i).next = res.get(i+1);
        return res.get(0);
    }
}
