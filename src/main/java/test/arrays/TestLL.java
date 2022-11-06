package test.arrays;


import test.ListNode;

public class TestLL {

    public static void main(String[] args) {

        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        System.out.println(mergeTwoLists(list1, list2));

//        for (int i = 0; i < 30; i++) {
//            System.out.println("i = " + i);
//            System.out.println("i % 16 = " + (i % 16) + "; i & 15 = " + (i & 15));
//            System.out.println("is even? " + ((i & 1)));
//        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode current1 = list1;
        ListNode current2 = list2;
        ListNode merged = null;

        ListNode head = list1.val < list2.val ? list1 : list2;

        while (current1 != null && current2 != null) {
            ListNode current;
            if (current1.val < current2.val) {
                current = current1;
                current1 = current1.next;
            } else {
                current = current2;
                current2 = current2.next;
            }

            if (merged == null) {
                merged = current;
            } else {
                merged.next = current;
                merged = current;
            }
        }

        if (current1 != null) {
            merged.next = current1;
        }

        if (current2 != null) {
            merged.next = current2;
        }

        return head;
    }
}

