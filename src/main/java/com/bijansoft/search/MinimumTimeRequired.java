package com.bijansoft.search;

import java.util.Arrays;

public class MinimumTimeRequired {
    static long minTime(long[] machines, long goal) {
        long start = shortestMinTime(machines, goal);
        long end = longestMinTime(machines, goal);

        Arrays.sort(machines);

        while (end - start > 1) {
            long middle = start + (end - start) / 2;
            long productCount = calculateProduction(machines, middle);
            if (productCount >= goal) {
                end = middle;
            } else {
                start = middle;
            }
        }

        if (calculateProduction(machines, start) == calculateProduction(machines, end))
            return start;
        else
            return end;
    }

    private static long longestMinTime(long[] machines, long goal) {
        return machines[0] * goal;
    }

    private static long shortestMinTime(long[] machines, long goal) {
        double total = 0;
        for (long machine : machines)
            total += 1.0 / machine;
        return (long) (goal / total);
    }

    private static long calculateProduction(long[] machines, long days) {
        long productCount = 0;
        for (long machine : machines)
            productCount += days / machine;
        return productCount;
    }
}
