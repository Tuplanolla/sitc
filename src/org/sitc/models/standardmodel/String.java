package org.sitc.models.standardmodel;

import java.io.Serializable;
import java.math.BigDecimal;

import org.sitc.util.StringFormatter;

/**
Represents an immutable string of an instrument.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class String implements Serializable {
	private static final long serialVersionUID = 1;

	/**
	The vibrating length of this string.
	**/
	private BigDecimal vibratingLength;

	/**
	The linear density (mass per unit length) of this string.
	**/
	private BigDecimal linearDensity;

	/**
	The maximum tension this string can handle or <code>null</code>.
	**/
	private BigDecimal maximumTension;

	/**
	Creates a string with a tension limit.

	@param vibratingLength The vibrating length.
	@param linearDensity The linear density.
	@param maximumTension The maximum tension.
	**/
	public String(final BigDecimal vibratingLength,
			final BigDecimal linearDensity,
			final BigDecimal maximumTension) {
		this.vibratingLength = vibratingLength;
		this.linearDensity = linearDensity;
		this.maximumTension = maximumTension;
	}

	/**
	Creates a string without a tension limit.

	@param vibratingLength The vibrating length.
	@param linearDensity The linear density.
	**/
	public String(final BigDecimal vibratingLength,
			final BigDecimal linearDensity) {
		this(vibratingLength, linearDensity, null);
	}

	/**
	Creates a string with a tension limit.

	@param vibratingLength The vibrating length.
	@param mass The total mass.
	@param length The total length.
	@param maximumTension The maximum tension.
	**/
	@Deprecated//TODO separate calculator
	public String(final BigDecimal vibratingLength,
			final double mass,
			final double length,
			final BigDecimal maximumTension) {
		this(vibratingLength, BigDecimal.valueOf(mass / length), maximumTension);
	}

	/**
	Creates a string without a tension limit.

	@param vibratingLength The vibrating length.
	@param mass The total mass.
	@param length The total length.
	**/
	@Deprecated//TODO separate calculator
	public String(final BigDecimal vibratingLength,
			final double mass,
			final double length) {
		this(vibratingLength, mass, length, null);
	}

	/**
	Creates a string with a tension limit.

	@param vibratingLength The vibrating length.
	@param density The density (mass per unit volume) of the material.
	@param length The total length.
	@param diameter The mean diameter.
	@param maximumTension The maximum tension.
	**/
	@Deprecated//TODO separate calculator
	public String(final BigDecimal vibratingLength,
			final double density,
			final double length,
			final double diameter,
			final BigDecimal maximumTension) {
		this(vibratingLength, BigDecimal.valueOf(Math.PI * density * length * (diameter * diameter)));
	}

	/**
	Creates a string without a tension limit.

	@param vibratingLength The vibrating length.
	@param density The density (mass per unit volume) of the material.
	@param length The total length.
	@param diameter The mean diameter.
	**/
	@Deprecated//TODO separate calculator
	public String(final BigDecimal vibratingLength,
			final double density,
			final double length,
			final double diameter) {
		this(vibratingLength, density, length, diameter, null);
	}

	/**
	Returns the tension of this string when tuned to a certain frequency.

	@param frequency The frequency.
	@return The tension.
	**/
	public double tension(final double frequency) {
		final double velocity = 2 * vibratingLength.doubleValue() * frequency,
				velocitySquared = velocity * velocity;
		return linearDensity.doubleValue() * velocitySquared;
	}

	/**
	@return The vibratingLength.
	**/
	public BigDecimal getVibratingLength() {
		return vibratingLength;
	}

	/**
	@param vibratingLength The new vibratingLength.
	**/
	public void setVibratingLength(final BigDecimal vibratingLength) {
		this.vibratingLength = vibratingLength;
	}

	/**
	@return The linearDensity.
	**/
	public BigDecimal getLinearDensity() {
		return linearDensity;
	}

	/**
	@param linearDensity The new linearDensity.
	**/
	public void setLinearDensity(final BigDecimal linearDensity) {
		this.linearDensity = linearDensity;
	}

	/**
	@return The maximumTension.
	**/
	public BigDecimal getMaximumTension() {
		return maximumTension;
	}

	/**
	@param maximumTension The new maximumTension.
	**/
	public void setMaximumTension(final BigDecimal maximumTension) {
		this.maximumTension = maximumTension;
	}

	@Override
	public java.lang.String toString() {
		final Object[][] objects = {
			{"vibrating length", vibratingLength, " cm"},
			{"linear density", linearDensity, " g/m"},
			{"maximum tension", maximumTension, " N"}
		};
		final java.lang.String fields = StringFormatter.formatFields(objects);
		return "string (" + fields + ")";
	}
}
