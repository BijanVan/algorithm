package com.bijansoft.search;

import java.util.Arrays;

public class Pairs {
    static int numberOfPairs(int target, int[] arr) {
        int length = arr.length;
        int count = 0;
        int idxa = 0;
        int idxd = length - 1;

        int[] arra = Arrays.copyOf(arr, length);
        Arrays.sort(arra);
        int[] arrd = new int[length];
        for (int i = 0; i < length; i++) arrd[i] = arra[length - i - 1];

        while (idxa < length) {
            while (idxd >= 0 && arrd[idxd] - arra[idxa] < target) idxd--;
            if (idxd < 0) break;

            if (arrd[idxd] - arra[idxa] == target) count++;

            while (idxd > 0 && arrd[idxd] == arrd[idxd - 1]) idxd--;
            while (idxa < length - 1 && arra[idxa] == arra[idxa + 1]) idxa++;
            idxa++;
        }

        return count;
    }
}
