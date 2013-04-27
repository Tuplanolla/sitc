package org.sitc.models.standardmodel;

import java.io.Serializable;
import java.math.BigInteger;

/**
Represents an immutable pitch system.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class PitchSystem implements Serializable {
	private static final long serialVersionUID = 1;

	/**
	Represents the twelve-tone scientific pitch system where C<sub>4</sub> is tuned to 256 Hz.
	**/
	public static final PitchSystem SCIENTIFIC = new PitchSystem(256,
			new Note(BigInteger.valueOf(4 * 12)),
			EqualTemperament.TWELVE_TONE);

	/**
	Represents the twelve-tone concert pitch system where A<sub>4</sub> is tuned to 440 Hz.
	**/
	public static final PitchSystem STANDARD = new PitchSystem(440,
			new Note(BigInteger.valueOf(4 * 12 + 9)),
			EqualTemperament.TWELVE_TONE);

	/**
	The frequency of the note that's used to define the rest.
	**/
	public final double baseFrequency;

	/**
	The note of the frequency that's used to define the rest.
	**/
	public final Note baseNote;

	/**
	The tuning system that's used to resolve imperfect intervals.
	**/
	public final TuningSystem tuningSystem;

	/**
	Creates a pitch system.

	@param baseFrequency The base frequency.
	@param baseNote The base note.
	@param tuningSystem The tuning system.
	**/
	public PitchSystem(final double baseFrequency,
			final Note baseNote,
			final TuningSystem tuningSystem) {
		this.baseFrequency = baseFrequency;
		this.baseNote = baseNote;
		this.tuningSystem = tuningSystem;
	}

	/**
	Creates a pitch system.

	@param baseFrequency The base frequency.
	@param tuningSystem The tuning system.
	**/
	public PitchSystem(final double baseFrequency,
			final TuningSystem tuningSystem) {
		this(baseFrequency, Note.C0, tuningSystem);
	}

	/**
	Creates a pitch system.

	@param baseFrequency The base frequency.
	**/
	public PitchSystem(final double baseFrequency) {
		this(baseFrequency, Note.C0, EqualTemperament.TWELVE_TONE);
	}

	/**
	Returns the frequency of a note in this pitch system.

	@param note The note.
	@return The frequency.
	**/
	public double frequency(final Note note) {
		return tuningSystem.frequency(this, note);
	}
}
