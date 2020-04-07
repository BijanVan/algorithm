package com.bijansoft.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.bijansoft.dp.Subarray.maxSubarray;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SubarrayTest {
    private static final String resourcePath = "search/maxsubarray";

    @ParameterizedTest
    @MethodSource("dataProvider")
    void maxSubarrayTest(int[] arr, int[] expected) {
        int[] actual = maxSubarray(arr);
        assertArrayEquals(expected, actual);
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
        String[] s = reader.readLine().split(" ");
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++)
            a[i] = Integer.parseInt(s[i]);

        s = reader.readLine().split(" ");
        int[] out = new int[s.length];
        for (int i = 0; i < s.length; i++)
            out[i] = Integer.parseInt(s[i]);
        return new Object[]{a, out};
    }
}