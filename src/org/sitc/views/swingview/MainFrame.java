package org.sitc.views.swingview;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.sitc.Project;

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

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(Project.ABBREVIATION);
		setIconImages(Resources.loadProjectIcons());
		setJMenuBar(menuBar);
		add(mainPane, BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);

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
