package org.sitc.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
public final class ClusteredLinkedList<Type> implements List<Type>, Cloneable, Serializable {
	private static final long serialVersionUID = 1;

	public static final int DEFAULT_CLUSTER_CAPACITY = 16;
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final double DEFAULT_STABILITY_FACTOR = 0.25;

	protected final int clusterCapacity;
	protected final double loadFactor,
			stabilityFactor;
	protected final int upperBound,
			lowerBound;
	protected final int upperBoundTimesTwo,
			lowerBoundTimesTwo;

	protected Cluster first,
			last;
	protected int clusterCount,
			elementCount;

	/**
	Throws an exception if a specific index is out of bounds.

	@param index The index.
	@param size The first invalid index.
	**/
	protected static void checkBounds(final int index, final int size) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size + ".");
		}
	}

	/**
	Represents a mutable cluster.
	**/
	@SuppressWarnings("unchecked")
	private final class Cluster {
		protected Cluster next,
				previous;
		protected int count;
		protected final Object[] elements;

		/**
		Creates an empty cluster.
		**/
		public Cluster() {//TODO maybe pass next and previous
			next = null;
			previous = null;
			count = 0;
			elements = new Object[clusterCapacity];
		}

		/**
		Creates an empty cluster and
		 adds it after this cluster.

		@return The cluster.
		**/
		public Cluster createAfter() {
			final Cluster cluster = new Cluster();
			cluster.previous = this;
			if (next == null) {
				last = cluster;
			}
			else {
				cluster.next = next;
				next.previous = cluster;
			}
			next = cluster;
			clusterCount++;
			return cluster;
		}

		/**
		Creates an empty cluster and
		 adds it before this cluster.

		@return The cluster.
		**/
		public Cluster createBefore() {
			final Cluster cluster = new Cluster();
			cluster.next = this;
			if (previous == null) {
				first = cluster;
			}
			else {
				cluster.previous = previous;
				previous.next = cluster;
			}
			previous = cluster;
			clusterCount++;
			return cluster;
		}

		/**
		Disposes this cluster.
		**/
		public void dispose() {
			if (next != null) {
				next.previous = previous;
				next = null;
			}
			if (previous != null) {
				previous.next = next;
				previous = null;
			}
			clusterCount--;
		}

		/**
		Shifts the elements in this cluster towards the end.

		@param index The dividing position.
		**/
		public void shiftRight(final int index) {
			final int displaced = count - index;
			if (displaced >= 1) System.arraycopy(elements, index, elements, index + 1, displaced);
		}

		/**
		Shifts the elements in this cluster towards the beginning.

		@param index The dividing position.
		**/
		public void shiftLeft(final int index) {
			final int displaced = count - index;
			if (displaced >= 1) System.arraycopy(elements, index + 1, elements, index, displaced);
		}

		/**
		Attempts to merge this cluster with its neighbors.
		**/
		public void merge() {
			if (clusterCount < lowerBound) {
				if (next != null) {
					if (previous != null) {
						final int mergedCount = clusterCount + next.count + previous.count;
						if (mergedCount >= lowerBoundTimesTwo && mergedCount <= upperBoundTimesTwo) {
							//TODO merge all into two
						}
						else if (mergedCount >= lowerBound && mergedCount <= upperBound) {
							//TODO merge all into one
						}
					}
					else {
						final int mergedCount = clusterCount + next.count;
						if (mergedCount >= lowerBound && mergedCount <= upperBound) {
							//TODO merge two into one
						}
					}
				}
				else if (previous != null) {
					final int mergedCount = clusterCount + previous.count;
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
			if (clusterCount < lowerBound) {
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
			else if (clusterCount > upperBound) {
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
			if (clusterCount > upperBound) {
				if (clusterCount >= lowerBoundTimesTwo && clusterCount <= upperBoundTimesTwo) {
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
		Returns an element in a certain position in this cluster.

		@param index The position.
		@return The element.
		**/
		public Type get(final int index) {
			checkBounds(index, count);

			return (Type )elements[index];
		}

		/**
		Changes an element in a certain position in this cluster.

		@param index The position.
		@param element The new element.
		**/
		public void set(final int index, final Type element) {
			checkBounds(index, count);

			elements[index] = element;
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
				final Cluster cluster = createAfter();
				cluster.add(element);
			}
			else {
				elements[count] = element;
				count++;
				elementCount++;
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
				final Cluster cluster = createAfter();
				cluster.add((Type )elements[count - 1]);
			}
			shiftRight(index);
			elements[index] = element;
			count++;
			elementCount++;
		}

		/**
		Removes an element from a certain position in this cluster,
		 possibly deletes this cluster and
		 returns the element.

		@param index The position.
		@return The element.
		**/
		public Type remove(final int index) {
			checkBounds(index, count);

			final Type element = (Type )elements[index];
			if (count <= 1) dispose();
			else {
				shiftLeft(index);
				elementCount--;
				count--;
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
					if (count <= 1) dispose();
					else {
						shiftLeft(index);
						elementCount--;
						count--;
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
		upperBoundTimesTwo = 2 * upperBound;
		lowerBoundTimesTwo = 2 * lowerBound;

		first = null;
		last = null;
		clusterCount = 0;
		elementCount = 0;
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
	Returns the index of the cluster that contains an item.

	@param index The index of the item.
	@return The index of the item inside the cluster.
	**/
	public Map.Entry<Integer, Cluster> lookup(final int index) {
		int subindex;
		Cluster cluster;
		if (index < elementCount / 2) {//probably in the first half
			cluster = first;
			subindex = 0;
			while (cluster != null) {
				final int nextElementIndex = subindex + cluster.count;
				if (nextElementIndex > index) break;
				subindex = nextElementIndex;
				cluster = cluster.next;
			}
		}
		else {//probably in the last half
			cluster = last;
			subindex = elementCount;
			while (cluster != null) {
				subindex -= cluster.count;
				if (subindex <= index) break;
				cluster = cluster.previous;
			}
		}
		return new AbstractMap.SimpleEntry<>(index - subindex, cluster);
	}

	/**
	@return The load factor.
	**/
	public double getLoadFactor() {
		return loadFactor;
	}

	/**
	@return The stability factor.
	**/
	public double getStabilityFactor() {
		return stabilityFactor;
	}

	/**
	@return The upper bound.
	**/
	public int getUpperBound() {
		return upperBound;
	}

	/**
	@return The lower bound.
	**/
	public int getLowerBound() {
		return lowerBound;
	}

	@Override
	public void clear() {
		Cluster cluster = first;
		first = null;
		last = null;
		while (cluster != null) {//helps the garbage collector
			final Cluster next = cluster.next;
			cluster.next = null;
			cluster.previous = null;
			cluster = next;
		}
		System.gc();
	}

	@Override
	public Type get(final int index) {
		final Map.Entry<Integer, Cluster> entry = lookup(index);
		final int subindex = entry.getKey();
		final Cluster cluster = entry.getValue();
		return cluster.get(subindex);
	}

	@Override//TODO implement
	public Type set(final int index, final Type element) {
		return null;
	}

	@Override
	public void add(final int index, final Type element) {
		if (elementCount <= 0) {//empty
			checkBounds(index, 1);

			final Cluster cluster = new Cluster();
			first = cluster;
			last = cluster;
			clusterCount++;
			cluster.add(element);
		}
		else {//not empty
			checkBounds(index, elementCount);

			final Map.Entry<Integer, Cluster> entry = lookup(index);
			final int subindex = entry.getKey();
			final Cluster cluster = entry.getValue();

			cluster.add(subindex, element);
			cluster.stabilize();
		}
	}

	@Override
	public boolean add(final Type element) {
		final Cluster cluster;
		if (elementCount <= 0) {//empty
			cluster = new Cluster();
			first = cluster;
			last = cluster;
			clusterCount++;
		}
		else {//not empty
			cluster = last;
		}
		return cluster.add(element);
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
		return elementCount;
	}

	@Override
	public boolean isEmpty() {
		return elementCount <= 0;
	}

	@Override
	public boolean contains(final Object object) {
		Cluster cluster = first;
		while (cluster != null) {
			if (cluster.contains(object)) return true;
			cluster = cluster.next;
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

	@Override
	public String toString() {
		Cluster cluster = first;
		final StringBuilder stringBuilder = new StringBuilder(16 * clusterCapacity * clusterCount);
		stringBuilder.append('[');
		boolean separate = false;
		while (cluster != null) {
			if (separate) stringBuilder.append(", ");
			else separate = true;
			stringBuilder.append(cluster.toString());
			cluster = cluster.next;
		}
		stringBuilder.append(']');
		return stringBuilder.toString();
	}
}
