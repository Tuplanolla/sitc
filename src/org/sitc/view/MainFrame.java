package org.sitc.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
Represents a main window.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 1;

	private final MenuBar menuBar;
	private final MainPane mainPane;
	private final StatusPanel statusPanel;

	/**
	Creates and initializes a main window.
	**/
	public MainFrame() {
			menuBar = new MenuBar();

				mainPane = new MainPane();

				statusPanel = new StatusPanel();

			final Container contentPane = getContentPane();
			contentPane.add(mainPane, BorderLayout.CENTER);
			contentPane.add(statusPanel, BorderLayout.SOUTH);

		setJMenuBar(menuBar);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("String Instrument Tuning Calculator");
		setIconImages(Resources.ICON_IMAGES);
		pack();

		Utilities.allStatesChanged(this);
	}

	/**
	Returns the actual menu bar.

	Relies on return type covariance.

	@return The actual menu bar.
	**/
	@Override
	public MenuBar getJMenuBar() {
		return menuBar;
	}

	/**
	Doesn't return the actual menu bar.

	@return Not the actual menu bar.
	@see #getJMenuBar()
	**/
	@Override
	public java.awt.MenuBar getMenuBar() {
		return super.getMenuBar();
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
