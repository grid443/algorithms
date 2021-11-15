package org.grid.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortedArrayTest {

    @Test
    void binarySearchTest() {
        var sortedArray = new SortedArray(new int[]{1, 2, 4, 6, 7, 20, 1990, 2000});
        assertThat(sortedArray.binarySearch(7)).isEqualTo(4);
        assertThat(sortedArray.binarySearch(1)).isEqualTo(0);
        assertThat(sortedArray.binarySearch(2000)).isEqualTo(7);
        assertThat(sortedArray.binarySearch(0)).isEqualTo(-1);
        assertThat(sortedArray.binarySearch(2001)).isEqualTo(-1);
        assertThat(sortedArray.binarySearch(8)).isEqualTo(-1);
    }

    @Test
    void sortedArrayUnsortedTest() {
        assertThrows(IllegalArgumentException.class, () -> new SortedArray(new int[]{1, 3, 2}));
    }
}
