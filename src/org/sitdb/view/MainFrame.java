package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
Represents the main window.

The window has the following structural hierarchy:

<pre>
/---------------------------\
| [ ] MainFrame [_] [#] [X] |
+---------------------------+
| MenuBar                   |
|---------------------------|
| MainPanel                 |
|---------------------------|
| StatusPanel               |
+---------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 1l;

	private final MenuBar menuPanel;
	private final MainPanel mainPanel;
	private final StatusPanel statusPanel;

	/**
	Constructs and initializes a new window.
	**/
	public MainFrame() {
		/*
		Where I deadlocked, I softer thread ---
		I sow sweet synch block --- From standard read ---
		I pause above that line ahead
			And assert.
		*/
		assert SwingUtilities.isEventDispatchThread();

		/*
		Whom I deadlocked, I cryptic bard
		From syntax harsh, or ill keyword ---
		Feeling as if their anger seared,
			Though vain!
		*/
		menuPanel = new MenuBar();

		mainPanel = new MainPanel();

		statusPanel = new StatusPanel();

		final Container contentPane = getContentPane();
		contentPane.add(menuPanel, BorderLayout.NORTH);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(statusPanel, BorderLayout.SOUTH);

		/*
		When I deadlock, you'll know by this ---
		A buffer black --- Flickers amiss ---
		A static tremor as a hiss
			Like piss!
		*/
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("String Instrument Tuning Database");
		setIconImages(Resources.ICON_IMAGES);
		pack();
		setMinimumSize(getSize());
		setSize(new Dimension(800, 600));
		Utilities.allStatesChanged(this);

		/*
		Why, I deadlocked, the people know
		Who thought this program isn't slow
		Went home a century ago
			Next Bliss!
		*/
		setVisible(true);
	}

	/**
	@return The menu panel.
	**/
	public MenuBar getMenuPanel() {
		return menuPanel;
	}

	/**
	@return The main panel.
	**/
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	/**
	@return The status panel.
	**/
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}
}
