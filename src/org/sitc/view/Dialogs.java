package org.sitc.view;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
Represents a collection of dialog creation methods, much like <code>JOptionPane</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Dialogs {
	private Dialogs() {
		throw new InstantiationError();
	}

	/**
	Brings up an error dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	@return The option chosen by the user.
	**/
	public static int showErrorDialog(final Component parentComponent, final Object message) {
		final Object[] options = {"Dismiss"};
		return JOptionPane.showOptionDialog(parentComponent,
				message,
				"Error",
				JOptionPane.OK_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,
				options,
				options[0]);
	}

	/**
	Brings up a warning dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	@return The option chosen by the user.
	**/
	public static int showWarningDialog(final Component parentComponent, final Object message) {
		final Object[] options = {"Ignore"};
		return JOptionPane.showOptionDialog(parentComponent,
				message,
				"Warning",
				JOptionPane.OK_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[0]);
	}

	/**
	Brings up an information dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	@return The option chosen by the user.
	**/
	public static int showInformationDialog(final Component parentComponent, final Object message) {
		final Object[] options = {"Acknowledge"};
		return JOptionPane.showOptionDialog(parentComponent,
				message,
				"Information",
				JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				options,
				options[0]);
	}

	/**
	Brings up an overwrite question dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	@return The option chosen by the user.
	**/
	public static int showOverwriteDialog(final Component parentComponent, final Object message) {
		final Object[] options = {"Overwrite", "Cancel"};
		return JOptionPane.showOptionDialog(parentComponent,
				message,
				"Warning",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[0]);
	}
}
