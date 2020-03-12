package com.bijansoft.search;

import com.bijansoft.sorting.CountingInversions;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.bijansoft.search.Pairs.numberOfPairs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PairsTest {

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testNumberOfPairs(int k, int[] input, int expected) {
        int actual = numberOfPairs(k, input);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> dataProvider() {
        URL url = CountingInversions.class.getClassLoader().getResource("search/pairs");
        String path = url.getPath();
        File[] files = new File(path).listFiles();
        return Arrays.stream(files).map(PairsTest::readFromFile);
    }

    @SneakyThrows(IOException.class)
    private static Arguments readFromFile(File file) {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String[] header = reader.readLine().split(" ");
        int length = Integer.parseInt(header[0]);
        int difference = Integer.parseInt(header[1]);
        String[] body = reader.readLine().split(" ");
        String output = reader.readLine();
        int[] values = new int[length];
        for (int i = 0; i < length; i++)
            values[i] = Integer.parseInt(body[i]);

        return arguments(difference, values, output);
    }
}
