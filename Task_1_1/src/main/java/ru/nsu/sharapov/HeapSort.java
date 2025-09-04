package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;

class Heap {
    ArrayList<Integer> heap = new ArrayList<>();

    public void insert(int value) {
        heap.add(value);
        _siftup(heap.size() - 1);
    }

    public int extract_max() {
        int max = heap.getFirst();
        Collections.swap(heap, 0, heap.size() - 1);
        heap.removeLast();
        _siftdown(0);
        return max;

    }

    public void _siftup(int pos) {
        while (pos > 0) {
            int parent = (pos - 1) / 2;
            if (heap.get(parent) > heap.get(pos)) {
                Collections.swap(heap, parent, pos);
                pos = parent;
            } else break;
        }
    }


    public void _siftdown(int pos) {
        while (true) {
            int curr = pos;
            int left = 2 * pos + 1;
            int right = 2 * pos + 2;
            if (left < heap.size() && heap.get(left) < heap.get(curr))
                curr = left;
            if (right < heap.size() && heap.get(right) < heap.get(curr))
                curr = left;
            if (curr != pos) {
                Collections.swap(heap, pos, curr);
                pos = curr;
            } else break;
        }
    }
}


public class HeapSort {
    public static Integer[] sort(Integer[] args) {
        Heap myheap = new Heap();
        for (int i : args) {
            myheap.insert(i);
        }
        ArrayList<Integer> actual = new ArrayList<>();
        while (!myheap.heap.isEmpty()) {
            actual.add(myheap.extract_max());
        }

        return actual.toArray(new Integer[0]);
    }
}