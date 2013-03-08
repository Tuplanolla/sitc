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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
Represents a fully automatically generated panel that
 can be used to edit a sequence.

It's magical.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class SequenceMagicPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final GridBagLayout layout;
	private int rows,
			columns;
	private final List<JLabel> tuningLabels;
	private final List<List<JLabel>> transpositionLabelLists,
			tensionLabelLists,
			transpositionChangeLabelLists,
			tensionChangeLabelLists;
	private final List<JLabel> transpositionSumLabels,
			transpositionAbsoluteSumLabels,
			tensionSumLabels,
			tensionAbsoluteSumLabels;
	private final List<JButton> swapButtons;

	/**
	Creates a sequence magic panel.

	@param rows The amount of rows.
	@param columns The amount of columns.
	**/
	public SequenceMagicPanel(final int rows, final int columns) {
		super(new GridBagLayout());
		layout = (GridBagLayout )getLayout();//avoids creating a disposable FlowLayout

		this.rows = rows;
		this.columns = columns;
		tuningLabels = new ArrayList<JLabel>();
		transpositionLabelLists = new ArrayList<List<JLabel>>();
		tensionLabelLists = new ArrayList<List<JLabel>>();
		transpositionChangeLabelLists = new ArrayList<List<JLabel>>();
		tensionChangeLabelLists = new ArrayList<List<JLabel>>();
		transpositionSumLabels = new ArrayList<JLabel>();
		transpositionAbsoluteSumLabels = new ArrayList<JLabel>();
		tensionSumLabels = new ArrayList<JLabel>();
		tensionAbsoluteSumLabels = new ArrayList<JLabel>();
		swapButtons = new ArrayList<JButton>();

		/*
		Tracks the position.
		*/
		int gridx,
				gridy = 0;

		/*
		Builds the header row.
		*/
		{
			gridx = 1;

				final JLabel tuningLabel = new JLabel("Tuning");
				tuningLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints tuningConstraints = new GridBagConstraints();
				tuningConstraints.gridx = gridx;
				tuningConstraints.gridy = gridy;
				tuningConstraints.gridheight = 2;
				tuningConstraints.weightx = 1;
				tuningConstraints.weighty = 0;
				tuningConstraints.fill = GridBagConstraints.BOTH;
				tuningConstraints.insets = Constants.SMALL_INSETS;

			add(tuningLabel, tuningConstraints);

			gridx++;

				final JLabel transpositionLabel = new JLabel("Transpositions");
				transpositionLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints transpositionConstraints = new GridBagConstraints();
				transpositionConstraints.gridx = gridx;
				transpositionConstraints.gridy = gridy;
				transpositionConstraints.gridwidth = columns + 2;
				transpositionConstraints.weightx = 1;
				transpositionConstraints.weighty = 0;
				transpositionConstraints.fill = GridBagConstraints.BOTH;
				transpositionConstraints.insets = Constants.SMALL_INSETS;

			add(transpositionLabel, transpositionConstraints);

			gridx += columns + 2;

				final JLabel tensionLabel = new JLabel("Tensions (N)");
				tensionLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints tensionConstraints = new GridBagConstraints();
				tensionConstraints.gridx = gridx;
				tensionConstraints.gridy = gridy;
				tensionConstraints.gridwidth = columns + 2;
				tensionConstraints.weightx = 1;
				tensionConstraints.weighty = 0;
				tensionConstraints.fill = GridBagConstraints.BOTH;
				tensionConstraints.insets = Constants.SMALL_INSETS;

			add(tensionLabel, tensionConstraints);

			gridx += columns + 2;

				final JLabel editLabel = new JLabel("Edit");
				editLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints editConstraints = new GridBagConstraints();
				editConstraints.gridx = gridx;
				editConstraints.gridy = gridy;
				editConstraints.gridheight = 2;
				editConstraints.weightx = 0;
				editConstraints.weighty = 0;
				editConstraints.fill = GridBagConstraints.BOTH;
				editConstraints.insets = Constants.SMALL_INSETS;

			add(editLabel, editConstraints);

			gridy++;

			gridx = 2;

			for (int column = 1; column <= columns; column++) {
					final JLabel transpositionNumberLabel = new JLabel("#" + column);
					transpositionNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints transpositionNumberConstraints = new GridBagConstraints();
					transpositionNumberConstraints.gridx = gridx;
					transpositionNumberConstraints.gridy = gridy;
					transpositionNumberConstraints.weightx = 1;
					transpositionNumberConstraints.weighty = 0;
					transpositionNumberConstraints.fill = GridBagConstraints.BOTH;
					transpositionNumberConstraints.insets = Constants.SMALL_INSETS;

				add(transpositionNumberLabel, transpositionNumberConstraints);

				gridx++;
			}

				final JLabel transpositionSumLabel = new JLabel("\u2211\u2206");//N-ARY SUMMATION INCREMENT
				transpositionSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints transpositionSumConstraints = new GridBagConstraints();
				transpositionSumConstraints.gridx = gridx;
				transpositionSumConstraints.gridy = gridy;
				transpositionSumConstraints.weightx = 1;
				transpositionSumConstraints.weighty = 0;
				transpositionSumConstraints.fill = GridBagConstraints.BOTH;
				transpositionSumConstraints.insets = Constants.SMALL_INSETS;

			add(transpositionSumLabel, transpositionSumConstraints);

			gridx++;

				final JLabel transpositionAbsoluteSumLabel = new JLabel("\u2211|\u2206|");
				transpositionAbsoluteSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints transpositionAbsoluteSumConstraints = new GridBagConstraints();
				transpositionAbsoluteSumConstraints.gridx = gridx;
				transpositionAbsoluteSumConstraints.gridy = gridy;
				transpositionAbsoluteSumConstraints.weightx = 1;
				transpositionAbsoluteSumConstraints.weighty = 0;
				transpositionAbsoluteSumConstraints.fill = GridBagConstraints.BOTH;
				transpositionAbsoluteSumConstraints.insets = Constants.SMALL_INSETS;

			add(transpositionAbsoluteSumLabel, transpositionAbsoluteSumConstraints);

			gridx++;

			for (int column = 1; column <= columns; column++) {
					final JLabel tensionNumberLabel = new JLabel("#" + column);
					tensionNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints tensionNumberConstraints = new GridBagConstraints();
					tensionNumberConstraints.gridx = gridx;
					tensionNumberConstraints.gridy = gridy;
					tensionNumberConstraints.weightx = 1;
					tensionNumberConstraints.weighty = 0;
					tensionNumberConstraints.fill = GridBagConstraints.BOTH;
					tensionNumberConstraints.insets = Constants.SMALL_INSETS;

				add(tensionNumberLabel, tensionNumberConstraints);

				gridx++;
			}

				final JLabel tensionSumLabel = new JLabel("\u2211\u2206");
				tensionSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints tensionSumConstraints = new GridBagConstraints();
				tensionSumConstraints.gridx = gridx;
				tensionSumConstraints.gridy = gridy;
				tensionSumConstraints.weightx = 1;
				tensionSumConstraints.weighty = 0;
				tensionSumConstraints.fill = GridBagConstraints.BOTH;
				tensionSumConstraints.insets = Constants.SMALL_INSETS;

			add(tensionSumLabel, tensionSumConstraints);

			gridx++;

				final JLabel tensionAbsoluteSumLabel = new JLabel("\u2211|\u2206|");
				tensionAbsoluteSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints tensionAbsoluteSumConstraints = new GridBagConstraints();
				tensionAbsoluteSumConstraints.gridx = gridx;
				tensionAbsoluteSumConstraints.gridy = gridy;
				tensionAbsoluteSumConstraints.weightx = 1;
				tensionAbsoluteSumConstraints.weighty = 0;
				tensionAbsoluteSumConstraints.fill = GridBagConstraints.BOTH;
				tensionAbsoluteSumConstraints.insets = Constants.SMALL_INSETS;

			add(tensionAbsoluteSumLabel, tensionAbsoluteSumConstraints);

			gridy++;
		}

		/*
		Tracks the maximum row height.
		*/
		int strutHeight = 0;

		/*
		Builds the rows.
		*/
		for (int row = 1; row <= rows; row++) {
			gridx = 0;

				final JLabel numberLabel = new JLabel("#" + row);
				numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

				final GridBagConstraints numberConstraints = new GridBagConstraints();
				numberConstraints.gridx = gridx;
				numberConstraints.gridy = gridy;
				numberConstraints.gridheight = 2;
				numberConstraints.weightx = 0;
				numberConstraints.weighty = 0;
				numberConstraints.fill = GridBagConstraints.BOTH;
				numberConstraints.insets = Constants.SMALL_INSETS;

			add(numberLabel, numberConstraints);

			strutHeight = Math.max(strutHeight, numberLabel.getPreferredSize().height);

			gridx++;

				final JLabel tuningLabel = new JLabel();
				tuningLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints tuningConstraints = new GridBagConstraints();
				tuningConstraints.gridx = gridx;
				tuningConstraints.gridy = gridy;
				tuningConstraints.gridheight = 2;
				tuningConstraints.weightx = 1;
				tuningConstraints.weighty = 0;
				tuningConstraints.fill = GridBagConstraints.BOTH;
				tuningConstraints.insets = Constants.SMALL_INSETS;

			add(tuningLabel, tuningConstraints);

			tuningLabels.add(tuningLabel);

			strutHeight = Math.max(strutHeight, tuningLabel.getPreferredSize().height);

			gridx++;

			final List<JLabel> transpositionLabels = new ArrayList<JLabel>();

			for (int column = 1; column <= columns; column++) {
					final JLabel transpositionLabel = new JLabel();
					transpositionLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints transpositionConstraints = new GridBagConstraints();
					transpositionConstraints.gridx = gridx;
					transpositionConstraints.gridy = gridy;
					transpositionConstraints.gridheight = 2;
					transpositionConstraints.weightx = 0;
					transpositionConstraints.weighty = 0;
					transpositionConstraints.fill = GridBagConstraints.BOTH;
					transpositionConstraints.insets = Constants.SMALL_INSETS;

				add(transpositionLabel, transpositionConstraints);

				transpositionLabels.add(transpositionLabel);

				strutHeight = Math.max(strutHeight, transpositionLabel.getPreferredSize().height);

				gridx++;
			}

			transpositionLabelLists.add(transpositionLabels);

			gridx += 2;

			final List<JLabel> tensionLabels = new ArrayList<JLabel>();

			for (int column = 1; column <= columns; column++) {
					final JLabel tensionLabel = new JLabel();
					tensionLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints tensionConstraints = new GridBagConstraints();
					tensionConstraints.gridx = gridx;
					tensionConstraints.gridy = gridy;
					tensionConstraints.gridheight = 2;
					tensionConstraints.weightx = 0;
					tensionConstraints.weighty = 0;
					tensionConstraints.fill = GridBagConstraints.BOTH;
					tensionConstraints.insets = Constants.SMALL_INSETS;

				add(tensionLabel, tensionConstraints);

				tensionLabels.add(tensionLabel);

				strutHeight = Math.max(strutHeight, tensionLabel.getPreferredSize().height);

				gridx++;
			}

			tensionLabelLists.add(tensionLabels);

			gridy++;

			if (row != rows) {//not last
				gridx += 2;

					final JButton swapButton = new JButton("Swap");
					Utilities.setScaledIcon(swapButton, Resources.UP_DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

					final GridBagConstraints swapConstraints = new GridBagConstraints();
					swapConstraints.gridx = gridx;
					swapConstraints.gridy = gridy;
					swapConstraints.gridheight = 3;
					swapConstraints.weightx = 0;
					swapConstraints.weighty = 0;
					swapConstraints.fill = GridBagConstraints.BOTH;
					swapConstraints.insets = Constants.SMALL_INSETS;

				add(swapButton, swapConstraints);

				swapButtons.add(swapButton);

				strutHeight = Math.max(strutHeight, swapButton.getPreferredSize().height);

				gridy++;

				gridx = 2;

				final List<JLabel> transpositionChangeLabels = new ArrayList<JLabel>();

				for (int column = 1; column <= columns; column++) {
						final JLabel transpositionChangeLabel = new JLabel();
						transpositionChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);

						final GridBagConstraints transpositionChangeConstraints = new GridBagConstraints();
						transpositionChangeConstraints.gridx = gridx;
						transpositionChangeConstraints.gridy = gridy;
						transpositionChangeConstraints.weightx = 0;
						transpositionChangeConstraints.weighty = 0;
						transpositionChangeConstraints.fill = GridBagConstraints.BOTH;
						transpositionChangeConstraints.insets = Constants.SMALL_INSETS;

					add(transpositionChangeLabel, transpositionChangeConstraints);

					transpositionChangeLabels.add(transpositionChangeLabel);

					strutHeight = Math.max(strutHeight, transpositionChangeLabel.getPreferredSize().height);

					gridx++;
				}

				transpositionChangeLabelLists.add(transpositionChangeLabels);

					final JLabel transpositionSumLabel = new JLabel();
					transpositionSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints transpositionSumConstraints = new GridBagConstraints();
					transpositionSumConstraints.gridx = gridx;
					transpositionSumConstraints.gridy = gridy;
					transpositionSumConstraints.weightx = 1;
					transpositionSumConstraints.weighty = 0;
					transpositionSumConstraints.fill = GridBagConstraints.BOTH;
					transpositionSumConstraints.insets = Constants.SMALL_INSETS;

				add(transpositionSumLabel, transpositionSumConstraints);

				transpositionSumLabels.add(transpositionSumLabel);

				strutHeight = Math.max(strutHeight, transpositionSumLabel.getPreferredSize().height);

				gridx++;

					final JLabel transpositionAbsoluteSumLabel = new JLabel();
					transpositionAbsoluteSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints transpositionAbsoluteSumConstraints = new GridBagConstraints();
					transpositionAbsoluteSumConstraints.gridx = gridx;
					transpositionAbsoluteSumConstraints.gridy = gridy;
					transpositionAbsoluteSumConstraints.weightx = 1;
					transpositionAbsoluteSumConstraints.weighty = 0;
					transpositionAbsoluteSumConstraints.fill = GridBagConstraints.BOTH;
					transpositionAbsoluteSumConstraints.insets = Constants.SMALL_INSETS;

				add(transpositionAbsoluteSumLabel, transpositionAbsoluteSumConstraints);

				transpositionAbsoluteSumLabels.add(transpositionAbsoluteSumLabel);

				strutHeight = Math.max(strutHeight, transpositionAbsoluteSumLabel.getPreferredSize().height);

				gridx++;

				final List<JLabel> tensionChangeLabels = new ArrayList<JLabel>();

				for (int column = 1; column <= columns; column++) {
						final JLabel tensionChangeLabel = new JLabel();
						tensionChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);

						final GridBagConstraints tensionChangeConstraints = new GridBagConstraints();
						tensionChangeConstraints.gridx = gridx;
						tensionChangeConstraints.gridy = gridy;
						tensionChangeConstraints.weightx = 0;
						tensionChangeConstraints.weighty = 0;
						tensionChangeConstraints.fill = GridBagConstraints.BOTH;
						tensionChangeConstraints.insets = Constants.SMALL_INSETS;

					add(tensionChangeLabel, tensionChangeConstraints);

					tensionChangeLabels.add(tensionChangeLabel);

					strutHeight = Math.max(strutHeight, tensionChangeLabel.getPreferredSize().height);

					gridx++;
				}

				tensionChangeLabelLists.add(tensionChangeLabels);

					final JLabel tensionSumLabel = new JLabel();
					tensionSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints tensionSumConstraints = new GridBagConstraints();
					tensionSumConstraints.gridx = gridx;
					tensionSumConstraints.gridy = gridy;
					tensionSumConstraints.weightx = 1;
					tensionSumConstraints.weighty = 0;
					tensionSumConstraints.fill = GridBagConstraints.BOTH;
					tensionSumConstraints.insets = Constants.SMALL_INSETS;

				add(tensionSumLabel, tensionSumConstraints);

				tensionSumLabels.add(tensionSumLabel);

				strutHeight = Math.max(strutHeight, tensionSumLabel.getPreferredSize().height);

				gridx++;

					final JLabel tensionAbsoluteSumLabel = new JLabel();
					tensionAbsoluteSumLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final GridBagConstraints tensionAbsoluteSumConstraints = new GridBagConstraints();
					tensionAbsoluteSumConstraints.gridx = gridx;
					tensionAbsoluteSumConstraints.gridy = gridy;
					tensionAbsoluteSumConstraints.weightx = 1;
					tensionAbsoluteSumConstraints.weighty = 0;
					tensionAbsoluteSumConstraints.fill = GridBagConstraints.BOTH;
					tensionAbsoluteSumConstraints.insets = Constants.SMALL_INSETS;

				add(tensionAbsoluteSumLabel, tensionAbsoluteSumConstraints);

				tensionAbsoluteSumLabels.add(tensionAbsoluteSumLabel);

				strutHeight = Math.max(strutHeight, tensionAbsoluteSumLabel.getPreferredSize().height);
			}

			gridx++;

			gridy++;
		}

		gridx += 2;

		/*
		Builds the glue.
		*/
		{
				final Component glue = Box.createVerticalGlue();

				final GridBagConstraints glueConstraints = new GridBagConstraints();
				glueConstraints.gridx = gridx;
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
			final int halfStrutHeight = (strutHeight - 1) / 2 + 1//round up
					- Constants.SMALL_INSETS.top - Constants.SMALL_INSETS.bottom;

			while (gridy > 1) {
				gridy--;

					final Component strut = Box.createVerticalStrut(halfStrutHeight);

					final GridBagConstraints strutConstraints = new GridBagConstraints();
					strutConstraints.gridx = gridx;
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
	@param rows The amount of rows.
	**/
	public void setRows(final int rows) {
		this.rows = rows;
		for (final Component component : getComponents()) {//TODO wrangle
			final GridBagConstraints constraints = layout.getConstraints(component);
			layout.setConstraints(component, constraints);
		}
	}

	/**
	@return The amount of columns.
	**/
	public int getColumns() {
		return columns;
	}

	/**
	@param columns The amount of columns.
	**/
	public void setColumns(final int columns) {
		this.columns = columns;
		for (final Component component : getComponents()) {//TODO wrangle
			final GridBagConstraints constraints = layout.getConstraints(component);
			layout.setConstraints(component, constraints);
		}
	}

	/**
	@param row The row.
	@return The tuning label.
	**/
	public JLabel getTuningLabel(final int row) {
		return tuningLabels.get(row);
	}

	/**
	@param row The row.
	@param column The column.
	@return The transposition label.
	**/
	public JLabel getTranspositionLabel(final int row, final int column) {
		return transpositionLabelLists.get(row).get(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The tension label.
	**/
	public JLabel getTensionLabel(final int row, final int column) {
		return tensionLabelLists.get(row).get(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The transposition change label.
	**/
	public JLabel getTranspositionChangeLabel(final int row, final int column) {
		return transpositionChangeLabelLists.get(row).get(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The tension change label.
	**/
	public JLabel getTensionChangeLabel(final int row, final int column) {
		return tensionChangeLabelLists.get(row).get(column);
	}

	/**
	@param row The row.
	@return The transposition sum label.
	**/
	public JLabel getTranspositionSumLabel(final int row) {
		return transpositionSumLabels.get(row);
	}

	/**
	@param row The row.
	@return The absolute transposition sum label.
	**/
	public JLabel getTranspositionAbsoluteSumLabel(final int row) {
		return transpositionAbsoluteSumLabels.get(row);
	}

	/**
	@param row The row.
	@return The tension sum label.
	**/
	public JLabel getTensionSumLabel(final int row) {
		return tensionSumLabels.get(row);
	}

	/**
	@param row The row.
	@return The absolute tension sum label.
	**/
	public JLabel getTensionAbsoluteSumLabel(final int row) {
		return tensionAbsoluteSumLabels.get(row);
	}

	/**
	@param row The row after.
	@return The insert button.
	**/
	public JButton getSwapButton(final int row) {
		return swapButtons.get(row - 1);
	}
}
