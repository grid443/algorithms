package org.grid.algorithms;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BinarySearchTreeTest {

    @Test
    void insertTest() {
        var bst = new BinarySearchTree<>(List.of(4, 2, 5, 1, 8, 3));
        checkBstContainsAll(bst, List.of(4, 2, 5, 1, 8, 3));
        assertThat(bst.contains(10)).isFalse();
        bst.add(10);
        assertThat(bst.contains(10)).isTrue();
    }

    @Test
    void removeTest() {
        var bst = new BinarySearchTree<>(List.of(4, 2, 3, 3, 5, 1, 8, 3));
        checkBstContainsAll(bst, List.of(4, 2, 3, 3, 5, 1, 8, 3));
        bst.remove(2);
        checkBstContainsAll(bst, List.of(4, 3, 5, 1, 8));
        assertThat(bst.contains(2)).isFalse();
        bst.remove(4);
        checkBstContainsAll(bst, List.of(3, 5, 1, 8));
        assertThat(bst.contains(4)).isFalse();
        bst.remove(3);
        checkBstContainsAll(bst, List.of(3, 5, 1, 8));
        bst.remove(3);
        checkBstContainsAll(bst, List.of(3, 5, 1, 8));
        bst.remove(3);
        checkBstContainsAll(bst, List.of(5, 1, 8));
        assertThat(bst.contains(3)).isFalse();
        bst.remove(8);
        checkBstContainsAll(bst, List.of(5, 1));
        assertThat(bst.contains(8)).isFalse();
        assertThat(bst.contains(1)).isTrue();
        bst.remove(5);
        assertThat(bst.contains(1)).isTrue();
        assertThat(bst.contains(5)).isFalse();
        bst.remove(1);
        assertThat(bst.contains(1)).isFalse();
    }

    private <T extends Comparable<T>> void checkBstContainsAll(BinarySearchTree<T> bst, List<T> expected) {
        expected.forEach((value) -> assertThat(bst.contains(value)).isTrue());
    }
}
