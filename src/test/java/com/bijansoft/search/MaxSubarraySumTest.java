package com.bijansoft.search;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.bijansoft.search.MaxSubarraySum.maximumSum;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MaxSubarraySumTest {
    private static final String resourcePath = "search/maxsubarraysum";

    @ParameterizedTest
    @MethodSource("dataProvider")
    void maximumSumTest(long[] a, long m, long expected) {
        long actual = maximumSum(a, m);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> dataProvider() throws FileNotFoundException {
        URL url = MethodHandles.lookup().lookupClass().getClassLoader().getResource(resourcePath);
        if (url == null) throw new FileNotFoundException(resourcePath + " not found");
        String path = url.getPath();
        File[] files = new File(path).listFiles();
        if (files == null) throw new InvalidPathException(path, path + " does not exist");
        return Arrays.stream(files).map(file -> {
            try {
                return readFromFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                return arguments();
            }
        });
    }

    private static Arguments readFromFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return arguments(interpret(reader));
    }

    private static Object[] interpret(BufferedReader reader) throws IOException {
        reader.readLine();
        String[] s = reader.readLine().split(" ");
        int length = Integer.parseInt(s[0]);
        long m = Long.parseLong(s[1]);
        long[] a = new long[length];
        s = reader.readLine().split(" ");
        for (int i = 0; i < length; i++) {
            a[i] = Long.parseLong(s[i]);
        }

        String output = reader.readLine();
        return new Object[]{a, m, output};
    }
}