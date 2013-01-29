package org.sitdb.util;

import java.io.Serializable;

/**
This class represents an exact immutable transfinite rational.

@see Scalar
@author Sampsa "Tuplanolla" Kiiskinen
**/
public class Rational implements Scalar<Rational>, Comparable<Rational>, Serializable {
	private static final long serialVersionUID = 1296295622916634130l;

	/**
	The smallest finite dividend or divisor <i>-2<sup>31</sup>+1</i>.
	**/
	public static final int MIN_PARAM_VALUE = -Integer.MAX_VALUE;

	/**
	The biggest finite dividend or divisor <i>2<sup>31</sup>-1</i>.
	**/
	public static final int MAX_PARAM_VALUE = Integer.MAX_VALUE;

	/**
	The rational representing <i>0</i>.
	**/
	public static final Rational ZERO = new Rational(0);

	/**
	The rational representing <i>1</i>.
	**/
	public static final Rational ONE = new Rational(1);

	/**
	The smallest finite rational <i>-2<sup>31</sup>+1</i>.
	**/
	public static final Rational MIN_VALUE = new Rational(MIN_PARAM_VALUE);

	/**
	The biggest finite rational <i>2<sup>31</sup>-1</i>.
	**/
	public static final Rational MAX_VALUE = new Rational(MAX_PARAM_VALUE);

	/**
	The smallest infinite rational <i>-&infin;</i>.
	**/
	public static final Rational NEGATIVE_INFINITY = new Rational(-1, 0);

	/**
	The biggest infinite rational <i>&infin;</i>.
	**/
	public static final Rational POSITIVE_INFINITY = new Rational(1, 0);

	/**
	An indeterminate rational <i>NaN</i>.
	**/
	public static final Rational NaN = new Rational(0, 0);

	/**
	The raw numerator of the rational.

	@serial
	**/
	private final int dividend;

	/**
	The raw denominator of the rational.

	@serial
	**/
	private final int divisor;

	/**
	Returns the greatest common divisor of two primitive integers.

	The integers must lie between <i>-2<sup>31</sup>+1</i> and <i>2<sup>31</sup>-1</i>.

	@param first The first integer.
	@param second The second integer.
	@return The greatest common divisor.
	**/
	public static int gcd(int first, int second) {
		if (first == 0 || second == 0) return first | second;//the same as Math.max(first, second)
		if (first == 1 || second == 1) return 1;//the same as Math.min(Math.abs(first), Math.abs(second))
		if (first < 0) first = -first;//the same as Math.abs(first)
		if (second < 0) second = -second;//the same as Math.abs(second)
		int power;//holds the greatest power of two dividing both of the numbers
		for (power = 0; (first & 1) == 0 && (second & 1) == 0; power++) {
			first >>= 1;//the same as first / 2
			second >>= 1;
		}
		int either;//holds the index of the even number in the sign bit
		if ((first & 1) == 0) either = first >> 1;
		else either = -second;
		do {//converted from a recursive algorithm
			while ((either & 1) == 0)
				either >>= 1;
			if (either > 0) first = either;
			else second = -either;
			either = first - second >> 1;
		}
		while (either != 0);
		return first * (1 << power);//the same as Math.abs(first) * Math.pow(2, power)
	}

	/**
	Constructs a new rational from two primitive integers.

	@param dividend The dividend of the new rational.
	@param divisor The divisor of the new rational.
	**/
	private Rational(final int dividend, final int divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}

	/**
	Constructs a new rational from a primitive integer.

	@param dividend The value of the new rational.
	**/
	private Rational(final int dividend) {
		this.dividend = dividend;
		divisor = 1;
	}

	/**
	Returns a new rational constructed from two primitive integers.

	@param dividend The dividend of the new rational.
	@param divisor The divisor of the new rational.
	@return The new rational.
	**/
	public static Rational valueOf(int dividend, int divisor) {
		if (divisor == 0) {
			if (dividend < 0) return NEGATIVE_INFINITY;
			if (dividend > 0) return POSITIVE_INFINITY;
			return NaN;
		}
		if (dividend < MIN_PARAM_VALUE) {
			if (divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return NaN;
			if (divisor < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (dividend > MAX_PARAM_VALUE) {
			if (divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return NaN;
			if (divisor < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (dividend < MIN_PARAM_VALUE || dividend > MAX_PARAM_VALUE) return NEGATIVE_INFINITY;
		if (dividend == 0 || divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return ZERO;
		if (divisor < 0) {
			dividend = -dividend;
			divisor = -divisor;
		}
		final int gcd = gcd(dividend, divisor);
		return new Rational(dividend / gcd, divisor / gcd);
	}

	/**
	Returns a new rational constructed from a primitive integer.

	@param dividend The value of the new rational.
	@return The new rational.
	**/
	public static Rational valueOf(final int dividend) {
		return valueOf(dividend, 1);
	}

	/**
	Returns the dividend of this rational.

	@return The dividend.
	**/
	public int getDividend() {
		return dividend;
	}

	/**
	Returns the divisor of this rational.

	@return The divisor.
	**/
	public int getDivisor() {
		return divisor;
	}

	/**
	Returns the dividend and the divisor of this rational.

	@return The dividend and the divisor.
	**/
	public int[] get() {
		return new int[] {dividend, divisor};
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
	public Rational opposite() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return POSITIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return NEGATIVE_INFINITY;
		return valueOf(-dividend, divisor);
	}

	@Override
	public Rational reciprocal() {
		if (this == NaN) return NaN;
		if (this == ZERO) return POSITIVE_INFINITY;//loses the sign
		if (this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY) return ZERO;
		if (dividend == divisor) return this;
		return valueOf(divisor, dividend);
	}

	@Override
	public Rational add(final Rational rational) {
		if (this == NaN || rational == NaN) return NaN;
		if (this == ZERO) return rational;
		if (this == NEGATIVE_INFINITY) {
			if (rational == POSITIVE_INFINITY) return NaN;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (rational == NEGATIVE_INFINITY) return NaN;
			return POSITIVE_INFINITY;
		}
		if (rational == ZERO) return this;
		if (rational == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (rational == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		final int gcd = gcd(divisor, rational.divisor);
		final int firstNewDivisor = divisor / gcd;
		final int secondNewDivisor = rational.divisor / gcd;
		final long dividend = (long )this.dividend
				* (long )secondNewDivisor
				+ (long )rational.dividend
				* (long )firstNewDivisor;
		final long divisor = (long )this.divisor * (long )secondNewDivisor;
		if (dividend < MIN_PARAM_VALUE) {
			if (divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return NaN;
			if (divisor < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (dividend > MAX_PARAM_VALUE) {
			if (divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return NaN;
			if (divisor < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		return valueOf((int )dividend, (int )divisor);
	}

	@Override
	public Rational subtract(final Rational rational) {
		return add(rational.opposite());
	}

	@Override
	public Rational multiply(final Rational rational) {
		if (this == NaN || rational == NaN) return NaN;
		if (this == ZERO) {
			if (rational == NEGATIVE_INFINITY || rational == POSITIVE_INFINITY) return NaN;
			return ZERO;
		}
		if (this == NEGATIVE_INFINITY) {
			if (rational == ZERO) return NaN;
			if (rational.dividend < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (this == POSITIVE_INFINITY) {
			if (rational == ZERO) return NaN;
			if (rational.dividend < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		if (rational == ZERO) return ZERO;
		if (rational == NEGATIVE_INFINITY) {
			if (dividend < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (rational == POSITIVE_INFINITY) {
			if (dividend < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		final long dividend = (long )this.dividend * (long )rational.dividend;
		final long divisor = (long )this.divisor * (long )rational.divisor;
		if (dividend < MIN_PARAM_VALUE) {
			if (divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return NaN;
			if (divisor < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (dividend > MAX_PARAM_VALUE) {
			if (divisor < MIN_PARAM_VALUE || divisor > MAX_PARAM_VALUE) return NaN;
			if (divisor < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		return valueOf((int )dividend, (int )divisor);
	}

	@Override
	public Rational divide(final Rational rational) {
		return multiply(rational.reciprocal());
	}

	@Override
	public Rational abs() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY || this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		if (dividend < 0) return valueOf(-dividend, divisor);
		return this;
	}

	@Override
	public int signum() {
		if (this == NaN || this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY || dividend < 0) return -1;
		if (this == POSITIVE_INFINITY || dividend > 0) return +1;
		return 0;
	}

	@Override
	public Rational sqrt() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		if (dividend < 0) throw new ArithmeticException("Can't convert a rational negative number to a complex number.");
		final int dividend = (int )Math.sqrt(this.dividend);
		final int divisor = (int )Math.sqrt(this.divisor);
		return valueOf(dividend, divisor);
	}

	@Override
	public Rational square() {
		if (this == NaN) return NaN;
		if (this == ZERO) return ZERO;
		if (this == NEGATIVE_INFINITY) return NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return POSITIVE_INFINITY;
		final int minParamValue = -(int )Math.sqrt(Math.abs(MIN_PARAM_VALUE));
		final int maxParamValue = (int )Math.sqrt(Math.abs(MIN_PARAM_VALUE));
		if (dividend < minParamValue) {
			if (divisor < minParamValue || divisor > maxParamValue) return NaN;
			if (divisor < 0) return POSITIVE_INFINITY;
			return NEGATIVE_INFINITY;
		}
		if (dividend > maxParamValue) {
			if (divisor < minParamValue || divisor > maxParamValue) return NaN;
			if (divisor < 0) return NEGATIVE_INFINITY;
			return POSITIVE_INFINITY;
		}
		final int dividend = this.dividend * this.dividend;
		final int divisor = this.divisor * this.divisor;
		return valueOf(dividend, divisor);
	}

	@Override
	public long longValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a rational not-a-number to a primitive long.");
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a rational negative infinity to a primitive long.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a rational positive infinity to a primitive long.");
		return dividend / divisor;
	}

	@Override
	public int intValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a rational not-a-number to a primitive integer.");
		if (this == ZERO) return 0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a rational negative infinity to a primitive integer.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a rational positive infinity to a primitive integer.");
		return dividend / divisor;
	}

	@Override
	public short shortValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a rational not-a-number to a primitive short.");
		if (this == ZERO) return (short )0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a rational negative infinity to a primitive short.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a rational positive infinity to a primitive short.");
		return (short )(dividend / divisor);
	}

	@Override
	public byte byteValue() {
		if (this == NaN) throw new ArithmeticException("Can't convert a rational not-a-number to a primitive byte.");
		if (this == ZERO) return (byte )0;
		if (this == NEGATIVE_INFINITY) throw new ArithmeticException("Can't convert a rational negative infinity to a primitive byte.");
		if (this == POSITIVE_INFINITY) throw new ArithmeticException("Can't convert a rational positive infinity to a primitive byte.");
		return (byte )(dividend / divisor);
	}

	@Override
	public double doubleValue() {
		if (this == NaN) return Double.NaN;
		if (this == ZERO) return 0.0;
		if (this == NEGATIVE_INFINITY) return Double.NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
		return (double )dividend / (double )divisor;
	}

	@Override
	public float floatValue() {
		if (this == NaN) return Float.NaN;
		if (this == ZERO) return 0.0f;
		if (this == NEGATIVE_INFINITY) return Float.NEGATIVE_INFINITY;
		if (this == POSITIVE_INFINITY) return Float.POSITIVE_INFINITY;
		return (float )dividend / (float )divisor;
	}

	/**
	Compares this rational to another rational.

	@param rational The other rational.
	@return <i>-1</i> if the this rational is smaller than the other rational, <i>+1</i> if this rational is bigger than the other rational and <i>0</i> otherwise.
	@throws NullPointerException If the other rational is <code>null</code>.
	@throws ArithmeticException If either of the rationals is indeterminate.
	@keywords comparator
	**/
	@Override
	public int compareTo(final Rational rational) {
		if (rational == this) return 0;
		if (rational == null) throw new NullPointerException("Can't compare a rational to null.");
		if (this == NaN || rational == NaN) throw new ArithmeticException("Can't compare a rational to a rational not-a-number.");
		if (this == NEGATIVE_INFINITY) {
			if (rational == NEGATIVE_INFINITY) return 0;
			return -1;
		}
		if (this == POSITIVE_INFINITY) {
			if (rational == POSITIVE_INFINITY) return 0;
			return +1;
		}
		if (dividend == rational.dividend && divisor == rational.divisor) return 0;
		final int first = dividend * rational.divisor;
		final int second = rational.dividend * divisor;
		if (first > second) return +1;//this > rational
		if (first < second) return -1;//this < rational
		return 0;
	}

	/**
	Equates this rational to an object.

	@param object The object.
	@return True if this rational is equal to the object and false otherwise.
	@throws NullPointerException If the object is <code>null</code>.
	**/
	@Override
	public boolean equals(final Object object) {
		if (object == this) return true;
		if (object == null) throw new NullPointerException("Can't equate a rational with null.");
		if (object.getClass() != this.getClass()) return false;
		final Rational rational = (Rational )object;
		return rational.dividend == dividend && rational.divisor == divisor;
	}

	/**
	Returns the hash code of this rational.

	@return The hash code.
	**/
	@Override
	public int hashCode() {
		return dividend + 31 * divisor;
	}

	/**
	Returns the string representation of this rational.

	@return The string.
	**/
	@Override
	public String toString() {
		if (this == NaN) return String.valueOf(Double.NaN);
		if (this == ZERO) return String.valueOf(0);
		if (this == NEGATIVE_INFINITY) return String.valueOf(Double.NEGATIVE_INFINITY);
		if (this == POSITIVE_INFINITY) return String.valueOf(Double.POSITIVE_INFINITY);
		if (divisor == 1) return String.valueOf(dividend);
		return dividend + "/" + divisor;
	}

	/**
	Tests the class.

	@param arguments Command line arguments are ignored.
	@throws AssertionError If the tests fail.
	**/
	public static void main(final String[] arguments) {
		assert valueOf(2, 3).equals(valueOf(2, 3)) : "Failed to equate.";
		assert valueOf(2, 3).compareTo(valueOf(4, 5)) == -1 : "Failed to compare.";
		assert valueOf(0, 0) == NaN : "Failed to construct a not-a-number.";
		assert valueOf(2, 0) == POSITIVE_INFINITY : "Failed to construct an infinite number.";
		assert valueOf(2, 4).equals(valueOf(1, 2)) : "Failed to simplify.";
		assert valueOf(2, -3).equals(valueOf(-2, 3)) && valueOf(-2, -3).equals(valueOf(2, 3)) : "Failed to propagate the sign.";
		assert valueOf(2, 3).opposite().equals(valueOf(-2, 3)) && valueOf(2, 3).reciprocal().equals(valueOf(3, 2)) : "Failed to invert.";
		assert valueOf(2, 3).add(valueOf(4, 5)).equals(valueOf(22, 15)) : "Failed to add.";
		assert valueOf(2, 3).subtract(valueOf(4, 5)).equals(valueOf(-2, 15)) : "Failed to subtract.";
		assert valueOf(2, 3).multiply(valueOf(4, 5)).equals(valueOf(8, 15)) : "Failed to multiply.";
		assert valueOf(2, 3).divide(valueOf(4, 5)).equals(valueOf(5, 6)) : "Failed to divide.";
		assert valueOf(-2, 3).abs().equals(valueOf(2, 3)) : "Failed to find the absolute value.";
		assert valueOf(-2, 3).signum() == -1 && valueOf(2, 3).signum() == +1 : "Failed to find the sign.";
		assert valueOf(4, 9).sqrt().equals(valueOf(2, 3)) : "Failed to find the square root.";
		assert valueOf(-2, 3).square().equals(valueOf(4, 9)) : "Failed to find the square.";
		assert valueOf(MIN_PARAM_VALUE, 1).subtract(valueOf(1, 1)) == NEGATIVE_INFINITY : "Failed to overflow.";
		assert valueOf(2, 3).add(NaN).multiply(valueOf(4, 5)).equals(NaN) : "Failed to operate on a not-a-number.";
		assert valueOf(2, 3).add(POSITIVE_INFINITY).multiply(valueOf(4, 5)).equals(POSITIVE_INFINITY) : "Failed to operate on an infinite number.";
		assert valueOf(-2, 1).intValue() == -2 : "Failed to convert to a primitive integer.";
	}
}
