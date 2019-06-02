package com.bijansoft.strings;

import com.bijansoft.sorting.CountingInversions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class CommonChild {

  private static int commonChild(String s1, String s2) {
    final int M = s1.length(), N = s2.length();
    int[][] cs = new int[M][N];

    cs[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
    for (int i = 1; i < M; i++) {
      if (s1.charAt(i) == s2.charAt(0)) {
        cs[i][0] = cs[i - 1][0] + 1;
      } else {
        cs[i][0] = cs[i - 1][0];
      }
    }
    for (int j = 1; j < N; j++) {
      if (s1.charAt(0) == s2.charAt(j)) {
        cs[0][j] = cs[0][j - 1] + 1;
      } else {
        cs[0][j] = cs[0][j - 1];
      }
    }
    for (int i = 1; i < M; i++) {
      for (int j = 1; j < N; j++) {
        if (s1.charAt(i) == s2.charAt(j)) {
          cs[i][j] = cs[i - 1][j - 1] + 1;
        } else {
          cs[i][j] = Math.max(cs[i][j - 1], cs[i - 1][j]);
        }
      }
    }

    return cs[M - 1][N - 1];
  }

  public static void main(String[] args) {
    // data0: 27 data1: 155 data2: 321 data3: 1417
    URL url = CommonChild.class.getClassLoader().getResource("strings/data1.txt");
    assert url != null;
    try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
      String s1 = reader.readLine();
      String s2 = reader.readLine();

      System.out.println(commonChild(s1, s2));

    } catch (IOException ex) {
      System.err.println("File not fount:" + ex.getMessage());
    }
  }
}
