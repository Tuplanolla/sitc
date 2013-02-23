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
	The notes this instrument has.
	**/
	public final List<String> strings;

	/**
	The minimum tension this instrument can handle.
	**/
	public final Double minimumTension;

	/**
	The maximum tension this instrument can handle.
	**/
	public final Double maximumTension;

	/**
	Creates a instrument with tension limits.

	@param name The name.
	@param minimumTension The minimum tension.
	@param maximumTension The maximum tension.
	**/
	public Instrument(final java.lang.String name, final Double minimumTension, final Double maximumTension) {
		this.name = name;
		strings = new LinkedList<String>();
		this.minimumTension = minimumTension;
		this.maximumTension = maximumTension;
	}

	/**
	Creates a instrument with a tension limit.

	@param name The name.
	@param maximumTension The maximum tension.
	**/
	public Instrument(final java.lang.String name, final Double maximumTension) {
		this(name, null, maximumTension);
	}

	/**
	Creates a instrument without tension limits.

	@param name The name.
	**/
	public Instrument(final java.lang.String name) {
		this(name, null, null);
	}
}
