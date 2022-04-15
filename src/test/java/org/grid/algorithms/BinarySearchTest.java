package org.grid.algorithms;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BinarySearchTest {

    @MethodSource("binarySearchVariants")
    @ParameterizedTest
    void testBinarySearch(int[] arr, int key, int expectedResult) {
        assertThat(binarySearch(arr, key)).isEqualTo(expectedResult);
    }

    private int binarySearch(int[] arr, int key) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + rightIndex >>> 1;
            int middleValue = arr[middleIndex];
            if (middleValue < key) {
                leftIndex = middleIndex + 1;
            } else if (middleValue > key) {
                rightIndex = rightIndex - 1;
            } else {
                return middleIndex;
            }
        }
        return -1;
    }

    private static Stream<Arguments> binarySearchVariants() {
        return Stream.of(
                arguments(new int[]{1}, 1, 0),
                arguments(new int[]{1}, 0, -1),
                arguments(new int[]{1, 2}, 1, 0),
                arguments(new int[]{MIN_VALUE, 1, 2}, MIN_VALUE, 0),
                arguments(new int[]{MIN_VALUE, 0, 1, 2, 3, 4, MAX_VALUE}, 4, 5),
                arguments(new int[]{MIN_VALUE, 0, 1, 2, 3, 4, MAX_VALUE}, MAX_VALUE, 6)
        );
    }

    @MethodSource("findFirstLargerThanKeyVariants")
    @ParameterizedTest
    void testFindFirstLargerThanKey(int[] arr, int key, int expectedResult) {
        assertThat(findFirstLargerThanKey(arr, key)).isEqualTo(expectedResult);
    }

    private int findFirstLargerThanKey(int[] arr, int key) {
        int length = arr.length;
        if (length == 0 || key == MAX_VALUE) {
            return -1;
        } else if (length == 1) {
            return arr[0] > key ? 0 : -1;
        }
        int leftIndex = 0;
        int rightIndex = length - 1;
        while (leftIndex <= rightIndex) {
            // the same as {int middleIndex = leftIndex + rightIndex >>> 1;}
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            int middleValue = arr[middleIndex];
            int nextIndex = middleIndex + 1;
            if (nextIndex < length && middleValue <= key && arr[nextIndex] > key) {
                return nextIndex;
            } else if (middleIndex == 0 && middleValue > key) {
                return middleIndex;
            } else if (middleValue >= key) {
                rightIndex = middleIndex - 1;
            } else {
                leftIndex = middleIndex + 1;
            }
        }
        return -1;
    }

    private static Stream<Arguments> findFirstLargerThanKeyVariants() {
        return Stream.of(
                arguments(new int[]{}, 0, -1),
                arguments(new int[]{}, MIN_VALUE, -1),
                arguments(new int[]{}, MAX_VALUE, -1),
                arguments(new int[]{1}, 0, 0),
                arguments(new int[]{1}, 1, -1),
                arguments(new int[]{1, 2}, 0, 0),
                arguments(new int[]{1, 2}, 1, 1),
                arguments(new int[]{-1, 1}, 0, 1),
                arguments(new int[]{-1, 1}, -1, 1),
                arguments(new int[]{-1, 1}, 1, -1),
                arguments(new int[]{-1, 1}, 2, -1),
                arguments(new int[]{MIN_VALUE}, MIN_VALUE, -1),
                arguments(new int[]{MIN_VALUE, MAX_VALUE}, MIN_VALUE, 1),
                arguments(new int[]{MIN_VALUE, 1, 2}, MIN_VALUE, 1),
                arguments(new int[]{MIN_VALUE, 0, 1, 2, 3, 4, MAX_VALUE}, 4, 6),
                arguments(new int[]{MIN_VALUE, 0, 1, 2, 3, 4, MAX_VALUE}, MAX_VALUE, -1)
        );
    }

    @MethodSource("findSkippedNumberVariants")
    @ParameterizedTest
    void testFindSkippedNumber(int[] arr, int expectedResult) {
        assertThat(findSkippedNumber(arr)).isEqualTo(expectedResult);
    }

    private static int findSkippedNumber(int[] arr) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            int middleValue = arr[middleIndex];
            int previousIndex = middleIndex - 1;
            if (middleIndex < middleValue && (middleIndex == 0 || arr[previousIndex] == previousIndex)) {
               return middleIndex;
            } else if (middleIndex == middleValue) {
                leftIndex = leftIndex + 1;
            } else {
                rightIndex = rightIndex - 1;
            }
        }
        return -1;
    }

    private static Stream<Arguments> findSkippedNumberVariants() {
        return Stream.of(
                arguments(new int[]{}, -1),
                arguments(new int[]{0}, -1),
                arguments(new int[]{1}, 0),
                arguments(new int[]{0, 1}, -1),
                arguments(new int[]{0, 2}, 1),
                arguments(new int[]{1, 2}, 0),
                arguments(new int[]{0, 1, 2, 3, 4, 5, 7}, 6)
        );
    }
}
