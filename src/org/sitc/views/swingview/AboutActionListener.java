package org.sitc.views.swingview;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
Represents an about dialog opening action listener.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class AboutActionListener implements ActionListener {
	private final Window parentWindow;

	/**
	Creates an action listener.

	Opens an about dialog when triggered.

	@param parentWindow The parent window of the dialog.
	**/
	public AboutActionListener(final Window parentWindow) {
		this.parentWindow = parentWindow;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		final AboutDialog aboutDialog = new AboutDialog(parentWindow);
		aboutDialog.getCloseButton().addActionListener(new CloseActionListener(aboutDialog));
		aboutDialog.setVisible(true);
	}
}
