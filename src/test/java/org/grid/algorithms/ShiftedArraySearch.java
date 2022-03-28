package org.grid.algorithms;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    @MethodSource("arraySearchSource")
    @ParameterizedTest(name = "Search for the key {1} in the array {0} should return {2}")
    void shifterArraySearchTest(int[] shiftedArr, int key, int expectedResult) {
        assertThat(shiftedArrSearch(shiftedArr, key)).isEqualTo(expectedResult);
    }

    @MethodSource("shirtPointSource")
    @ParameterizedTest(name = "Search for the shift point in the array {0} should return {1}")
    void shiftPointTest(int[] shiftedArr, int expectedShiftPoint) {
        assertThat(shiftPoint(shiftedArr)).isEqualTo(expectedShiftPoint);
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
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
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
            double halfDifference = ((double) rightIndex - leftIndex) / 2;
            int middleIndex = leftIndex + ceil(halfDifference);
            int middleValue = shiftArr[middleIndex];
            if (middleIndex == 0 || middleValue < shiftArr[middleIndex - 1]) {
                return middleIndex;
            } else if (middleValue > shiftArr[0]) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex - 1;
            }
        }
        return 0;
    }

    private static int ceil(double value) {
        int intValue = (int) value;
        if (value == (double) intValue) {
            return intValue;
        } else {
            return intValue + 1;
        }
    }

    private static Stream<Arguments> arraySearchSource() {
        return Stream.of(
                arguments(new int[]{3, 1, 2}, 1, 1),
                arguments(new int[]{3, 1, 2}, 2, 2),
                arguments(new int[]{3, 1, 2}, 3, 0),
                arguments(new int[]{2, 3, 1}, 1, 2),
                arguments(new int[]{2, 3, 1}, 2, 0),
                arguments(new int[]{2, 3, 1}, 3, 1),
                arguments(new int[]{1, 2, 3}, 1, 0),
                arguments(new int[]{1, 2, 3}, 2, 1),
                arguments(new int[]{1, 2, 3}, 3, 2),
                arguments(new int[]{7, 3}, 7, 0),
                arguments(new int[]{7, 3}, 3, 1),
                arguments(new int[]{7, 3}, -1, -1),
                arguments(new int[]{7, 3}, 5, -1),
                arguments(new int[]{7, 3}, 8, -1),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 7, 0),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 3, 5),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 9, 2),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 1, 3),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 8, 1),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 2, 4),
                arguments(new int[]{7, 8, 9, 10, 2, 3}, 10, 3),
                arguments(new int[]{7, 8, 9, 10, 2, 3}, 2, 4),
                arguments(new int[]{7, 8, 0, 1, 2, 3}, 0, 2),
                arguments(new int[]{7, 8, 0, 1, 2, 3}, 1, 3),
                arguments(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, -34, 4),
                arguments(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, 192, 3),
                arguments(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, -2, -1)
        );
    }

    private static Stream<Arguments> shirtPointSource() {
        return Stream.of(
                arguments(new int[]{3, 1, 2}, 1, 1),
                arguments(new int[]{3, 1, 2}, 1),
                arguments(new int[]{2, 3, 1}, 2),
                arguments(new int[]{1, 2, 3}, 0),
                arguments(new int[]{7, 3}, 1),
                arguments(new int[]{7, 8, 9, 1, 2, 3}, 3),
                arguments(new int[]{7, 8, 9, 10, 2, 3}, 4),
                arguments(new int[]{7, 8, 0, 1, 2, 3}, 2),
                arguments(new int[]{5, 7, 78, 192, -34, 0, 1, 2}, 4)
        );
    }
}
