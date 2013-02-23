package org.sitdb.view;

import java.awt.Insets;

/**
Represents a collection of constants, much like <code>SwingConstants</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Constants {
	private Constants() {
		throw new InstantiationError();
	}

	public static final int BIG_INSET = 8,
			MEDIUM_INSET = 4,
			SMALL_INSET = 2;
	public static final Insets BIG_INSETS = new Insets(BIG_INSET, BIG_INSET, BIG_INSET, BIG_INSET),
			MEDIUM_INSETS = new Insets(MEDIUM_INSET, MEDIUM_INSET, MEDIUM_INSET, MEDIUM_INSET),
			SMALL_INSETS = new Insets(SMALL_INSET, SMALL_INSET, SMALL_INSET, SMALL_INSET);
	public static final double VERY_SMALL_SCALE = 0.5,
			SMALL_SCALE = 0.75,
			ORIGINAL_SCALE = 1,
			LARGE_SCALE = 1.25,
			VERY_LARGE_SCALE = 1.5;
}
