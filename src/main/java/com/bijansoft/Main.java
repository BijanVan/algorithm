package com.bijansoft;

// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdRandom;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

// import javax.swing.*;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.IntStream;

// import static java.util.stream.Collectors.toList;

// -Xloggc:gclog -Xms2g -Xmx2g -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -server
public class Main {

  // public static void comaptStrings() {
  // long startTime = System.currentTimeMillis();
  //
  // List strings = IntStream.rangeClosed(1, 10_000_000)
  // .mapToObj(Integer::toString)
  // .collect(toList());
  //
  // long totalTime = System.currentTimeMillis() - startTime;
  // System.out.println(
  // "Generated " + strings.size() + " strings in " + totalTime + " ms.");
  //
  // startTime = System.currentTimeMillis();
  //
  // String appended = (String) strings.stream()
  // .limit(100_000)
  // .reduce("", (l, r) -> l.toString() + r.toString());
  //
  // totalTime = System.currentTimeMillis() - startTime;
  // System.out.println("Created string of length " + appended.length()
  // + " in " + totalTime + " ms.");
  // }

  private static String lcp(String s, String t) {
    var maxLength = Math.min(s.length(), t.length());
    var length = 0;
    for (int i = 0; i < maxLength; i++) {
      if (s.charAt(i) == t.charAt(i)) length++;
    }

    return (length == 0) ? "" : s.substring(0, length);
  }

  private static int countBits(int x) {
    int count = 0;

    while (x != 0) {
      count += (x & 0x1);
      x >>>= 1;
    }

    return count;
  }

  private static void evenOdd(int[] ints) {
    if (ints == null) return;
    int idx1 = 0;
    int idx2 = 1;

    while (idx2 < ints.length) {
      if (ints[idx1] % 2 != 0) {
        if (ints[idx2] % 2 != 0) {
          idx2++;
          continue;
        }
        // if (idx2 >= ints.length) break;
        int temp = ints[idx1];
        ints[idx1] = ints[idx2];
        ints[idx2] = temp;
      }
      idx1++;
      idx2++;
    }
  }

  private enum Color {
    RED,
    WHITE,
    BLUE
  }

  private static void dutchFlagPartition(int pivotIndex, List<Color> colors) {
    if (colors == null || pivotIndex < 0 || pivotIndex >= colors.size()) return;
    Color pivot = colors.get(pivotIndex);

    int smaller = 0, equals = 0, larger = colors.size() - 1;
    for (int i = 0; i < colors.size(); i++) {
      if (colors.get(i).ordinal() < pivot.ordinal()) {
        Collections.swap(colors, i, smaller);
        smaller++;
        equals++;
      } else if (colors.get(i).ordinal() == pivot.ordinal()) {
        equals++;
      } else {
        Collections.swap(colors, i, larger);
        larger--;
        i--;
      }

      if (larger < equals) break;
    }

    // // alternative #1
    // int smaller = 0;
    // for (int i = 0; i < colors.size(); i++) {
    // if (colors.get(i).ordinal() < pivot.ordinal()) {
    // Collections.swap(colors, i, smaller);
    // smaller++;
    // }
    // }
    //
    // int larger = colors.size() - 1;
    // for (int i = colors.size() - 1; i >= 0 && colors.get(i).ordinal() >= pivot.ordinal();
    // i--)
    // {
    // if (colors.get(i).ordinal() > pivot.ordinal()) {
    // Collections.swap(colors, i, larger);
    // larger--;
    // }
    // }
  }

  private static boolean isPalindromic(String str) {
    int start = 0, end = str.length() - 1;

    while (start < end) {
      if (str.charAt(start) != str.charAt(end)) return false;
      start++;
      end--;
    }
    return true;
  }

  private static String intToString(int value) {
    boolean negative = value < 0;
    if (negative) value = -value;
    StringBuilder sb = new StringBuilder();
    while (value > 0) {
      sb.append((char) ('0' + value % 10));
      // sb.append(Character.forDigit(value % 10, 10));
      value /= 10;
    }
    if (negative) sb.append('-');

    return sb.reverse().toString();
  }

  private static int stringtoInt(String str) {
    int value = 0;
    int index = 0;
    int length = str.length();
    boolean negative = str.charAt(index) == '-';
    if (negative) index++;

    for (int i = index; i < length; i++) {
      value += (str.charAt(i) - '0') * Math.pow(10, (length - i - 1));
    }

    if (negative) value = -value;
    return value;
  }

  private static String convertBase(String str, int b1, int b2) {
    int value = 0;
    for (int i = 0; i < str.length(); i++) {
      int digit = str.charAt(i) - '0';
      int offset = (digit % b1 > 9) ? 7 : 0;
      value = value * b1 + (digit - offset);
    }

    return convertToBase(value, b2);
  }

  private static String convertToBase(int value, int base) {
    StringBuilder sb = new StringBuilder();

    while (value > 0) {
      int offset = (value % base > 9) ? 7 : 0;
      sb.append((char) ('0' + offset + value % base));
      value /= base;
    }

    return sb.reverse().toString();
  }

  private static class ListNode<T> {
    T data;
    ListNode<T> next;
  }

  private static ListNode<Integer> search(ListNode<Integer> list, int key) {
    while (list != null && !list.data.equals(key)) {
      list = list.next;
    }
    return list;
  }

  private static void insertAfter(ListNode<Integer> node, ListNode<Integer> newNode) {
    newNode.next = node.next;
    node.next = newNode;
  }

  private static void deleteList(ListNode<Integer> aNode) {
    aNode.next = aNode.next.next;
  }

  private static ListNode<Integer> hasCycle(ListNode<Integer> head) {
    ListNode<Integer> iter1 = head, iter2 = head.next;
    if (iter2 == null) return null;
    while (iter2.next != null) {
      if (iter1.next == iter2.next) return iter1.next;
      iter1 = iter1.next;
      iter2 = iter2.next.next;
    }
    return null;
  }

  static class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left, right;
  }

  private static void treeTraversal(BinaryTreeNode<Integer> root) {
    // pre-order
    if (root != null) {
      System.out.println(root.data);
      treeTraversal(root.left);
      treeTraversal(root.right);
    }
  }

  private static List<String> topK(int k, Iterator<String> iter) {
    PriorityQueue<String> minHeap = new PriorityQueue<>(Comparator.comparingInt(String::length));
    int minLength = 0;
    while (iter.hasNext()) {
      String input = iter.next();
      if (input.length() > minLength) {
        if (k == 0) {
          String head = minHeap.remove();
          minHeap.removeIf(s -> s.length() == head.length());
        }
        minLength = input.length();
        minHeap.add(input);
        if (k > 0) k--;
      } else if (input.length() == minLength) {
        minHeap.add(input);
      }
    }

    return new ArrayList<>(minHeap);
  }

  private static int searchFirstOfK(List<Integer> list, int k) {
    int lo = 0, up = list.size() - 1;
    while (lo <= up) {
      int mid = lo + (up - lo) / 2;
      if (k <= list.get(mid)) {
        if (lo == up && k == list.get(mid)) return lo;
        if (lo == up) return -1;
        up = mid;
      } else {
        lo = mid + 1;
      }
    }

    return -1;
  }

  private static List<Integer> findMaximumSubarray(List<Integer> data) {
    List<Integer> result = Arrays.asList(0, 0, Integer.MIN_VALUE);
    int n1 = 0, acc = 0, max = Integer.MIN_VALUE;
    for (int i = 0; i < data.size(); i++) {
      acc += data.get(i);

      if (acc - data.get(n1) > acc) {
        acc -= data.get(n1);
        max = acc;
        n1++;
        result = Arrays.asList(n1, i, max);
      } else if (acc > max) {
        max = acc;
        result = Arrays.asList(n1, i, max);
      }
    }

    return result;
  }

  // private static void generatePowerSetNth(List<Integer> input, int startIndex,
  // List<List<Integer>> powerSet) {
  // powerSet.add(new ArrayList<>());
  // for (int i = startIndex; i < input.size(); i++) {
  // generatePowerSetNth(input, ++startIndex, powerSet);
  // for (int j = 0; j < startIndex; j++) {
  // powerSet.get(i).add(input.get(j));
  // }
  // }
  // }

  private static int fib(int n) {
    int[] memo = new int[n + 1];
    return fib(n, memo);
  }

  private static int fib(int n, int[] memo) {
    if (memo[n] > 0) return memo[n];
    if (n == 0) return 0;
    if (n == 1) return 1;
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    return memo[n];
  }

  private static int countPaths(boolean[][] grid) {
    int[][] cache = new int[grid.length][grid[0].length];
    return countPaths(grid, cache, 0, 0);
  }

  private static int countPaths(boolean[][] grid, int[][] cache, int row, int col) {
    int countDown, countRight;
    int rows = grid.length;
    int cols = grid[0].length;

    if (row > rows - 1 || col > cols - 1 || !grid[row][col]) return 0;

    if (row == rows - 1 && col == cols - 1 && grid[row][col]) return 1;

    if (row < rows - 1 && cache[row + 1][col] > 0) {
      countDown = cache[row + 1][col];
    } else {
      countDown = countPaths(grid, cache, row + 1, col);
      if (countDown > 0) cache[row + 1][col] = countDown;
    }

    if (col < cols - 1 && cache[row][col + 1] > 0) {
      countRight = cache[row][col + 1];
    } else {
      countRight = countPaths(grid, cache, row, col + 1);
      if (countRight > 0) cache[row][col + 1] = countRight;
    }

    return countDown + countRight;
  }

  private static int factorial(int n) {
    if (n == 0) return 1;
    if (n == 1) return 1;
    return n * factorial(n - 1);
  }

  private static void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  // private static void allPermutations(int[] input, int idx) {
  // for (int i = idx; i < input.length - 1; i++) {
  // int[] swapped = Arrays.copyOf(input, input.length);
  // swap(swapped, idx, i + 1);
  // System.out.println(Arrays.toString(swapped));
  // allPermutations(swapped, idx + 1);
  // }
  // }

  private static void allPermutations(int[] input, int idx) {
    for (int i = idx; i < input.length - 1; i++) {
      for (int j = i + 1; j < input.length - 1; j++) {
        int[] swapped = Arrays.copyOf(input, input.length);
        swap(swapped, i, j);
        System.out.println(Arrays.toString(swapped));
        allPermutations(swapped, j + 1);
      }
      // int[] swapped = Arrays.copyOf(input, input.length);
      // swap(swapped, idx, i + 1);
      // System.out.println(Arrays.toString(swapped));
      // allPermutations(swapped, idx + 1);
    }
  }

  private static int searchAnagramPattern(String pat, String txt) {
    int index = 0;
    int count = 0;
    Set<Character> ref = new HashSet<>(pat.length());
    Set<Character> exist = new HashSet<>(pat.length());
    for (int i = 0; i < pat.length(); i++) ref.add(pat.charAt(i));

    for (int i = 0; i < txt.length(); i++) {
      if (ref.contains(txt.charAt(i))) {
        if (!exist.contains(txt.charAt(i))) exist.add(txt.charAt(i));
        else {
          index = i;
          exist.clear();
          exist.add(txt.charAt(i));
          System.out.println("repeat: " + index);
        }
      }
      if (ref.equals(exist)) {
        System.out.println("found: " + index);
        count++;
        exist.remove(txt.charAt(index));
        index += 1;
      }
      if (i - index > pat.length() - 1) {
        index = i + 1;
        exist.clear();
      }
    }

    return count;
  }

  public static void main(String[] args) throws Exception {
    // System.out.println(searchAnagramPattern("abcd", "abcdabkkkkaaaabcd"));
    // System.out.println(searchAnagramPattern("ab", "babba"));

    allPermutations(new int[] {1, 2, 3, 4}, 0);

    // // boolean[][] grid =
    // // new boolean[][] {{true, false, false}, {true, true, true}, {true, true, true}};
    // final int DIM = 30;
    // boolean[][] grid = new boolean[DIM][DIM];
    // Random rnd = new Random();
    // for (int i = 0; i < DIM; i++) {
    // for (int j = 0; j < DIM; j++) {
    // if (i % 6 == 0 && j % 9 == 0) grid[i][j] = rnd.nextBoolean();
    // else grid[i][j] = true;
    //// System.out.print(grid[i][j] + "\t");
    // }
    //// System.out.println();
    // }
    // System.out.println(countPaths(grid));

    // System.out.println(fib(6));
    // List<Integer> list = Arrays.asList(-5, -10, 900, 50, 500, 100, -335, -385, -124, 481,
    // -31);
    // System.out.println(findMaximumSubarray(list));
    // List<List<Integer>> ps = generatePowerSet(Arrays.asList(0, 1, 2));
    // for (List<Integer> list : ps) {
    // for (Integer integer : list) {
    // System.out.print(integer + ", ");
    // }
    // System.out.println();
    // }

    // List<Integer> list = Arrays.asList(-14, -10, 2, 108, 108, 243, 285, 285, 401);
    // System.out.println(searchFirstOfK(list, 401));

    // String[] strs =
    // new String[] {
    // "1", "11", "111", "1111", "11111", "111111", "111111", "1111111", "111111111111"
    // };
    // List<String> top3 = topK(3, Arrays.asList(strs).iterator());
    // for (String str : top3) {
    // System.out.println(str);
    // }

    // System.out.println(convertBase("615", 7, 13));

    // System.out.println(stringtoInt("123"));

    // System.out.println(intToString(-1237819));

    // System.out.println(isPalindromic("aabbaa"));
    // System.out.println(isPalindromic("aabcbaa"));
    // System.out.println(isPalindromic("aabcqaa"));

    // List<Color> colors =
    // Arrays.asList(
    // Color.BLUE, Color.WHITE, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE,
    // Color.RED);
    // dutchFlagPartition(-1, colors);
    // dutchFlagPartition(4, colors);
    // for (Color color : colors) {
    // System.out.print("-" + color);
    // }

    // int[] ints = new int[] {4, 1, 2, 3, 8, 5, 6};
    // evenOdd(null);
    // evenOdd(ints);
    // for (int i : ints) System.out.print("-" + i);

    // var s = "abc123";
    // var t = "abcdef";
    // var c = lcp(s, t);
    // System.out.println(c);
    //
    // System.out.println(Character.MIN_VALUE + 0);
    //
    // System.out.println(Character.MAX_VALUE + 0);

    // long m1 = Runtime.getRuntime().freeMemory();
    // ArrayList<Integer> data = new ArrayList<>(1000000);
    // for (int i = 0; i < 1000000; i++) {
    // data.add(i, i);
    // }
    // long m2 = Runtime.getRuntime().freeMemory();
    // System.out.println("=======>" + (m1 - m2) / 1024);

    // long start = System.nanoTime();

    // // int x = 1;
    // // int num = 0;
    // // while (x < 1000000000) {
    // // if (x % 10 == 0) {
    // // num += 1;
    // // }
    // // x += 1;
    // // }

    // long num = IntStream.range(1, 1000000000).filter(x -> x % 10 == 0).count();

    // long end = System.nanoTime();
    // System.out.println((end - start) / 1e6);
    // System.out.println(num);

    // setLookAndFeel();
    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    // final int N = 50;
    // double[] a = new double[N];
    // for (int i = 0; i < N; i++) a[i] = StdRandom.uniform();
    // Arrays.sort(a);
    // for (int i = 0; i < N; i++) {
    // double x = 1.0 * i / N;
    // double y = 0;
    // double rw = 0.5 / N;
    // double rh = a[i];
    // StdDraw.filledRectangle(x, y, rw, rh);
    // }
  }

  // private static void setLookAndFeel() {
  // try {
  // for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
  // System.out.println(info.getName());
  // if ("Nimbus".equals(info.getName())) {
  // UIManager.setLookAndFeel(info.getClassName());
  // break;
  // }
  // }
  // } catch (Exception e) {
  // System.out.println("----- Nimbus -----");
  // // If Nimbus is not available, you can set the GUI to another look and feel.
  // }
  // }
}
