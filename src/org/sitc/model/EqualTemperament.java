package org.sitc.model;

/**
Represents the equal temperament tuning system in which
 the frequencies follow <i>f<sub>i</sub></i> = <i>f</i><sub>0</sub>2<sup>(<i>N<sub>i</sub></i>&minus;<i>N</i><sub>0</sub>)/<i>T</i></sup>, where
  <i>f</i><sub>0</sub> is the base frequency,
  <i>N</i><sub>0</sub> is the base note and
  <i>T</i> is the amount of semitones in an octave.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public class EqualTemperament implements TuningSystem {
	/**
	The twelve-tone tuning system.
	**/
	public static final TuningSystem TWELVE_TONE = new EqualTemperament(12);

	/**
	The amount of semitones in an octave.
	**/
	public final int semitonesInOctave;

	/**
	Creates a tuning system.

	@param semitonesInOctave The amount of semitones in an octave.
	**/
	public EqualTemperament(final int semitonesInOctave) {
		this.semitonesInOctave = semitonesInOctave;
	}

	@Override
	public double frequency(final PitchSystem pitchSystem, final Note note) {
		final Note distance = note.distance(pitchSystem.baseNote);
		return pitchSystem.baseFrequency * Math.pow(2, distance.getTones().doubleValue() / semitonesInOctave);
	}

	@Override
	public java.lang.String toString() {
		return semitonesInOctave + "-Tone Equal Temperament";
	}
}
