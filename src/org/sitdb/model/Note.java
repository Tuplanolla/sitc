package org.sitdb.model;

import java.io.Serializable;

import org.sitdb.util.Natural;
import org.sitdb.util.Numeric;
import org.sitdb.util.Rational;

/**
Represents an immutable note as a distance from C<sub>0</sub>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Note implements Numeric, Serializable {
	private static final long serialVersionUID = 3843295524835957841l;

	/**
	The amount of semitones from C<sub>0</sub>.
	**/
	public final Natural semitones;

	/**
	The amount of microtones from the semitone.
	**/
	public final Rational microtones;

	/**
	Constructs a new note.

	@param semitones The distance in semitones.
	@param microtones The distance in microtones.
	**/
	public Note(Natural semitones, Rational microtones) {
		final Rational minusOne = Rational.ONE.opposite();
		while (microtones.compareTo(minusOne) <= 0) {
			semitones = semitones.subtract(Natural.ONE);
			microtones = microtones.add(Rational.ONE);
		}
		while (microtones.compareTo(Rational.ONE) >= 0) {
			semitones = semitones.add(Natural.ONE);
			microtones = microtones.subtract(Rational.ONE);
		}
		this.semitones = semitones;
		this.microtones = microtones;
	}

	/**
	Constructs a new note.

	@param semitones The distance in semitones.
	**/
	public Note(final Natural semitones) {
		this(semitones, Rational.ZERO);
	}

	/**
	Returns the distance of this note from another note.

	@param note The other note.
	@return The distance.
	**/
	public Note distance(final Note note) {
		return new Note(semitones.subtract(note.semitones), microtones.subtract(note.microtones));
	}

	/**
	Returns the transposition of this note.

	@param semitones The amount of semitones to transpose.
	@param microtones The amount of microtones to transpose.
	@return The transposition.
	**/
	public Note transpose(final Natural semitones, final Rational microtones) {
		return new Note(this.semitones.add(semitones), this.microtones.add(microtones));
	}

	/**
	Returns the transposition of this note.

	@param semitones The amount of semitones to transpose.
	@return The transposition.
	**/
	public Note transpose(final Natural semitones) {
		return transpose(semitones, Rational.ZERO);
	}

	/**
	Returns the transposition of this note.

	@param note The note to transpose by.
	@return The transposition.
	**/
	public Note transpose(final Note note) {
		return transpose(note.semitones, note.microtones);
	}

	/**
	Returns the frequency of this note in a pitch system.

	@param pitchSystem The pitch system.
	@return The frequency.
	**/
	public double frequency(final PitchSystem pitchSystem) {
		return pitchSystem.frequency(this);
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Note)) return false;
		final Note note = (Note )object;
		return semitones.equals(note.semitones) && microtones.equals(note.microtones);
	}

	@Override
	public int hashCode() {
		return semitones.hashCode() ^ microtones.hashCode();
	}

	@Override
	public java.lang.String toString() {//TODO scientific pitch notation
		return semitones.toString() + " + " + microtones.toString();
	}

	@Override
	public double doubleValue() {
		return semitones.doubleValue() + microtones.doubleValue();
	}

	@Override
	public long longValue() {
		return (long )doubleValue();
	}

	@Override
	public int intValue() {
		return (int )doubleValue();
	}

	@Override
	public short shortValue() {
		return (short )doubleValue();
	}

	@Override
	public byte byteValue() {
		return (byte )doubleValue();
	}

	@Override
	public float floatValue() {
		return (float )doubleValue();
	}
}
