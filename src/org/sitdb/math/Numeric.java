package org.sitdb.math;

/**
Outlines basic conversions to numeric types and is implemented by
 all numeric classes and
 their containers.

@see Natural
@see Rational
@author Sampsa "Tuplanolla" Kiiskinen
**/
public interface Numeric {
	/**
	Returns the value of this number as a long integer primitive.

	@return The converted number.
	**/
	public long longValue();

	/**
	Returns the value of this number as an integer primitive.

	@return The converted number.
	**/
	public int intValue();

	/**
	Returns the value of this number as a short integer primitive.

	@return The converted number.
	**/
	public short shortValue();

	/**
	Returns the value of this number as a byte primitive.

	@return The converted number.
	**/
	public byte byteValue();

	/**
	Returns the value of this number as a double precision floating-point primitive.

	@return The converted number.
	**/
	public double doubleValue();

	/**
	Returns the value of this number as a floating-point primitive.

	@return The converted number.
	**/
	public float floatValue();
}
