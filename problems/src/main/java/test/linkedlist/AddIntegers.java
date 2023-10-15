package test.linkedlist;

import java.util.List;

/**
 * Given the head pointers of two linked lists where each linked list represents an integer number (each node is a digit),
 * add them and return the resulting number in a linked list.
 */
public class AddIntegers {

    public static void main(String[] args) {
        LinkedList integer1 = new LinkedList();
        integer1.createLinkedList(List.of(1, 0, 9, 9));

        LinkedList integer2 = new LinkedList();
        integer2.createLinkedList(List.of(7, 3, 2));

        LinkedListNode sum = addIntegers(integer1.head, integer2.head);
        System.out.println(sum);
    }

    static LinkedListNode addIntegers(LinkedListNode integer1, LinkedListNode integer2) {
        LinkedListNode result = null;
        LinkedListNode last = null;
        int carry = 0;

        // This loop will run till any of the list is not null or carry is greater than 0
        while (integer1 != null || integer2 != null || carry > 0) {
            // Check if first list is empty
            int first = (integer1 == null ? 0 : integer1.data);

            // Check if second list is empty
            int second = (integer2 == null ? 0 : integer2.data);

            // Calculates sum
            int sum = first + second + carry;

            // Update carry after each iteration
            LinkedListNode pNew = new LinkedListNode(sum % 10);
            carry = sum / 10;

            // Stores the last added integer in result in the first iteration
            if (result == null) {
                result = pNew;
            } else {
                // last.next points to the current added node
                last.next = pNew;
            }

            // last now points to the current added node
            last = pNew;

            // advance if list_1 is not null
            if (integer1 != null) {
                integer1 = integer1.next;
            }

            // advance if list_2 is not null
            if (integer2 != null) {
                integer2 = integer2.next;
            }
        }
        return result;
    }
}
