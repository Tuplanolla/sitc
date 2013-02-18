package org.sitdb.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sitdb.Part;
import org.sitdb.model.Model;

/**
Represents an immutable view.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class View implements Part {
	private final Model model;

	/**
	Constructs a new view and links it with a model.

	@param model The model.
	**/
	public View(final Model model) {
		this.model = model;
	}

	@Override
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
