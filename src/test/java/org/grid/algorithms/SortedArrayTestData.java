package org.grid.algorithms;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.junit.jupiter.params.provider.Arguments.arguments;

interface SortedArrayTestData {

    static Stream<Arguments> sortedArrays() {
        return Stream.of(
                arguments(
                        new int[]{},
                        new int[]{}
                ),
                arguments(
                        new int[]{1},
                        new int[]{1}
                ),
                arguments(
                        new int[]{0, -1},
                        new int[]{-1, 0}
                ),
                arguments(
                        new int[]{-1, -1},
                        new int[]{-1, -1}
                ),
                arguments(
                        new int[]{-1, 0},
                        new int[]{-1, 0}
                ),
                arguments(
                        new int[]{-1, 0, 1},
                        new int[]{-1, 0, 1}
                ),
                arguments(
                        new int[]{12, 45, -100, MAX_VALUE, 2, 0, MIN_VALUE, 1, 2},
                        new int[]{MIN_VALUE, -100, 0, 1, 2, 2, 12, 45, MAX_VALUE}
                ),
                arguments(
                        new int[]{12, 45, MAX_VALUE, -100, MAX_VALUE, 2, 0, MIN_VALUE, 1, 2, MIN_VALUE},
                        new int[]{MIN_VALUE, MIN_VALUE, -100, 0, 1, 2, 2, 12, 45, MAX_VALUE, MAX_VALUE}
                )
        );
    }

    static Stream<Arguments> reverseSortedArrays() {
        return Stream.of(
                arguments(
                        new int[]{},
                        new int[]{}
                ),
                arguments(
                        new int[]{1},
                        new int[]{1}
                ),
                arguments(
                        new int[]{0, -1},
                        new int[]{0, -1}
                ),
                arguments(
                        new int[]{-1, -1},
                        new int[]{-1, -1}
                ),
                arguments(
                        new int[]{-1, 0},
                        new int[]{0, -1}
                ),
                arguments(
                        new int[]{-1, 0, 1},
                        new int[]{1, 0, -1}
                ),
                arguments(
                        new int[]{12, 45, -100, MAX_VALUE, 2, 0, MIN_VALUE, 1, 2},
                        new int[]{MAX_VALUE, 45, 12, 2, 2, 1, 0, -100, MIN_VALUE}
                ),
                arguments(
                        new int[]{MIN_VALUE, 12, 45, -100, MAX_VALUE, MAX_VALUE, 2, 0, MIN_VALUE, 1, 2},
                        new int[]{MAX_VALUE, MAX_VALUE, 45, 12, 2, 2, 1, 0, -100, MIN_VALUE, MIN_VALUE}
                )
        );
    }
}
