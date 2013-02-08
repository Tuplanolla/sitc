package org.sitdb.util;

import java.io.Serializable;

/**
Represents an inexact immutable transfinite real.
The name <code>Real</code> was chosen solely to
 avoid confusion with <code>java.lang.Float</code> and
 doesn't imply that the numbers are exact.

@see Scalar
@author Sampsa "Tuplanolla" Kiiskinen
**/
public class Real implements Scalar<Real>, Comparable<Real>, Serializable {
	private static final long serialVersionUID = 4580365846004085424l;

	/**
	The smallest finite real <i>2<sup>104</sup>-2<sup>128</sup></i> accepted by the constructor.
	**/
	public static final float MIN_PARAM_VALUE = -Float.MAX_VALUE;

	/**
	The biggest finite real <i>2<sup>128</sup>-2<sup>104</sup></i> accepted by the constructor.
	**/
	public static final float MAX_PARAM_VALUE = Float.MAX_VALUE;

	/**
	The real representing <i>0</i>.
	**/
	public static final Real ZERO = new Real(0);

	/**
	The real representing <i>1</i>.
	**/
	public static final Real ONE = new Real(1);

	/**
	The smallest finite real <i>2<sup>104</sup>-2<sup>128</sup></i>.
	**/
	public static final Real MIN_VALUE = new Real(MIN_PARAM_VALUE);

	/**
	The biggest finite real <i>2<sup>128</sup>-2<sup>104</sup></i>.
	**/
	public static final Real MAX_VALUE = new Real(MAX_PARAM_VALUE);

	/**
	The smallest infinite real <i>-&infin;</i>.
	**/
	public static final Real NEGATIVE_INFINITY = new Real(Float.NEGATIVE_INFINITY);

	/**
	The biggest infinite real <i>&infin;</i>.
	**/
	public static final Real POSITIVE_INFINITY = new Real(Float.POSITIVE_INFINITY);

	/**
	An indeterminate real <i>NaN</i>.
	**/
	public static final Real NaN = new Real(Float.NaN);

	/**
	The raw value of the real.

	The smallest value is reserved for <i>NaN</i>, the second smallest value for <i>-&infin;</i> and the biggest value for <i>&infin;</i>.

	@serial
	**/
	private final float value;

	/**
	Constructs a new real from a primitive float.

	@param value The value of the new real.
	**/
	private Real(final float value) {
		this.value = value;
	}

	/**
	Returns a new real constructed from a primitive float.

	@param value The value of the new real.
	@return The new real.
	**/
	public static Real valueOf(final float value) {
		if (value == 0) return ZERO;
		if (Double.isNaN(value)) return NaN;
		if (value == Double.NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (value == Double.POSITIVE_INFINITY) return POSITIVE_INFINITY;
		return new Real(value);
	}

	/**
	Returns the raw value of this real.

	If this real is indeterminate or infinite anything may be returned.

	@return The value.
	@see Real#doubleValue()
	**/
	public double get() {
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
	public Real opposite() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return POSITIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return NEGATIVE_INFINITY;
		return valueOf(-value);
	}

	@Override
	public Real reciprocal() {
		if (this == NaN) return NaN;
		if (this == ZERO) return POSITIVE_INFINITY;//loses the sign
		if (this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY) return ZERO;
		if (Math.abs(value) > 1) return ZERO;
		return ONE;
	}

	@Override
	public Real add(final Real real) {
		if (this == NaN || real == NaN) return NaN;
		if (this == ZERO) return real;
		if (this == NEGATIVE_INFINITY) {
			if (real == POSITIVE_INFINITY) return NaN;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (real == NEGATIVE_INFINITY) return NaN;
			return POSITIVE_INFINITY;
		}
		if (real == ZERO) return this;
		if (real == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (real == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		final double value = (double )this.value + (double )real.value;
		if (value < MIN_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (value > MAX_PARAM_VALUE) return POSITIVE_INFINITY;
		return valueOf((float )value);
	}

	@Override
	public Real subtract(final Real real) {
		return add(real.opposite());
	}

	@Override
	public Real multiply(final Real real) {
		if (this == NaN || real == NaN) return NaN;
		if (this == ZERO) {
			if (real == NEGATIVE_INFINITY || real == POSITIVE_INFINITY) return NaN;
			return ZERO;
		}
		if (this == NEGATIVE_INFINITY) {
			if (real == ZERO) return NaN;
			if (real.value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (real == ZERO) return NaN;
			if (real.value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (real == ZERO) return ZERO;
		if (real == NEGATIVE_INFINITY) {
			if (value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (real == POSITIVE_INFINITY) {
			if (value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		final double value = (double )this.value * (double )real.value;
		if (value < MIN_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (value > MAX_PARAM_VALUE) return POSITIVE_INFINITY;
		return valueOf((float )value);
	}

	@Override
	public Real divide(final Real real) {
		if (this == NaN || real == NaN) return NaN;
		if (this == ZERO) {
			if (real == ZERO) return NaN;
			return ZERO;
		}
		if (this == NEGATIVE_INFINITY) {
			if (real == NEGATIVE_INFINITY || real == POSITIVE_INFINITY) return NaN;
			if (real.value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (real == NEGATIVE_INFINITY || real == POSITIVE_INFINITY) return NaN;
			if (real.value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (real == ZERO) {
			if (value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (real == NEGATIVE_INFINITY) {
			if (value < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (real == POSITIVE_INFINITY) {
			if (value < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		final float value = this.value / real.value;
		return valueOf(value);
	}

	@Override
	public Real abs() {
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
	public Real sqrt() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		if (value < 0) throw new ArithmeticException("Can't convert a real negative number to a complex number.");
		final int value = (int )Math.sqrt(this.value);
		return valueOf(value);
	}

	@Override
	public Real square() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		final int minParamValue = -(int )Math.sqrt(Math.abs(MIN_PARAM_VALUE));
		final int maxParamValue = (int )Math.sqrt(Math.abs(MIN_PARAM_VALUE));
		if (value < minParamValue) return NEGATIVE_INFINITY;
		if (value > maxParamValue) return POSITIVE_INFINITY;
		final double value = this.value * this.value;
		return valueOf((float )value);
	}

	@Override
	public long longValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a real not-a-number to a primitive long.");
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a real negative infinity to a primitive long.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a real positive infinity to a primitive long.");
		return (long )value;
	}

	@Override
	public int intValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a real not-a-number to a primitive integer.");
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a real negative infinity to a primitive integer.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a real positive infinity to a primitive integer.");
		return (int )value;
	}

	@Override
	public short shortValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a real not-a-number to a primitive short.");
		if (this == ZERO) return (short )0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a real negative infinity to a primitive short.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a real positive infinity to a primitive short.");
		return (short )value;
	}

	@Override
	public byte byteValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a real not-a-number to a primitive byte.");
		if (this == ZERO) return (byte )0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a real negative infinity to a primitive byte.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a real positive infinity to a primitive byte.");
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
	Compares this real to another real.

	@param real The other real.
	@return <i>-1</i> if the this real is smaller than the other real, <i>+1</i> if this real is bigger than the other real and <i>0</i> otherwise.
	@throws NullPointerException If the other real is <code>null</code>.
	@throws ArithmeticException If either of the reals is indeterminate.
	**/
	@Override
	public int compareTo(final Real real) {
		if (real == this) return 0;
		if (real == null) throw new NullPointerException("Can't compare a real number to null.");
		if (this == NaN || real == NaN) throw new ArithmeticException("Can't compare a real number to a real not-a-number.");
		if (this == NEGATIVE_INFINITY) {
			if (real == NEGATIVE_INFINITY) return 0;
			return -1;
		}
		if (this == POSITIVE_INFINITY) {
			if (real == POSITIVE_INFINITY) return 0;
			return +1;
		}
		if (value > real.value) return +1;
		if (value < real.value) return -1;
		return 0;
	}

	/**
	Equates this real to an object.

	@param object The object.
	@return True if this real is equal to the object and false otherwise.
	@throws NullPointerException If the object is <code>null</code>.
	**/
	@Override
	public boolean equals(final Object object) {
		if (object == this) return true;
		if (object == null) throw new NullPointerException("Can't equate a real number with null.");
		if (object.getClass() != this.getClass()) return false;
		final Real real = (Real )object;
		return real.value == value;
	}

	/**
	Returns the hash code of this real.

	@return The hash code.
	**/
	@Override
	public int hashCode() {
		final long result = Double.doubleToRawLongBits(value);
		return (int )(result ^ result >>> 32);
	}

	/**
	Returns the string representation of this real.

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
		assert valueOf(MIN_PARAM_VALUE).subtract(valueOf(Math.ulp(MIN_PARAM_VALUE))) == NEGATIVE_INFINITY : "Failed to overflow.";
		assert valueOf(2).add(NaN).multiply(valueOf(3)).equals(NaN) : "Failed to operate on a not-a-number.";
		assert valueOf(2).add(POSITIVE_INFINITY).multiply(valueOf(3)).equals(POSITIVE_INFINITY) : "Failed to operate on an infinite number.";
		assert valueOf(-2).intValue() == -2 : "Failed to convert to a primitive integer.";
	}
}
