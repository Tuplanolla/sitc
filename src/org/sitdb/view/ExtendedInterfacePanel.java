package org.sitdb.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Represents a panel that does something.

The panel has the following structural hierarchy:

<pre>
+---------------------------+
| ManagerPanel              |
+---------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ExtendedInterfacePanel extends JPanel {
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
	public ExtendedInterfacePanel() {
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

		final JPanel instrumentButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		instrumentButtonPanel.add(useButton);
		instrumentButtonPanel.add(discardButton);

		final JPanel tuningButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		tuningButtonPanel.add(addButton);
		tuningButtonPanel.add(removeButton);

		final JPanel transitionButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		transitionButtonPanel.add(revertButton);
		transitionButtonPanel.add(applyButton);

		final GridBagConstraints instrumentConstraints = new GridBagConstraints();
		instrumentConstraints.gridx = 0;
		instrumentConstraints.gridy = 0;
		instrumentConstraints.weightx = 1;
		instrumentConstraints.weighty = 0;
		instrumentConstraints.fill = GridBagConstraints.BOTH;

		final GridBagConstraints instrumentTuningConstraints = new GridBagConstraints();
		instrumentTuningConstraints.gridx = 0;
		instrumentTuningConstraints.gridy = 1;
		instrumentTuningConstraints.weightx = 1;
		instrumentTuningConstraints.weighty = 1;
		instrumentTuningConstraints.fill = GridBagConstraints.BOTH;

		final GridBagConstraints tuningConstraints = new GridBagConstraints();
		tuningConstraints.gridx = 0;
		tuningConstraints.gridy = 2;
		tuningConstraints.weightx = 1;
		tuningConstraints.weighty = 0;
		tuningConstraints.fill = GridBagConstraints.BOTH;

		final GridBagConstraints tuningTransitionConstraints = new GridBagConstraints();
		tuningTransitionConstraints.gridx = 0;
		tuningTransitionConstraints.gridy = 3;
		tuningTransitionConstraints.weightx = 1;
		tuningTransitionConstraints.weighty = 1;
		tuningTransitionConstraints.fill = GridBagConstraints.BOTH;

		final GridBagConstraints transitionConstraints = new GridBagConstraints();
		transitionConstraints.gridx = 0;
		transitionConstraints.gridy = 4;
		transitionConstraints.weightx = 1;
		transitionConstraints.weighty = 0;
		transitionConstraints.fill = GridBagConstraints.BOTH;

		add(instrumentButtonPanel, instrumentConstraints);
		add(Box.createVerticalStrut(Constants.MEDIUM_INSET), instrumentTuningConstraints);
		add(tuningButtonPanel, tuningConstraints);
		add(Box.createVerticalStrut(Constants.MEDIUM_INSET), tuningTransitionConstraints);
		add(transitionButtonPanel, transitionConstraints);
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