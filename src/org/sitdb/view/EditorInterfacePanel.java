package org.sitdb.view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Represents an editor interface panel that
 connects a local panel with an editor panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public class EditorInterfacePanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final JButton revertButton,
			applyButton;

	/**
	Creates an editor interface panel.
	**/
	public EditorInterfacePanel() {
		super(new GridBagLayout());

				revertButton = new JButton("Revert");
				Utilities.setScaledIcon(revertButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

				applyButton = new JButton("Apply");
				Utilities.setScaledIcon(applyButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

			final JPanel buttonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			buttonPanel.add(revertButton);
			buttonPanel.add(applyButton);

		add(buttonPanel);
	}

	/**
	@return The revert button.
	**/
	public JButton getRevertButton() {
		return revertButton;
	}

	/**
	@return The apply button.
	**/
	public JButton getApplyButton() {
		return applyButton;
	}
}
