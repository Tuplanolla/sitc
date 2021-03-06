package org.sitc.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math3.fraction.BigFraction;
import org.sitc.util.StringFormatter;

/**
Represents an immutable note as a distance from C<sub>0</sub>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Note implements Comparable<Note>, Serializable {
	private static final long serialVersionUID = 1;

	/**
	The string pattern for the parser that doesn't allow spaces.
	**/
	public static final Pattern STRICT_PATTERN = Pattern.compile("([A-G])"//note
			+ "([bn#\u266d\u266e\u266f]?)"//accidental
			+ "([+-\u208a\u208b]?)([0-9\u2080-\u2089]+)"//octave
			+ "(?:"//microtones
			+ "([+-\u207a\u207b]?)([0-9\u2070-\u2079]+)"//dividend or divisor
			+ "(?:"//rational
			+ "([/\\\\])"//division or inverted division
			+ "([+-\u207a\u207b]?)([0-9\u2070-\u2079]+)"//divisor or dividend
			+ ")?)?", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	/**
	The string pattern for the parser that allows spaces.
	**/
	public static final Pattern LENIENT_PATTERN = Pattern.compile("\\s*"//whitespace
			+ "([A-G])\\s*"//note
			+ "([bn#\u266d\u266e\u266f]?)\\s*"//accidental
			+ "([+-\u208a\u208b]?)\\s*([0-9\u2080-\u2089]+)\\s*"//octave
			+ "(?:\\s*"//microtones
			+ "([+-\u207a\u207b]?)\\s*([0-9\u2070-\u2079]+)\\s*"//dividend or divisor
			+ "(?:"//rational
			+ "([/\\\\])\\s*"//division or inverted division
			+ "([+-\u207a\u207b]?)\\s*([0-9\u2070-\u2079]+)\\s*"//divisor or dividend
			+ ")?)?\\s*", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	/**
	The note C<sub>0</sub>.
	**/
	public static final Note C0 = new Note(BigInteger.ZERO);

	/**
	The amount of semitones from C<sub>0</sub>.
	**/
	private final BigInteger semitones;

	/**
	The amount of microtones from the semitone.
	**/
	private final BigFraction microtones;

	/**
	The amount of semitones and microtones from C<sub>0</sub> or <code>null</code>.

	Not calculated until needed.
	**/
	private BigFraction tones;

	/**
	Creates a note.

	@param semitones The distance in semitones.
	@param microtones The distance in microtones.
	**/
	public Note(BigInteger semitones, BigFraction microtones) {
		while (microtones.compareTo(BigFraction.MINUS_ONE) <= 0) {
			semitones = semitones.subtract(BigInteger.ONE);
			microtones = microtones.add(BigFraction.ONE);
		}
		while (microtones.compareTo(BigFraction.ONE) >= 0) {
			semitones = semitones.add(BigInteger.ONE);
			microtones = microtones.subtract(BigFraction.ONE);
		}
		this.semitones = semitones;
		this.microtones = microtones;
		tones = null;
	}

	/**
	Creates a note.

	@param semitones The distance in semitones.
	**/
	public Note(final BigInteger semitones) {
		this.semitones = semitones;
		microtones = null;
		tones = null;
	}

	/**
	Parses a character as the letter part of a note.

	@param character The character.
	@return The contribution in the composition of a note in semitones.
	**/
	private static int parseLetter(final char character) throws NumberFormatException {
		switch (Character.toUpperCase(character)) {
		case 'C': return 0;
		case 'D': return +2;
		case 'E': return +4;
		case 'F': return +5;
		case 'G': return +7;
		case 'A': return +9;
		case 'B': return +11;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a character as the letter part of a note.

	@param character The character.
	@return The contribution in the composition of a note in semitones.
	**/
	private static int parseAccidental(final char character) throws NumberFormatException {
		switch (Character.toLowerCase(character)) {
		case 'b': case '\u266d': return -1;
		case 'n': case '\u266e': return 0;
		case '#': case '\u266f': return +1;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a character as the octave sign part of a note.

	@param character The character.
	@return The sign.
	**/
	private static int parseSubscriptSign(final char character) throws NumberFormatException {
		switch (character) {
		case '+': case '\u208a': return +1;
		case '-': case '\u208b': return -1;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a character as the microtonal sign part of a note.

	@param character The character.
	@return The sign.
	**/
	private static int parseSuperscriptSign(final char character) throws NumberFormatException {
		switch (character) {
		case '+': case '\u207a': return +1;
		case '-': case '\u207b': return -1;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a character as the octave part of a note.

	@param character The character.
	@return The contribution in the composition of a note in octaves.
	**/
	private static int parseSubscript(final char character) throws NumberFormatException {
		switch (character) {
		case '0': case '\u2080': return 0;
		case '1': case '\u2081': return 1;
		case '2': case '\u2082': return 2;
		case '3': case '\u2083': return 3;
		case '4': case '\u2084': return 4;
		case '5': case '\u2085': return 5;
		case '6': case '\u2086': return 6;
		case '7': case '\u2087': return 7;
		case '8': case '\u2088': return 8;
		case '9': case '\u2089': return 9;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a character as the microtonal part of a note.

	@param character The character.
	@return The contribution in the composition of a note in microtones.
	**/
	private static int parseSuperscript(final char character) throws NumberFormatException {
		switch (character) {
		case '0': case '\u2070': return 0;
		case '1': case '\u2071': return 1;
		case '2': case '\u2072': return 2;
		case '3': case '\u2073': return 3;
		case '4': case '\u2074': return 4;
		case '5': case '\u2075': return 5;
		case '6': case '\u2076': return 6;
		case '7': case '\u2077': return 7;
		case '8': case '\u2078': return 8;
		case '9': case '\u2079': return 9;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a string as the octave part of a note.

	@param string The string.
	@return The contribution in the composition of a note in octaves.
	**/
	private static int parseSubscripts(final java.lang.String string) throws NumberFormatException {
		int result = 0;
		int magnitude = 1;
		for (int index = string.length() - 1; index >= 0; index--) {
			result += parseSubscript(string.charAt(index)) * magnitude;
			magnitude *= 10;
		}
		return result;
	}

	/**
	Parses a string as the microtonal part of a note.

	@param string The string.
	@return The contribution in the composition of a note in microtones.
	**/
	private static int parseSuperscripts(final java.lang.String string) throws NumberFormatException {
		int result = 0;
		int magnitude = 1;
		for (int index = string.length() - 1; index >= 0; index--) {
			result += parseSuperscript(string.charAt(index)) * magnitude;
			magnitude *= 10;
		}
		return result;
	}

	/**
	Parses a character as the microtonal division part of a note.

	@param character The character.
	@return Whether the division is normal and not inverted.
	**/
	private static boolean parseDivision(final char character) throws NumberFormatException {
		switch (character) {
		case '/': case '\u00f7': case '\u2044': case '\u2215': return true;
		case '\\': return false;
		default: throw new NumberFormatException();
		}
	}

	/**
	Parses a string as a note.

	@param string The string.
	@return The note.
	@throws NumberFormatException If the string couldn't be parsed.
	**/
	public static Note parse(final java.lang.String string) throws NumberFormatException {//TODO outsource
		final Matcher matcher = LENIENT_PATTERN.matcher(string);
		if (!matcher.matches()) throw new NumberFormatException();

		final int letter = parseLetter(matcher.group(1).charAt(0));

		final int accidental;
		if (matcher.group(2).isEmpty()) accidental = 0;
		else accidental = parseAccidental(matcher.group(2).charAt(0));

		final int octaveSign;
		if (matcher.group(3).isEmpty()) octaveSign = 1;
		else octaveSign = parseSubscriptSign(matcher.group(3).charAt(0));

		final int octave = octaveSign * parseSubscripts(matcher.group(4));

		final BigInteger semitones = BigInteger.valueOf(letter + accidental + 12 * octave);//TODO notes in octave

		if (matcher.group(6) == null || matcher.group(6).isEmpty()) return new Note(semitones);

		final int dividendSign;
		if (matcher.group(5).isEmpty()) dividendSign = 1;
		else dividendSign = parseSuperscriptSign(matcher.group(5).charAt(0));

		final int dividend = dividendSign * parseSubscripts(matcher.group(6));

		BigFraction microtones = new BigFraction(dividend);

		if (matcher.group(9) == null || matcher.group(9).isEmpty()) return new Note(semitones, microtones);

		final boolean division = parseDivision(matcher.group(7).charAt(0));

		final int divisorSign;
		if (matcher.group(8).isEmpty()) divisorSign = 1;
		else divisorSign = parseSuperscriptSign(matcher.group(8).charAt(0));

		final int divisor = divisorSign * parseSuperscripts(matcher.group(9));

		microtones = new BigFraction(dividend, divisor);

		if (!division) microtones = microtones.reciprocal();

		return new Note(semitones, microtones);
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
	Returns a transposition of this note.

	@param semitones The amount of semitones to transpose.
	@param microtones The amount of microtones to transpose.
	@return The transposition.
	**/
	public Note transpose(final BigInteger semitones, final BigFraction microtones) {
		return new Note(this.semitones.add(semitones), this.microtones.add(microtones));
	}

	/**
	Returns a transposition of this note.

	@param semitones The amount of semitones to transpose.
	@return The transposition.
	**/
	public Note transpose(final BigInteger semitones) {
		return new Note(this.semitones.add(semitones), microtones);
	}

	/**
	Returns a transposition of this note.

	@param microtones The amount of microtones to transpose.
	@return The transposition.
	**/
	public Note transpose(final BigFraction microtones) {
		return new Note(semitones, this.microtones.add(microtones));
	}

	/**
	Returns a transposition of this note.

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

	/**
	@return The semitones.
	**/
	public BigInteger getSemitones() {
		return semitones;
	}

	/**
	@return The microtones.
	**/
	public BigFraction getMicrotones() {
		return microtones;
	}

	/**
	@return The semitones and microtones together.
	**/
	public BigFraction getTones() {
		if (tones == null) tones = new BigFraction(semitones).add(microtones);
		return tones;
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

	public java.lang.String toFullString() {
		final Object[][] objects = {
			{"semitones", semitones},
			{"microtones", microtones}
		};
		final java.lang.String fields = StringFormatter.formatFields(objects);
		return "note (" + fields + ")";
	}

	@Override
	public java.lang.String toString() {
		return "note";//TODO Formatter.format(this);
	}

	@Override
	public int compareTo(final Note note) {
		final int comparison = semitones.compareTo(note.semitones);
		if (comparison == 0) {
			return microtones.compareTo(note.microtones);
		}
		return semitones.compareTo(note.semitones);
	}
}
