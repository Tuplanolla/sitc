package org.sitc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.sitc.util.StringFormatter;

/**
Represents a mutable instrument.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Instrument implements Comparable<Instrument>, Serializable {
	private static final long serialVersionUID = 1;

	/**
	The name of this instrument.
	**/
	private java.lang.String name;

	/**
	The maximum tension this instrument can handle or <code>null</code>.
	**/
	private BigDecimal maximumTension;

	/**
	The tuning system this instrument is restricted to or <code>null</code>.
	**/
	private TuningSystem tuningSystem;

	/**
	The strings this instrument has.
	**/
	private List<String> strings;

	/**
	Creates an instrument with a tension limit and a tuning system.

	@param name The name.
	@param maximumTension The maximum tension.
	@param tuningSystem The tuning system.
	**/
	public Instrument(final java.lang.String name, final BigDecimal maximumTension, final TuningSystem tuningSystem) {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
		this.tuningSystem = tuningSystem;
		this.maximumTension = maximumTension;
		strings = new LinkedList<>();
	}

	/**
	Creates an instrument without a tuning system.

	@param name The name.
	@param maximumTension The maximum tension.
	**/
	public Instrument(final java.lang.String name, final BigDecimal maximumTension) {
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

	/**
	Creates an instrument without a tension limit or a tuning system.

	@param name The name.
	**/
	public Instrument(final java.lang.String name) {
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
	@return The maximumTension.
	**/
	public BigDecimal getMaximumTension() {
		return maximumTension;
	}

	/**
	@param maximumTension The new maximumTension.
	**/
	public void setMaximumTension(final BigDecimal maximumTension) {
		this.maximumTension = maximumTension;
	}

	/**
	@return The tuning system.
	**/
	public TuningSystem getTuningSystem() {
		return tuningSystem;
	}

	/**
	@param tuningSystem The new tuning system.
	**/
	public void setTuningSystem(final TuningSystem tuningSystem) {
		this.tuningSystem = tuningSystem;
	}

	/**
	@return The strings.
	**/
	public List<String> getStrings() {
		return strings;
	}

	/**
	@param strings The new strings.
	**/
	public void setStrings(final List<String> strings) {
		this.strings = strings;
	}

	public java.lang.String toFullString() {
		final Object[][] objects = {
			{"strings", strings},
			{"maximum tension", maximumTension, " N"},
			{"tuning system", tuningSystem}
		};
		final java.lang.String fields = StringFormatter.formatFields(objects);
		return name + " (" + fields + ")";
	}

	@Override
	public java.lang.String toString() {
		return name + " (" + strings.size() + ")";
	}

	@Override
	public int compareTo(final Instrument instrument) {
		return getName().compareTo(instrument.getName());
	}
}
