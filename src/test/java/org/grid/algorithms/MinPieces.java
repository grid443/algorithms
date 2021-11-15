package org.grid.algorithms;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are give two paper strips. On each strip numbers (1,2,...,N) are written in random order.
 * Cut the original paper strip into several pieces and rearrange those pieces to form the desired sequence.
 * <p>
 * Write the function, that efficiently with respect to time used, returns the minimum number
 * of cut pieces needed to perform the desired operation.
 * <p>
 * For example, the following code should return 3 because the pieces used should be (1), (4,3) and (2)
 * int[] original = new int[]{1, 4, 3, 2};
 * int[] desired = new int[]{1, 2, 4, 3};
 * stripsCount(original, desired);
 */
public class MinPieces {

    @Test
    void test() {
        int[] original = new int[]{1, 4, 3, 2, 5, 6, 7, 8, 9};
        int[] desired = new int[]{9, 1, 2, 4, 3, 5, 6, 7, 8};
        assertThat(stripsCount(original, desired)).isEqualTo(5);
    }

    private int stripsCount(int[] original, int[] desired) {
        //offset = 1 because the values in the arrays are 1-based
        int[] originalSubstituted = new int[original.length + 1];

        //substitute values with indexes to be able to quickly search indexes of values
        for (int i = 0; i < original.length; i++) {
            originalSubstituted[original[i]] = i;
        }

        int stripsCount = 1;
        int previousPosition = -1;
        for (int i = 0; i < desired.length; i++) {
            int originalPosition = originalSubstituted[desired[i]];
            if (i != 0 && previousPosition + 1 != originalPosition) {
                stripsCount++;
            }
            previousPosition = originalPosition;
        }

        return stripsCount;
    }
}
