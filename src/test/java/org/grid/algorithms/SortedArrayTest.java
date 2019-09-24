package org.grid.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class SortedArrayTest {

    @Test
    public void binarySearchTest() {
        SortedArray sortedArray = new SortedArray(new int[]{1, 2, 4, 6, 7, 20, 1990, 2000});
        Assert.assertEquals(4, sortedArray.binarySearch(7));
        Assert.assertEquals(0, sortedArray.binarySearch(1));
        Assert.assertEquals(7, sortedArray.binarySearch(2000));
        Assert.assertEquals(-1, sortedArray.binarySearch(0));
        Assert.assertEquals(-1, sortedArray.binarySearch(2001));
        Assert.assertEquals(-1, sortedArray.binarySearch(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortedArrayUnsortedTest() {
        new SortedArray(new int[]{1, 3, 2});
    }
}
