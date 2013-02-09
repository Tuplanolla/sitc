package org.sitdb.view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Is an alternative to the moving <code>ExtendedInterfacePanel</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class FixedExtendedInterfacePanel extends JPanel {
	private static final long serialVersionUID = 6930879354977115881l;

	private final JButton useButton,
			discardButton;
	private final JButton addButton,
			removeButton;
	private final JButton revertButton,
			applyButton;

	/**
	Constructs a new panel.
	**/
	public FixedExtendedInterfacePanel() {
		super(new GridBagLayout());

		useButton = new JButton("Use Instrument");
		Utilities.setScaledIcon(useButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		discardButton = new JButton("Don't Use Instrument");
		Utilities.setScaledIcon(discardButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		addButton = new JButton("Add Tuning");
		Utilities.setScaledIcon(addButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		removeButton = new JButton("Remove Tuning");
		Utilities.setScaledIcon(removeButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		revertButton = new JButton("Revert Transition");
		Utilities.setScaledIcon(revertButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		applyButton = new JButton("Apply Transition");
		Utilities.setScaledIcon(applyButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		final JPanel buttonPanel = new JPanel(new GridLayout(8, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		buttonPanel.add(useButton);
		buttonPanel.add(discardButton);
		buttonPanel.add(Box.createVerticalStrut(Constants.MEDIUM_INSET));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(Box.createVerticalStrut(Constants.MEDIUM_INSET));
		buttonPanel.add(revertButton);
		buttonPanel.add(applyButton);

		add(buttonPanel);
	}

	/**
	@return The use button.
	**/
	public JButton getUseButton() {
		return useButton;
	}

	/**
	@return The discard button.
	**/
	public JButton getDiscardButton() {
		return discardButton;
	}

	/**
	@return The add button.
	**/
	public JButton getAddButton() {
		return addButton;
	}

	/**
	@return The remove button.
	**/
	public JButton getRemoveButton() {
		return removeButton;
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
