package com.bijansoft.sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FraudulentActivityNotifications {

  private static int activityNotifications(int[] values, int d) {
    int count = 0;
    int[] counts = new int[201];

    for (int i = 0; i < d; i++) {
      counts[values[i]]++;
    }
    double median = calculateMedian(counts, d);

    for (int i = d; i < values.length; i++) {
      if (values[i] >= 2 * median) {
        count++;
      }
      updateCounts(counts, values[i - d], values[i]);
      median = calculateMedian(counts, d);
    }

    return count;
  }

  private static double calculateMedian(int[] counts, int d) {
    double median;
    boolean odd = d % 2 != 0;
    int i = 0, acc = 0, median1 = 0, d2 = d / 2;
    while (true) {
      if (counts[i] > 0) {
        acc += counts[i];
        median1 = i;
        if (acc >= d2) {
          break;
        }
      }
      i++;
    }
    if (acc > d2) {
      median = median1;
    } else {
      i++;
      while (counts[i] == 0) {
        i++;
      }
      if (odd) {
        median = i;
      } else {
        median = (i + median1) / 2.0;
      }
    }
    System.out.println(median);
    return median;
  }

  private static void updateCounts(int[] counts, int last, int curr) {
    counts[last]--;
    counts[curr]++;
  }

  public static void main(String[] args) {
    URL url = FraudulentActivityNotifications.class.getClassLoader()
        .getResource("sorting/data1.txt");
    assert url != null;
    try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
      String[] header = reader.readLine().split(" ");
      int length = Integer.valueOf(header[0]);
      int d = Integer.valueOf(header[1]);
      String[] body = reader.readLine().split(" ");
      int[] values = new int[length];
      for (int i = 0; i < length; i++) {
        values[i] = Integer.valueOf(body[i]);
      }

      int total = activityNotifications(values, d);
      System.out.println(total);
    } catch (IOException ex) {
      System.err.println("File not fount:" + ex.getMessage());
    }
  }
}
