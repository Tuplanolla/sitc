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
	private static final long serialVersionUID = 1;

	private static final int DEFAULT_CLUSTER_CAPACITY = 16;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private static final double DEFAULT_STABILITY_FACTOR = 0.25;

	private final int clusterCapacity;
	private final double loadFactor,
			stabilityFactor;
	private final int upperBound,
			lowerBound;

	private Cluster first,
			last;
	private int count;

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

		/**
		Creates an empty cluster.
		**/
		public Cluster() {
			elements = new Object[clusterCapacity];

			next = null;
			previous = null;
			count = 0;
		}

		/**
		Attempts to merge this cluster with its neighbors.
		**/
		public void merge() {
			if (count < lowerBound) {
				if (next != null) {
					if (previous != null) {
						final int mergedCount = count + next.count + previous.count;
						if (mergedCount >= 2 * lowerBound && mergedCount <= 2 * upperBound) {
							//TODO merge all into two
						}
						else if (mergedCount >= lowerBound && mergedCount <= upperBound) {
							//TODO merge all into one
						}
					}
					else {
						final int mergedCount = count + next.count;
						if (mergedCount >= lowerBound && mergedCount <= upperBound) {
							//TODO merge two into one
						}
					}
				}
				else if (previous != null) {
					final int mergedCount = count + previous.count;
					if (mergedCount >= lowerBound && mergedCount <= upperBound) {
						//TODO merge two into one
					}
				}
			}
		}

		/**
		Attempts to balance this cluster with its neighbors.
		**/
		public void balance() {
			if (count < lowerBound) {
				if (previous != null) {
					if (previous.count > upperBound) {
						//TODO balance from previous
					}
				}
				if (next != null) {
					if (next.count > upperBound) {
						//TODO balance from next
					}
				}
				if (previous != null) {
					if (previous.count > lowerBound) {
						//TODO balance from previous
					}
				}
				if (next != null) {
					if (next.count > lowerBound) {
						//TODO balance from next
					}
				}
			}
			else if (count > upperBound) {
				if (next != null) {
					if (next.count < lowerBound) {
						//TODO balance from next
					}
				}
				if (previous != null) {
					if (previous.count < lowerBound) {
						//TODO balance from previous
					}
				}
				if (next != null) {
					if (next.count < upperBound) {
						//TODO balance from next
					}
				}
				if (previous != null) {
					if (previous.count < upperBound) {
						//TODO balance from previous
					}
				}
			}
		}

		/**
		Attempts to split this cluster.
		**/
		public void split() {
			if (count > upperBound) {
				if (count >= 2 * lowerBound && count <= 2 * upperBound) {
					//TODO split into two
				}
			}
		}

		/**
		Attempts to stabilize (merge, balance and split) this cluster.

		No changes are guaranteed to happen.
		**/
		public void stabilize() {
			merge();
			balance();
			split();
		}

		/**
		Adds an element
		 to the end of this cluster or
		 to the beginning of a new cluster and
		  returns whether the operation was successful.

		@param element The element.
		@return Whether the element was added.
		**/
		public boolean add(final Type element) {
			if (count >= clusterCapacity) {
				final Cluster cluster = new Cluster();
				cluster.previous = this;
				if (next != null) {
					cluster.next = next;
					next.previous = cluster;
				}
				else {
					last = cluster;
				}
				next = cluster;
				cluster.add(element);
			}
			else {
				elements[count] = element;
				count++;
			}
			return true;
		}

		/**
		Adds an element to a certain position
		 in this cluster or
		 in a new cluster.

		@param index The position.
		@param element The element.
		**/
		public void add(final int index, final Type element) {
			checkBounds(index, count);

			if (count >= clusterCapacity) {
				final Cluster cluster = new Cluster();
				cluster.previous = this;
				if (next != null) {
					cluster.next = next;
					next.previous = cluster;
				}
				else {
					last = cluster;
				}
				next = cluster;
				cluster.add((Type )elements[count - 1]);
			}
			final int displaced = count - index;
			if (displaced >= 1) System.arraycopy(elements, index, elements, index + 1, count - index);
			elements[index] = element;
			count++;
		}

		/**
		Removes an element from a certain position in this cluster,
		 possibly deletes this cluster and
		 returns it.

		@param index The position.
		@return The element.
		**/
		public Type remove(final int index) {
			checkBounds(index, count);

			final Type element = (Type )elements[index];
			if (count <= 1) {
				if (next != null) next.previous = previous;
				if (previous != null) previous.next = next;
			}
			else {
				count--;
				final int displaced = count - index;
				if (displaced >= 1) System.arraycopy(elements, index + 1, elements, index, displaced);
				elements[count] = null;
			}
			return element;
		}

		/**
		Removes a certain element from this cluster,
		 possibly deletes this cluster and
		 returns whether the operation was successful.

		@param element The element.
		@return Whether the element was removed.
		**/
		public boolean remove(final Type element) {
			for (int index = 0; index < count; index++) {
				if (elements[index] == element) {
					if (count <= 1) {
						if (next != null) next.previous = previous;
						if (previous != null) previous.next = next;
					}
					else {
						count--;
						final int displaced = count - index;
						if (displaced >= 1) System.arraycopy(elements, index + 1, elements, index, displaced);
						elements[count] = null;
					}
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
			stringBuilder.append('[');
			for (int index = 0; index < count; index++) {
				if (index != 0) stringBuilder.append(", ");
				stringBuilder.append(elements[index].toString());
			}
			stringBuilder.append(']');
			return stringBuilder.toString();
		}
	}

	/**
	Creates an empty list with the specified cluster capacity, load factor and stability factor.

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
		upperBound = (int )(loadFactor * clusterCapacity);
		lowerBound = (int )((loadFactor - stabilityFactor) * clusterCapacity);

		first = null;
		last = null;
		count = 0;
	}

	/**
	Creates an empty list with the specified cluster capacity and load factor.

	@param clusterCapacity The capacity of the clusters.
	@param loadFactor The load factor of the clusters.
	**/
	public ClusteredLinkedList(final int clusterCapacity, final double loadFactor) {
		this(clusterCapacity, loadFactor, DEFAULT_STABILITY_FACTOR);
	}

	/**
	Creates an empty list with the specified cluster capacity.

	@param clusterCapacity The capacity of the clusters.
	**/
	public ClusteredLinkedList(final int clusterCapacity) {
		this(clusterCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	Creates an empty list.
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

			final Cluster current = new Cluster();
			first = current;
			last = current;
			count++;
			current.add(element);
		}
		else {//not empty
			checkBounds(index, count);

			Cluster cluster;
			int clusterIndex;
			if (index < count / 2) {//probably in the first half
				cluster = first;
				clusterIndex = 0;
				while (cluster != null) {
					final int nextElementIndex = clusterIndex + cluster.count;
					if (nextElementIndex > index) break;
					clusterIndex = nextElementIndex;
					cluster = cluster.next;
				}
			}
			else {//probably in the last half
				cluster = last;
				clusterIndex = count;
				while (cluster != null) {
					clusterIndex -= cluster.count;
					if (clusterIndex <= index) break;
					cluster = cluster.previous;
				}
			}
			final int elementIndex = index - clusterIndex;

			count++;
			cluster.add(elementIndex, element);
			cluster.stabilize();
		}
	}

	@Override
	public boolean add(final Type element) {
		if (count <= 0) {//empty
			final Cluster cluster = new Cluster();
			first = cluster;
			last = cluster;
			count++;
			return cluster.add(element);
		}
		else {//not empty
			count++;
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
			current = current.next;
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
	public boolean removeFirstOccurrence(final Object object) {
		return false;
	}

	@Override//TODO implement
	public boolean removeLastOccurrence(final Object object) {
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
		stringBuilder.append('[');
		boolean separate = false;
		while (current != null) {
			if (separate) stringBuilder.append(", ");
			else separate = true;
			stringBuilder.append(current.toString());
			current = current.next;
		}
		stringBuilder.append(']');
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		ClusteredLinkedList<Integer> cll = new ClusteredLinkedList<>(8);
		for (int index = 1; index <= 24; index++) {
			cll.add(index);
			System.out.println(cll);
		}
	}
}
