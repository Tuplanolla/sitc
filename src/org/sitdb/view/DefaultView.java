package org.sitdb.view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sitdb.Model;
import org.sitdb.View;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class DefaultView extends View {
	public DefaultView(final Model model) {
		super(model);
	}

	/**
	Creates the main window.
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
