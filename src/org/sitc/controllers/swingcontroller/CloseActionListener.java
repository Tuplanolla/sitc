package org.sitc.controllers.swingcontroller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
Represents a window closing action listener.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class CloseActionListener implements ActionListener {
	private final Window parentWindow;

	/**
	Creates an action listener.

	Closes a window when triggered.

	@param parentWindow The window.
	**/
	public CloseActionListener(final Window parentWindow) {
		this.parentWindow = parentWindow;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		parentWindow.dispose();
	}
}
