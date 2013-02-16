package org.sitdb;

import java.util.LinkedList;
import java.util.List;

import org.sitdb.model.Instrument;
import org.sitdb.model.Sequence;
import org.sitdb.model.Tuning;

/**
Represents a mutable model.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Model {
	public final List<Instrument> instruments;
	public final List<Tuning> tunings;
	public final List<Sequence> sequences;

	/**
	Constructs a new model.

	@param arguments The command-line arguments.
	**/
	public Model(final List<String> arguments) {
		instruments = new LinkedList<Instrument>();
		tunings = new LinkedList<Tuning>();
		sequences = new LinkedList<Sequence>();
	}

	public void activate() {}
}
