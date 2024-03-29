package org.grid.algorithms;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.grid.algorithms.ArrayBinaryHeap.heapSort;
import static org.grid.algorithms.ArrayBinaryHeap.inPlaceReverseHeapSort;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class ArrayBinaryHeapTest implements SortedArrayTestData {

    @ParameterizedTest
    @MethodSource("heap")
    void should_remove_min_element(int[] values, int[] expectedValues) {
        final var heap = new ArrayBinaryHeap();
        heap.addAll(values);
        int expectedSize = expectedValues.length;
        for (int value : expectedValues) {
            assertThat(heap.size()).isEqualTo(expectedSize);
            assertThat(heap.removeMin()).isEqualTo(value);
            expectedSize--;
        }
        assertThat(heap.isEmpty()).isTrue();
        final var exception = assertThrows(IllegalStateException.class, heap::removeMin);
        assertThat(exception).hasMessage("empty heap");
    }

    @ParameterizedTest
    @MethodSource("sortedArrays")
    void should_sort_array(int[] arr, int[] sortedArr) {
        // when
        heapSort(arr);

        // then
        assertThat(arr).isEqualTo(sortedArr);
    }

    @ParameterizedTest
    @MethodSource("reverseSortedArrays")
    void should_sort_array_in_reverse_order(int[] arr, int[] sortedArr) {
        // when
        inPlaceReverseHeapSort(arr);

        // then
        assertThat(arr).isEqualTo(sortedArr);
    }

    private static Stream<Arguments> heap() {
        return Stream.of(
                arguments(
                        new int[]{1},
                        new int[]{1}
                ),
                arguments(
                        new int[]{3, 1, 2},
                        new int[]{1, 2, 3}
                ),
                arguments(
                        new int[]{3, 1, 2, 2},
                        new int[]{1, 2, 2, 3}
                ),
                arguments(
                        new int[]{3, -1, 2},
                        new int[]{-1, 2, 3}
                ),
                arguments(
                        new int[]{3, 0, 2},
                        new int[]{0, 2, 3}
                ),
                arguments(
                        new int[]{0, 3, -30, 3, 1, 2},
                        new int[]{-30, 0, 1, 2, 3, 3}
                ),
                arguments(
                        new int[]{0, 3, -30, 3, 1, -30, 2},
                        new int[]{-30, -30, 0, 1, 2, 3, 3}
                )
        );
    }
}