package org.sitc.controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.text.JTextComponent;

import org.sitc.views.swingview.Dialogs;
import org.sitc.views.swingview.StackTracePanel;


/**
Represents a browse button action listener.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class BrowseActionListener implements ActionListener {
	private final Component parentComponent;
	private final JTextComponent textComponent;
	private final JFileChooser fileChooser;

	/**
	Creates an action listener.

	Opens a file chooser dialog and possibly related error dialogs when triggered.

	@param parentComponent The parent component of all the dialogs.
	@param textComponent The text component where the chosen (canonical) path is stored.
	**/
	public BrowseActionListener(final Component parentComponent, final JTextComponent textComponent) {
		this.parentComponent = parentComponent;
		this.textComponent = textComponent;
		fileChooser = new JFileChooser();
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		final int value = fileChooser.showOpenDialog(parentComponent);
		if (value == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			try {
				textComponent.setText(file.getCanonicalPath());
			}
			catch (final IOException exception) {
				Dialogs.showErrorDialog(parentComponent, new StackTracePanel(exception,
						"Resolving the file failed. Make sure your file system is working properly."));
			}
			catch (final SecurityException exception) {
				Dialogs.showErrorDialog(parentComponent, new StackTracePanel(exception,
						"Accessing the file failed. Make sure your permissions are sufficient."));
			}
		}
	}
}
