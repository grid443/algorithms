package org.grid.algorithms;

/**
 * TODO
 */
public class SortedArray {
    private final int [] arr;

    public SortedArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException();
        }
        checkSorted(arr);
        this.arr = new int[arr.length];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
    }

    /**
     * TODO
     */
    public int binarySearch(int value) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException();
        }

        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) >>> 1;
            int middleValue = arr[middleIndex];
            if (middleValue > value) {
                lastIndex = middleIndex - 1;
            } else if (middleValue < value) {
                firstIndex = middleIndex + 1;
            } else {
                return middleIndex;
            }
        }
        return -1;
    }

    /**
     * TODO
     */
    private void checkSorted(int[] arr) {
        int previousValue = Integer.MIN_VALUE;
        for (int currentValue : arr) {
            if (currentValue < previousValue) {
                throw new IllegalArgumentException("array is unsorted");
            }
            previousValue = currentValue;
        }
    }
}
