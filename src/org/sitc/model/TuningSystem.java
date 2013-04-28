package org.sitc.model;

/**
Outlines an immutable tuning system.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public interface TuningSystem {
	/**
	Returns the frequency of a note in a pitch system.

	@param pitchSystem The pitch system.
	@param note The note.
	@return The frequency.
	**/
	public double frequency(PitchSystem pitchSystem, Note note);
}
