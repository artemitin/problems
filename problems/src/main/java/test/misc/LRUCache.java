package test.misc;


import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement an LRU cache class with the following functions:
 * <p>
 * LRUCache(size): Initializes an LRU cache with the capacity size.
 * get(key): Returns the value of the key, or -1 if the key does not exist.
 * set(key, value): Adds a new key-value pair or updates an existing key with a new value.
 * After adding a new key, if the number of keys exceeds the cache capacity,
 * evict the least recently used key and add the new one.
 * <p>
 * A cache store is usually not big enough to store a complete data set.
 * Whenever the cache becomes full, we need to evict some data from it.
 * There are several caching algorithms to implement a cache eviction policy.
 * LRU is a very simple and commonly used algorithm.
 * The core concept of the LRU algorithm is to evict the oldest data from the cache to accommodate more data.
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.get(10);      //(),                       return -1
        cache.set(10, 100); //([10,100])
        cache.get(10);      //([10,100]),               return 100
        cache.set(15, 150); //([15,150], [10,100])
        cache.get(10);      //([10,100], [15,150])      return 100
        cache.set(30, 300); //([30,300], [10,100])
        cache.get(15);      //([30,300], [10,100])      return -1
        cache.get(30);      //([30,300], [10,100])      return 300
    }

    int cacheCapacity;

    // Our hashmap will store a keys and iterators to the LinkedList nodes
    Map<Integer, Pair> cacheMap = new HashMap<>();

    Deque<Pair> cacheList = new java.util.LinkedList<>();

    public LRUCache(int size) {
        cacheCapacity = size;
    }

    int get(int key) {
        Pair current = cacheMap.get(key);
        if (current == null) {
            return -1;
        }

        // rebalance
        Pair first = cacheList.getFirst();
        if (current != first) {
            cacheList.remove(current);
            cacheList.addFirst(current);
        }

        return current.second;
    }

    void set(int key, int value) {
        Pair newElement = new Pair(key, value);
        cacheList.addFirst(newElement);
        cacheMap.put(key, newElement);
        if (cacheList.size() > cacheCapacity) {
            Pair last = cacheList.getLast();
            Integer lastKey = last.first;
            cacheMap.remove(lastKey);
            cacheList.removeLast();
        }
    }
}


// We will use a linkedlist of a pair of integers
// where the first integer will be the key
// and the second integer will be the value
class KeyValuePairLL extends LinkedList<Pair> {
}

class LinkedList<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int length;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public int size() {
        return this.length;
    }

    public void insertAtHead(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.length++;
    }

    public void insertAtTail(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.length++;
    }

    public void addFirst(LinkedListNode<T> newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.length++;
    }

    public void addLast(LinkedListNode<T> newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.length++;
    }

    public void remove(T data) {
        LinkedListNode<T> tmp = this.head;
        while (tmp != null) {
            if (tmp.data == data) {
                this.remove(tmp);
                return;
            }
            tmp = tmp.next;
        }
    }

    public void remove(LinkedListNode<T> node) {
        if (node == null)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (node == this.head)
            this.head = this.head.next;
        if (node == this.tail) {
            this.tail = this.tail.prev;
            if (this.tail != null)
                this.tail.next = null;
        }
        this.length--;
        node = null;
    }

    public void removeFirst() {
        this.remove(this.head);
    }

    public void removeLast() {
        this.remove(this.tail);
    }

    public LinkedListNode<T> getFirst() {
        return this.head;
    }

    public LinkedListNode<T> getLast() {
        return this.tail;
    }
}

class LinkedListNode<T> {
    public T data;
    public LinkedListNode<T> next;
    public LinkedListNode<T> prev;

    public LinkedListNode(T dataVal) {
        this.data = dataVal;
        this.next = null;
        this.prev = null;
    }
}