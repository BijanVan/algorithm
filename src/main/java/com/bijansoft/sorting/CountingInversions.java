package com.bijansoft.sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class CountingInversions {

  private static long countInversions(int[] arr) {
    int[] temp = new int[arr.length];
    return mergeSort(arr, temp, 0, arr.length);
  }

  private static long mergeSort(int[] arr, int[] temp, int start, int end) {
    long swaps = 0;
    int mid = start + (end - start) / 2;
    if (end - start >= 2) {
      swaps = mergeSort(arr, temp, start, mid) + mergeSort(arr, temp, mid, end);
      swaps += merge(arr, temp, start, mid, end);
    }

    return swaps;
  }

  private static long merge(int[] arr, int[] temp, int start, int mid, int end) {
    long swaps = 0;
    int idx1 = start, idx2 = mid;
    for (int i = start; i < end; i++) {
      if (idx1 < mid && idx2 < end) {
        if (arr[idx1] <= arr[idx2]) {
          temp[i] = arr[idx1++];
        } else {
          swaps += idx2 - i;
          temp[i] = arr[idx2++];
        }
      } else if (idx1 == mid) {
        swaps += idx2 - i;
        temp[i] = arr[idx2++];
      } else {
        temp[i] = arr[idx1++];
      }
    }
    System.arraycopy(temp, start, arr, start, end - start);

    return swaps;
  }

  public static void main(String[] args) {
    URL url = CountingInversions.class.getClassLoader().getResource("sorting/data2.txt");
    assert url != null;
    try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
      String[] header = reader.readLine().split(" ");
      int length = Integer.valueOf(header[0]);
      String[] body = reader.readLine().split(" ");
      int[] values = new int[length];
      for (int i = 0; i < length; i++) {
        values[i] = Integer.valueOf(body[i]);
      }

      long result = countInversions(values);
      System.out.println(Arrays.toString(values));
      System.out.println(result);

    } catch (IOException ex) {
      System.err.println("File not fount:" + ex.getMessage());
    }
  }
}
