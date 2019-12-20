package com.bijansoft.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {
    LruCache<Integer, Integer> lru;

    @BeforeEach
    void initLruCache() {
        lru = new LruCache<>(5);
        lru.put(1, 10);
        lru.put(2, 20);
        lru.put(3, 30);
    }

    @DisplayName("containsKey should return null if key does not exist.")
    @Test
    void containsKey() {
        assertFalse(lru.containsKey(0));
    }

    @DisplayName("get should return null if key does not exist.")
    @Test
    void getReturnNull() {
        assertNull(lru.get(10));
    }

    @DisplayName("get should return value if key exists and reorder the entries.")
    @Test
    void getReturnValueAndReorder() {
        lru.put(4, 40);
        lru.put(5, 50);
        var v = lru.get(1);
        lru.put(6, 60);
        v = lru.get(1);

        assertEquals(v, 10);
        assertFalse(lru.containsKey(2));
    }

//    @DisplayName("put should add key/value and reorder the entries - Below threshold case")
//    @Test
//    void putBelowThreshold() {
//    }

    @DisplayName("put should add key/value, remove the least and reorder the entries - Above threshold case")
    @Test
    void putAboveThreshold() {
        lru.put(4, 40);
        lru.put(5, 50);
        lru.put(6, 60);

        assertTrue(lru.containsKey(6));
        assertFalse(lru.containsKey(1));
    }

    @DisplayName("put should update value for the existing key and reorder the entries")
    @Test
    void putUpdate() {
        lru.put(4, 40);
        lru.put(5, 50);
        lru.put(1, 11);
        lru.put(6, 60);
        var v = lru.get(1);

        assertEquals(v, 11);
        assertTrue(lru.containsKey(6));
        assertFalse(lru.containsKey(2));
    }
}