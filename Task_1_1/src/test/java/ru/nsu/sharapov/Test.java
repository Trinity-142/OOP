package ru.nsu.sharapov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

    @Test
    void checkSort() {
        Integer[] test = {5, 4, 3, 2, 1};
        Integer[] actual = HeapSort.sort(test);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(actual, expected);
    }
}