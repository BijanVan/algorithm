package com.bijansoft.search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.bijansoft.search.TripleSum.triplets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TripleSumTest {

    private static final String resourcePath = "search/triplesum";

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testTriplets(int[] a, int[] b, int[] c, long expected) {
        long actual = triplets(a, b, c);
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
        int[][] arrs = new int[3][];
        for (int i = 0; i < 3; i++) {
            String[] cells = reader.readLine().split(" ");
            arrs[i] = new int[cells.length];
            for (int j = 0; j < cells.length; j++)
                arrs[i][j] = Integer.parseInt(cells[j]);
        }

        String output = reader.readLine();
        return new Object[]{arrs[0], arrs[1], arrs[2], output};
    }
}
