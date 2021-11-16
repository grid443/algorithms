package org.grid.algorithms;

import java.util.Iterator;

/**
 * NOT Thread-Safe
 */
public class SingleLinkedList<T> implements Iterable<T> {

    private Node<T> first;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                var next = current;
                current = current.next;
                return next.value;
            }
        };
    }

    /**
     * Add element to the end of the list
     */
    public void add(T value) {
        var newNode = new Node<>(value);
        var lastNode = getLastNode();
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
    }

    /**
     * Get element at the specified position
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public T get(int index) {
        if (first == null || index < 0) {
            throw new IllegalArgumentException("wrong index:" + index);
        }
        var current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new IndexOutOfBoundsException("wrong index:" + index + "; max index:" + i);
            }
        }
        return current.value;
    }

    /**
     * Invert order of the elements
     */
    public void invert() {
        if (first == null || first.next == null) {
            return;
        }
        Node<T> previous = null;
        Node<T> current = first;
        while (current != null) {
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        first = previous;
    }

    private Node<T> getLastNode() {
        if (first == null) {
            return null;
        }
        var lastNode = first;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
