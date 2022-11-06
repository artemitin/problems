package test.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LRUCacheTest {

    @Test
    void test() {
        LRUCache cache = new LRUCache(2);
        assertEquals(-1, cache.get(10));        //(),                       return -1
        cache.set(10, 100);                     //([10,100])
        assertEquals(100, cache.get(10));       //([10,100]),               return 100
        cache.set(15, 150);                     //([15,150], [10,100])
        assertEquals(100, cache.get(10));       //([10,100], [15,150])      return 100
        cache.set(30, 300);                     //([30,300], [10,100])
        assertEquals(-1, cache.get(15));        //([30,300], [10,100])      return -1
        assertEquals(300, cache.get(30));       //([30,300], [10,100])      return 300
    }
}
