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
	The tuning system this instrument is restricted to or <code>null</code>.
	**/
	public final TuningSystem tuningSystem;

	/**
	The strings this instrument has.
	**/
	public final List<String> strings;

	/**
	Creates an instrument with a tension limit.

	@param name The name.
	@param maximumTension The maximum tension.
	@param tuningSystem The tuning system.
	**/
	public Instrument(final java.lang.String name, final Double maximumTension, final TuningSystem tuningSystem) {
		this.name = name;
		this.tuningSystem = tuningSystem;
		this.maximumTension = maximumTension;
		strings = new LinkedList<String>();
	}

	/**
	Creates an instrument without a tuning system.

	@param name The name.
	@param maximumTension The maximum tension.
	**/
	public Instrument(final java.lang.String name, final Double maximumTension) {
		this(name, maximumTension, null);
	}

	/**
	Creates an instrument without a tension limit.

	@param name The name.
	@param tuningSystem The tuning system.
	**/
	public Instrument(final java.lang.String name, final TuningSystem tuningSystem) {
		this(name, null, tuningSystem);
	}

	@Override
	public java.lang.String toString() {
		return name + " (strings: " + strings + ", maximum tension: " + maximumTension + ")";
	}
}
