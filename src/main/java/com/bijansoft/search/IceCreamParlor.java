package com.bijansoft.search;

import java.util.Arrays;

public class IceCreamParlor {

  private static class Cost implements Comparable<Cost> {

    private final int index;
    private final int cost;

    private Cost(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo(Cost other) {
      return Integer.compare(cost, other.cost);
    }
  }

  private static int findSecond(Cost[] cost, int second, int exclude) {
    int index = 0;
    int start = 0, end = cost.length;
    while (end - start > 1) {
      int mid = start + (end - start) / 2;

      if (cost[mid].cost == second && cost[mid].index != exclude) {
        index = cost[mid].index;
        break;
      } else if (cost[mid].cost < second) {
        start = mid;
      } else {
        end = mid;
      }
    }

    return index;
  }

  private static void whatFlavors(int[] cost, int money) {
    Cost[] cst = new Cost[cost.length];
    for (int i = 0; i < cost.length; i++) {
      cst[i] = new Cost(i + 1, cost[i]);
    }
    Arrays.sort(cst);

    for (int i = 0; i < cst.length; i++) {
      int first = cst[i].cost;
      int secondIndex = findSecond(cst, money - first, cst[i].index);
      if (secondIndex > 0) {
        if (cst[i].index < secondIndex) {
          System.out.println(cst[i].index + " " + secondIndex);
        } else {
          System.out.println(secondIndex + " " + cst[i].index );
        }
        break;
      }
    }
  }

  public static void main(String[] args) {
    int money = 12;
    int[] cost = {3,6,3,6};
    whatFlavors(cost, money);
  }
}
