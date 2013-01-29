package org.sitdb.view;

import javax.swing.ImageIcon;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Assets {
	public static final ImageIcon ICON = new ImageIcon("gfx/icon.png"),
			RIGHT_ICON = new ImageIcon("gfx/right.png"),
			LEFT_ICON = new ImageIcon("gfx/left.png"),
			UP_ICON = new ImageIcon("gfx/up.png"),
			DOWN_ICON = new ImageIcon("gfx/down.png"),
			UP_DOWN_ICON = null,
			LEFT_RIGHT_ICON = null,
			PLUS_ICON = new ImageIcon("gfx/plus.png"),
			MINUS_ICON = new ImageIcon("gfx/minus.png"),
			PLAY_ICON = null,
			CALCULATION_ICON = null;

	private Assets() {
		throw new InstantiationError();
	}
}
