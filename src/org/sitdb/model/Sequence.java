package org.sitdb.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.sitdb.util.StringFormatter;

/**
Represents a mutable sequence of tunings.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Sequence implements Serializable {
	private static final long serialVersionUID = 1l;

	/**
	The name of this sequence.
	**/
	private java.lang.String name;

	/**
	The tuning system this sequence prefers or <code>null</code>.
	**/
	private TuningSystem tuningSystem;

	/**
	The instrument this sequence is for or <code>null</code>.
	**/
	private Instrument instrument;

	/**
	The tunings this sequence is made of.
	**/
	private List<Tuning> tunings;

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
	Creates a sequence of tunings with a tuning system for any instrument.

	@param name The name.
	@param tuningSystem The tuning system.
	**/
	public Sequence(final java.lang.String name, final TuningSystem tuningSystem) {
		this(name, tuningSystem, null);
	}

	/**
	Creates a sequence of tunings without a tuning system for a specific instrument.

	@param name The name.
	@param instrument The instrument.
	**/
	public Sequence(final java.lang.String name, final Instrument instrument) {
		this(name, null, instrument);
	}

	/**
	Creates a sequence of tunings without a tuning system for any instrument.

	@param name The name.
	**/
	public Sequence(final java.lang.String name) {
		this(name, null, null);
	}

	/**
	@return The name.
	**/
	public java.lang.String getName() {
		return name;
	}

	/**
	@param name The new name.
	**/
	public void setName(final java.lang.String name) {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	@return The tuningSystem.
	**/
	public TuningSystem getTuningSystem() {
		return tuningSystem;
	}

	/**
	@param tuningSystem The new tuningSystem.
	**/
	public void setTuningSystem(final TuningSystem tuningSystem) {
		this.tuningSystem = tuningSystem;
	}

	/**
	@return The instrument.
	**/
	public Instrument getInstrument() {
		return instrument;
	}

	/**
	@param instrument The new instrument.
	**/
	public void setInstrument(final Instrument instrument) {
		this.instrument = instrument;
	}

	/**
	@return The tunings.
	**/
	public List<Tuning> getTunings() {
		return tunings;
	}

	/**
	@param tunings The new tunings.
	**/
	public void setTunings(final List<Tuning> tunings) {
		this.tunings = tunings;
	}

	@Override
	public java.lang.String toString() {
		final Object[][] objects = {
			{"instrument", instrument},
			{"tunings", tunings}
		};
		final java.lang.String fields = StringFormatter.formatFields(objects);
		return name + " (" + fields + ")";
	}
}
