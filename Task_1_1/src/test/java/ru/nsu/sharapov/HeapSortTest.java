package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class HeapSortTest {

    @Test
    void checkSort1() {
        int[] test = {10, 3, 15, 4, 2};
        int[] actual = HeapSort.sort(test);
        int[] expected = {2, 3, 4, 10, 15};
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSort2() {
        int[] test = {9, 1, 20, 6, 9, 13};
        int[] actual = HeapSort.sort(test);
        int[] expected = {1, 6, 9, 9, 13, 20};
        assertArrayEquals(actual, expected);
    }

    @Test
    void reverseSorted() {
        int[] test = {5, 4, 3, 2, 1, 0};
        int[] actual = HeapSort.sort(test);
        int[] expected = {0, 1, 2, 3, 4, 5};
        assertArrayEquals(actual, expected);
    }

    @Test
    void voidArray() {
        int[] input = {};
        int[] expected = {};
        assertArrayEquals(HeapSort.sort(input), expected);
    }

    @Test
    void sameValues() {
        int[] input = {5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5};
        assertArrayEquals(HeapSort.sort(input), expected);
    }

    @Test
    void negativeNumbers() {
        int[] input = {-42, -142, 0, 42, -100};
        int[] expected = {-142, -100, -42, 0, 42};
        assertArrayEquals(HeapSort.sort(input), expected);
    }


}