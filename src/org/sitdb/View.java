package org.sitdb;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sitdb.view.MainFrame;

/**
Represents an immutable view.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class View {
	private final Model model;

	/**
	Constructs a new view and links it with a model.

	@param model The model.
	**/
	public View(final Model model) {
		this.model = model;
	}

	/**
	Sets the look and feel and creates the main window.
	**/
	public void activate() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final ClassNotFoundException
				| InstantiationException
				| IllegalAccessException
				| UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
		}
		new MainFrame();
	}
}
