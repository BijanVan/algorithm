package com.bijansoft.cache;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> {
    private final int capacity;
    private int count;
    private Node<K, V> head;
    private Node<K, V> tail;
    private final Map<K, Node<K, V>> map;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        head = new Node<>(null, null, null, null);
        tail = new Node<>(null, null, null, null);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>(capacity);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            moveToHead(node);
            return node.value;
        } else {
            return null;
        }
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (count == capacity) {
                map.remove(tail.prev.key);
                tail.prev = tail.prev.prev;
                count--;
            }
            var node = new Node<>(key, value, head, head.next);
            head.next.prev = node;
            head.next = node;
            map.put(key, node);
            count++;
        }
    }

    private void moveToHead(Node<K, V> n) {
        var prev = n.prev;
        var next = n.next;

        if (prev == head) return;

        n.prev = head;
        n.next = head.next;
        head.next = n;

        prev.next = next;
        next.prev = prev;
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> prev, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
