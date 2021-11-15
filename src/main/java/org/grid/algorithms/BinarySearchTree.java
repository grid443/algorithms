package org.grid.algorithms;

import java.util.List;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree(List<T> source) {
        if (source == null || source.isEmpty()) {
            return;
        }
        source.forEach(this::add);
    }

    public void add(T value) {
        final var newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            return;
        }
        var currentNode = root;
        while (true) {
            T currentValue = currentNode.value;
            if (value.compareTo(currentValue) < 0) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    return;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    return;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
    }

    public boolean contains(T value) {
        var currentNode = root;
        while (currentNode != null) {
            if (value.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else if (value.compareTo(currentNode.value) > 0) {
                currentNode = currentNode.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public void remove(T value) {
        removeInternal(root, value, null);
    }

    private void removeInternal(Node<T> currentNode, T value, Node<T> parentNode) {
        while (currentNode != null) {
            if (value.compareTo(currentNode.value) < 0) {
                parentNode = currentNode;
                currentNode = currentNode.left;
            } else if (value.compareTo(currentNode.value) > 0) {
                parentNode = currentNode;
                currentNode = currentNode.right;
            } else {
                if (currentNode.left != null && currentNode.right != null) {
                    currentNode.value = minValue(currentNode.right);
                    removeInternal(currentNode.right, currentNode.value, currentNode);
                } else if (parentNode == null) {
                    if (currentNode.left != null) {
                        currentNode.value = currentNode.left.value;
                        currentNode.right = currentNode.left.right;
                        currentNode.left = currentNode.left.left;
                    } else if (currentNode.right != null) {
                        currentNode.value = currentNode.right.value;
                        currentNode.left = currentNode.right.left;
                        currentNode.right = currentNode.right.right;
                    } else {
                        root = null;
                    }
                } else if (parentNode.left.equals(currentNode)) {
                    parentNode.left = (currentNode.left != null) ? currentNode.left : currentNode.right;
                } else if (parentNode.right.equals(currentNode)) {
                    parentNode.right = (currentNode.right != null) ? currentNode.right : currentNode.left;
                }
                break;
            }
        }

    }

    private T minValue(Node<T> node) {
        if (node == null) {
            throw new NullPointerException("Can't get min value for null");
        }
        var currentNode = node;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            if (value == null) {
                throw new NullPointerException("Value is null");
            }
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final var node = (Node<?>) o;
            return value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
