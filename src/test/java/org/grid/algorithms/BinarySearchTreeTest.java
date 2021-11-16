package org.grid.algorithms;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.grid.algorithms.BinarySearchTree.TraverseType.IN_ORDER;
import static org.grid.algorithms.BinarySearchTree.TraverseType.POST_ORDER;
import static org.grid.algorithms.BinarySearchTree.TraverseType.PRE_ORDER;

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

    @Test
    void traverseInOrderTest() {
        var bst = new BinarySearchTree<>(List.of(4, 2, 3, 3, 5, 1, 8, 3));
        var inOrder = bst.traverse(IN_ORDER);
        assertThat(inOrder).isEqualTo(List.of(1, 2, 3, 3, 3, 4, 5 ,8));
    }

    @Test
    void traversePreOrderTest() {
        var bst = new BinarySearchTree<>(List.of(4, 2, 3, 3, 5, 1, 8, 3));
        var inOrder = bst.traverse(PRE_ORDER);
        assertThat(inOrder).isEqualTo(List.of(4, 2, 1, 3, 3, 3, 5, 8));
    }

    @Test
    void traversePostOrderTest() {
        var bst = new BinarySearchTree<>(List.of(4, 2, 3, 3, 5, 1, 8, 3));
        var inOrder = bst.traverse(POST_ORDER);
        assertThat(inOrder).isEqualTo(List.of(1, 3, 3, 3, 2, 8, 5, 4));
    }

    private <T extends Comparable<T>> void checkBstContainsAll(BinarySearchTree<T> bst, List<T> expected) {
        expected.forEach((value) -> assertThat(bst.contains(value)).isTrue());
    }
}
