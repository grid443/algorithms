package org.grid.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class SingleLinkedListTest {

	@Test
	public void singleLinkedListInvertTest() {
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		Assert.assertEquals(Integer.valueOf(1), list.get(0));
		Assert.assertEquals(Integer.valueOf(2), list.get(1));
		Assert.assertEquals(Integer.valueOf(3), list.get(2));
		list.invert();
		Assert.assertEquals(Integer.valueOf(3), list.get(0));
		Assert.assertEquals(Integer.valueOf(2), list.get(1));
		Assert.assertEquals(Integer.valueOf(1), list.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void singleLinkedListIndexOutOfBoundsTest() {
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.get(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void singleLinkedListIllegalArgumentExceptionTest() {
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.get(-3);
	}
}
