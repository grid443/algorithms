package org.grid.algorithms;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickSortTest implements SortedArrayTestData {

    @ParameterizedTest
    @MethodSource("sortedArrays")
    void testSort(int[] arr, int[] expectedArr) {
        // when
        quickSort(arr);

        // then
        assertThat(arr).isEqualTo(expectedArr);
    }

    private static void quickSort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private static void sort(int[] arr, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 2) {
            return;
        }
        int randomValue = arr[ThreadLocalRandom.current().nextInt(leftIndex, rightIndex)];
        int splitRightIndex = splitArrayBy(arr, randomValue, leftIndex, rightIndex);
        int splitLeftIndex = handleDuplicates(arr, splitRightIndex, rightIndex);
        sort(arr, leftIndex, splitRightIndex);
        sort(arr, splitLeftIndex, rightIndex);
    }

    private static int splitArrayBy(int[] arr, int randomValue, int leftIndex, int rightIndex) {
        int splitIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            if (arr[i] < randomValue) {
                swap(arr, i, splitIndex);
                splitIndex++;
            }
        }
        return splitIndex;
    }

    private static int handleDuplicates(int[] arr, int splitRightIndex, int rightIndex) {
        int splitLeftIndex = splitRightIndex;
        for (int i = splitLeftIndex + 1; i < rightIndex; i++) {
            if (arr[i] == arr[splitLeftIndex]) {
                splitLeftIndex++;
            } else {
                break;
            }
        }
        return splitLeftIndex;
    }

    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        if (firstIndex == secondIndex) {
            return;
        }
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }
}
