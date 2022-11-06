package test.linkedlist;

import test.ListNode;

public class SwapPairs {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

        swapPairs(list1).print();
    }

    public static ListNode swapPairs(ListNode head) {
//        ? -> 1 -> 2 -> 3 -> 4
//            cur  nxt

        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;

        ListNode cur = head;
        ListNode newHead = head.next;

        while(cur != null && cur.next != null) {
            ListNode nxt = cur.next;
            cur.next = nxt.next;
            nxt.next = cur;

            pre.next = nxt;

            pre = cur;
            cur = cur.next;
        }

        return newHead;
    }
}
