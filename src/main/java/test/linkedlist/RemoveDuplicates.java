package test.linkedlist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a linked list of integers,
 * remove all the duplicate nodes from the linked list while retaining only the first occurrence of each duplicate.
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
//        ll.createLinkedList(List.of(1, 2, 3, 4));
        ll.createLinkedList(List.of(4, 7, 4, 9, 7, 11, 4));
        ll.displayLinkedList();
        removeDuplicates(ll.head);
        ll.displayLinkedList();
    }

    public static LinkedListNode removeDuplicates(LinkedListNode head) {
        LinkedListNode current = head;
        LinkedListNode lastUnique = current;

        Set<Integer> unique = new HashSet<>();

        while(current != null) {
            LinkedListNode next = current.next;
            if (!unique.contains(current.data)) {
                unique.add(current.data);
                lastUnique.next = current;
                lastUnique = current;
            }
            current = next;
        }
        if (lastUnique != null) {
            lastUnique.next = null;
        }

        return head;
    }

}
