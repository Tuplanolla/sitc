package org.sitdb.view;

import java.awt.Insets;

/**
Provides additional Swing constants.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Constants {
	public static final int BIG_INSET = 8,
			MEDIUM_INSET = 4,
			SMALL_INSET = 2;
	public static final Insets BIG_INSETS = new Insets(BIG_INSET, BIG_INSET, BIG_INSET, BIG_INSET),
			MEDIUM_INSETS = new Insets(MEDIUM_INSET, MEDIUM_INSET, MEDIUM_INSET, MEDIUM_INSET),
			SMALL_INSETS = new Insets(SMALL_INSET, SMALL_INSET, SMALL_INSET, SMALL_INSET);
	public static final double ICON_SCALE = 0.75;

	private Constants() {
		throw new InstantiationError();
	}
}
