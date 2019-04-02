package com.bijansoft;

import edu.princeton.cs.algs4.StdIn;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * @param <K> Type of keys
 * @param <V> Type of mapped values
 */
public class BinarySearchTree<K, V> {
  private final Comparator<? super K> comparator;
  private Entry<K, V> root = null;

  public BinarySearchTree() {
    comparator = null;
  }

  public BinarySearchTree(Comparator<? super K> comparator) {
    this.comparator = comparator;
  }

  public int size() {
    return size(root);
  }

  // public V put (K key, V value) {
  // Entry<K, V> entry = root;
  //
  // }

  private int size(Entry<K, V> entry) {
    return entry == null ? 0 : entry.size;
  }

  private static boolean valEquals(Object o1, Object o2) {
    return o1 == null ? o2 == null : o1.equals(o2);
  }

  static final class Entry<K, V> implements Map.Entry<K, V> {
    K key;
    V value;
    Entry<K, V> left = null;
    Entry<K, V> right = null;
    Entry<K, V> parent;
    int size;

    Entry(K key, V value, Entry<K, V> parent) {
      this.key = key;
      this.value = value;
      this.parent = parent;
      size = 1;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public V setValue(V value) {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Map.Entry)) return false;
      Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
      return valEquals(this.key, e.getKey()) && valEquals(this.value, e.getValue());
    }

    @Override
    public int hashCode() {
      int keyHash = key == null ? 0 : key.hashCode();
      int valueHash = value == null ? 0 : value.hashCode();
      return keyHash ^ valueHash;
    }

    @Override
    public String toString() {
      return (left == null ? "" : left.toString())
          + "("
          + key
          + ", "
          + value
          + "), "
          + (right == null ? "" : right.toString());
    }
  }

  public static void main(String[] args) {
    Entry<Integer, Integer> e1 = new Entry<>(1, 10, null);
    Entry<Integer, Integer> e2 = new Entry<>(0, 0, e1);
    Entry<Integer, Integer> e3 = new Entry<>(2, 20, e1);
    e1.left = e2;
    e1.right = e3;
    System.out.println(e1);
  }
}
