package com.bijansoft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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
        if (total > max) {
          max = total;
        }
      }
    }
    return max;
  }

  private static long[] slidingSum(int[] data, int width) {
    long[] sums = new long[data.length - width + 1];

    for (int i = 0; i < sums.length; i++) {
      sums[i] = 0;
      for (int j = i; j < i + width; j++) {
        sums[i] += data[j];
      }
    }

    return sums;
  }

  private static List<Long> modulo(long value) {
    if (value <= 0) {
      throw new IllegalArgumentException("value must be positive");
    }

    long exp = (long) Math.log10(value);
    long rem = (long) (value - Math.pow(10, exp));

    return Arrays.asList(exp, rem);
  }

  private static BiFunction<String, String, Boolean> createAnagramCheck() {
    int[] s1Counts = new int[28];
    int[] s2Counts = new int[28];
    return (s1, s2) -> {
      if (s1.length() != s2.length()) {
        return false;
      }
      Arrays.fill(s1Counts, 0);
      Arrays.fill(s2Counts, 0);

      for (int i = 0; i < s1.length(); i++) {
        s1Counts[s1.charAt(i) - 'a'] += 1;
        s1Counts[s2.charAt(i) - 'a'] += 1;
      }
      for (int i = 0; i < s1.length(); i++) {
        if (s1Counts[i] != s2Counts[i]) {
          return false;
        }
      }

      return true;
    };
  }

  private static int sherlockAndAnagrams(String s) {
    int count = 0;
    int size = s.length();

    Map<String, Integer> map = new HashMap<>(size);
    for (int i = 1; i < size; i++) {
      for (int j = 0; j <= size - i; j++) {
        char[] subc = s.substring(j, j + i).toCharArray();
        Arrays.sort(subc);
        String sub = new String(subc);
        map.computeIfPresent(sub, (k, v) -> v + 1);
        map.putIfAbsent(sub, 1);
      }
    }
    for (Integer v : map.values()) {
      count += v * (v - 1) / 2;
    }

    return count;
  }

  private static long countTriplets(List<Long> arr, long r) {
    int length = arr.size();
    if (length < 3) {
      return 0;
    }

    if (r == 1) {
      Collections.sort(arr);
      long size = 0, distValue = Long.MIN_VALUE, acc = 0;
      for (long value : arr) {
        if (value != distValue) {
          if (size > 2) {
            acc += size * (size - 1) * (size - 2) / 6;
          }
          distValue = value;
          size = 1;
        } else {
          size++;
        }
      }
      acc += size * (size - 1) * (size - 2) / 6;
      return acc;
    }

    long count = 0;
    Map<Long, List<Integer>> mapR0 = new HashMap<>();
    Map<Long, List<Integer>> mapR1 = new HashMap<>();
    Map<Long, List<Integer>> mapR2 = new HashMap<>();

    for (int i = 0; i < arr.size(); i++) {
      long value = arr.get(i);
      boolean remR1 = value % r == 0;
      boolean remR2 = remR1 && value / r % r == 0;

      addToMap(mapR0, value, i);
      if (remR1) {
        addToMap(mapR1, value, i);
      }
      if (remR2) {
        addToMap(mapR2, value, i);
      }
    }

    for (Map.Entry<Long, List<Integer>> en : mapR1.entrySet()) {
      long valueR1 = en.getKey();
      long valueR0 = valueR1 / r;
      long valueR2 = valueR1 * r;
      List<Integer> locsR1 = en.getValue();
      List<Integer> locsR0 = mapR0.get(valueR0);
      List<Integer> locsR2 = mapR2.get(valueR2);
      if (locsR0 == null || locsR2 == null) {
        continue;
      }

      int indexR0 = 0, indexR2 = 0;
      for (int locR1 : locsR1) {
        indexR2 = findSmallest(locsR2, locR1, indexR2);
        if (indexR2 != -1) {
          indexR0 = findBiggest(locsR0, locR1, indexR0);
          if (indexR0 != -1) {
            count += (indexR0 + 1) * (locsR2.size() - indexR2);
          }
        }
      }
    }

    return count;
  }

  private static <T extends Comparable<T>> int findBiggest(List<T> list, T value, int from) {
    int start = from > 0 ? from : 0;
    int end = list.size();

    if (list.get(start).compareTo(value) > 0) {
      return -1;
    }
    if (list.get(end - 1).compareTo(value) < 0) {
      return end - 1;
    }

    while (end - start >= 2) {
      int mid = start + (end - start) / 2;
      T midValue = list.get(mid);
      if (midValue == value) {
        return mid;
      }
      if (midValue.compareTo(value) > 0) {
        end = mid;
      } else {
        start = mid;
      }
    }

    return start;
  }

  private static <T extends Comparable<T>> int findSmallest(List<T> list, T value, int from) {
    int start = from > 0 ? from : 0;
    int end = list.size();

    if (list.get(start).compareTo(value) > 0) {
      return start;
    }
    if (list.get(end - 1).compareTo(value) < 0) {
      return -1;
    }

    while (end - start >= 2) {
      int mid = start + (end - start) / 2;
      T midValue = list.get(mid);
      if (midValue == value) {
        return mid;
      }
      if (midValue.compareTo(value) < 0) {
        start = mid;
      } else {
        end = mid;
      }
    }

    return end;
  }

  private static void addToMap(Map<Long, List<Integer>> map, long value, int loc) {
    map.computeIfPresent(value, (k, v) -> {
      v.add(loc);
      return v;
    });
    map.computeIfAbsent(value, k -> {
      List<Integer> list = new ArrayList<>();
      list.add(loc);
      return list;
    });
  }

  public static void main(String[] args) throws IOException {
    String[] nums = Files.readString(Path.of("./data.txt")).split(" ");
    System.out.println("Size: " + nums.length);
    List<Long> data = new ArrayList<>(nums.length);
    for (String num : nums) {
      data.add(Long.valueOf(num));
    }
    long start = System.nanoTime();
    System.out.println(countTriplets(data, 10)); // ? 1339347780085

//    long start = System.nanoTime();
//    List<Long> data = new ArrayList<>(List.of(1L, 1L, 1L, 1L, 1L, 1L, 10L, 10L, 10L, 10L));
//    System.out.println(countTriplets(data, 1));
//    List<Long> data = new ArrayList<>(
//        List.of(1L, 100L, 10000L, 100000L, 1000000L, 1000000000L, 1000000000L, 10000000L));
//    System.out.println(countTriplets(data, 1));

//    List<Long> data = new ArrayList<>(
//        List.of(1L, 3L, 9L, 9L, 27L, 81L));
//    System.out.println(countTriplets(data, 3));

//    List<Long> data = new ArrayList<>(
//        List.of(1L, 5L, 5L, 8L, 11L, 25L, 40L, 125L, 25L, 200L, 500L, 1L, 5L, 25L, 25L, 5L, 15L));
//    System.out.println(countTriplets(data, 5));

//    List<Long> data = new ArrayList<>(
//        List.of(1L, 1L, 1000000000L, 100000L, 100L, 100L, 10L, 100L, 1000000000L, 100L, 10000000L, 100000000L, 100000000L, 10000000L, 1L, 1000L, 100000000L, 10000000L, 1000000L, 100L, 1L, 100000L, 10000L, 100000L, 10000L, 10000000L, 10L, 100L, 1000000L));
//    System.out.println(countTriplets(data, 10));

    long end = System.nanoTime();
    System.out.println("end - start: " + (end - start) / 1_000_000.0);

//    long start = System.nanoTime();
//    System.out.println(sherlockAndAnagrams(
//        "ahftdfdgsrsiuchmfcuiahftdfdgsrsiuchmfcuiahftdfdgsrsiuchmfcuiahftdfdgsrsiuchmfcuiahftdfdgsrsiuchmfcui"));
//    long end = System.nanoTime();
//    System.out.println("end - start: " + (end - start) / 1_000_000.0);

    //    int[] test1 = new int[]{6, 1, 4, 6, 3, 2, 7, 4};
//    int[] test2 = new int[]{10, 19, 15};
//
//    long max1 = solution(test1, 3, 2);
//    System.out.println("max1 = " + max1);
//    System.out.println("max1 (modulo format) = " + modulo(max1));
//    long max2 = solution(test2, 2, 2);
//    System.out.println("max2 = " + max2);
//
//    int N = 20000;
//    Random rnd = new Random();
//    int[] testN = new int[N];
//    for (int i = 0; i < N; i++) {
//      testN[i] = rnd.nextInt(1000000000);
//    }
//    long start = System.currentTimeMillis();
//    long maxN = solution(testN, 1000, 2000);
//    long end = System.currentTimeMillis();
//    System.out.println("maxN = " + maxN);
//    System.out.println(end - start);
//    System.out.println("maxN (modulo format) = " + modulo(maxN));

  }
}
