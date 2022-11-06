package test.linkedlist;

import java.util.List;

public class Reverse {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.createLinkedList(List.of(1, 2, 3, 4));
        ll.displayLinkedList();
        ll.head = reverse(ll.head);
        ll.displayLinkedList();
    }

    public static LinkedListNode reverse(LinkedListNode head) {

        LinkedListNode prev = null;
        LinkedListNode current = head;

        while(current != null) {
            LinkedListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

}
