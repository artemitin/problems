package test.linkedlist;

import java.util.List;

/**
 * Given the head nodes of two linked lists, return the node at which the two lists intersect.
 * We will return null if we do not find any intersection between the two linked lists.
 */
public class IntersectionPointTwoLists {

    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList();
        ll1.createLinkedList(List.of(13, 4, 12, 27));
        LinkedList ll2 = new LinkedList();
        ll2.createLinkedList(List.of(29, 23, 82, 11, 12, 27));

        intersect(ll1.head, ll2.head);
    }

    public static LinkedListNode intersect(LinkedListNode head1, LinkedListNode head2) {

        // Finding lengths of both linked list
        int list1length = getLength(head1);
        int list2length = getLength(head2);

        int lengthDifference = list1length - list2length;
        LinkedListNode longest = head1;
        LinkedListNode shortest = head2;
        if (list1length < list2length) {
            shortest = head1;
            longest = head2;
            lengthDifference *= -1;
        }

        // Move head pointer of longer list 'lengthDifference' steps forward
        for (int i = 0; i < lengthDifference; i++) {
            longest = longest.next;
        }

        // Now traverse both lists, comparing nodes until we find
        // a match or reach the end of lists
        while (shortest != null && longest != null) {
            if (shortest == longest) {
                return longest;
            }
            shortest = shortest.next;
            longest = longest.next;
        }

        return null;
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
