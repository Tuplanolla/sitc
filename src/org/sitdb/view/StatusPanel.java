package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
Represents a panel that does something.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1247479376441738475l;

	private final JProgressBar progressBar;

	/**
	Constructs a new panel.
	**/
	public StatusPanel() {
		progressBar = new JProgressBar(SwingConstants.HORIZONTAL, Byte.MIN_VALUE, Byte.MAX_VALUE);

		final JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.add(progressBar);
	}

	/**
	@return The progress bar.
	**/
	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
