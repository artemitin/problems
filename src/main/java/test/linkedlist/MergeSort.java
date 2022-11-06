package test.linkedlist;

import java.util.List;

/**
 * Merge sort is one of the standard sorting algorithms for a linked list.
 * If the given linked list is 29−>23−>82−>11,
 * then the sorted (in ascending order) list should be 11−>23−>29−>82
 * .
 */
public class MergeSort {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
//        ll.createLinkedList(List.of(4));
//        ll.createLinkedList(List.of(4, 2));
        ll.createLinkedList(List.of(38, 27, 43, 3, 9, 82, 10));
//        ll.createLinkedList(List.of(29, 23, 82, 11));
        ll.displayLinkedList();
        ll.head = mergeSort(ll.head);
        ll.displayLinkedList();
    }

    // This method splits the linked list in two halves by iterating over the whole list
    // It returns head pointers of first and second halves of the split linked list
    // Head of the first half is just the head node of linked list
    public static LinkedListNode splitInHalf(LinkedListNode head) {
        // Check if there is only one element in the list
        if (head == null || head.next == null) {
            return null;
        }

        // Let's use the classic technique of moving two pointers:
        // 'fast' and 'slow'. 'fast' will move two steps in each
        // iteration whereas 'slow' will move only one step,
        // when 'fast' points to null, 'slow' will be pointing to middle element
        LinkedListNode slow, fast;
        slow = head;
        fast = head.next;

        while (fast != null) {

            fast = fast.next;

            if (fast != null) {

                fast = fast.next;
                slow = slow.next;
            }
        }

        LinkedListNode tmp = slow.next;

        // Terminate first linked list.
        slow.next = null;
        return tmp;
    }

    public static LinkedListNode mergeSortedLists(LinkedListNode first, LinkedListNode second) {

        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }

        LinkedListNode newHead;
        if (first.data <= second.data) {
            newHead = first;
            first = first.next;
        } else {
            newHead = second;
            second = second.next;
        }

        LinkedListNode newCurrent = newHead;
        while (first != null && second != null) {
            LinkedListNode temp;
            if (first.data <= second.data) {
                temp = first;
                first = first.next;
            } else {
                temp = second;
                second = second.next;
            }

            newCurrent.next = temp;
            newCurrent = temp;
        }

        if (first == null) {
            newCurrent.next = second;
        } else {
            newCurrent.next = first;
        }

        return newHead;
    }

    public static LinkedListNode mergeSort(LinkedListNode head) {

        // No need to sort a single element.
        if (head == null || head.next == null) {
            return head;
        }

        // Let's split the list in half, sort the sublists
        // and then merge the sorted lists.
        LinkedListNode second = splitInHalf(head);
        head = mergeSort(head);
        second = mergeSort(second);

        return mergeSortedLists(head, second);
    }
}

@Deprecated
class Pair<X, Y> {
    public X first;
    public Y second;

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}