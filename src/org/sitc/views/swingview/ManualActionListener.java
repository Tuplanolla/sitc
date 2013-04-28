package org.sitc.views.swingview;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
Represents a manual opening action listener.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ManualActionListener implements ActionListener {
	private final Component parentComponent;

	/**
	Creates an action listener.

	Tries to open a manual or related error dialogs when triggered.

	@param parentComponent The parent component of all the dialogs.
	**/
	public ManualActionListener(final Component parentComponent) {
		this.parentComponent = parentComponent;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		try {
			Desktop.getDesktop().open(Resources.loadUserManual());
		}
		catch (final UnsupportedOperationException exception) {
			Dialogs.showErrorDialog(parentComponent, new StackTracePanel(exception,
					"Accessing the desktop failed. Make sure your desktop environment supports opening portable documents."));
		}
		catch (final IllegalArgumentException exception) {
			Dialogs.showErrorDialog(parentComponent, new StackTracePanel(exception,
					"Finding the manual failed. Make sure you have the documents that come with the installation."));
		}
		catch (final IOException exception) {
			Dialogs.showErrorDialog(parentComponent, new StackTracePanel(exception,
					"Opening the manual failed. Make sure you have a portable document reader."));
		}
	}
}

