package org.sitdb.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
Represents a mutable clustered linked list.

The list is made of linked arrays that are write-optimized by using start and end pointers.

The behavior of the clusters can be modified with three variables:
 the cluster capacity determines
  the maximum amount of elements in each cluster and
  the memory used by each cluster,
 the load factor determines
  how full each cluster should ideally be and
 the stability factor determines
  when to consider clusters too empty or too full and
  when to merge or split consecutive clusters.

A full cluster in a list with
 a cluster capacity <code>c</code> of 8,
 a load factor <code>l</code> of 0.75 and
 a stability factor <code>s</code> of 0.25
  is as follows:

<pre>
 0 1 2 3 4 5 6 7 8
+-+-+-+-+-+-+-+-+
| | | | | | | | |
+-+-+-+-+-+-+-+-+
         ^   ^   ^
         |   |   |
         |   |   `- c
         |   `- l * c
         `- (l - s) * c

@param <Type> The type of the elements.
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ClusteredLinkedList<Type> implements List<Type>, Deque<Type>, Cloneable, Serializable {
	private static final long serialVersionUID = 1l;

	private static final int DEFAULT_CLUSTER_CAPACITY = 16;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private static final double DEFAULT_STABILITY_FACTOR = 0.25;

	private final int clusterCapacity;
	private final double loadFactor;
	private final double stabilityFactor;
	private final double lowClusterCapacity,
			highClusterCapacity;

	private Cluster first,
			last;
	private int count;

	/**
	Lists the states of a cluster.
	**/
	private enum State {
		STABLE,
		EMPTY,
		FULL,
		ALMOST_EMPTY,
		ALMOST_FULL
	}

	/**
	Represents a mutable cluster.

	Arguments are left unchecked.
	**/
	@SuppressWarnings("unchecked")
	private final class Cluster {
		private final Object[] elements;

		private Cluster next,
				previous;
		private int count;
		private State state;
		private boolean valid;

		/**
		Constructs an empty cluster with a specific capacity.

		@param clusterCapacity The capacity.
		**/
		public Cluster(final int clusterCapacity) {
			elements = new Object[clusterCapacity];

			next = null;
			previous = null;
			count = 0;
			state = State.EMPTY;
			valid = true;
		}

		/**
		Validates the state of this cluster.
		**/
		private void validate() {
			if (count <= 0) state = State.EMPTY;
			else if (count >= clusterCapacity) state = State.FULL;
			else if (count < lowClusterCapacity) state = State.ALMOST_EMPTY;
			else if (count > highClusterCapacity) state = State.ALMOST_FULL;
			else state = State.STABLE;
			valid = true;
		}

		/**
		Invalidates the state of this cluster.
		**/
		private void invalidate() {
			valid = false;
		}

		/**
		Returns the next cluster.

		@return The next cluster.
		**/
		public Cluster getNext() {
			return next;
		}

		/**
		Sets the next cluster.

		@param next The next cluster.
		**/
		public void setNext(final Cluster next) {
			this.next = next;
		}

		/**
		Returns the previous cluster.

		@return The previous cluster.
		**/
		public Cluster getPrevious() {
			return previous;
		}

		/**
		Sets the previous cluster.

		@param previous The previous cluster.
		**/
		public void setPrevious(final Cluster previous) {
			this.previous = previous;
		}

		/**
		Returns the amount of elements in this cluster.

		@return The amount of elements.
		**/
		public int size() {
			return count;
		}

		/**
		Returns the state of this cluster.

		@return The state.
		**/
		public State getState() {
			if (!valid) validate();
			return state;
		}

		/**
		Returns whether this cluster is empty.

		@return Whether this cluster is empty.
		**/
		public boolean isEmpty() {
			return count <= 0;
		}

		/**
		Adds an element to the end of this cluster and
		 returns whether the operation was successful.

		@param element The element.
		@return Whether the element was added. 
		**/
		public boolean add(final Type element) {
			if (count >= elements.length) return false;

			elements[count] = element;
			count++;

			invalidate();
			return true;
		}

		/**
		Adds an element to a certain position in this cluster and
		 returns whether the operation was successful.

		@param index The position.
		@param element The element.
		**/
		public void add(final int index, final Type element) {
			final int displaced = count - index;
			if (displaced >= 1) System.arraycopy(elements, index, elements, index + 1, count - index);
			elements[index] = element;
			count++;

			invalidate();
		}

		/**
		Removes an element from a certain position in this cluster and
		 returns it.

		@param index The position.
		@return The element.
		**/
		public Type remove(final int index) {
			final Type element = (Type )elements[index];
			count--;
			final int displaced = count - index;
			if (displaced >= 1) System.arraycopy(elements, index + 1, elements, index, displaced);
			elements[count] = null;

			invalidate();
			return element;
		}

		/**
		Removes a certain element from this cluster and
		 returns whether the operation was successful.

		@param element The element.
		@return Whether the element was removed.
		**/
		public boolean remove(final Type element) {
			for (int index = 0; index < count; index++) {
				if (elements[index] == element) {
					count--;
					final int displaced = count - index;
					if (displaced >= 1) System.arraycopy(elements, index + 1, elements, index, displaced);
					elements[count] = null;

					invalidate();
					return true;
				}
			}
			return false;
		}

		/**
		Checks whether this cluster contains an object.

		@param object The object.
		@return Whether the object is in this cluster.
		**/
		public boolean contains(final Object object) {
			for (int index = 0; index < count; index++) {
				if (elements[index] == object) return true;
			}
			return false;
		}

		@Override
		public String toString() {
			final StringBuilder stringBuilder = new StringBuilder(16 * count);
			stringBuilder.append('(');
			for (int index = 0; index < count; index++) {
				if (index != 0) stringBuilder.append(", ");
				stringBuilder.append(elements[index].toString());
			}
			stringBuilder.append(')');
			return stringBuilder.toString();
		}
	}

	/**
	Constructs an empty list with the specified cluster capacity, load factor and stability factor.

	@param clusterCapacity The capacity of the clusters.
	@param loadFactor The load factor of the clusters.
	@param stabilityFactor The stability factor of the clusters.
	**/
	public ClusteredLinkedList(final int clusterCapacity, final double loadFactor, final double stabilityFactor) {
		if (clusterCapacity < 1
				|| loadFactor < 0 || loadFactor > 1
				|| stabilityFactor < 0 || stabilityFactor > 1) throw new IllegalArgumentException();
		this.clusterCapacity = clusterCapacity;
		this.loadFactor = loadFactor;
		this.stabilityFactor = stabilityFactor;
		lowClusterCapacity = (loadFactor - stabilityFactor) * clusterCapacity;
		highClusterCapacity = (loadFactor + stabilityFactor) * clusterCapacity;

		first = null;
		last = null;
		count = 0;
	}

	/**
	Constructs an empty list with the specified cluster capacity and load factor.

	@param clusterCapacity The capacity of the clusters.
	@param loadFactor The load factor of the clusters.
	**/
	public ClusteredLinkedList(final int clusterCapacity, final double loadFactor) {
		this(clusterCapacity, loadFactor, DEFAULT_STABILITY_FACTOR);
	}

	/**
	Constructs an empty list with the specified cluster capacity.

	@param clusterCapacity The capacity of the clusters.
	**/
	public ClusteredLinkedList(final int clusterCapacity) {
		this(clusterCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	Constructs an empty list.
	**/
	public ClusteredLinkedList() {
		this(DEFAULT_CLUSTER_CAPACITY);
	}

	/**
	Throws an exception if a specific index is out of bounds.

	@param index The index.
	@param size The first invalid index.
	**/
	private static void checkBounds(final int index, final int size) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size + ".");
		}
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
	public void add(final int index, final Type element) {
		if (count <= 0) {//empty
			checkBounds(index, 1);

			final Cluster current = new Cluster(clusterCapacity);
			first = current;
			last = current;
			current.add(element);
		}
		else {//not empty
			checkBounds(index, count);

			Cluster current;
			int superIndex;
			if (index < count / 2) {//probably in the first half
				current = first;
				superIndex = 0;
				while (current != null) {
					final int nextElementIndex = superIndex + current.size();
					if (nextElementIndex > index) break;
					superIndex = nextElementIndex;
					current = current.getNext();
				}
			}
			else {//probably in the last half
				current = last;
				superIndex = count;
				while (current != null) {
					superIndex -= current.size();
					if (superIndex <= index) break;
					current = current.getPrevious();
				}
			}
			final int subIndex = index - superIndex;

			final State state = current.getState();
			switch (state) {}//TODO state management
			current.add(subIndex, element);
		}
	}

	@Override//TODO implement
	public boolean add(final Type element) {
		if (count <= 0) {//empty
			final Cluster current = new Cluster(clusterCapacity);
			first = current;
			last = current;
			return current.add(element);
		}
		else {//not empty
			final State state = last.getState();
			switch (state) {}//TODO state management
			return last.add(element);
		}
	}

	@Override//TODO implement
	public Type remove(final int index) {
		return null;
	}

	@Override//TODO implement
	public boolean remove(final Object object) {
		return false;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count <= 0;
	}

	@Override
	public boolean contains(final Object object) {
		Cluster current = first;
		while (current != null) {
			if (current.contains(object)) return true;
			current = current.getNext();
		}
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
	public <Subtype> Subtype[] toArray(final Subtype[] array) {
		return null;
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

	@Override//TODO implement
	public void addFirst(final Type element) {}

	@Override//TODO implement
	public void addLast(final Type element) {}

	@Override//TODO implement
	public boolean offerFirst(final Type element) {
		return false;
	}

	@Override//TODO implement
	public boolean offerLast(final Type element) {
		return false;
	}

	@Override//TODO implement
	public final Type removeFirst() {
		return null;
	}

	@Override//TODO implement
	public final Type removeLast() {
		return null;
	}

	@Override//TODO implement
	public final Type pollFirst() {
		return null;
	}

	@Override//TODO implement
	public final Type pollLast() {
		return null;
	}

	@Override//TODO implement
	public final Type getFirst() {
		return null;
	}

	@Override//TODO implement
	public final Type getLast() {
		return null;
	}

	@Override//TODO implement
	public final Type peekFirst() {
		return null;
	}

	@Override//TODO implement
	public final Type peekLast() {
		return null;
	}

	@Override//TODO implement
	public boolean removeFirstOccurrence(Object o) {
		return false;
	}

	@Override//TODO implement
	public boolean removeLastOccurrence(Object o) {
		return false;
	}

	@Override//TODO implement
	public boolean offer(final Type element) {
		return false;
	}

	@Override//TODO implement
	public final Type remove() {
		return null;
	}

	@Override//TODO implement
	public final Type poll() {
		return null;
	}

	@Override//TODO implement
	public final Type element() {
		return null;
	}

	@Override//TODO implement
	public final Type peek() {
		return null;
	}

	@Override//TODO implement
	public void push(final Type element) {}

	@Override//TODO implement
	public final Type pop() {
		return null;
	}

	@Override//TODO implement
	public Iterator<Type> descendingIterator() {
		return null;
	}

	@Override
	public String toString() {
		Cluster current = first;
		final StringBuilder stringBuilder = new StringBuilder(16 * clusterCapacity * count);
		stringBuilder.append('(');
		boolean separate = false;
		while (current != null) {
			if (separate) stringBuilder.append(", ");
			else separate = true;
			stringBuilder.append(current.toString());
			current = current.getNext();
		}
		stringBuilder.append(')');
		return stringBuilder.toString();
	}
}
