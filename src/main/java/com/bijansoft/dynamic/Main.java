package com.bijansoft.dynamic;

import java.util.Arrays;

public class Main {

  private static int countTripleSteps(int stepCount) {
    int[] memo = new int[stepCount];
    Arrays.fill(memo, -1);
    return countTripleSteps(stepCount, 0, memo);
  }

  private static int countTripleSteps(int stepCount, int combCount, int[] memo) {
    if (stepCount == 0) {
      return combCount + 1;
    }

    if (stepCount >= 3) {
      if (memo[stepCount - 3] == -1) {
        combCount = countTripleSteps(stepCount - 3, combCount, memo);
      } else {
        combCount = memo[stepCount - 3];
      }
    }
    if (stepCount >= 2) {
      if (memo[stepCount - 2] == -1) {
        combCount = countTripleSteps(stepCount - 2, combCount, memo);
      } else {
        combCount = memo[stepCount - 2];
      }
    }
    if (stepCount >= 1) {
      if (memo[stepCount - 1] == -1) {
        combCount = countTripleSteps(stepCount - 1, combCount, memo);
      } else {
        combCount = memo[stepCount - 1];
      }
    }

    return combCount;
  }

  public static void main(String[] args) {
    System.out.println(countTripleSteps(36));//2082876103
  }
}
