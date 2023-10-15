package test.linkedlist;

import java.util.List;

public class Rotate {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.createLinkedList(List.of(1, 2, 3, 4, 5));
        ll.displayLinkedList();
        ll.head = rotateList(ll.head, 3);
        ll.displayLinkedList();
    }

    public static LinkedListNode rotateList(LinkedListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        int length = getLength(head);

        // If n (number of rotations required) is bigger than
        // length of linked list or if n is negative then
        // we need to adjust total number of rotations needed
        n %= length;

        if (n < 0) {
            n += length;
        }

        if (n == 0) {
            return head;
        }

        LinkedListNode current = head;
        LinkedListNode tail = head;

        while (tail.next != null) {
            tail = current;
            int count = n;
            while(count > 0 && tail.next != null) {
                tail = tail.next;
                count--;
            }
            if (tail.next != null) {
                current = current.next;
            }
        }

        tail.next = head;
        LinkedListNode newHead = current.next;
        current.next = null;

        return newHead;
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
