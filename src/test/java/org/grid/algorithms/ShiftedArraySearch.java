package org.grid.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A sorted array of distinct integers shiftArr is shifted to the left by an unknown offset
 * You don’t have a pre-shifted copy of it.
 * For instance, the sequence 1, 2, 3, 4, 5 becomes 3, 4, 5, 1, 2,
 * after shifting it twice to the left.
 * <p>
 * Given shiftArr and an integer num, implement a function shiftedArrSearch
 * that finds and returns the index of num in shiftArr.
 * If num isn’t in shiftArr, return -1.
 * Assume that the offset can be any value between 0 and arr.length - 1.
 */
public class ShiftedArraySearch {

    @Test
    void shifterArraySearchTest() {
        assertThat(shiftedArrSearch(new int[]{3, 1, 2}, 1)).isEqualTo(1);
        assertThat(shiftedArrSearch(new int[]{3, 1, 2}, 2)).isEqualTo(2);
        assertThat(shiftedArrSearch(new int[]{3, 1, 2}, 3)).isEqualTo(0);
        assertThat(shiftedArrSearch(new int[]{2, 3, 1}, 1)).isEqualTo(2);
        assertThat(shiftedArrSearch(new int[]{2, 3, 1}, 2)).isEqualTo(0);
        assertThat(shiftedArrSearch(new int[]{2, 3, 1}, 3)).isEqualTo(1);
        assertThat(shiftedArrSearch(new int[]{1, 2, 3}, 1)).isEqualTo(0);
        assertThat(shiftedArrSearch(new int[]{1, 2, 3}, 2)).isEqualTo(1);
        assertThat(shiftedArrSearch(new int[]{1, 2, 3}, 3)).isEqualTo(2);
        assertThat(shiftedArrSearch(new int[]{7, 3}, 7)).isEqualTo(0);
        assertThat(shiftedArrSearch(new int[]{7, 3}, 3)).isEqualTo(1);
        assertThat(shiftedArrSearch(new int[]{7, 3}, -1)).isEqualTo(-1);
        assertThat(shiftedArrSearch(new int[]{7, 3}, 5)).isEqualTo(-1);
        assertThat(shiftedArrSearch(new int[]{7, 3}, 8)).isEqualTo(-1);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 1, 2, 3}, 7)).isEqualTo(0);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 1, 2, 3}, 3)).isEqualTo(5);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 1, 2, 3}, 9)).isEqualTo(2);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 1, 2, 3}, 1)).isEqualTo(3);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 1, 2, 3}, 8)).isEqualTo(1);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 1, 2, 3}, 2)).isEqualTo(4);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 10, 2, 3}, 10)).isEqualTo(3);
        assertThat(shiftedArrSearch(new int[]{7, 8, 9, 10, 2, 3}, 2)).isEqualTo(4);
        assertThat(shiftedArrSearch(new int[]{7, 8, 0, 1, 2, 3}, 0)).isEqualTo(2);
        assertThat(shiftedArrSearch(new int[]{7, 8, 0, 1, 2, 3}, 1)).isEqualTo(3);
        assertThat(shiftedArrSearch(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, -34)).isEqualTo(4);
        assertThat(shiftedArrSearch(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, 192)).isEqualTo(3);
        assertThat(shiftedArrSearch(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, -2)).isEqualTo(-1);
    }

    @Test
    void shiftPointTest() {
        assertThat(shiftPoint(new int[]{3, 1, 2})).isEqualTo(1);
        assertThat(shiftPoint(new int[]{2, 3, 1})).isEqualTo(2);
        assertThat(shiftPoint(new int[]{1, 2, 3})).isEqualTo(0);
        assertThat(shiftPoint(new int[]{7, 3})).isEqualTo(1);
        assertThat(shiftPoint(new int[]{7, 8, 9, 1, 2, 3})).isEqualTo(3);
        assertThat(shiftPoint(new int[]{7, 8, 9, 10, 2, 3})).isEqualTo(4);
        assertThat(shiftPoint(new int[]{7, 8, 0, 1, 2, 3})).isEqualTo(2);
        assertThat(shiftPoint(new int[]{5, 7, 78, 192, -34, 0, 1, 2})).isEqualTo(4);
    }

    private static int shiftedArrSearch(int[] shiftArr, int key) {
        int shiftPoint = shiftPoint(shiftArr);
        int arrLength = shiftArr.length;
        if (shiftPoint == 0 || key < shiftArr[0]) {
            return binarySearch(shiftArr, shiftPoint, arrLength, key);
        } else {
            return binarySearch(shiftArr, 0, shiftPoint, key);
        }
    }

    private static int binarySearch(int[] arr, int fromIndex, int toIndex, int key) {
        int leftIndex = fromIndex;
        int rightIndex = toIndex - 1;
        while (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) >>> 1;
            int middleValue = arr[middleIndex];
            if (middleValue > key) {
                rightIndex = middleIndex - 1;
            } else if (middleValue < key) {
                leftIndex = middleIndex + 1;
            } else {
                return middleIndex;
            }
        }
        return -1;
    }

    private static int shiftPoint(int[] shiftArr) {
        int leftIndex = 0;
        int arrLength = shiftArr.length;
        int rightIndex = arrLength - 1;
        while (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) >>> 1;
            if (middleIndex > 0 && shiftArr[middleIndex] < shiftArr[middleIndex - 1]) {
                return middleIndex;
            } else if (middleIndex < arrLength - 1 && shiftArr[middleIndex] > shiftArr[middleIndex + 1]) {
                return middleIndex + 1;
            } else if (shiftArr[middleIndex] < shiftArr[leftIndex]) {
                rightIndex = middleIndex - 1;
            } else if (shiftArr[middleIndex] > shiftArr[rightIndex]) {
                leftIndex = middleIndex + 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
