package ru.nsu.sharapov;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleTest {

    @Test
    void checkSort1() {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(10, 3, 15, 4, 2));
        Integer[] actual = HeapSort.sort(test);
        Integer[] expected = {2, 3, 4, 10, 15};
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSort2() {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(9, 1, 20, 6, 9, 13));
        Integer[] actual = HeapSort.sort(test);
        Integer[] expected = {1, 6, 9, 9, 13, 20};
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSort3() {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1, 0));
        Integer[] actual = HeapSort.sort(test);
        Integer[] expected = {0, 1, 2, 3, 4, 5};
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSort4() {
        String[] test = {"1", "2", "5", "3"};
        HeapSort.main(test);
        assertTrue(true);
    }
}