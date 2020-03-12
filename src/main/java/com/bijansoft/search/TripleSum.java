package com.bijansoft.search;

import java.util.Arrays;

public class TripleSum {
    static long triplets(int[] a, int[] b, int[] c) {
        long count = 0;

        Arrays.sort(a);
        a = removeDuplicates(a);
        Arrays.sort(b);
        b = removeDuplicates(b);
        Arrays.sort(c);
        c = removeDuplicates(c);

        for (int i = 0; i < b.length; i++) {
            long countA = findSmallerEqual(a, b[i]) + 1;
            long countB = findSmallerEqual(c, b[i]) + 1;
            count += countA * countB;
        }

        return count;
    }

    private static int[] removeDuplicates(int[] a) {
        int[] b = new int[a.length];
        b[0] = a[0];
        int i = 1;
        int j = 1;
        while (i < a.length) {
            if (a[i] != a[i - 1]) b[j++] = a[i];
            i++;
        }
        return Arrays.copyOf(b, j);
    }

    private static int findSmallerEqual(int[] a, int e) {
        int start = 0;
        int end = a.length;
        int middle = start + (end - start) / 2;

        while (end - start > 1) {
            if (a[middle] > e)
                end = middle;
            else
                start = middle;

            middle = start + (end - start) / 2;
        }

        if (a[middle] <= e)
            return middle;

        return -1;
    }
}
