package org.sitc.view;

import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sitc.Part;
import org.sitc.model.Model;

/**
Represents an immutable view.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class View implements Part {
	protected final Model model;

	private final MainFrame mainFrame;

	/**
	Creates a view and links it with a model.

	@param model The model.
	**/
	public View(final Model model) {
		this.model = model;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final ClassNotFoundException
				| InstantiationException
				| IllegalAccessException
				| UnsupportedLookAndFeelException exception) {
			//nothing can save us now
		}
		mainFrame = new MainFrame();
	}

	@Override
	public void activate() {
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setSize(new Dimension(800, 800));
		mainFrame.setVisible(true);
	}

	/**
	@return The main frame.
	**/
	public MainFrame getMainFrame() {
		return mainFrame;
	}
}
