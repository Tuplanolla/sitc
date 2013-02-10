package org.sitdb.model;

import java.io.Serializable;

import org.sitdb.util.Natural;

/**
Represents an immutable pitch system.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class PitchSystem implements Serializable {
	private static final long serialVersionUID = 8200556001796394964l;

	/**
	Represents the equal temperament tuning system in which
	 the frequencies follow <i>f<sub>i</sub></i> = <i>f</i><sub>0</sub>2<sup>(<i>N<sub>i</sub></i>&minus;<i>N</i><sub>0</sub>)/<i>T</i></sup>, where
	  <i>f</i><sub>0</sub> is the base frequency,
	  <i>N</i><sub>0</sub> is the base note and
	  <i>T</i> is the amount of semitones in an octave.
	**/
	public static final TuningSystem TWELVE_TONE_CHROMATIC_EQUAL_TEMPERAMENT = new TuningSystem() {
		@Override
		public double frequency(final PitchSystem pitchSystem, final Note note) {
			final Note distance = note.distance(pitchSystem.baseNote);
			return pitchSystem.baseFrequency * Math.pow(2, distance.doubleValue() / pitchSystem.semitonesInOctave);
		}
	};

	/**
	Represents the twelve-tone chromatic scientific pitch system where C<sub>4</sub> is tuned to 256 Hz.
	**/
	public static final PitchSystem SCIENTIFIC = new PitchSystem(12, 256, new Note(Natural.valueOf(48)), TWELVE_TONE_CHROMATIC_EQUAL_TEMPERAMENT);

	/**
	Represents the twelve-tone chromatic concert pitch system where A<sub>4</sub> is tuned to 440 Hz.
	**/
	public static final PitchSystem STANDARD = new PitchSystem(12, 440, new Note(Natural.valueOf(57)), TWELVE_TONE_CHROMATIC_EQUAL_TEMPERAMENT);

	/**
	The amount of semitones in an octave.
	**/
	public final int semitonesInOctave;

	/**
	The frequency of the note that's used to define the rest.
	**/
	public final double baseFrequency;

	/**
	The note that the base frequency relates to.
	**/
	public final Note baseNote;

	/**
	The tuning system that's used to resolve imperfect intervals.
	**/
	public final TuningSystem tuningSystem;

	/**
	Constructs a new pitch system.

	@param semitonesInOctave The amount of semitones in an octave.
	@param baseFrequency The base frequency.
	@param baseNote The base note.
	@param tuningSystem The tuning system.
	**/
	public PitchSystem(final int semitonesInOctave,
			final double baseFrequency,
			final Note baseNote,
			final TuningSystem tuningSystem) {
		this.semitonesInOctave = semitonesInOctave;
		this.baseFrequency = baseFrequency;
		this.baseNote = baseNote;
		this.tuningSystem = tuningSystem;
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
