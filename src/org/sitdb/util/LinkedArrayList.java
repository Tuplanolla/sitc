package org.sitdb.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
Represents a linked list consisting of array ranges.

The following example has
 the capacity <code>c</code> of 8,
 the load factor <code>f</code> of 0.625 and
 the stability <code>s</code> of 0.125:

<pre>
 0 1 2 3 4 5 6 7 8
+-+-+-+-+-+-+-+-+
| | | | | | | | |
+-+-+-+-+-+-+-+-+
         ^ ^ ^   ^
         | | |   |
         | | |   `- c
         | `-+- f * c
         |   |
         |   `- (f + s) * c
         `- (f - s) * c
</pre>

@param <Type> The type of the elements.
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class LinkedArrayList<Type> implements List<Type>, Cloneable, Serializable {
	private static final long serialVersionUID = 1l;

	private static final class Node<Type> {
		private final Object[] elements;

		public Node(final int capacity) {
			elements = new Object[capacity];
		}
	}

	private final List<Node<Type>> elements;

	/**
	Constructs an empty list with the specified initial capacity.
	**/
	public LinkedArrayList(final int capacity) {
		elements = new LinkedList<Node<Type>>();
	}

	/**
	Constructs an empty list with an initial capacity of ten.
	**/
	public LinkedArrayList() {
		this(10);
	}

	@Override//TODO implement
	public int size() {
		return 0;
	}

	@Override//TODO implement
	public boolean isEmpty() {
		return false;
	}

	@Override//TODO implement
	public boolean contains(final Object object) {
		return false;
	}

	@Override//TODO implement
	public Iterator<Type> iterator() {
		return null;
	}

	@Override//TODO implement
	public Object[] toArray() {
		return null;
	}

	@Override//TODO implement
	public <T> T[] toArray(final T[] array) {
		return null;
	}

	@Override//TODO implement
	public boolean add(final Type element) {
		return false;
	}

	@Override//TODO implement
	public boolean remove(final Object object) {
		return false;
	}

	@Override//TODO implement
	public boolean containsAll(final Collection<?> collection) {
		return false;
	}

	@Override//TODO implement
	public boolean addAll(final Collection<? extends Type> collection) {
		return false;
	}

	@Override//TODO implement
	public boolean addAll(final int index, final Collection<? extends Type> collection) {
		return false;
	}

	@Override//TODO implement
	public boolean removeAll(final Collection<?> collection) {
		return false;
	}

	@Override//TODO implement
	public boolean retainAll(final Collection<?> collection) {
		return false;
	}

	@Override//TODO implement
	public void clear() {}

	@Override//TODO implement
	public Type get(final int index) {
		return null;
	}

	@Override//TODO implement
	public Type set(final int index, final Type element) {
		return null;
	}

	@Override//TODO implement
	public void add(final int index, final Type element) {}

	@Override//TODO implement
	public Type remove(final int index) {
		return null;
	}

	@Override//TODO implement
	public int indexOf(final Object object) {
		return 0;
	}

	@Override//TODO implement
	public int lastIndexOf(final Object object) {
		return 0;
	}

	@Override//TODO implement
	public ListIterator<Type> listIterator() {
		return null;
	}

	@Override//TODO implement
	public ListIterator<Type> listIterator(final int index) {
		return null;
	}

	@Override//TODO implement
	public List<Type> subList(final int fromIndex, final int toIndex) {
		return null;
	}
}
