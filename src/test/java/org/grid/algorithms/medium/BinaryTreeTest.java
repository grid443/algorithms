package org.grid.algorithms.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTreeTest {

    @Test
    void isBst_should_return_true_for_strict_difference() {
        var tree = new Tree(3);
        var tree1 = new Tree(1);
        var tree5 = new Tree(5);
        tree.left = tree1;
        tree.right = tree5;
        tree5.left = new Tree(4);
        tree5.right = new Tree(10);
        tree1.right = new Tree(2);
        assertThat(tree.isBst()).isTrue();
    }

    @Test
    void isBst_should_return_false_for_strict_difference() {
        var tree = new Tree(3);
        var tree1 = new Tree(1);
        var tree5 = new Tree(5);
        tree.left = tree1;
        tree.right = tree5;
        tree5.left = new Tree(4);
        tree5.right = new Tree(10);
        tree1.right = new Tree(0);
        assertThat(tree.isBst()).isFalse();
    }

    @Test
    void isBst_should_return_true_for_duplicates_on_the_right_subtree() {
        var tree = new Tree(3);
        var tree1 = new Tree(1);
        var tree5 = new Tree(5);
        tree.left = tree1;
        tree.right = tree5;
        tree5.left = new Tree(4);
        tree5.right = new Tree(5);
        tree1.right = new Tree(1);
        assertThat(tree.isBst()).isTrue();
    }

    @Test
    void isBst_should_return_false_for_duplicates_on_the_left_subtree() {
        var tree = new Tree(3);
        var tree1 = new Tree(1);
        var tree5 = new Tree(5);
        tree.left = tree1;
        tree.right = tree5;
        tree5.left = new Tree(4);
        tree5.right = new Tree(10);
        tree1.right = new Tree(1);
        tree1.left = new Tree(1);
        assertThat(tree.isBst()).isFalse();
    }

    @Test
    void invertRecursiveTest() {
        var tree = new Tree(3);
        var tree1 = new Tree(1);
        var tree5 = new Tree(5);
        tree.left = tree1;
        tree.right = tree5;
        tree5.left = new Tree(4);
        tree5.right = new Tree(10);
        tree1.right = new Tree(1);
        tree1.left = new Tree(1);
        assertThat(tree.traverse()).isEqualTo(List.of(1, 1, 1, 3, 4, 5, 10));
        tree.invert();
        assertThat(tree.traverse()).isEqualTo(List.of(10, 5, 4, 3, 1, 1, 1));
    }

    @Test
    void invertIterativeTest() {
        var tree = new Tree(3);
        var tree1 = new Tree(1);
        var tree5 = new Tree(5);
        tree.left = tree1;
        tree.right = tree5;
        tree5.left = new Tree(4);
        tree5.right = new Tree(10);
        tree1.right = new Tree(1);
        tree1.left = new Tree(1);
        assertThat(tree.traverse()).isEqualTo(List.of(1, 1, 1, 3, 4, 5, 10));
        tree.invertIterative();
        assertThat(tree.traverse()).isEqualTo(List.of(10, 5, 4, 3, 1, 1, 1));
    }


    private static class Tree {
        private final int value;
        private Tree left;
        private Tree right;

        private Tree(int value) {
            this.value = value;
        }

        private boolean isBst() {
            return isBstInternal(this, MIN_VALUE, MAX_VALUE);
        }

        private boolean isBstInternal(Tree tree, int min, int max) {
            if (tree == null) {
                return true;
            }
            int value = tree.value;
            return value >= min
                    && value < max
                    && isBstInternal(tree.left, min, value)
                    && isBstInternal(tree.right, value, max);
        }

        private List<Integer> traverse() {
            final var result = new ArrayList<Integer>();
            traverseInternal(this, result);
            return result;
        }

        private void traverseInternal(Tree tree, List<Integer> result) {
            if (tree == null) {
                return;
            }
            traverseInternal(tree.left, result);
            result.add(tree.value);
            traverseInternal(tree.right, result);
        }

        private void invert() {
            invertRecursive(this);
        }

        private void invertIterative() {
            invertIterative(this);
        }

        /**
         * time  = O(N)
         * space = O(depth)
         * depth = logN for the balanced tree
         * Depth can be equal to N in the worst case
         */
        private void invertRecursive(Tree tree) {
            if (tree == null) {
                return;
            }
            swap(tree);
            invertRecursive(tree.left);
            invertRecursive(tree.right);
        }

        /**
         * Breadth First Inversion
         * time  = O(N)
         * space = O(N)
         */
        private void invertIterative(Tree tree) {
            if (tree == null) {
                return;
            }
            Queue<Tree> queue = new ArrayDeque<>();
            queue.add(tree);
            while (!queue.isEmpty()) {
                var current = queue.poll();
                swap(current);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        private void swap(Tree tree) {
            var tmp = tree.left;
            tree.left = tree.right;
            tree.right = tmp;
        }
    }
}
