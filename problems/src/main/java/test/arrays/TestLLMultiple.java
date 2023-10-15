package test.arrays;

import test.ListNode;

public class TestLLMultiple {

    public static void main(String[] args) {

        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));

//        ListNode[] lists = {list1, list2, list3};
        ListNode[] lists = {new ListNode(1)};

        mergeMultiple(lists).print();

//        for (int i = 0; i < 30; i++) {
//            System.out.println("i = " + i);
//            System.out.println("i % 16 = " + (i % 16) + "; i & 15 = " + (i & 15));
//            System.out.println("is even? " + ((i & 1)));
//        }
    }

    //expected = [1,1,2,3,4,4,5,6]
    public static ListNode mergeMultiple(ListNode[] lists) {
        ListNode newHead = null;
        ListNode merged = null;

        while (true) {
            int minInd = 0;
            ListNode currentMin = null;
            for (int i = 0; i < lists.length; i++) {
                ListNode head = lists[i];

                if (head != null && (currentMin == null || head.val < currentMin.val)) {
                    minInd = i;
                    currentMin = head;
                }
            }

            if (currentMin == null) {
                break;
            } else {
                lists[minInd] = currentMin.next;
            }

            if (merged != null) {
                merged.next = currentMin;
            }
            merged = currentMin;

            if (newHead == null) {
                newHead = merged;
            }
        }

        return newHead;
    }
}

