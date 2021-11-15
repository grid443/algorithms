package org.grid.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleLinkedListTest {

    @Test
    void singleLinkedListInvertTest() {
        var list = new SingleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        list.invert();
        assertThat(list.get(0)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(1);
    }

    @Test
    public void singleLinkedListIndexOutOfBoundsTest() {
        var list = new SingleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    public void singleLinkedListIllegalArgumentExceptionTest() {
        var list = new SingleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThrows(IllegalArgumentException.class, () -> list.get(-3));
    }
}
