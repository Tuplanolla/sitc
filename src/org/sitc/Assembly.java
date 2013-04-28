package org.sitc;

/**
Provides project information.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Assembly {
	private Assembly() {
		throw new InstantiationError();
	}

	public static final String NAME = "String Instrument Tuning Calculator",
			ABBREVIATION = "SITC",
			VERSION = "0.2.0",
			AUTHOR = "Sampsa \"Tuplanolla\" Kiiskinen",
			LICENSE = "CC by-nc-sa";
}
