package org.sitdb.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
Represents a mutable instrument.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Instrument implements Serializable {
	private static final long serialVersionUID = 1l;

	/**
	The name of this instrument.
	**/
	public final java.lang.String name;

	/**
	The maximum tension this instrument can handle.
	**/
	public final Double maximumTension;

	/**
	The strings this instrument has.
	**/
	public final List<String> strings;

	/**
	Creates an instrument with a tension limit.

	@param name The name.
	@param maximumTension The maximum tension.
	**/
	public Instrument(final java.lang.String name, final Double maximumTension) {
		this.name = name;
		this.maximumTension = maximumTension;
		strings = new LinkedList<String>();
	}

	/**
	Creates an instrument without a tension limit.

	@param name The name.
	**/
	public Instrument(final java.lang.String name) {
		this(name, null);
	}

	@Override
	public java.lang.String toString() {
		return name + " (strings: " + strings + ", maximum tension: " + maximumTension + ")";
	}
}
