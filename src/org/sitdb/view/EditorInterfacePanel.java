package org.sitdb.view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Represents a panel that's used to move data between
 a <code>ManagerPanel</code> and
 a specific <code>EditorPanel</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public class EditorInterfacePanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final JButton revertButton,
			applyButton;

	/**
	Constructs a new panel.
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
