package test.linkedlist;

import java.util.List;

/**
 * We’re given the head pointer of a linked list.
 * Sort the linked list in ascending order using insertion sort.
 * Return the new head pointer of the sorted linked list.
 */
public class LinkedListInsertionSort {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.createLinkedList(List.of(3, 2, 1));
//        ll.createLinkedList(List.of(29, 23, 82, 11));
        ll.displayLinkedList();
        ll.head = insertionSort(ll.head);
        ll.displayLinkedList();
    }

    public static LinkedListNode sortedInsert(LinkedListNode head, LinkedListNode node) {
        // Return head if node is None
        if (node == null) {
            return head;
        }

        // Return node if there is no head node
        // or node's value is less than or equal to the head's value
        if (head == null || node.data <= head.data) {
            node.next = head;
            return node;
        }

        LinkedListNode curr = head;

        // Loop to find the sorted position in the linked list
        while (curr.next != null && curr.next.data < node.data) {
            curr = curr.next;
        }

        // Adding node in the linked list
        node.next = curr.next;
        curr.next = node;

        // Returning head of the modified linked list
        return head;
    }

    public static LinkedListNode insertionSort(LinkedListNode head) {
        // Initializing a list for sorted linked list
        LinkedListNode sorted = null;
        LinkedListNode curr = head;

        // Looping over the original linked list and passing each node
        // to the sorted_insert function to get a sorted linked list
        while (curr != null) {
            LinkedListNode temp = curr.next;
            sorted = sortedInsert(sorted, curr);
            curr = temp;
        }

        return sorted;
    }
}
