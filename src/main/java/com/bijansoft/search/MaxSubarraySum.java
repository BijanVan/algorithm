package com.bijansoft.search;

public class MaxSubarraySum {
    static long maximumSum(long[] a, long m) {
        long[] am = moduloize(a, m);
        int[] indices = findIndices(am, 0, am.length - 1, m);
        return moduloSum(am, indices[0], indices[1], m);
    }

    private static int[] findIndices(long[] a, int start, int end, long m) {
        int[] indices = new int[]{start, end};
        if (start == end) return indices;

        int mid = start + (end - start) / 2;
        int[] leftIndices = findIndices(a, start, mid, m);
        int[] rightIndices = findIndices(a, mid + 1, end, m);
        indices = mergeIndices(a, start, end, leftIndices[0], leftIndices[1], mid,
                rightIndices[0], rightIndices[1], m);

        return indices;
    }

    private static int[] mergeIndices(long[] a, int start, int end, int start1, int end1, int mid,
                                      int start2, int end2, long m) {
        int[] indices = new int[]{start1, end1};
        long sum1 = moduloSum(a, start1, end1, m);
        long sum2 = moduloSum(a, start2, end2, m);
        if (sum2 > sum1) {
            indices[0] = start2;
            indices[1] = end2;
        }

        int left = findLeftIndex(a, start, mid, m);
        int right = findRightIndex(a, mid + 1, end, m);
        long sum3 = moduloSum(a, left, right, m);
        if (sum3 > sum1 && sum3 > sum2) {
            indices[0] = left;
            indices[1] = right;
        }

        return indices;
    }

    private static int findRightIndex(long[] a, int start, int end, long m) {
        int idx = start;
        int maxIdx = start;
        long max = 0;
        long sum = 0;
        while (idx <= end && sum != m - 1) {
            sum = (sum + a[idx]) % m;
            if (sum > max) {
                maxIdx = idx;
                max = sum;
            }
            idx++;
        }

        return maxIdx;
    }

    private static int findLeftIndex(long[] a, int start, int end, long m) {
        int idx = end;
        int maxIdx = end;
        long max = 0;
        long sum = 0;
        while (idx >= start && sum != m - 1) {
            sum = (sum + a[idx]) % m;
            if (sum > max) {
                maxIdx = idx;
                max = sum;
            }
            idx--;
        }

        return maxIdx;
    }

    private static long moduloSum(long[] a, int start, int end, long m) {
        long sum = 0;
        for (int i = start; i <= end; i++)
            sum += a[i];
        return sum % m;
    }

    private static long[] moduloize(long[] a, long m) {
        long[] a1 = new long[a.length];
        for (int i = 0; i < a.length; i++)
            a1[i] = a[i] % m;
        return a1;
    }
}
