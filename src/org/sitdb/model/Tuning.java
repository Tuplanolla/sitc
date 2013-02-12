package org.sitdb.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
Represents a mutable tuning.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Tuning implements Serializable {
	private static final long serialVersionUID = 4640012830741353240l;

	/**
	The name of this tuning.
	**/
	public final java.lang.String name;

	/**
	The notes this tuning is made of.
	**/
	public final List<Note> notes;

	/**
	Constructs a new tuning.

	@param name The name.
	**/
	public Tuning(final java.lang.String name) {
		this.name = name;
		notes = new LinkedList<Note>();
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

	@return Whether this tuning is monotonous.
	**/
	public boolean monotonous() {
		return increasing() || decreasing();
	}
}
