package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;

class Heap {
    ArrayList<Integer> heap;

    public Heap(ArrayList<Integer> array) {
        heap = array;
        for (int i = array.size() / 2 - 1; i >= 0; --i) {
            _siftdown(i);
        }
    }

    public int extract_min() {
        int max = heap.getFirst();
        Collections.swap(heap, 0, heap.size() - 1);
        heap.removeLast();
        _siftdown(0);
        return max;

    }

    public void _siftdown(int pos) {
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


public class HeapSort {
    public static Integer[] sort(ArrayList<Integer> args) {
        Heap myheap = new Heap(args);
        ArrayList<Integer> actual = new ArrayList<>();
        while (!myheap.heap.isEmpty()) {
            actual.add(myheap.extract_min());
        }

        return actual.toArray(new Integer[0]);
    }

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