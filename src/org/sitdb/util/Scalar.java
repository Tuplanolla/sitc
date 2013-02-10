package org.sitdb.util;

/**
Outlines basic symbolic arithmetic that is implemented by
 all numeric classes and
 their containers.

The methods never throw exceptions, but instead overflow to
 infinite and
 indeterminate
  values.

@param <Type> The type of the number.
@see Natural
@see Rational
@author Sampsa "Tuplanolla" Kiiskinen
**/
public interface Scalar<Type extends Scalar<Type>> {
	/**
	Returns whether this number is indeterminate.

	@return True if this number is <i>NaN</i> and false otherwise.
	**/
	public boolean isNaN();

	/**
	Returns whether this number is infinite.

	@return True if this number is <i>&plusmn;&infin;</i> and false otherwise.
	**/
	public boolean isInfinite();

	/**
	Returns the opposite of this number <i>x</i>.

	@return The additive inverse <i>-x</i>.
	**/
	public Type opposite();

	/**
	Returns the reciprocal of this number.

	@return The multiplicative inverse <i><sup>1</sup>&frasl;<sub>x</sub></i>.
	**/
	public Type reciprocal();

	/**
	Returns the addition of this number <i>x</i> and another number <i>y</i>.

	@param number The number <i>y</i>.
	@return The sum <i>x+y</i>.
	**/
	public Type add(final Type number);

	/**
	Returns the subtraction of this number <i>x</i> and another number <i>y</i>.

	@param number The number <i>y</i>.
	@return The sum <i>x-y</i>.
	**/
	public Type subtract(final Type number);

	/**
	Returns the multiplication of this number <i>x</i> and another number <i>y</i>.

	@param number The number <i>y</i>.
	@return The product <i>xy</i>.
	**/
	public Type multiply(final Type number);

	/**
	Returns the division of this number <i>x</i> and another number <i>y</i>.

	@param number The number <i>y</i>.
	@return The product <i><sup>x</sup>&frasl;<sub>y</sub></i>.
	**/
	public Type divide(final Type number);

	/**
	Returns the absolute value of this number <i>x</i>.

	@return The number <i>|x|</i>.
	**/
	public Type abs();

	/**
	Returns the square root of this number <i>x</i>.

	@return The number <i>&radic;<span style="text-decoration: overline;">x</span></i>.
	**/
	public Type sqrt();

	/**
	Returns the square of this number <i>x</i>.

	@return The number <i>x<sup>2</sup></i>.
	**/
	public Type square();

	/**
	Returns the sign of this number.

	@return <code>-1</code> if this is negative, <code>+1</code> if positive and <code>0</code> otherwise.
	**/
	public int signum();
}
