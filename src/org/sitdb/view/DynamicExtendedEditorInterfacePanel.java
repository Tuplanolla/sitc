package org.sitdb.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Is an alternative to the static <code>ExtendedEditorInterfacePanel</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
@Deprecated
public final class DynamicExtendedEditorInterfacePanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final JButton useButton,
			discardButton;
	private final JButton addButton,
			removeButton;
	private final JButton revertButton,
			applyButton;

	/**
	Creates a panel.
	**/
	public DynamicExtendedEditorInterfacePanel() {
		super(new GridBagLayout());

		useButton = new JButton("Use Instrument");
		Utilities.setScaledIcon(useButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		discardButton = new JButton("Don't Use Instrument");
		Utilities.setScaledIcon(discardButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		addButton = new JButton("Add Tuning");
		Utilities.setScaledIcon(addButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		removeButton = new JButton("Remove Tuning");
		Utilities.setScaledIcon(removeButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		revertButton = new JButton("Revert Sequence");
		Utilities.setScaledIcon(revertButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		applyButton = new JButton("Apply Sequence");
		Utilities.setScaledIcon(applyButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

		final JPanel instrumentButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		instrumentButtonPanel.add(useButton);
		instrumentButtonPanel.add(discardButton);

		final JPanel tuningButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		tuningButtonPanel.add(addButton);
		tuningButtonPanel.add(removeButton);

		final JPanel sequenceButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		sequenceButtonPanel.add(revertButton);
		sequenceButtonPanel.add(applyButton);

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

		final GridBagConstraints tuningSequenceConstraints = new GridBagConstraints();
		tuningSequenceConstraints.gridx = 0;
		tuningSequenceConstraints.gridy = 3;
		tuningSequenceConstraints.weightx = 1;
		tuningSequenceConstraints.weighty = 1;
		tuningSequenceConstraints.fill = GridBagConstraints.BOTH;

		final GridBagConstraints sequenceConstraints = new GridBagConstraints();
		sequenceConstraints.gridx = 0;
		sequenceConstraints.gridy = 4;
		sequenceConstraints.weightx = 1;
		sequenceConstraints.weighty = 0;
		sequenceConstraints.fill = GridBagConstraints.BOTH;

		add(instrumentButtonPanel, instrumentConstraints);
		add(Box.createVerticalStrut(Constants.MEDIUM_INSET), instrumentTuningConstraints);
		add(tuningButtonPanel, tuningConstraints);
		add(Box.createVerticalStrut(Constants.MEDIUM_INSET), tuningSequenceConstraints);
		add(sequenceButtonPanel, sequenceConstraints);
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