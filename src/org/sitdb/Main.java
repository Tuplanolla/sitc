package org.sitdb;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main {
	private JFrame frame;

	/**
	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exception) {}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final Main window = new Main();
					window.frame.setVisible(true);
				}
				catch (final Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}

	/**
	**/
	public Main() {
		initialize();
	}

	/**
	**/
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(160, 80, 320, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
