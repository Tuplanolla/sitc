package org.sitdb.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
Represents a mutable sequence of tunings.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Sequence implements Serializable {
	private static final long serialVersionUID = 1l;

	/**
	The name of this sequence.
	**/
	public final java.lang.String name;

	/**
	The tuning system this sequence uses.
	**/
	public final TuningSystem tuningSystem;

	/**
	The instrument this sequence is for or <code>null</code>.
	**/
	public final Instrument instrument;

	/**
	The tunings this sequence is made of.
	**/
	public final List<Tuning> tunings;

	/**
	Creates a sequence of tunings for a specific instrument.

	@param name The name.
	@param tuningSystem The tuning system.
	@param instrument The instrument.
	**/
	public Sequence(final java.lang.String name, final TuningSystem tuningSystem, final Instrument instrument) {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
		this.tuningSystem = tuningSystem;
		this.instrument = instrument;
		tunings = new LinkedList<Tuning>();
	}

	/**
	Creates a sequence of tunings for any instrument.

	@param name The name.
	@param tuningSystem The tuning system.
	**/
	public Sequence(final java.lang.String name, final TuningSystem tuningSystem) {
		this(name, tuningSystem, null);
	}
}
