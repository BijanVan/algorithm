package com.bijansoft;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Misc1 {
  private static long solution(int[] data, int k, int l) {
    long[] ks = slidingSum(data, k);
    long[] ls = slidingSum(data, l);

    // K first
    long maxK = findMax(ks, k, ls, l, false);
    // L first
    long maxL = findMax(ks, k, ls, l, true);

    return (maxK > maxL) ? maxK : maxL;
  }

  private static long findMax(long[] ks, int k, long[] ls, int l, boolean swap) {
    long max = -1;
    if (swap) {
      long[] temp = ks;
      ks = ls;
      ls = temp;
    }

    for (int i = 0; i <= ks.length - l; i++) {
      for (int j = i + k; j < ls.length; j++) {
        long total = ks[i] + ls[j];
        if (total > max) max = total;
      }
    }
    return max;
  }

  private static long[] slidingSum(int[] data, int width) {
    long[] sums = new long[data.length - width + 1];

    for (int i = 0; i < sums.length; i++) {
      sums[i] = 0;
      for (int j = i; j < i + width; j++) sums[i] += data[j];
    }

    return sums;
  }

  private static List<Long> modulo(long value) {
    if (value <= 0) throw new IllegalArgumentException("value must be positive");

    long exp = (long)Math.log10(value);
    long rem = (long)(value - Math.pow(10, exp));

    return Arrays.asList(exp, rem);
  }

  public static void main(String[] args) {
    int[] test1 = new int[] {6, 1, 4, 6, 3, 2, 7, 4};
    int[] test2 = new int[] {10, 19, 15};

    long max1 = solution(test1, 3, 2);
    System.out.println("max1 = " + max1);
    System.out.println("max1 (modulo format) = " + modulo(max1));
    long max2 = solution(test2, 2, 2);
    System.out.println("max2 = " + max2);

    int N = 20000;
    Random rnd = new Random();
    int[] testN = new int[N];
    for (int i = 0; i < N; i++) {
      testN[i] = rnd.nextInt(1000000000);
    }
    long start = System.currentTimeMillis();
    long maxN = solution(testN, 1000, 2000);
    long end = System.currentTimeMillis();
    System.out.println("maxN = " + maxN);
    System.out.println(end - start);
    System.out.println("maxN (modulo format) = " + modulo(maxN));

  }
}
