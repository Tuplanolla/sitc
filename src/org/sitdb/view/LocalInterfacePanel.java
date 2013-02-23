package org.sitdb.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Represents a local interface panel that
 connects a remote panel with a local panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class LocalInterfacePanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final JButton loadButton,
			saveButton;

	/**
	Creates a local interface panel.
	**/
	public LocalInterfacePanel() {
		super(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			loadButton = new JButton("Load");
			Utilities.setScaledIcon(loadButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			saveButton = new JButton("Save");
			Utilities.setScaledIcon(saveButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		add(loadButton);
		add(saveButton);
	}

	/**
	@return The load button.
	**/
	public JButton getLoadButton() {
		return loadButton;
	}

	/**
	@return The save button.
	**/
	public JButton getSaveButton() {
		return saveButton;
	}
}
