package com.bijansoft.greedy;

import java.util.Arrays;

public class MaxMin {

  private static int maxMin(int k, int[] arr) {
    int diff = Integer.MAX_VALUE;
    int n = arr.length;
    Arrays.sort(arr);
    for (int i = 0; i <= n - k; i++) {
      int min = arr[i];
      int max = arr[i + k - 1];
      diff = Math.min(diff, max - min);
    }

    return diff;
  }

  public static void main(String[] args) {
    int k = 3;
    int[] arr = {10, 100, 300, 200, 1000, 20, 30};
    System.out.println(maxMin(k, arr));
  }
}
