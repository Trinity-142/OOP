package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс пирамиды.
 */
public class Heap {
    ArrayList<Integer> heap;

    /**
     * Конструктор класса, преобразует массив в пирамиду.
     *
     * @param array Любой входной массив целых чисел,
     *              который будет преобразован в неубывающую пирамиду
     */
    public Heap(ArrayList<Integer> array) {
        heap = array;
        for (int i = array.size() / 2 - 1; i >= 0; --i) {
            siftdown(i);
        }
    }

    /**
     * Извлекает минимальный элемент кучи.
     *
     * @return Целое число, минимальный элемент кучи
     */
    public int extract_min() {
        final int min = heap.get(0);
        Collections.swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        siftdown(0);
        return min;
    }

    /**
     * "Просеивание вниз" - просеивает элемент по заданному индексу до своей корректной позиции.
     *
     * @param pos Индекс элемента, который необходимо "просеить" вниз по пирамиде
     */
    private void siftdown(int pos) {
        while (true) {
            int curr = pos;
            int left = 2 * pos + 1;
            int right = 2 * pos + 2;
            if (left < heap.size() && heap.get(left) < heap.get(curr)) curr = left;
            if (right < heap.size() && heap.get(right) < heap.get(curr) && heap.get(right) < heap.get(left))
                curr = right;
            if (curr != pos) {
                Collections.swap(heap, pos, curr);
                pos = curr;
            } else break;
        }
    }
}