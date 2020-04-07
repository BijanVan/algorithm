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

import static com.bijansoft.search.MinimumTimeRequired.minTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MinimumTimeRequiredTest {
    private static final String resourcePath = "search/minimumtimerequired";

    @ParameterizedTest
    @MethodSource("dataProvider")
    void minTimeTest(long[] machines, long goal, long expected) {
        long actual = minTime(machines, goal);
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
        long goal = Integer.parseInt(reader.readLine().split(" ")[1]);
        String[] cells = reader.readLine().split(" ");
        long[] machines = new long[cells.length];
        for (int i = 0; i < cells.length; i++) {
            machines[i] = Long.parseLong(cells[i]);
        }

        String output = reader.readLine();
        return new Object[]{machines, goal, output};
    }
}