package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
Represents a main window.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 1l;

	private final OptionBar extraMenuBar;
	private final MainPane mainPane;
	private final StatusPanel statusPanel;

	/**
	Creates and initializes a main window.
	**/
	public MainFrame() {
				extraMenuBar = new OptionBar();

				mainPane = new MainPane();

				statusPanel = new StatusPanel();

			final Container contentPane = getContentPane();
			contentPane.add(extraMenuBar, BorderLayout.NORTH);
			contentPane.add(mainPane, BorderLayout.CENTER);
			contentPane.add(statusPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("String Instrument Tuning Database");
		setIconImages(Resources.ICON_IMAGES);
		pack();
		setMinimumSize(getSize());

		Utilities.allStatesChanged(this);
	}

	/**
	@return The menu bar.
	**/
	public OptionBar getExtraMenuBar() {
		return extraMenuBar;
	}

	/**
	@return The main pane.
	**/
	public MainPane getMainPane() {
		return mainPane;
	}

	/**
	@return The status panel.
	**/
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}
}
