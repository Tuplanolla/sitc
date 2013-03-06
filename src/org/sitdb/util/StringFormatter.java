package org.sitdb.util;

/**
Represents a general-purpose string formatter.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class StringFormatter {
	private StringFormatter() {
		throw new InstantiationError();
	}

	/**
	Formats objects (typically fields) in a comma-separated list.
	The arrays of objects should contain
	 a name,
	 a prefix (optional),
	 a value and
	 a suffix (optional).
	The suffix has a higher priority than the prefix, so
	 using a prefix without a suffix requires passing an empty prefix.
	Empty objects can be left <code>null</code>.

	Use with care:

	<pre>
StringFormatter.formatFields(new Object[][] {
	{"ratio", gearRatio},
	{"torque", totalTorque, " N/m"},
	{"potential", "-", scalarPotential, " J/kg"}
});
</pre>

	@param objects The array.
	@return The formatted string.
	**/
	public static String formatFields(final Object[][] objects) {
		final String[][] strings = new String[objects.length][];
		int capacity = 0;
		for (int groupIndex = 0; groupIndex < objects.length; groupIndex++) {
			final Object[] objectGroup = objects[groupIndex];
			final int length = objectGroup.length;
			final String[] stringGroup = new String[length];
			for (int elementIndex = 0; elementIndex < length; elementIndex++) {
				final Object object = objectGroup[elementIndex];
				final String string;
				if (object != null) {
					string = object.toString();
					capacity += string.length();
				}
				else string = null;
				stringGroup[elementIndex] = string;
			}
			strings[groupIndex] = stringGroup;
		}
		final StringBuilder stringBuilder = new StringBuilder(capacity);
		boolean separate = false;
		for (int groupIndex = 0; groupIndex < strings.length; groupIndex++) {
			final String[] stringGroup = strings[groupIndex];
			String name = null,
					prefix = null,
					value = null,
					suffix = null;
			switch (stringGroup.length) {
			case 2:
				name = stringGroup[0];
				value = stringGroup[1];
				break;
			case 3:
				name = stringGroup[0];
				value = stringGroup[1];
				suffix = stringGroup[2];
				break;
			case 4:
				name = stringGroup[0];
				prefix = stringGroup[1];
				value = stringGroup[2];
				suffix = stringGroup[3];
				break;
			default: throw new IllegalArgumentException();
			}
			if (name != null && value != null) {
				if (separate) stringBuilder.append(", ");
				else separate = true;
				stringBuilder.append(name);
				stringBuilder.append(": ");
				if (prefix != null) stringBuilder.append(prefix);
				stringBuilder.append(value);
				if (suffix != null) stringBuilder.append(suffix);
			}
		}
		return stringBuilder.toString();
	}
}
