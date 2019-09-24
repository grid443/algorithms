package org.grid.algorithms;

import org.junit.Assert;
import org.junit.Test;

/**
 * The tree is represented by an array {@code nodes}
 * where {@code nodes{i}} is the parent of node {@code i}.
 * For the root node {@code r}, {@code nodes[r] = -1}
 */
public class TreeNodesCount {

    @Test
    public void test() {
        Assert.assertEquals(2, internalNodesCount(new int[]{1, 3, 1, -1, 3}));
    }

    private int internalNodesCount(int[] nodes) {
        int counter = 0;

        int[] internalNodesCount = new int[nodes.length];

        for (int node : nodes) {
            if (node != -1 && internalNodesCount[node] < 1) {
                internalNodesCount[node]++;
                counter++;
            }
        }

        return counter;
    }
}
