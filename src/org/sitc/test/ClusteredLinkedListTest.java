package org.sitc.test;

import static org.junit.Assert.*;
import static org.sitc.util.ClusteredLinkedList.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.sitc.util.ClusteredLinkedList;

/**
Tests the clustered linked list.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ClusteredLinkedListTest {
	private static final int CAPACITY = DEFAULT_CLUSTER_CAPACITY * 5 / 2;

	/**
	Creates two test lists.

	@return The lists.
	**/
	private static Case<List<Object>, List<Object>> create() {
		final List<Object> expected = new LinkedList<>(),
				actual = new ClusteredLinkedList<>();
		return new Case<>(expected, actual);
	}

	/**
	Adds elements to two test lists.

	@param lists The lists.
	@param count The amount of elements.
	**/
	private static void add(final Case<List<Object>, List<Object>> lists, final int count) {
		final List<Object> expected = lists.getExpected(),
				actual = lists.getActual();
		for (int index = 0; index < count; index++) {
			final Object object = new Object();
			expected.add(object);
			actual.add(object);
		}
	}

	/**
	Removes every other element from two test lists.

	@param lists The lists.
	**/
	private static void remove(final Case<List<Object>, List<Object>> lists) {
		final List<Object> expected = lists.getExpected(),
				actual = lists.getActual();
		final int count = Math.min(expected.size(), actual.size()) / 2;
		for (int index = 0; index < count; index++) {
			expected.remove(index);
			actual.remove(index);
		}
	}

	/**
	Clears two test lists.

	@param lists The lists.
	**/
	private static void clear(final Case<List<Object>, List<Object>> lists) {
		lists.getExpected().clear();
		lists.getActual().clear();
	}

	@Test
	public void testSize() {
		final Case<List<Object>, List<Object>> both = create();
		final List<Object> expected = both.getExpected(),
				actual = both.getActual();

		assertEquals(expected.size(), actual.size());

		add(both, CAPACITY);
		assertEquals(expected.size(), actual.size());

		remove(both);
		assertEquals(expected.size(), actual.size());

		clear(both);
		assertEquals(expected.size(), actual.size());
	}

	@Test
	public void testAddGet() {
		final Case<List<Object>, List<Object>> both = create();
		final List<Object> expected = both.getExpected(),
				actual = both.getActual();

		for (int index = 0; index < expected.size(); index++) {
			assertEquals(expected.get(index), actual.get(index));
		}
	}

	@Test
	public void testToString() {
		final Case<List<Object>, List<Object>> both = create();
		final List<Object> expected = both.getExpected(),
				actual = both.getActual();

		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testContains() {
		final Case<List<Object>, List<Object>> both = create();
		final List<Object> expected = both.getExpected(),
				actual = both.getActual();

		add(both, CAPACITY);
		for (int index = 0; index < expected.size(); index++) {
			assertTrue(actual.contains(expected.get(index)));
			assertFalse(actual.contains(new Object()));
		}

		remove(both);
		for (int index = 0; index < expected.size(); index++) {
			assertTrue(actual.contains(expected.get(index)));
			assertFalse(actual.contains(new Object()));
		}
	}
}
