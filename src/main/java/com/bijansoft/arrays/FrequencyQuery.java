package com.bijansoft.arrays;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FrequencyQuery {

  private Map<Integer, Integer> numbers = new HashMap<>();
  private Map<Integer, Set<Integer>> frequencies = new HashMap<>();

  private void updateFrequencies(int key, int value) {
    frequencies.computeIfPresent(key, (k, v) -> {
      v.add(value);
      return v;
    });
    frequencies.computeIfAbsent(key, k -> {
      Set<Integer> set = new HashSet<>();
      set.add(value);
      return set;
    });
  }

  private void insert(int value) {
    boolean exists = numbers.containsKey(value);
    if (exists) {
      int keyFreq = numbers.get(value);
      Set<Integer> set = frequencies.get(keyFreq);
      set.remove(value);
      if (set.isEmpty()) {
        frequencies.remove(keyFreq);
      }
      numbers.put(value, keyFreq + 1);
      updateFrequencies(keyFreq + 1, value);
    } else {
      numbers.put(value, 1);
      updateFrequencies(1, value);
    }
  }

  private void delete(int value) {
    boolean exists = numbers.containsKey(value);
    if (exists) {
      int keyFreq = numbers.get(value);
      Set<Integer> set = frequencies.get(keyFreq);
      set.remove(value);
      if (set.isEmpty()) {
        frequencies.remove(keyFreq);
      }

      if (keyFreq == 1) {
        numbers.remove(value);
        return;
      }
      numbers.put(value, keyFreq - 1);
      updateFrequencies(keyFreq - 1, value);
    }
  }

  private int frequency(int freq) {
    return frequencies.containsKey(freq) ? 1 : 0;
  }

  private List<Integer> freqQuery(List<List<Integer>> queries) {
    List<Integer> freq = new ArrayList<>();
    for (List<Integer> query : queries) {
      switch (query.get(0)) {
        case 1:
          insert(query.get(1));
          break;
        case 2:
          delete(query.get(1));
          break;
        case 3:
          freq.add(frequency(query.get(1)));
          break;
      }
    }
    return freq;
  }

  public static void main(String[] args) {
    URL file = FrequencyQuery.class.getClassLoader().getResource("arrays/data2.txt");
    assert file != null;
    try (BufferedReader reader = new BufferedReader(new FileReader(file.getFile()))) {
      String queryCount = reader.readLine();
      if (queryCount == null) {
        System.err.println("File is not valid.");
        return;
      }
      List<List<Integer>> queries = new ArrayList<>(Integer.valueOf(queryCount));
      String query;
      while ((query = reader.readLine()) != null) {
        List<Integer> queryInt = Arrays
            .stream(query.replaceAll("\\s+$", "").split(" "))
            .map(Integer::valueOf)
            .collect(Collectors.toUnmodifiableList());
        queries.add(queryInt);
      }

      FrequencyQuery main = new FrequencyQuery();
      List<Integer> result = main.freqQuery(queries);
      System.out.println(result);
    } catch (IOException ex) {
      System.err.println("File not fount:" + ex.getMessage());
    }
  }
}
