package org.sitdb.model;

import java.util.LinkedList;
import java.util.List;

import org.sitdb.Model;

/**
Represents a mutable model.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class DefaultModel extends Model {
	public final List<Instrument> instruments;
	public final List<Tuning> tunings;
	public final List<Sequence> sequences;

	/**
	Constructs a new model.
	
	@param arguments The command-line arguments.
	**/
	public DefaultModel(final java.lang.String[] arguments) {
		super(arguments);

		instruments = new LinkedList<Instrument>();
		tunings = new LinkedList<Tuning>();
		sequences = new LinkedList<Sequence>();
	}

	@Override
	public void activate() {}
}
