package com.bijansoft.greedy;

import java.util.Arrays;

public class GreedyFlorist {

  private static int getMinimumCost(int k, int[] c) {
    int cost = 0;
    int n = c.length;

    Arrays.sort(c);
    for (int i = 0; i < Math.ceil((double)n / k); i++) {
      for (int j = n - 1 - i * k; j >= n - k - i * k && j >= 0; j--) {
        cost += (1 + i) * c[j];
      }
    }

    return cost;
  }

  public static void main(String[] args) {
    int k = 3;
    int[] c = {1, 3, 5, 7, 9};
    System.out.println(getMinimumCost(k, c));
  }
}
