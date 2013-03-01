package org.sitdb.model;

import java.io.Serializable;

/**
Represents an immutable string of an instrument.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class String implements Serializable {
	private static final long serialVersionUID = 1l;

	/**
	The vibrating length of this string.
	**/
	public final double vibratingLength;

	/**
	The linear density (mass per unit length) of this string.
	**/
	public final double linearDensity;

	/**
	The maximum tension this string can handle.
	**/
	public final Double maximumTension;

	/**
	Creates a string with a tension limit.

	@param vibratingLength The vibrating length.
	@param linearDensity The linear density.
	@param maximumTension The maximum tension.
	**/
	public String(final double vibratingLength,
			final double linearDensity,
			final Double maximumTension) {
		this.vibratingLength = vibratingLength;
		this.linearDensity = linearDensity;
		this.maximumTension = maximumTension;
	}

	/**
	Creates a string without a tension limit.

	@param vibratingLength The vibrating length.
	@param linearDensity The linear density.
	**/
	public String(final double vibratingLength,
			final double linearDensity) {
		this(vibratingLength, linearDensity, null);
	}

	/**
	Creates a string with a tension limit.

	@param vibratingLength The vibrating length.
	@param mass The total mass.
	@param length The total length.
	@param maximumTension The maximum tension.
	**/
	public String(final double vibratingLength,
			final double mass,
			final double length,
			final Double maximumTension) {
		this(vibratingLength, mass / length, maximumTension);
	}

	/**
	Creates a string without a tension limit.

	@param vibratingLength The vibrating length.
	@param mass The total mass.
	@param length The total length.
	**/
	public String(final double vibratingLength,
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
	public String(final double vibratingLength,
			final double density,
			final double length,
			final double diameter,
			final Double maximumTension) {
		this(vibratingLength, Math.PI * density * length * (diameter * diameter));
	}

	/**
	Creates a string without a tension limit.

	@param vibratingLength The vibrating length.
	@param density The density (mass per unit volume) of the material.
	@param length The total length.
	@param diameter The mean diameter.
	**/
	public String(final double vibratingLength,
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
		final double velocity = 2 * vibratingLength * frequency,
				velocitySquared = velocity * velocity;
		return linearDensity * velocitySquared;
	}

	@Override
	public java.lang.String toString() {
		return "(vibrating length: " + vibratingLength + " cm, linear density: " + linearDensity + " g/m, maximum tension: " + maximumTension + " N)";
	}
}
