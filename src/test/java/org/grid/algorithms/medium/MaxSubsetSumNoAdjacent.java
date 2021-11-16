package org.grid.algorithms.medium;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MaxSubsetSumNoAdjacent {

    @Test
    void maxSumTest() {
        assertThat(maxSum(new int[]{7, 10, 12, 7, 9, 14})).isEqualTo(33);
        assertThat(maxSum(new int[]{0, 40, 1, 5, 7, 20, 3})).isEqualTo(65);
        assertThat(maxSum(new int[]{MAX_VALUE, 0, MAX_VALUE})).isEqualTo((long) MAX_VALUE * 2);
        assertThat(maxSum(new int[]{1})).isEqualTo(1);
        assertThat(maxSum(new int[]{1, 2})).isEqualTo(2);
        assertThat(maxSum(new int[]{1, 7, 3})).isEqualTo(7);
        assertThat(maxSum(new int[]{100, 7, 3, 0, 4, 12})).isEqualTo(115);
        assertThrows(IllegalArgumentException.class, () -> maxSum(new int[]{}), "Array is empty");
    }

    private long maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int length = arr.length;
        if (length == 1) {
            return arr[0];
        } else if (length == 2) {
            int first = arr[0];
            int second = arr[1];
            return max(first, second);
        }
        long first = arr[0];
        long second = max(first, arr[1]);
        for (int i = 2; i < length; i++) {
            long maxSum = max(second, (first + arr[i]));
            first = second;
            second = maxSum;
        }
        return second;
    }
}
