package test.linkedlist;

import java.util.List;

/**
 * We’re given the head of a linked list and a key. Delete all the nodes that contain the given key.
 * <p>
 * Note: The input linked list will not have cycles in it.
 */
public class DeleteAllOccurrences {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.createLinkedList(List.of(1, 2, 3, 4));
        ll.displayLinkedList();
        ll.head = deleteNode(ll.head, 1);
        ll.displayLinkedList();
    }

    public static LinkedListNode deleteNode(LinkedListNode head, int key) {
        LinkedListNode prev = null;
        LinkedListNode current = head;
        while (current != null) {
            if (current.data == key) {
                if (current == head) {
                    head = head.next;
                    current = head;
                } else {
                    prev.next = current.next;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }
        return head;
    }
}
