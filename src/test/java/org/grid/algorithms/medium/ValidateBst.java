package org.grid.algorithms.medium;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidateBst {

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
    }
}
