package test.linkedlist;

import java.util.*;

public class LinkedList {
    public LinkedListNode head;

    public LinkedList() {
        this.head = null;
    }

    public void insertAtHead(int data) {
        if (this.head == null) {
            this.head = new LinkedListNode(data);
        } else {
            LinkedListNode newNode = new LinkedListNode(data);
            newNode.next = this.head;
            this.head = newNode;
        }
    }

    public void insertAtTail(int data) {
        if (this.head == null) {
            this.head = new LinkedListNode(data);
        } else {
            LinkedListNode newNode = new LinkedListNode(data);
            LinkedListNode temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void createLinkedList(List<Integer> lst) {
        for (int i = lst.size() - 1; i >= 0; i--) {
            insertAtHead(lst.get(i));
        }
    }

    public void displayLinkedList() {
        LinkedListNode temp = this.head;
        StringBuilder res = new StringBuilder("[");
        while (temp != null) {
            res.insert(res.length(), temp.data);
            temp = temp.next;
            if (temp != null) {
                res.insert(res.length(), ", ");
            }
        }
        res.insert(res.length(), "]");
        System.out.println(res.toString());
    }
}

class LinkedListNode {
    public int data;
    public LinkedListNode next;
    public LinkedListNode prev;
    public LinkedListNode arbitraryPointer;

    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
        this.arbitraryPointer = null;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}