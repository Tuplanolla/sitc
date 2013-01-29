package org.sitdb.view;

import java.awt.Insets;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Constants {
	public static final int BIG_INSET = 4,
			SMALL_INSET = 2,
			NO_INSET = 0;
	public static final Insets BIG_INSETS = new Insets(BIG_INSET, BIG_INSET, BIG_INSET, BIG_INSET),
			SMALL_INSETS = new Insets(SMALL_INSET, SMALL_INSET, SMALL_INSET, SMALL_INSET),
			NO_INSETS = new Insets(NO_INSET, NO_INSET, NO_INSET, NO_INSET);

	private Constants() {
		throw new InstantiationError();
	}
}
