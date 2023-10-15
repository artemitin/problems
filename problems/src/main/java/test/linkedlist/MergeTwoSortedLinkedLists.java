package test.linkedlist;

import java.util.List;

/**
 * We’re given the head of a linked list and a key. Delete all the nodes that contain the given key.
 * <p>
 * Note: The input linked list will not have cycles in it.
 */
public class MergeTwoSortedLinkedLists {

    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList();
        ll1.createLinkedList(List.of(1, 2, 3));
        LinkedList ll2 = new LinkedList();
        ll2.createLinkedList(List.of(4, 5, 6));

        LinkedListNode ll3 = mergeSorted(ll1.head, ll2.head);

    }

    public static LinkedListNode mergeSorted(LinkedListNode head1, LinkedListNode head2) {
        // If both lists are empty then merged list is also empty
        // If one of the lists is empty then other is the merged list
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }

        // Initializing merge_head
        LinkedListNode mergeHead;

        if (head1.data <= head2.data) {
            mergeHead = head1;
            head1 = head1.next;
        } else {
            mergeHead = head2;
            head2 = head2.next;
        }

        // Initializing merge_tail
        LinkedListNode mergeTail = mergeHead;

        while (head1 != null && head2 != null) {
            LinkedListNode temp;
            if (head1.data <= head2.data) {
                temp = head1;
                head1 = head1.next;
            } else {
                temp = head2;
                head2 = head2.next;
            }
            mergeTail.next = temp;
            mergeTail = temp;
        }

        if (head1 != null) {
            mergeTail.next = head1;
        } else if (head2 != null) {
            mergeTail.next = head2;
        }

        return mergeHead;
    }
}
