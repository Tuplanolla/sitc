package org.sitdb.model;

import java.util.LinkedList;
import java.util.List;

import org.sitdb.Part;


/**
Represents a mutable model.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Model implements Part {
	private final List<Instrument> instruments;
	private final List<Tuning> tunings;
	private final List<Sequence> sequences;

	/**
	Constructs a new model.

	@param arguments The command-line arguments.
	**/
	public Model(final List<String> arguments) {
		instruments = new LinkedList<Instrument>();
		tunings = new LinkedList<Tuning>();
		sequences = new LinkedList<Sequence>();
	}

	@Override
	public void activate() {}

	public void loadInstruments(final String filename) {
		//InstrumentParser.load(instruments, filename);
	}

	public void saveInstruments(final String filename) {}

	public void loadTunings(final String filename) {}

	public void saveTunings(final String filename) {}

	public void loadSequences(final String filename) {}

	public void saveSequences(final String filename) {}
}
