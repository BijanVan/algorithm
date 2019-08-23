package com.bijansoft.greedy;

import com.bijansoft.arrays.FrequencyQuery;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseShuffleMerge {

  private static char[] repeat(char ch, int n) {
    char[] chars = new char[n];
    Arrays.fill(chars, ch);
    return chars;
  }

  private static String reverseShuffleMerge(String s) {
    StringBuilder small = new StringBuilder(s.length() / 2);
    int smallKeyCount = 0;
    char smallKey = s.charAt(s.length() - 1);
    int lastPositionSmallKey = s.length() - 1;
    int[][] table = new int[26][3];

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      int idx = ch - 'a';
      table[idx][2]++;
    }
    for (int i = 0; i < table.length; i++) {
      table[i][2] /= 2;
    }

    for (int i = s.length() - 1; i >= 0; i--) {
      char ch = s.charAt(i);
      int idx = ch - 'a';
      int[] rec = table[idx];

      if (ch < smallKey && rec[0] < rec[2]) {
        smallKey = ch;
        lastPositionSmallKey = i;
        smallKeyCount = 1;
      } else if (ch == smallKey  && rec[0] + smallKeyCount < rec[2] && rec[1] + smallKeyCount < rec[2]) {
        lastPositionSmallKey = i;
        smallKeyCount++;
      }

      if (rec[1] == rec[2] && rec[0] != rec[2]) {
        int[] rec1 = table[smallKey - 'a'];
         small.append(repeat(smallKey, smallKeyCount));
        rec1[0] += smallKeyCount;
        if (ch == smallKey && i != lastPositionSmallKey) {
          rec1[1] -= smallKeyCount;
        } else if (i != lastPositionSmallKey) {
          rec1[1] -= smallKeyCount;
        }

        for (int j = lastPositionSmallKey - 1; j > i; j--) {
          table[s.charAt(j) - 'a'][1]--;
        }

        i = lastPositionSmallKey;
        smallKey = (char) ('z' + 1);
        lastPositionSmallKey = -1;
        smallKeyCount = 0;
      } else if (rec[1] < rec[2]) {
        rec[1]++;
      }
    }

    return small.toString();
  }

  public static void main(String[] args) throws IOException {
//    String s = "aahaxxxhxhxxah"; //ahhxxxa
//    String s = "jjcddjggcdjd"; //cgddjj
//    String s = "aeiouuoiea"; //aeiou
//    String s = "bdabaceadaedaaaeaecdeadababdbeaeeacacaba"; // aaaaaabaaceededecbdb
//    String s = "djjcddjggbiigjhfghehhbgdigjicafgjcehhfgifadihiajgciagicdahcbajjbhifjiaajigdgdfhdiijjgaiejgegbbiigida"; // aaaaabccigicgjihidfiejfijgidgbhhehgfhjgiibggjddjjd
//    String s = "qslwkpmmldjqslllwkpmmldjlyslmralfyslmraf"; //aflrlsydlmjlmmpkwlsq


    URL file = FrequencyQuery.class.getClassLoader().getResource("greedy/data4.txt");
    assert file != null;
    String s = Files.readString(Path.of(file.getPath()));

    System.out.println(reverseShuffleMerge(s));
  }
}

