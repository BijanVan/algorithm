package com.bijansoft.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class luckBalances {

  private static int luckBalance(int k, int[][] contests) {
    int n = contests.length;
    int result = 0;

    Arrays.sort(contests, Comparator.comparingInt(t -> -t[0]));
    for (int i = 0; i < n; i++) {
      if (contests[i][1] == 1) {
        if (k > 0) {
          result += contests[i][0];
          k--;
        } else {
          result -= contests[i][0];
        }
      } else {
        result += contests[i][0];
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int k = 3;
    int[][] contests = {
        {5, 1},
        {2, 1},
        {1, 1},
        {8, 1},
        {10, 0},
        {5, 0}};

    System.out.println(luckBalance(k, contests));
  }

}
