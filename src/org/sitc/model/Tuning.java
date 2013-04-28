package org.sitc.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.sitc.util.StringFormatter;

/**
Represents a mutable tuning.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Tuning implements Named, Comparable<Tuning>, Serializable {
	private static final long serialVersionUID = 1;

	/**
	The name of this tuning.
	**/
	private java.lang.String name;

	/**
	The tuning system this tuning uses or <code>null</code>.
	**/
	private TuningSystem tuningSystem;

	/**
	The notes this tuning is made of.
	**/
	private List<Note> notes;

	/**
	Creates a tuning with a tuning system.

	@param name The name.
	@param tuningSystem The tuning system.
	**/
	public Tuning(final java.lang.String name, final TuningSystem tuningSystem) {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
		this.tuningSystem = tuningSystem;
		notes = new LinkedList<>();
	}

	/**
	Creates a tuning without a tuning system.

	@param name The name.
	**/
	public Tuning(final java.lang.String name) {
		this(name, null);
	}

	/**
	Returns the lowest note in this tuning.

	@return The lowest note.
	**/
	public Note lowest() {
		return Collections.min(notes);
	}

	/**
	Returns the highest note in this tuning.

	@return The highest note.
	**/
	public Note highest() {
		return Collections.max(notes);
	}

	/**
	Returns the distance between the lowest and the highest notes in this tuning.

	@return The distance.
	**/
	public Note span() {
		return highest().distance(lowest());
	}

	/**
	Returns whether this tuning is increasing.

	@return Whether this tuning is increasing.
	**/
	public boolean increasing() {
		Note previous = null;
		for (final Note note : notes) {
			if (previous != null) {
				if (note.compareTo(previous) < 0) return false;
			}
			previous = note;
		}
		return true;
	}

	/**
	Returns whether this tuning is decreasing.

	@return Whether this tuning is decreasing.
	**/
	public boolean decreasing() {
		Note previous = null;
		for (final Note note : notes) {
			if (previous != null) {
				if (note.compareTo(previous) > 0) return false;
			}
			previous = note;
		}
		return true;
	}

	/**
	Returns whether this tuning is increasing or decreasing.

	A tuning can be both increasing and decreasing.

	@return Whether this tuning is monotonous.
	**/
	public boolean monotonous() {
		return increasing() || decreasing();
	}

	/**
	@return The name.
	**/
	@Override
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
	@return The notes.
	**/
	public List<Note> getNotes() {
		return notes;
	}

	/**
	@param notes The new notes.
	**/
	public void setNotes(final List<Note> notes) {
		this.notes = notes;
	}

	public java.lang.String toFullString() {
		final Object[][] objects = {
			{"tuning system", tuningSystem},
			{"notes", notes}
		};
		final java.lang.String fields = StringFormatter.formatFields(objects);
		return name + " (" + fields + ")";
	}

	@Override
	public java.lang.String toString() {
		return name + " (" + notes.size() + ")";
	}

	@Override
	public int compareTo(final Tuning tuning) {
		return getName().compareTo(tuning.getName());
	}
}
