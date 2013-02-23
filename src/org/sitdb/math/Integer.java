package org.sitdb.math;

import java.io.Serializable;

/**
Represents an exact immutable transfinite integer.

@see Scalar
@author Sampsa "Tuplanolla" Kiiskinen
**/
public class Integer implements Numeric, Scalar<Integer>, Comparable<Integer>, Serializable {
	private static final long serialVersionUID = 1l;

	/**
	The smallest finite integer <i>-2<sup>31</sup>+2</i> accepted by the constructor.
	**/
	public static final int MIN_PARAM_VALUE = -java.lang.Integer.MAX_VALUE + 1;

	/**
	The biggest finite integer <i>2<sup>31</sup>-1</i> accepted by the constructor.
	**/
	public static final int MAX_PARAM_VALUE = java.lang.Integer.MAX_VALUE - 1;

	/**
	The integer representing <i>0</i>.
	**/
	public static final Integer ZERO = new Integer(0);

	/**
	The integer representing <i>1</i>.
	**/
	public static final Integer ONE = new Integer(1);

	/**
	The integer representing <i>-1</i>.
	**/
	public static final Integer MINUS_ONE = new Integer(-1);

	/**
	The smallest finite integer <i>-2<sup>31</sup>+2</i>.
	**/
	public static final Integer MIN_VALUE = new Integer(MIN_PARAM_VALUE);

	/**
	The biggest finite integer <i>2<sup>31</sup>-1</i>.
	**/
	public static final Integer MAX_VALUE = new Integer(MAX_PARAM_VALUE);

	/**
	The smallest infinite integer <i>-&infin;</i>.
	**/
	public static final Integer NEGATIVE_INFINITY = new Integer(MIN_PARAM_VALUE - 1);

	/**
	The biggest infinite integer <i>&infin;</i>.
	**/
	public static final Integer POSITIVE_INFINITY = new Integer(MAX_PARAM_VALUE + 1);

	/**
	An indeterminate integer <i>NaN</i>.
	**/
	public static final Integer NaN = new Integer(MIN_PARAM_VALUE - 2);

	/**
	The raw value of the integer.

	The smallest value is reserved for <i>NaN</i>, the second smallest value for <i>-&infin;</i> and the biggest value for <i>&infin;</i>.
	**/
	private final int value;

	/**
	Creates a integer from a primitive integer.

	@param value The value of the new integer.
	**/
	private Integer(final int value) {
		this.value = value;
	}

	/**
	Returns a new integer constructed from a primitive integer.

	@param value The value of the new integer.
	@return The new integer.
	**/
	public static Integer valueOf(final int value) {
		if (value == 0) return ZERO;
		if (value < MIN_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (value > MAX_PARAM_VALUE) return POSITIVE_INFINITY;
		return new Integer(value);
	}

	/**
	Returns the raw value of this integer.

	If this integer is indeterminate or infinite anything may be returned.

	@return The value.
	@see #intValue()
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
	public Integer opposite() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return POSITIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return NEGATIVE_INFINITY;
		return valueOf(-value);
	}

	@Override
	public Integer reciprocal() {
		if (this == NaN) return NaN;
		if (this == ZERO) return POSITIVE_INFINITY;//loses the sign
		if (this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY) return ZERO;
		if (Math.abs(value) > 1) return ZERO;
		return ONE;
	}

	@Override
	public Integer add(final Integer natural) {
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
	public Integer subtract(final Integer natural) {
		return add(natural.opposite());
	}

	@Override
	public Integer multiply(final Integer natural) {
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
	public Integer divide(final Integer natural) {
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
	public Integer abs() {
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
	public Integer sqrt() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		if (value < 0) throw new ArithmeticException("Can't convert a natural negative number to a complex number.");
		final int value = (int )Math.sqrt(this.value);
		return valueOf(value);
	}

	@Override
	public Integer square() {
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
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) return Double.NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
		return value;
	}

	@Override
	public float floatValue() {
		if (this == NaN) return Float.NaN;
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) return Float.NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return Float.POSITIVE_INFINITY;
		return value;
	}

	@Override
	public int compareTo(final Integer natural) {
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

	@Override
	public boolean equals(final Object object) {
		if (object == this) return true;
		if (object == null) throw new NullPointerException("Can't equate a natural number with null.");
		if (object.getClass() != this.getClass()) return false;
		final Integer natural = (Integer )object;
		return natural.value == value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public String toString() {
		if (this == NaN) return String.valueOf(Double.NaN);
		if (this == NEGATIVE_INFINITY) return String.valueOf(Double.NEGATIVE_INFINITY);
		if (this == POSITIVE_INFINITY) return String.valueOf(Double.POSITIVE_INFINITY);
		return String.valueOf(value);
	}
}
