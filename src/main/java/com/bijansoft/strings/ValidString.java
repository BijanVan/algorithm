package com.bijansoft.strings;

import java.util.Arrays;

public class ValidString {

  private static String isValid(String s) {
    if (s.length() == 1) {
      return "YES";
    }

    int[] counts = new int[26];
    for (int i = 0; i < s.length(); i++) {
      int idx = s.charAt(i) - 'a';
      counts[idx]++;
    }

    Arrays.sort(counts);
    System.out.println(Arrays.toString(counts));
    int i = 0, base = 0;
    while (counts[i] == 0) {
      i++;
    }

    base = counts[i];
    i++;
    if (base == 1) {
      while (i < counts.length - 1) {
        if (counts[i + 1] != counts[i]) {
          return "NO";
        }
        i++;
      }
      return "YES";
    }

    while (i < counts.length - 1) {
      int diff = counts[i + 1] - base;
      if (diff == 1 && i == counts.length - 2) {
        return "YES";
      }
      if (diff != 0) {
        return "NO";
      }
      i++;
    }

    return "YES";
  }

  public static void main(String[] args) {
    String s = "aaabbbc";
    String result = isValid(s);
    System.out.println(result);
  }
}
