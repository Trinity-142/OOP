package ru.nsu.sharapov;

import java.util.ArrayList;

/**
 * Класс сортировки.
 */
public class HeapSort {
    /**
     * Сортирует массив целых чисел по возрастанию с использованием пирамидальной сортировки.
     *
     * @param args Элементы неостортированного массива
     * @return Возвращает отсортированный массив
     */
    public static Integer[] sort(ArrayList<Integer> args) {
        Heap myheap = new Heap(args);
        ArrayList<Integer> actual = new ArrayList<>();
        while (!myheap.heap.isEmpty()) {
            actual.add(myheap.extract_min());
        }

        return actual.toArray(new Integer[0]);
    }

    /**
     * Сортирует массив и выводит результат.
     *
     * @param args Строковый массив чисел
     */
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (String i : args) {
            integers.add(Integer.parseInt(i));
        }
        Integer[] res = sort(integers);
        for (int i : res) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}

