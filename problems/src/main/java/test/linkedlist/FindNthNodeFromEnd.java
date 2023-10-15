package test.linkedlist;

import java.util.List;

/**
 * Given a singly linked list, return the nth node from the last node. Return null if n is larger than the size of the list.
 */
public class FindNthNodeFromEnd {

    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList();
        ll1.createLinkedList(List.of(1, 1, 2, 3, 4, 5));

        LinkedListNode nthFromLast = findNthFromLast(ll1.head, 3);
        System.out.println(nthFromLast);
    }

    public static LinkedListNode findNthFromLast(LinkedListNode head, int n) {
        int length = getLength(head);

        if (n > length) {
            return null;
        }

        LinkedListNode node = head;
        for (int i = 0; i < length - n; i++) {
            node = node.next;
        }

        return node;
    }

    // Function to find length of a linked list
    private static int getLength(LinkedListNode head) {
        LinkedListNode node = head;
        int count = 0;
        while(node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

}
