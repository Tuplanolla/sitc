package org.sitdb.util;

import java.io.Serializable;

/**
Represents an exact immutable transfinite integer.

The name <code>Natural</code> was chosen solely to
 avoid confusion with <code>java.lang.Integer</code> and
 doesn't imply that the numbers can't be negative.

@see Scalar
@author Sampsa "Tuplanolla" Kiiskinen
**/
public class Natural implements Numeric, Scalar<Natural>, Comparable<Natural>, Serializable {
	private static final long serialVersionUID = 5889084946555354348l;

	/**
	The smallest finite integer <i>-2<sup>31</sup>+2</i> accepted by the constructor.
	**/
	public static final int MIN_PARAM_VALUE = -Integer.MAX_VALUE + 1;

	/**
	The biggest finite integer <i>2<sup>31</sup>-1</i> accepted by the constructor.
	**/
	public static final int MAX_PARAM_VALUE = Integer.MAX_VALUE - 1;

	/**
	The integer representing <i>0</i>.
	**/
	public static final Natural ZERO = new Natural(0);

	/**
	The integer representing <i>1</i>.
	**/
	public static final Natural ONE = new Natural(1);

	/**
	The smallest finite integer <i>-2<sup>31</sup>+2</i>.
	**/
	public static final Natural MIN_VALUE = new Natural(MIN_PARAM_VALUE);

	/**
	The biggest finite integer <i>2<sup>31</sup>-1</i>.
	**/
	public static final Natural MAX_VALUE = new Natural(MAX_PARAM_VALUE);

	/**
	The smallest infinite integer <i>-&infin;</i>.
	**/
	public static final Natural NEGATIVE_INFINITY = new Natural(MIN_PARAM_VALUE - 1);

	/**
	The biggest infinite integer <i>&infin;</i>.
	**/
	public static final Natural POSITIVE_INFINITY = new Natural(MAX_PARAM_VALUE + 1);

	/**
	An indeterminate integer <i>NaN</i>.
	**/
	public static final Natural NaN = new Natural(MIN_PARAM_VALUE - 2);

	/**
	The raw value of the integer.

	The smallest value is reserved for <i>NaN</i>, the second smallest value for <i>-&infin;</i> and the biggest value for <i>&infin;</i>.

	@serial
	**/
	private final int value;

	/**
	Constructs a new integer from a primitive integer.

	@param value The value of the new integer.
	**/
	private Natural(final int value) {
		this.value = value;
	}

	/**
	Returns a new integer constructed from a primitive integer.

	@param value The value of the new integer.
	@return The new integer.
	**/
	public static Natural valueOf(final int value) {
		if (value == 0) return ZERO;
		if (value < MIN_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (value > MAX_PARAM_VALUE) return POSITIVE_INFINITY;
		return new Natural(value);
	}

	/**
	Returns the raw value of this integer.

	If this integer is indeterminate or infinite anything may be returned.

	@return The value.
	@see Natural#intValue()
	**/
	public int get() {
		return value;
	}

	@Override
	public boolean isNaN() {
		return this == NaN;
	}

	@Override
	public boolean isInfinite() {
		return this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY;
	}

	@Override
	public Natural opposite() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return POSITIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return NEGATIVE_INFINITY;
		return valueOf(-value);
	}

	@Override
	public Natural reciprocal() {
		if (this == NaN) return NaN;
		if (this == ZERO) return POSITIVE_INFINITY;//loses the sign
		if (this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY) return ZERO;
		if (Math.abs(value) > 1) return ZERO;
		return ONE;
	}

	@Override
	public Natural add(final Natural natural) {
		if (this == NaN || natural == NaN) return NaN;
		if (this == ZERO) return natural;
		if (this == NEGATIVE_INFINITY) {
			if (natural == POSITIVE_INFINITY) return NaN;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (natural == NEGATIVE_INFINITY) return NaN;
			return POSITIVE_INFINITY;
		}
		if (natural == ZERO) return this;
		if (natural == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (natural == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		final long value = (long )this.value + (long )natural.value;
		if (value < MIN_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (value > MAX_PARAM_VALUE) return POSITIVE_INFINITY;
		return valueOf((int )value);
	}

	@Override
	public Natural subtract(final Natural natural) {
		return add(natural.opposite());
	}

	@Override
	public Natural multiply(final Natural natural) {
		if (this == NaN || natural == NaN) return NaN;
		if (this == ZERO) {
			if (natural == NEGATIVE_INFINITY || natural == POSITIVE_INFINITY) return NaN;
			return ZERO;
		}
		if (this == NEGATIVE_INFINITY) {
			if (natural == ZERO) return NaN;
			if (natural.value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (natural == ZERO) return NaN;
			if (natural.value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (natural == ZERO) return ZERO;
		if (natural == NEGATIVE_INFINITY) {
			if (value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (natural == POSITIVE_INFINITY) {
			if (value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		final long value = (long )this.value * (long )natural.value;
		if (value < MIN_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (value > MAX_PARAM_VALUE) return POSITIVE_INFINITY;
		return valueOf((int )value);
	}

	@Override
	public Natural divide(final Natural natural) {
		if (this == NaN || natural == NaN) return NaN;
		if (this == ZERO) {
			if (natural == ZERO) return NaN;
			return ZERO;
		}
		if (this == NEGATIVE_INFINITY) {
			if (natural == NEGATIVE_INFINITY || natural == POSITIVE_INFINITY) return NaN;
			if (natural.value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (natural == NEGATIVE_INFINITY || natural == POSITIVE_INFINITY) return NaN;
			if (natural.value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (natural == ZERO) {
			if (value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (natural == NEGATIVE_INFINITY) {
			if (value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (natural == POSITIVE_INFINITY) {
			if (value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		final int value = this.value / natural.value;
		return valueOf(value);
	}

	@Override
	public Natural abs() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		if (value < 0) return valueOf(-value);
		return this;
	}

	@Override
	public int signum() {
		if (this == NaN || this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY || value < 0) return -1;
		if (this == POSITIVE_INFINITY || value > 0) return +1;
		return 0;
	}

	@Override
	public Natural sqrt() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		if (value < 0) throw new ArithmeticException("Can't convert a natural negative number to a complex number.");
		final int value = (int )Math.sqrt(this.value);
		return valueOf(value);
	}

	@Override
	public Natural square() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		final int minParamValue = -(int )Math.sqrt(Math.abs(MIN_PARAM_VALUE));
		final int maxParamValue = (int )Math.sqrt(Math.abs(MIN_PARAM_VALUE));
		if (value < minParamValue) return NEGATIVE_INFINITY;
		if (value > maxParamValue) return POSITIVE_INFINITY;
		final int value = this.value * this.value;
		return valueOf(value);
	}

	@Override
	public long longValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a natural not-a-number to a primitive long.");
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a natural negative infinity to a primitive long.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a natural positive infinity to a primitive long.");
		return value;
	}

	@Override
	public int intValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a natural not-a-number to a primitive integer.");
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a natural negative infinity to a primitive integer.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a natural positive infinity to a primitive integer.");
		return value;
	}

	@Override
	public short shortValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a natural not-a-number to a primitive short.");
		if (this == ZERO) return (short )0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a natural negative infinity to a primitive short.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a natural positive infinity to a primitive short.");
		return (short )value;
	}

	@Override
	public byte byteValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a natural not-a-number to a primitive byte.");
		if (this == ZERO) return (byte )0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a natural negative infinity to a primitive byte.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a natural positive infinity to a primitive byte.");
		return (byte )value;
	}

	@Override
	public double doubleValue() {
		if (this == NaN) return Double.NaN;
		if (this == ZERO) return 0.0;
		if (this == NEGATIVE_INFINITY) return Double.NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
		return value;
	}

	@Override
	public float floatValue() {
		if (this == NaN) return Float.NaN;
		if (this == ZERO) return 0.0f;
		if (this == NEGATIVE_INFINITY) return Float.NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return Float.POSITIVE_INFINITY;
		return value;
	}

	/**
	Compares this integer to another integer.

	@param natural The other integer.
	@return <i>-1</i> if the this integer is smaller than the other integer, <i>+1</i> if this integer is bigger than the other integer and <i>0</i> otherwise.
	@throws NullPointerException If the other integer is <code>null</code>.
	@throws ArithmeticException If either of the integers is indeterminate.
	**/
	@Override
	public int compareTo(final Natural natural) {
		if (natural == this) return 0;
		if (natural == null) throw new NullPointerException("Can't compare a natural number to null.");
		if (this == NaN || natural == NaN) throw new ArithmeticException("Can't compare a natural number to a natural not-a-number.");
		if (this == NEGATIVE_INFINITY) {
			if (natural == NEGATIVE_INFINITY) return 0;
			return -1;
		}
		if (this == POSITIVE_INFINITY) {
			if (natural == POSITIVE_INFINITY) return 0;
			return +1;
		}
		if (value > natural.value) return +1;
		if (value < natural.value) return -1;
		return 0;
	}

	/**
	Equates this integer to an object.

	@param object The object.
	@return True if this integer is equal to the object and false otherwise.
	@throws NullPointerException If the object is <code>null</code>.
	**/
	@Override
	public boolean equals(final Object object) {
		if (object == this) return true;
		if (object == null) throw new NullPointerException("Can't equate a natural number with null.");
		if (object.getClass() != this.getClass()) return false;
		final Natural natural = (Natural )object;
		return natural.value == value;
	}

	/**
	Returns the hash code of this integer.

	@return The hash code.
	**/
	@Override
	public int hashCode() {
		return value;
	}

	/**
	Returns the string representation of this integer.

	@return The string.
	**/
	@Override
	public String toString() {
		if (this == NaN) return String.valueOf(Double.NaN);
		if (this == NEGATIVE_INFINITY) return String.valueOf(Double.NEGATIVE_INFINITY);
		if (this == POSITIVE_INFINITY) return String.valueOf(Double.POSITIVE_INFINITY);
		return String.valueOf(value);
	}

	/**
	Tests the class.

	@param arguments Command line arguments are ignored.
	@throws AssertionError If the tests fail.
	**/
	public static void main(final String[] arguments) {
		assert valueOf(2).equals(valueOf(2)) : "Failed to equate.";
		assert valueOf(2).compareTo(valueOf(3)) == -1 : "Failed to compare.";
		assert valueOf(0).divide(valueOf(0)) == NaN : "Failed to construct a not-a-number.";
		assert valueOf(2).divide(valueOf(0)) == POSITIVE_INFINITY : "Failed to construct an infinite number.";
		assert valueOf(2).opposite().equals(valueOf(-2)) : "Failed to invert.";
		assert valueOf(2).add(valueOf(3)).equals(valueOf(5)) : "Failed to add.";
		assert valueOf(2).subtract(valueOf(3)).equals(valueOf(-1)) : "Failed to subtract.";
		assert valueOf(2).multiply(valueOf(3)).equals(valueOf(6)) : "Failed to multiply.";
		assert valueOf(4).divide(valueOf(2)).equals(valueOf(2)) : "Failed to divide.";
		assert valueOf(-2).abs().equals(valueOf(2)) : "Failed to find the absolute value.";
		assert valueOf(-2).signum() == -1 && valueOf(2).signum() == +1 : "Failed to find the sign.";
		assert valueOf(4).sqrt().equals(valueOf(2)) : "Failed to find the square root.";
		assert valueOf(-2).square().equals(valueOf(4)) : "Failed to find the square.";
		assert valueOf(MIN_PARAM_VALUE).subtract(valueOf(1)) == NEGATIVE_INFINITY : "Failed to overflow.";
		assert valueOf(2).add(NaN).multiply(valueOf(3)).equals(NaN) : "Failed to operate on a not-a-number.";
		assert valueOf(2).add(POSITIVE_INFINITY).multiply(valueOf(3)).equals(POSITIVE_INFINITY) : "Failed to operate on an infinite number.";
		assert valueOf(-2).intValue() == -2 : "Failed to convert to a primitive integer.";
	}
}
