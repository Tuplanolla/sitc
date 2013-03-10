package org.sitdb.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
Represents an editor interface panel that
 connects list panels with an editor panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ExtendedEditorInterfacePanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final JButton useButton,
			discardButton;
	private final JButton addButton,
			removeButton;
	private final JButton revertButton,
			applyButton;

	/**
	Creates a panel.
	**/
	public ExtendedEditorInterfacePanel() {
		super(new GridBagLayout());

				useButton = new JButton("Use");//"Use Instrument"
				Utilities.setScaledIcon(useButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

				discardButton = new JButton("Discard");//"Discard Instrument"
				Utilities.setScaledIcon(discardButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

			final JPanel instrumentButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			instrumentButtonPanel.add(useButton);
			instrumentButtonPanel.add(discardButton);

				addButton = new JButton("Add");//"Add Tuning"
				Utilities.setScaledIcon(addButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

				removeButton = new JButton("Remove");//"Remove Tuning"
				Utilities.setScaledIcon(removeButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

			final JPanel tuningButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			tuningButtonPanel.add(addButton);
			tuningButtonPanel.add(removeButton);

				revertButton = new JButton("Revert");//"Revert Sequence"
				Utilities.setScaledIcon(revertButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

				applyButton = new JButton("Apply");//"Apply Sequence"
				Utilities.setScaledIcon(applyButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.SMALL_SCALE);

			final JPanel sequenceButtonPanel = new JPanel(new GridLayout(2, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			sequenceButtonPanel.add(revertButton);
			sequenceButtonPanel.add(applyButton);

			final GridBagConstraints nothingInstrumentConstraints = new GridBagConstraints();
			nothingInstrumentConstraints.gridx = 0;
			nothingInstrumentConstraints.gridy = 0;
			nothingInstrumentConstraints.weightx = 1;
			nothingInstrumentConstraints.weighty = 1;
			nothingInstrumentConstraints.fill = GridBagConstraints.BOTH;

			final GridBagConstraints instrumentConstraints = new GridBagConstraints();
			instrumentConstraints.gridx = 0;
			instrumentConstraints.gridy = 1;
			instrumentConstraints.weightx = 1;
			instrumentConstraints.weighty = 0;
			instrumentConstraints.fill = GridBagConstraints.BOTH;

			final GridBagConstraints instrumentTuningConstraints = new GridBagConstraints();
			instrumentTuningConstraints.gridx = 0;
			instrumentTuningConstraints.gridy = 2;
			instrumentTuningConstraints.weightx = 1;
			instrumentTuningConstraints.weighty = 1;
			instrumentTuningConstraints.fill = GridBagConstraints.BOTH;

			final GridBagConstraints tuningConstraints = new GridBagConstraints();
			tuningConstraints.gridx = 0;
			tuningConstraints.gridy = 3;
			tuningConstraints.weightx = 1;
			tuningConstraints.weighty = 0;
			tuningConstraints.fill = GridBagConstraints.BOTH;

			final GridBagConstraints tuningSequenceConstraints = new GridBagConstraints();
			tuningSequenceConstraints.gridx = 0;
			tuningSequenceConstraints.gridy = 4;
			tuningSequenceConstraints.weightx = 1;
			tuningSequenceConstraints.weighty = 1;
			tuningSequenceConstraints.fill = GridBagConstraints.BOTH;

			final GridBagConstraints sequenceConstraints = new GridBagConstraints();
			sequenceConstraints.gridx = 0;
			sequenceConstraints.gridy = 5;
			sequenceConstraints.weightx = 1;
			sequenceConstraints.weighty = 0;
			sequenceConstraints.fill = GridBagConstraints.BOTH;

			final GridBagConstraints sequenceNothingConstraints = new GridBagConstraints();
			sequenceNothingConstraints.gridx = 0;
			sequenceNothingConstraints.gridy = 6;
			sequenceNothingConstraints.weightx = 1;
			sequenceNothingConstraints.weighty = 1;
			sequenceNothingConstraints.fill = GridBagConstraints.BOTH;

		add(Box.createVerticalStrut(0), nothingInstrumentConstraints);
		add(instrumentButtonPanel, instrumentConstraints);
		add(Box.createVerticalStrut(Constants.MEDIUM_INSET), instrumentTuningConstraints);
		add(tuningButtonPanel, tuningConstraints);
		add(Box.createVerticalStrut(Constants.MEDIUM_INSET), tuningSequenceConstraints);
		add(sequenceButtonPanel, sequenceConstraints);
		add(Box.createVerticalStrut(0), sequenceNothingConstraints);
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