package org.grid.algorithms;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class ArrayBinaryHeap {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private final int[] values;
    private int length = 0;

    public ArrayBinaryHeap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    private ArrayBinaryHeap(int initialCapacity) {
        this.values = new int[initialCapacity];
    }

    public int removeMin() {
        if (length < 1) {
            throw new IllegalStateException("empty heap");
        }
        int minElement = values[0];
        int lastElementIndex = length - 1;
        swapElements(0, lastElementIndex);
        length--;
        int currentIndex = 0;
        int leftChildIndex = 1;
        int rightChildIndex = 2;
        int minElementIndex = (rightChildIndex >= length) || (values[leftChildIndex] < values[rightChildIndex]) ? leftChildIndex : rightChildIndex;
        while (minElementIndex < length && values[minElementIndex] < values[currentIndex]) {
            swapElements(minElementIndex, currentIndex);
            currentIndex = minElementIndex;
            leftChildIndex = (currentIndex * 2) + 1;
            rightChildIndex = (currentIndex * 2) + 2;
            minElementIndex = (rightChildIndex >= length) || (values[leftChildIndex] < values[rightChildIndex]) ? leftChildIndex : rightChildIndex;
        }
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
        int parentIndex = (newElementIndex - 1) / 2;
        while (newElementIndex > 0 && element < values[parentIndex]) {
            swapElements(newElementIndex, parentIndex);
            newElementIndex = parentIndex;
            parentIndex = (newElementIndex - 1) / 2;
        }
    }

    @Override
    public String toString() {
        return stream(values)
                .limit(length)
                .mapToObj(String::valueOf)
                .collect(joining(",", "[", "]"));
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    private void swapElements(int firstIndex, int secondIndex) {
        int tmp = values[firstIndex];
        values[firstIndex] = values[secondIndex];
        values[secondIndex] = tmp;
    }
}
