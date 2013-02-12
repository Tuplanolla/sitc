package org.sitdb.view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sitdb.Model;
import org.sitdb.View;

/**
Represents an immutable view.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class DefaultView extends View {
	/**
	Constructs a new view and links it with a model.
	
	@param model The model.
	**/
	public DefaultView(final Model model) {
		super(model);
	}

	/**
	Sets the look and feel and creates the main window.
	**/
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
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
