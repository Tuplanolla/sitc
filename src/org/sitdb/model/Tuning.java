package org.sitdb.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
Represents a mutable tuning.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Tuning implements Serializable {//TODO nice things
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

	public Note minimum() {
		return null;
	}

	public Note maximum() {
		return null;
	}

	public Note span() {
		return null;
	}

	public Note mean() {
		return null;
	}

	public boolean monotonous() {
		return false;
	}

	public boolean increasing() {
		return false;
	}

	public boolean decreasing() {
		return false;
	}
}
