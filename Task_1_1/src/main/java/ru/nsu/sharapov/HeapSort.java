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
    public static int[] sort(int[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : args) {
            list.add(i);
        }
        Heap myheap = new Heap(list);
        int len = myheap.heap.size();
        int[] actual = new int[len];
        for (int i = 0; i < len; i++) {
            actual[i] = myheap.extract_min();
        }
        return actual;
    }

    /**
     * Сортирует массив и выводит результат.
     *
     * @param args Строковый массив чисел
     */
    public static void main(String[] args) {
        int[] integers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            integers[i] = Integer.parseInt(args[i]);
        }
        int[] res = sort(integers);
        for (int i : res) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}

