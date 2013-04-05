package org.sitc.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
Represents a dynamically adjusting panel that
 can be used to edit a sequence.

It's magical.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class SequenceMagicPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private static class Row {
		private int columns;
		private final List<JLabel> labels;

		public Row() {
			columns = 0;
			labels = new ArrayList<>();
		}

		private void addColumn() {
			final JLabel label = new JLabel();
			label.setHorizontalAlignment(SwingConstants.CENTER);
			labels.add(label);
			columns++;
		}

		private void removeColumn() {
			final int nextColumns = columns - 1;
			switch (columns) {
			default:
				labels.remove(nextColumns);
				columns = nextColumns;
			case 0:
			}
		}

		public void setColumns(final int columns) {
			while (this.columns > columns) removeColumn();
			while (this.columns < columns) addColumn();
		}

		public JLabel getLabel(final int column) {
			return labels.get(column - 1);
		}
	}

	private static final class ChangeRow extends Row {
		private final JLabel sumLabel,
				absoluteSumLabel;

		public ChangeRow() {
			sumLabel = new JLabel();
			sumLabel.setHorizontalAlignment(SwingConstants.CENTER);

			absoluteSumLabel = new JLabel();
			absoluteSumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}

		public JLabel getSumLabel() {
			return sumLabel;
		}

		public JLabel getAbsoluteSumLabel() {
			return absoluteSumLabel;
		}
	}

	private static final class Header {
		private final JLabel tuningLabel,
				transpositionLabel,
				tensionLabel,
				editLabel;
		private final ChangeRow transpositionChangeRow,
				tensionChangeRow;

		public Header() {
			tuningLabel = new JLabel("Tuning");
			tuningLabel.setHorizontalAlignment(SwingConstants.CENTER);

			transpositionLabel = new JLabel("Transpositions");
			transpositionLabel.setHorizontalAlignment(SwingConstants.CENTER);

			tensionLabel = new JLabel("Tensions (N)");
			tensionLabel.setHorizontalAlignment(SwingConstants.CENTER);

			editLabel = new JLabel("Edit");
			editLabel.setHorizontalAlignment(SwingConstants.CENTER);

			transpositionChangeRow = new ChangeRow();

			tensionChangeRow = new ChangeRow();
		}

		public JLabel getTuningLabel() {
			return tuningLabel;
		}

		public JLabel getTranspositionLabel() {
			return transpositionLabel;
		}

		public JLabel getTensionLabel() {
			return tensionLabel;
		}

		public JLabel getEditLabel() {
			return editLabel;
		}

		public ChangeRow getTranspositionChangeRow() {
			return transpositionChangeRow;
		}

		public ChangeRow getTensionChangeRow() {
			return tensionChangeRow;
		}
	}

	private static final class BodyPart {
		private boolean last;
		private final JLabel numberLabel,
				tuningLabel;
		private final Row transpositionRow,
				tensionRow;
		private final ChangeRow transpositionChangeRow,
				tensionChangeRow;
		private final JButton swapButton;
		private final Component topStrut,
				bottomStrut,
				extraStrut;

		public BodyPart(final int row) {
			last = true;

			numberLabel = new JLabel("#" + row);
			numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			tuningLabel = new JLabel("?");
			tuningLabel.setHorizontalAlignment(SwingConstants.CENTER);

			transpositionRow = new Row();

			tensionRow = new Row();

			transpositionChangeRow = new ChangeRow();

			tensionChangeRow = new ChangeRow();

			swapButton = new JButton("Swap");
			Utilities.setScaledIcon(swapButton, Resources.UP_DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			topStrut = Box.createVerticalStrut(0);

			bottomStrut = Box.createVerticalStrut(0);

			extraStrut = Box.createVerticalStrut(0);
		}

		public boolean getLast() {
			return last;
		}

		public void setLast(final boolean last) {
			this.last = last;
		}

		public JLabel getNumberLabel() {
			return numberLabel;
		}

		public JLabel getTuningLabel() {
			return tuningLabel;
		}

		public Row getTranspositionRow() {
			return transpositionRow;
		}

		public Row getTensionRow() {
			return tensionRow;
		}

		public ChangeRow getTranspositionChangeRow() {
			return transpositionChangeRow;
		}

		public ChangeRow getTensionChangeRow() {
			return tensionChangeRow;
		}

		public JButton getSwapButton() {
			return swapButton;
		}

		public Component getTopStrut() {
			return topStrut;
		}

		public Component getBottomStrut() {
			return bottomStrut;
		}

		public Component getExtraStrut() {
			return extraStrut;
		}
	}

	private static final class Footer {
		private final Component glue;

		public Footer() {
			glue = Box.createGlue();
		}

		public Component getGlue() {
			return glue;
		}
	}

	private final Header header;
	private int rows,
			columns;
	private final List<BodyPart> bodyParts;
	private final Footer footer;

	/**
	Adjusts the row heights.
	**/
	private void adjustStruts() {
		if (bodyParts.size() < 1) return;
		final List<Integer> componentHeights = new ArrayList<>();
		for (final BodyPart bodyPart : bodyParts) {
			componentHeights.add(bodyPart.getNumberLabel().getPreferredSize().height);
			componentHeights.add(bodyPart.getTuningLabel().getPreferredSize().height);
		}
		final int rowHeight = Collections.max(componentHeights);
		final int halfRowHeight = rowHeight / 2,
				leftoverRowHeight = rowHeight % 2;//no pixel left behind
		final Dimension topStrutSize = new Dimension(0, halfRowHeight),
				bottomStrutSize = new Dimension(0, halfRowHeight + leftoverRowHeight);
		for (final BodyPart bodyPart : bodyParts) {
			Utilities.setAllSizes(bodyPart.getTopStrut(), topStrutSize);
			Utilities.setAllSizes(bodyPart.getBottomStrut(), bottomStrutSize);
			Utilities.setAllSizes(bodyPart.getExtraStrut(), bottomStrutSize);
		}
	}

	/**
	Builds the layout.
	**/
	private void build() {
		removeAll();

		adjustStruts();

		int gridy = 0,
				gridx = 0;
		final int gridwidth = columns + 2;

		/*header: */{
			gridx = 1;

				final GridBagConstraints tuningConstraints = new GridBagConstraints();
				tuningConstraints.gridx = gridx;
				tuningConstraints.gridy = gridy;
				tuningConstraints.gridwidth = 1;
				tuningConstraints.gridheight = 2;
				tuningConstraints.weightx = 1;
				tuningConstraints.weighty = 0;
				tuningConstraints.fill = GridBagConstraints.BOTH;
				tuningConstraints.insets = Constants.SMALL_INSETS;

			add(header.getTuningLabel(), tuningConstraints);

			gridx++;

				final GridBagConstraints transpositionConstraints = new GridBagConstraints();
				transpositionConstraints.gridx = gridx;
				transpositionConstraints.gridy = gridy;
				transpositionConstraints.gridwidth = gridwidth;
				transpositionConstraints.gridheight = 1;
				transpositionConstraints.weightx = 1;
				transpositionConstraints.weighty = 0;
				transpositionConstraints.fill = GridBagConstraints.BOTH;
				transpositionConstraints.insets = Constants.SMALL_INSETS;

			add(header.getTranspositionLabel(), transpositionConstraints);

			gridy++;

			{
				final ChangeRow changeRow = header.getTranspositionChangeRow();

				for (int column = 1; column <= columns; column++) {
						final JLabel label = changeRow.getLabel(column);
						label.setText("#" + column);

						final GridBagConstraints constraints = new GridBagConstraints();
						constraints.gridx = gridx;
						constraints.gridy = gridy;
						constraints.weightx = 1;
						constraints.weighty = 0;
						constraints.fill = GridBagConstraints.BOTH;
						constraints.insets = Constants.SMALL_INSETS;

					add(label, constraints);

					gridx++;
				}

					final JLabel sumLabel = changeRow.getSumLabel();
					sumLabel.setText("\u2211\u2206");

					final GridBagConstraints sumConstraints = new GridBagConstraints();
					sumConstraints.gridx = gridx;
					sumConstraints.gridy = gridy;
					sumConstraints.weightx = 1;
					sumConstraints.weighty = 0;
					sumConstraints.fill = GridBagConstraints.BOTH;
					sumConstraints.insets = Constants.SMALL_INSETS;

				add(sumLabel, sumConstraints);

				gridx++;

					final JLabel absoluteSumLabel = changeRow.getAbsoluteSumLabel();
					absoluteSumLabel.setText("\u2211|\u2206|");

					final GridBagConstraints absoluteSumConstraints = new GridBagConstraints();
					absoluteSumConstraints.gridx = gridx;
					absoluteSumConstraints.gridy = gridy;
					absoluteSumConstraints.weightx = 1;
					absoluteSumConstraints.weighty = 0;
					absoluteSumConstraints.fill = GridBagConstraints.BOTH;
					absoluteSumConstraints.insets = Constants.SMALL_INSETS;

				add(absoluteSumLabel, absoluteSumConstraints);

				gridx++;
			}

			gridy--;

				final GridBagConstraints tensionConstraints = new GridBagConstraints();
				tensionConstraints.gridx = gridx;
				tensionConstraints.gridy = gridy;
				tensionConstraints.gridwidth = gridwidth;
				tensionConstraints.gridheight = 1;
				tensionConstraints.weightx = 1;
				tensionConstraints.weighty = 0;
				tensionConstraints.fill = GridBagConstraints.BOTH;
				tensionConstraints.insets = Constants.SMALL_INSETS;

			add(header.getTensionLabel(), tensionConstraints);

			gridy++;

			{
				final ChangeRow changeRow = header.getTensionChangeRow();

				for (int column = 1; column <= columns; column++) {
						final JLabel label = changeRow.getLabel(column);
						label.setText("#" + column);

						final GridBagConstraints constraints = new GridBagConstraints();
						constraints.gridx = gridx;
						constraints.gridy = gridy;
						constraints.weightx = 1;
						constraints.weighty = 0;
						constraints.fill = GridBagConstraints.BOTH;
						constraints.insets = Constants.SMALL_INSETS;

					add(label, constraints);

					gridx++;
				}

					final JLabel sumLabel = changeRow.getSumLabel();
					sumLabel.setText("\u2211\u2206");

					final GridBagConstraints sumConstraints = new GridBagConstraints();
					sumConstraints.gridx = gridx;
					sumConstraints.gridy = gridy;
					sumConstraints.weightx = 1;
					sumConstraints.weighty = 0;
					sumConstraints.fill = GridBagConstraints.BOTH;
					sumConstraints.insets = Constants.SMALL_INSETS;

				add(sumLabel, sumConstraints);

				gridx++;

					final JLabel absoluteSumLabel = changeRow.getAbsoluteSumLabel();
					absoluteSumLabel.setText("\u2211|\u2206|");

					final GridBagConstraints absoluteSumConstraints = new GridBagConstraints();
					absoluteSumConstraints.gridx = gridx;
					absoluteSumConstraints.gridy = gridy;
					absoluteSumConstraints.weightx = 1;
					absoluteSumConstraints.weighty = 0;
					absoluteSumConstraints.fill = GridBagConstraints.BOTH;
					absoluteSumConstraints.insets = Constants.SMALL_INSETS;

				add(absoluteSumLabel, absoluteSumConstraints);

				gridx++;
			}

			gridy--;

				final GridBagConstraints editConstraints = new GridBagConstraints();
				editConstraints.gridx = gridx;
				editConstraints.gridy = gridy;
				editConstraints.gridwidth = 1;
				editConstraints.gridheight = 2;
				editConstraints.fill = GridBagConstraints.BOTH;
				editConstraints.insets = Constants.SMALL_INSETS;

			add(header.getEditLabel(), editConstraints);

			gridy += 2;
		}

		/*body: */{
			for (int row = 0; row < rows; row++) {
				gridx = 0;

				final BodyPart bodyPart = bodyParts.get(row);

					final GridBagConstraints numberConstraints = new GridBagConstraints();
					numberConstraints.gridx = gridx;
					numberConstraints.gridy = gridy;
					numberConstraints.gridwidth = 1;
					numberConstraints.gridheight = 2;
					numberConstraints.fill = GridBagConstraints.BOTH;
					numberConstraints.insets = Constants.SMALL_INSETS;

				add(bodyPart.getNumberLabel(), numberConstraints);

				gridx++;

					final GridBagConstraints tuningConstraints = new GridBagConstraints();
					tuningConstraints.gridx = gridx;
					tuningConstraints.gridy = gridy;
					tuningConstraints.gridwidth = 1;
					tuningConstraints.gridheight = 2;
					tuningConstraints.weightx = 1;
					tuningConstraints.weighty = 0;
					tuningConstraints.fill = GridBagConstraints.BOTH;
					tuningConstraints.insets = Constants.SMALL_INSETS;

				add(bodyPart.getTuningLabel(), tuningConstraints);

				gridx++;

				/*transpositions: */{
					final Row valueRow = bodyPart.getTranspositionRow();
					final ChangeRow changeRow = bodyPart.getTranspositionChangeRow();

					for (int column = 1; column <= columns; column++) {
							final JLabel valueLabel = valueRow.getLabel(column);
							valueLabel.setText("?");

						final GridBagConstraints valueConstraints = new GridBagConstraints();
						valueConstraints.gridx = gridx;
						valueConstraints.gridy = gridy;
						valueConstraints.gridwidth = 1;
						valueConstraints.gridheight = 2;
						valueConstraints.fill = GridBagConstraints.BOTH;
						valueConstraints.insets = Constants.SMALL_INSETS;

						add(valueLabel, valueConstraints);

						if (!bodyPart.getLast()) {
								gridy += 2;

								final JLabel changeLabel = changeRow.getLabel(column);
								changeLabel.setText("?");

								final GridBagConstraints changeConstraints = new GridBagConstraints();
								changeConstraints.gridx = gridx;
								changeConstraints.gridy = gridy;
								changeConstraints.fill = GridBagConstraints.BOTH;
								changeConstraints.insets = Constants.SMALL_INSETS;

							add(changeLabel, changeConstraints);

							gridy -= 2;
						}

						gridx++;
					}

					gridy += 2;

					if (!bodyPart.getLast()) {
							final JLabel sumLabel = changeRow.getSumLabel();
							sumLabel.setText("?");

							final GridBagConstraints sumConstraints = new GridBagConstraints();
							sumConstraints.gridx = gridx;
							sumConstraints.gridy = gridy;
							sumConstraints.fill = GridBagConstraints.BOTH;
							sumConstraints.insets = Constants.SMALL_INSETS;

						add(sumLabel, sumConstraints);

						gridx++;

							final JLabel absoluteSumLabel = changeRow.getAbsoluteSumLabel();
							absoluteSumLabel.setText("?");

							final GridBagConstraints absoluteSumConstraints = new GridBagConstraints();
							absoluteSumConstraints.gridx = gridx;
							absoluteSumConstraints.gridy = gridy;
							absoluteSumConstraints.fill = GridBagConstraints.BOTH;
							absoluteSumConstraints.insets = Constants.SMALL_INSETS;

						add(absoluteSumLabel, absoluteSumConstraints);

						gridx--;
					}

					gridy -= 2;

					gridx += 2;
				}

				/*tensions: */{
					final Row valueRow = bodyPart.getTensionRow();
					final ChangeRow changeRow = bodyPart.getTensionChangeRow();

					for (int column = 1; column <= columns; column++) {
							final JLabel valueLabel = valueRow.getLabel(column);
							valueLabel.setText("?");

						final GridBagConstraints valueConstraints = new GridBagConstraints();
						valueConstraints.gridx = gridx;
						valueConstraints.gridy = gridy;
						valueConstraints.gridwidth = 1;
						valueConstraints.gridheight = 2;
						valueConstraints.fill = GridBagConstraints.BOTH;
						valueConstraints.insets = Constants.SMALL_INSETS;

						add(valueLabel, valueConstraints);

						if (!bodyPart.getLast()) {
								gridy += 2;

								final JLabel changeLabel = changeRow.getLabel(column);
								changeLabel.setText("?");

								final GridBagConstraints changeConstraints = new GridBagConstraints();
								changeConstraints.gridx = gridx;
								changeConstraints.gridy = gridy;
								changeConstraints.fill = GridBagConstraints.BOTH;
								changeConstraints.insets = Constants.SMALL_INSETS;

							add(changeLabel, changeConstraints);

							gridy -= 2;
						}

						gridx++;
					}

					gridy += 2;

					if (!bodyPart.getLast()) {
							final JLabel sumLabel = changeRow.getSumLabel();
							sumLabel.setText("?");

							final GridBagConstraints sumConstraints = new GridBagConstraints();
							sumConstraints.gridx = gridx;
							sumConstraints.gridy = gridy;
							sumConstraints.fill = GridBagConstraints.BOTH;
							sumConstraints.insets = Constants.SMALL_INSETS;

						add(sumLabel, sumConstraints);

						gridx++;

							final JLabel absoluteSumLabel = changeRow.getAbsoluteSumLabel();
							absoluteSumLabel.setText("?");

							final GridBagConstraints absoluteSumConstraints = new GridBagConstraints();
							absoluteSumConstraints.gridx = gridx;
							absoluteSumConstraints.gridy = gridy;
							absoluteSumConstraints.fill = GridBagConstraints.BOTH;
							absoluteSumConstraints.insets = Constants.SMALL_INSETS;

						add(absoluteSumLabel, absoluteSumConstraints);
					}

					gridy -= 2;

					gridx++;
				}

				gridy++;

				if (!bodyPart.getLast()) {
						final GridBagConstraints swapConstraints = new GridBagConstraints();
						swapConstraints.gridx = gridx;
						swapConstraints.gridy = gridy;
						swapConstraints.gridwidth = 1;
						swapConstraints.gridheight = 3;
						swapConstraints.fill = GridBagConstraints.BOTH;
						swapConstraints.insets = Constants.SMALL_INSETS;

					add(bodyPart.getSwapButton(), swapConstraints);

					gridx++;

					gridy++;

						final GridBagConstraints extraConstraints = new GridBagConstraints();
						extraConstraints.gridx = gridx;
						extraConstraints.gridy = gridy;
						extraConstraints.fill = GridBagConstraints.VERTICAL;

					add(bodyPart.getExtraStrut(), extraConstraints);

					gridy--;

					gridx--;
				}

				gridx++;

				gridy--;

					final GridBagConstraints topConstraints = new GridBagConstraints();
					topConstraints.gridx = gridx;
					topConstraints.gridy = gridy;
					topConstraints.fill = GridBagConstraints.VERTICAL;

				add(bodyPart.getTopStrut(), topConstraints);

				gridy++;

					final GridBagConstraints bottomConstraints = new GridBagConstraints();
					bottomConstraints.gridx = gridx;
					bottomConstraints.gridy = gridy;
					bottomConstraints.fill = GridBagConstraints.VERTICAL;

				add(bodyPart.getBottomStrut(), bottomConstraints);

				gridy += 2;
			}
		}

		/*footer: */{
				final GridBagConstraints glueConstraints = new GridBagConstraints();
				glueConstraints.gridx = gridx;
				glueConstraints.gridy = gridy;
				glueConstraints.weightx = 0;
				glueConstraints.weighty = 1;
				glueConstraints.fill = GridBagConstraints.VERTICAL;

			add(footer.getGlue(), glueConstraints);
		}

		revalidate();
		repaint();
	}

	/**
	Creates a sequence magic panel.
	**/
	public SequenceMagicPanel() {
		super(new GridBagLayout());

		footer = new Footer();

		rows = 0;
		columns = 0;
		bodyParts = new ArrayList<>();

		header = new Header();

		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		build();
	}

	/**
	Adds a row.
	**/
	private void addRow() {
		final int nextRows = rows + 1;
		switch (rows) {
		default:
			final BodyPart secondLastBodyPart = bodyParts.get(rows - 1);
			secondLastBodyPart.setLast(false);
		case 0:
			final BodyPart lastBodyPart = new BodyPart(nextRows);
			bodyParts.add(lastBodyPart);
			lastBodyPart.setLast(true);
		}
		rows = nextRows;
	}

	/**
	Removes a row.
	**/
	private void removeRow() {
		final int nextRows = rows - 1;
		switch (rows) {
		default:
			bodyParts.remove(nextRows);
			final BodyPart lastBodyPart = bodyParts.get(nextRows - 1);
			lastBodyPart.setLast(true);
			rows = nextRows;
		case 0:
		}
	}

	/**
	@return The amount of rows.
	**/
	public int getRows() {
		return rows;
	}

	/**
	@param rows The new amount of rows.
	**/
	public void setRows(final int rows) {
		if (rows == this.rows) return;
		while (this.rows > rows) removeRow();
		while (this.rows < rows) addRow();
		build();
	}

	/**
	@return The amount of columns.
	**/
	public int getColumns() {
		return columns;
	}

	/**
	@param columns The new amount of columns.
	**/
	public void setColumns(final int columns) {
		if (columns == this.columns) return;
		this.columns = columns;
		header.getTranspositionChangeRow().setColumns(columns);
		header.getTensionChangeRow().setColumns(columns);
		for (final BodyPart bodyPart : bodyParts) {
			bodyPart.getTranspositionRow().setColumns(columns);
			bodyPart.getTensionRow().setColumns(columns);
			bodyPart.getTranspositionChangeRow().setColumns(columns);
			bodyPart.getTensionChangeRow().setColumns(columns);
		}
		build();
	}

	/**
	@param row The row.
	@return The tuning label.
	**/
	public JLabel getTuningLabel(final int row) {
		return bodyParts.get(row - 1).getTuningLabel();
	}

	/**
	@param row The row.
	@param column The column.
	@return The transposition label.
	**/
	public JLabel getTranspositionLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTranspositionRow().getLabel(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The tension label.
	**/
	public JLabel getTensionLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTensionRow().getLabel(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The transposition change label.
	**/
	public JLabel getTranspositionChangeLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTranspositionChangeRow().getLabel(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The tension change label.
	**/
	public JLabel getTensionChangeLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTensionChangeRow().getLabel(column);
	}

	/**
	@param row The row.
	@param column The column.
	@return The transposition change sum label.
	**/
	public JLabel getTranspositionSumLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTranspositionChangeRow().getSumLabel();
	}

	/**
	@param row The row.
	@param column The column.
	@return The transposition change absolute sum label.
	**/
	public JLabel getTranspositionAbsoluteSumLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTranspositionChangeRow().getAbsoluteSumLabel();
	}

	/**
	@param row The row.
	@param column The column.
	@return The tension change sum label.
	**/
	public JLabel getTensionSumLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTensionChangeRow().getSumLabel();
	}

	/**
	@param row The row.
	@param column The column.
	@return The tension change absolute sum label.
	**/
	public JLabel getTensionAbsoluteSumLabel(final int row, final int column) {
		return bodyParts.get(row - 1).getTensionChangeRow().getAbsoluteSumLabel();
	}

	/**
	Returns the swap button between two rows.

	@param firstRow The first row.
	@param secondRow The second row.
	@return The swap button.
	**/
	public JButton getSwapButton(final int firstRow, final int secondRow) {
		final int row = firstRow + secondRow >>> 1;
		return bodyParts.get(row - 1).getSwapButton();
	}
}
