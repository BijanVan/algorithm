package com.bijansoft.strings;

import java.util.HashMap;
import java.util.Map;

public class Anagrams {

  private static int makeAnagram(String a, String b) {
    int count = 0;
    Map<Character, Integer> first = new HashMap<>(a.length());
    Map<Character, Integer> second = new HashMap<>(b.length());
    for (int i = 0; i < a.length(); i++) {
      first.computeIfPresent(a.charAt(i), (k, v) -> v + 1);
      first.putIfAbsent(a.charAt(i), 1);
    }
    for (int i = 0; i < b.length(); i++) {
      second.computeIfPresent(b.charAt(i), (k, v) -> v + 1);
      second.putIfAbsent(b.charAt(i), 1);
    }

    for(Map.Entry<Character, Integer> e : first.entrySet()) {
      Integer count2;
      if ((count2 = second.get(e.getKey())) != null) {
        count += Math.abs(e.getValue() - count2);
        e.setValue(0);
        second.put(e.getKey(), 0);
      } else {
        count += e.getValue();
        e.setValue(0);
      }
    }

    for (int value : second.values()) {
      count += value;
    }

    return count;
  }

  public static void main(String[] args) {
    String a = "cde";
    String b = "abcc";
    int result = makeAnagram(a, b);
    System.out.println(result);

  }

}
