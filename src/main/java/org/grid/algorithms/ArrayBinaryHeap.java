package org.grid.algorithms;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class ArrayBinaryHeap {
    private static final int DEFAULT_INITIAL_CAPACITY = 15;
    private final int[] values;
    private int length = 0;

    public ArrayBinaryHeap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    private ArrayBinaryHeap(int initialCapacity) {
        this.values = new int[initialCapacity];
    }

    public static void heapSort(int[] arr) {
        final var heap = new ArrayBinaryHeap();
        heap.addAll(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.removeMin();
        }
    }

    public static void inPlaceReverseHeapSort(int[] arr) {
        for (int newElementIndex = 0; newElementIndex < arr.length; newElementIndex++) {
            siftUp(arr, newElementIndex);
        }
        int length = arr.length;
        while (length > 0) {
            int lastElementIndex = length - 1;
            swapElements(arr, 0, lastElementIndex);
            length--;
            siftDown(arr, length, 0);
        }
    }

    public int removeMin() {
        if (length < 1) {
            throw new IllegalStateException("empty heap");
        }
        int minElementIndex = 0;
        int lastElementIndex = length - 1;
        int minElement = values[minElementIndex];
        swapElements(values, minElementIndex, lastElementIndex);
        length--;
        siftDown(values, length, minElementIndex);
        return minElement;
    }

    public void addAll(int[] elements) {
        for (int element : elements) {
            add(element);
        }
    }

    public void add(int element) {
        // TODO check capacity
        values[length] = element;
        length++;
        int newElementIndex = length - 1;
        siftUp(values, newElementIndex);
    }

    @Override
    public String toString() {
        return stream(values).limit(length).mapToObj(String::valueOf).collect(joining(",", "[", "]"));
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    private static void siftUp(int[] heap, int siftedIndex) {
        int siftedElement = heap[siftedIndex];
        int parentIndex = parentIndex(siftedIndex);
        while (siftedIndex > 0 && siftedElement < heap[parentIndex]) {
            swapElements(heap, siftedIndex, parentIndex);
            siftedIndex = parentIndex;
            parentIndex = parentIndex(siftedIndex);
        }
    }

    private static void siftDown(int[] heap, int length, int siftedIndex) {
        int minElementIndex = minChildElementIndex(heap, length, siftedIndex);
        while (minElementIndex < length && heap[minElementIndex] < heap[siftedIndex]) {
            swapElements(heap, minElementIndex, siftedIndex);
            siftedIndex = minElementIndex;
            minElementIndex = minChildElementIndex(heap, length, siftedIndex);
        }
    }

    private static int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private static int minChildElementIndex(int[] heap, int length, int index) {
        int leftChildIndex = (index * 2) + 1;
        int rightChildIndex = (index * 2) + 2;
        return (rightChildIndex >= length) || (heap[leftChildIndex] < heap[rightChildIndex])
                ? leftChildIndex
                : rightChildIndex;
    }

    private static void swapElements(int[] arr, int firstIndex, int secondIndex) {
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }
}
