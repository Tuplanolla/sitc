package org.sitdb.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
Represents a fully automatically generated panel that
 can be used to edit an instrument.

It's magical.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class InstrumentMagicPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final int rows;
	private final List<JTextField> lengthTextFields,
			densityTextFields,
			tensionTextFields;
	private final List<JButton> insertButtons,
			deleteButtons,
			swapButtons;

	/**
	Creates an instrument magic panel.

	@param rows The amount of rows.
	**/
	public InstrumentMagicPanel(final int rows) {
		super(new GridBagLayout());

		this.rows = rows;
		lengthTextFields = new ArrayList<JTextField>();
		densityTextFields = new ArrayList<JTextField>();
		tensionTextFields = new ArrayList<JTextField>();
		insertButtons = new ArrayList<JButton>();
		deleteButtons = new ArrayList<JButton>();
		swapButtons = new ArrayList<JButton>();

		/*
		Tracks the position.
		*/
		int gridy = 0;

		/*
		Builds the header row.
		*/
		{
				final JLabel lengthLabel = new JLabel("Length (cm)");
				lengthLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel densityLabel = new JLabel("Density (g/m)");
				densityLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel tensionLabel = new JLabel("Maximum Tension (N)");
				tensionLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel editLabel = new JLabel("Edit");
				editLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints lengthConstraints = new GridBagConstraints();
				lengthConstraints.gridx = 1;
				lengthConstraints.gridy = gridy;
				lengthConstraints.weightx = 1;
				lengthConstraints.weighty = 0;
				lengthConstraints.fill = GridBagConstraints.BOTH;
				lengthConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints densityConstraints = new GridBagConstraints();
				densityConstraints.gridx = 2;
				densityConstraints.gridy = gridy;
				densityConstraints.weightx = 1;
				densityConstraints.weighty = 0;
				densityConstraints.fill = GridBagConstraints.BOTH;
				densityConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints tensionConstraints = new GridBagConstraints();
				tensionConstraints.gridx = 3;
				tensionConstraints.gridy = gridy;
				tensionConstraints.weightx = 1;
				tensionConstraints.weighty = 0;
				tensionConstraints.fill = GridBagConstraints.BOTH;
				tensionConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints editConstraints = new GridBagConstraints();
				editConstraints.gridx = 4;
				editConstraints.gridy = gridy;
				editConstraints.gridwidth = 3;
				editConstraints.weightx = 0;
				editConstraints.weighty = 0;
				editConstraints.fill = GridBagConstraints.BOTH;
				editConstraints.insets = Constants.SMALL_INSETS;

			add(lengthLabel, lengthConstraints);
			add(densityLabel, densityConstraints);
			add(tensionLabel, tensionConstraints);
			add(editLabel, editConstraints);

			gridy++;
		}

		/*
		Tracks the maximum quarter row height.
		*/
		int strutHeight;

		/*
		Builds the first half row.
		*/
		{
				final JButton topInsertButton = new JButton("Insert");
				Utilities.setScaledIcon(topInsertButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				final GridBagConstraints topInsertConstraints = new GridBagConstraints();
				topInsertConstraints.gridx = 4;
				topInsertConstraints.gridy = 1;
				topInsertConstraints.gridheight = 2;
				topInsertConstraints.weightx = 0;
				topInsertConstraints.weighty = 0;
				topInsertConstraints.fill = GridBagConstraints.BOTH;
				topInsertConstraints.insets = Constants.SMALL_INSETS;

			add(topInsertButton, topInsertConstraints);

			insertButtons.add(topInsertButton);

			strutHeight = topInsertButton.getPreferredSize().height;

			gridy++;
		}

		/*
		Builds the rows.
		*/
		for (int row = 1; row <= rows; row++) {
				final JLabel numberLabel = new JLabel("#" + row);
				numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

				final JTextField lengthTextField = new JTextField();
				lengthTextField.setHorizontalAlignment(SwingConstants.CENTER);

				final JTextField densityTextField = new JTextField();
				densityTextField.setHorizontalAlignment(SwingConstants.CENTER);

				final JTextField tensionTextField = new JTextField();
				tensionTextField.setHorizontalAlignment(SwingConstants.CENTER);

				final JButton deleteButton = new JButton("Delete");
				Utilities.setScaledIcon(deleteButton, Resources.MINUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				final GridBagConstraints numberConstraints = new GridBagConstraints();
				numberConstraints.gridx = 0;
				numberConstraints.gridy = gridy;
				numberConstraints.gridheight = 2;
				numberConstraints.weightx = 0;
				numberConstraints.weighty = 0;
				numberConstraints.fill = GridBagConstraints.BOTH;
				numberConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints lengthConstraints = new GridBagConstraints();
				lengthConstraints.gridx = 1;
				lengthConstraints.gridy = gridy;
				lengthConstraints.gridheight = 2;
				lengthConstraints.weightx = 1;
				lengthConstraints.weighty = 0;
				lengthConstraints.fill = GridBagConstraints.BOTH;
				lengthConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints densityConstraints = new GridBagConstraints();
				densityConstraints.gridx = 2;
				densityConstraints.gridy = gridy;
				densityConstraints.gridheight = 2;
				densityConstraints.weightx = 1;
				densityConstraints.weighty = 0;
				densityConstraints.fill = GridBagConstraints.BOTH;
				densityConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints tensionConstraints = new GridBagConstraints();
				tensionConstraints.gridx = 3;
				tensionConstraints.gridy = gridy;
				tensionConstraints.gridheight = 2;
				tensionConstraints.weightx = 1;
				tensionConstraints.weighty = 0;
				tensionConstraints.fill = GridBagConstraints.BOTH;
				tensionConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints deleteConstraints = new GridBagConstraints();
				deleteConstraints.gridx = 5;
				deleteConstraints.gridy = gridy;
				deleteConstraints.gridheight = 2;
				deleteConstraints.weightx = 0;
				deleteConstraints.weighty = 0;
				deleteConstraints.fill = GridBagConstraints.BOTH;
				deleteConstraints.insets = Constants.SMALL_INSETS;

			add(numberLabel, numberConstraints);
			add(lengthTextField, lengthConstraints);
			add(densityTextField, densityConstraints);
			add(tensionTextField, tensionConstraints);
			add(deleteButton, deleteConstraints);

			lengthTextFields.add(lengthTextField);
			densityTextFields.add(densityTextField);
			tensionTextFields.add(tensionTextField);
			deleteButtons.add(deleteButton);

			strutHeight = Math.max(strutHeight, numberLabel.getPreferredSize().height);
			strutHeight = Math.max(strutHeight, lengthTextField.getPreferredSize().height);
			strutHeight = Math.max(strutHeight, densityTextField.getPreferredSize().height);
			strutHeight = Math.max(strutHeight, tensionTextField.getPreferredSize().height);
			strutHeight = Math.max(strutHeight, deleteButton.getPreferredSize().height);

			gridy++;

				final JButton insertButton = new JButton("Insert");
				Utilities.setScaledIcon(insertButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				final GridBagConstraints insertConstraints = new GridBagConstraints();
				insertConstraints.gridx = 4;
				insertConstraints.gridy = gridy;
				insertConstraints.gridheight = 2;
				insertConstraints.weightx = 0;
				insertConstraints.weighty = 0;
				insertConstraints.fill = GridBagConstraints.BOTH;
				insertConstraints.insets = Constants.SMALL_INSETS;

			strutHeight = Math.max(strutHeight, insertButton.getPreferredSize().height);

			insertButtons.add(insertButton);

			add(insertButton, insertConstraints);

			if (row != rows) {//not last
					final JButton swapButton = new JButton("Swap");
					Utilities.setScaledIcon(swapButton, Resources.UP_DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

					final GridBagConstraints swapConstraints = new GridBagConstraints();
					swapConstraints.gridx = 6;
					swapConstraints.gridy = gridy;
					swapConstraints.gridheight = 2;
					swapConstraints.weightx = 0;
					swapConstraints.weighty = 0;
					swapConstraints.fill = GridBagConstraints.BOTH;
					swapConstraints.insets = Constants.SMALL_INSETS;

				add(swapButton, swapConstraints);

				swapButtons.add(swapButton);

				strutHeight = Math.max(strutHeight, swapButton.getPreferredSize().height);
			}

			gridy++;
		}

		/*
		Builds the last half row.
		*/
		gridy++;

		/*
		Builds the glue.
		*/
		{
				final Component glue = Box.createVerticalGlue();

				final GridBagConstraints glueConstraints = new GridBagConstraints();
				glueConstraints.gridx = 8;
				glueConstraints.gridy = gridy;
				glueConstraints.weightx = 0;
				glueConstraints.weighty = 1;
				glueConstraints.fill = GridBagConstraints.BOTH;

			add(glue, glueConstraints);
		}

		/*
		Builds the struts for the half rows.
		*/
		{
			final int quarterStrutHeight = (strutHeight - 1) / 2 + 1//round up
					- Constants.SMALL_INSETS.top - Constants.SMALL_INSETS.bottom;

			while (gridy > 1) {
				gridy--;

					final Component strut = Box.createVerticalStrut(quarterStrutHeight);

					final GridBagConstraints strutConstraints = new GridBagConstraints();
					strutConstraints.gridx = 8;
					strutConstraints.gridy = gridy;
					strutConstraints.weightx = 0;
					strutConstraints.weighty = 0;
					strutConstraints.fill = GridBagConstraints.BOTH;
					strutConstraints.insets = Constants.SMALL_INSETS;

				add(strut, strutConstraints);
			}
		}

		/*
		Builds the border.
		*/
		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
	}

	/**
	@return The amount of rows.
	**/
	public int getRows() {
		return rows;
	}

	/**
	@param row The row.
	@return The length text field.
	**/
	public JTextField getLengthTextField(final int row) {
		return lengthTextFields.get(row);
	}

	/**
	@param row The row.
	@return The density text field.
	**/
	public JTextField getDensityTextField(final int row) {
		return densityTextFields.get(row);
	}

	/**
	@param row The row.
	@return The tension text field.
	**/
	public JTextField getTensionTextField(final int row) {
		return tensionTextFields.get(row);
	}

	/**
	@param row The row after.
	@return The insert button.
	**/
	public JButton getInsertButton(final int row) {
		return insertButtons.get(row);
	}

	/**
	@param row The row.
	@return The delete button.
	**/
	public JButton getDeleteButton(final int row) {
		return deleteButtons.get(row);
	}

	/**
	@param row The row after.
	@return The insert button.
	**/
	public JButton getSwapButton(final int row) {
		return swapButtons.get(row - 1);
	}
}
