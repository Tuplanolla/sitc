package org.sitdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
Contains the data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public abstract class Model implements Part {
	protected final List<String> arguments;

	public Model(final String[] arguments) {
		final List<String> modifiableList = new ArrayList<String>(arguments.length);
		for (final String argument : arguments) modifiableList.add(argument);
		this.arguments = Collections.unmodifiableList(modifiableList);
	}
}
