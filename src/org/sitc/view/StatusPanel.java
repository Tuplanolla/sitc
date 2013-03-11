package org.sitc.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
Represents a status panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final JProgressBar progressBar;

	/**
	Creates a status panel.
	**/
	public StatusPanel() {
		super(new BorderLayout());

			progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
			progressBar.setStringPainted(true);

		add(progressBar);
	}

	/**
	@return The progress bar.
	**/
	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
