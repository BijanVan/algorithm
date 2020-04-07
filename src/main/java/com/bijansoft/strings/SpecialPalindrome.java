package com.bijansoft.strings;

import java.util.ArrayList;
import java.util.List;

public class SpecialPalindrome {

  private static class Location {

    private int start;
    private int length;

    private Location(int start, int length) {
      this.start = start;
      this.length = length;
    }
  }

  private static long calculateCombinations(long l) {
    return l * (l + 1) / 2;
  }

  private static long substrCount(int n, String s) {
    long count = 0;
    int curr = 0, prev = -1;
    List<List<Location>> locsList = new ArrayList<>(26);
    for (int i = 0; i < 26; i++) {
      locsList.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      curr = s.charAt(i) - 'a';
      if (curr == prev) {
        Location loc = locsList.get(curr).get(locsList.get(curr).size() - 1);
        loc.length++;
      } else {
        Location loc = new Location(i, 1);
        locsList.get(curr).add(loc);
      }

      prev = curr;
    }

    for (List<Location> locs : locsList) {
      if (locs.isEmpty()) {
        continue;
      }
      for (int i = 0; i < locs.size() - 1; i++) {
        count += calculateCombinations(locs.get(i).length);
        Location loc1 = locs.get(i);
        Location loc2 = locs.get(i + 1);
        if (loc2.start - loc1.start - loc1.length == 1) {
          count += Math.min(loc1.length, loc2.length);
        }
      }
      count += calculateCombinations(locs.get(locs.size() - 1).length);
    }

    return count;
  }

  public static void main(String[] args) {
    String s = "asasd";
    System.out.println(substrCount(s.length(), s));
  }
}
