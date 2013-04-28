package org.sitc.views.swingview;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
Represents a dynamically adjusting panel that
 can be used to edit an instrument.

It's magical.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class InstrumentMagicPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private static final class Header {
		private final JLabel lengthLabel,
				densityLabel,
				tensionLabel,
				editLabel;

		public Header() {
			lengthLabel = new JLabel("Length (cm)");
			lengthLabel.setHorizontalAlignment(SwingConstants.CENTER);

			densityLabel = new JLabel("Density (g/m)");
			densityLabel.setHorizontalAlignment(SwingConstants.CENTER);

			tensionLabel = new JLabel("Maximum Tension (N)");
			tensionLabel.setHorizontalAlignment(SwingConstants.CENTER);

			editLabel = new JLabel("Edit");
			editLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}

		public JLabel getLengthLabel() {
			return lengthLabel;
		}

		public JLabel getDensityLabel() {
			return densityLabel;
		}

		public JLabel getTensionLabel() {
			return tensionLabel;
		}

		public JLabel getEditLabel() {
			return editLabel;
		}
	}

	private static final class Body {
		private final JButton insertButton;
		private final Component topStrut,
				bottomStrut;

		public Body() {
			insertButton = new JButton("Insert");
			Utilities.setScaledIcon(insertButton, Resources.loadAddIcon(), SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			topStrut = Box.createVerticalStrut(0);

			bottomStrut = Box.createVerticalStrut(0);
		}

		public JButton getInsertButton() {
			return insertButton;
		}

		public Component getTopStrut() {
			return topStrut;
		}

		public Component getBottomStrut() {
			return bottomStrut;
		}
	}

	private static final class BodyPart {
		private boolean last;
		private final JLabel numberLabel;
		private final JTextField lengthTextField,
				densityTextField,
				tensionTextField;
		private final JButton insertButton,
				deleteButton,
				swapButton;
		private final Component topStrut,
				bottomStrut;

		public BodyPart(final int row) {
			last = true;

			numberLabel = new JLabel("#" + row);
			numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			lengthTextField = new JTextField();
			lengthTextField.setHorizontalAlignment(SwingConstants.CENTER);

			densityTextField = new JTextField();
			densityTextField.setHorizontalAlignment(SwingConstants.CENTER);

			tensionTextField = new JTextField();
			tensionTextField.setHorizontalAlignment(SwingConstants.CENTER);

			insertButton = new JButton("Insert");
			Utilities.setScaledIcon(insertButton, Resources.loadAddIcon(), SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			deleteButton = new JButton("Delete");
			Utilities.setScaledIcon(deleteButton, Resources.loadRemoveIcon(), SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			swapButton = new JButton("Swap");
			Utilities.setScaledIcon(swapButton, Resources.loadUpDownIcon(), SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			topStrut = Box.createVerticalStrut(0);

			bottomStrut = Box.createVerticalStrut(0);
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

		public JTextField getLengthTextField() {
			return lengthTextField;
		}

		public JTextField getDensityTextField() {
			return densityTextField;
		}

		public JTextField getTensionTextField() {
			return tensionTextField;
		}

		public JButton getInsertButton() {
			return insertButton;
		}

		public JButton getDeleteButton() {
			return deleteButton;
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
	private int rows;
	private final Body body;
	private final List<BodyPart> bodyParts;
	private final Footer footer;

	/**
	Adjusts the row heights.
	**/
	private void adjustStruts() {
		final List<Integer> componentHeights = new ArrayList<>();
		componentHeights.add(body.getInsertButton().getPreferredSize().height);
		for (final BodyPart bodyPart : bodyParts) {
			componentHeights.add(bodyPart.getNumberLabel().getPreferredSize().height);
			componentHeights.add(bodyPart.getLengthTextField().getPreferredSize().height);
			componentHeights.add(bodyPart.getDensityTextField().getPreferredSize().height);
			componentHeights.add(bodyPart.getTensionTextField().getPreferredSize().height);
			componentHeights.add(bodyPart.getDeleteButton().getPreferredSize().height);
		}
		final int rowHeight = Collections.max(componentHeights);
		final int halfRowHeight = rowHeight / 2,
				leftoverRowHeight = rowHeight % 2;//no pixel left behind
		final Dimension topStrutSize = new Dimension(0, halfRowHeight),
				bottomStrutSize = new Dimension(0, halfRowHeight + leftoverRowHeight);
		Utilities.setAllSizes(body.getTopStrut(), bottomStrutSize);//intentionally mixed
		for (final BodyPart bodyPart : bodyParts) {
			Utilities.setAllSizes(bodyPart.getTopStrut(), topStrutSize);
			Utilities.setAllSizes(bodyPart.getBottomStrut(), bottomStrutSize);
		}
		Utilities.setAllSizes(body.getBottomStrut(), topStrutSize);
	}

	/**
	Builds the layout.
	**/
	private void build() {
		removeAll();

		adjustStruts();

		int gridy = 0;

		/*header: */{
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
				editConstraints.gridheight = 1;
				editConstraints.fill = GridBagConstraints.BOTH;
				editConstraints.insets = Constants.SMALL_INSETS;

			add(header.getLengthLabel(), lengthConstraints);
			add(header.getDensityLabel(), densityConstraints);
			add(header.getTensionLabel(), tensionConstraints);
			add(header.getEditLabel(), editConstraints);

			gridy++;
		}

		/*body: */{

				final GridBagConstraints firstInsertConstraints = new GridBagConstraints();
				firstInsertConstraints.gridx = 4;
				firstInsertConstraints.gridy = gridy;
				firstInsertConstraints.gridwidth = 1;
				firstInsertConstraints.gridheight = 2;
				firstInsertConstraints.fill = GridBagConstraints.BOTH;
				firstInsertConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints topmostConstraints = new GridBagConstraints();
				topmostConstraints.gridx = 7;
				topmostConstraints.gridy = gridy;
				topmostConstraints.fill = GridBagConstraints.VERTICAL;

			add(body.getInsertButton(), firstInsertConstraints);
			add(body.getTopStrut(), topmostConstraints);

			gridy++;

			for (int row = 0; row < rows; row++) {
				final BodyPart bodyPart = bodyParts.get(row);

					final GridBagConstraints numberConstraints = new GridBagConstraints();
					numberConstraints.gridx = 0;
					numberConstraints.gridy = gridy;
					numberConstraints.gridwidth = 1;
					numberConstraints.gridheight = 2;
					numberConstraints.fill = GridBagConstraints.BOTH;
					numberConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints lengthConstraints = new GridBagConstraints();
					lengthConstraints.gridx = 1;
					lengthConstraints.gridy = gridy;
					lengthConstraints.gridwidth = 1;
					lengthConstraints.gridheight = 2;
					lengthConstraints.weightx = 1;
					lengthConstraints.weighty = 0;
					lengthConstraints.fill = GridBagConstraints.BOTH;
					lengthConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints densityConstraints = new GridBagConstraints();
					densityConstraints.gridx = 2;
					densityConstraints.gridy = gridy;
					densityConstraints.gridwidth = 1;
					densityConstraints.gridheight = 2;
					densityConstraints.weightx = 1;
					densityConstraints.weighty = 0;
					densityConstraints.fill = GridBagConstraints.BOTH;
					densityConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints tensionConstraints = new GridBagConstraints();
					tensionConstraints.gridx = 3;
					tensionConstraints.gridy = gridy;
					tensionConstraints.gridwidth = 1;
					tensionConstraints.gridheight = 2;
					tensionConstraints.weightx = 1;
					tensionConstraints.weighty = 0;
					tensionConstraints.fill = GridBagConstraints.BOTH;
					tensionConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints deleteConstraints = new GridBagConstraints();
					deleteConstraints.gridx = 5;
					deleteConstraints.gridy = gridy;
					deleteConstraints.gridwidth = 1;
					deleteConstraints.gridheight = 2;
					deleteConstraints.fill = GridBagConstraints.BOTH;
					deleteConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints topConstraints = new GridBagConstraints();
					topConstraints.gridx = 7;
					topConstraints.gridy = gridy;
					topConstraints.fill = GridBagConstraints.VERTICAL;

				add(bodyPart.getNumberLabel(), numberConstraints);
				add(bodyPart.getLengthTextField(), lengthConstraints);
				add(bodyPart.getDensityTextField(), densityConstraints);
				add(bodyPart.getTensionTextField(), tensionConstraints);
				add(bodyPart.getDeleteButton(), deleteConstraints);
				add(bodyPart.getTopStrut(), topConstraints);

				gridy++;

				if (!bodyPart.getLast()) {
						final GridBagConstraints swapConstraints = new GridBagConstraints();
						swapConstraints.gridx = 6;
						swapConstraints.gridy = gridy;
						swapConstraints.gridwidth = 1;
						swapConstraints.gridheight = 2;
						swapConstraints.fill = GridBagConstraints.BOTH;
						swapConstraints.insets = Constants.SMALL_INSETS;

					add(bodyPart.getSwapButton(), swapConstraints);
				}

					final GridBagConstraints insertConstraints = new GridBagConstraints();
					insertConstraints.gridx = 4;
					insertConstraints.gridy = gridy;
					insertConstraints.gridwidth = 1;
					insertConstraints.gridheight = 2;
					insertConstraints.fill = GridBagConstraints.BOTH;
					insertConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints bottomConstraints = new GridBagConstraints();
					bottomConstraints.gridx = 7;
					bottomConstraints.gridy = gridy;
					bottomConstraints.fill = GridBagConstraints.VERTICAL;

				add(bodyPart.getInsertButton(), insertConstraints);
				add(bodyPart.getBottomStrut(), bottomConstraints);

				gridy++;
			}

				final GridBagConstraints bottommostConstraints = new GridBagConstraints();
				bottommostConstraints.gridx = 7;
				bottommostConstraints.gridy = gridy;
				bottommostConstraints.fill = GridBagConstraints.VERTICAL;

			add(body.getBottomStrut(), bottommostConstraints);

			gridy++;
		}

		/*footer: */{
				final GridBagConstraints glueConstraints = new GridBagConstraints();
				glueConstraints.gridx = 7;
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
	Creates an instrument magic panel.
	**/
	public InstrumentMagicPanel() {
		super(new GridBagLayout());

		footer = new Footer();

		rows = 0;
		body = new Body();
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
			final BodyPart lastBodyPart = bodyParts.get(nextRows - 1);
			lastBodyPart.setLast(true);
		case 1:
			bodyParts.remove(nextRows);
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
		while (this.rows < rows) addRow();
		while (this.rows > rows) removeRow();
		build();
	}

	/**
	@param row The row.
	@return The length text field.
	**/
	public JTextField getLengthTextField(final int row) {
		return bodyParts.get(row - 1).getLengthTextField();
	}

	/**
	@param row The row.
	@return The density text field.
	**/
	public JTextField getDensityTextField(final int row) {
		return bodyParts.get(row - 1).getDensityTextField();
	}

	/**
	@param row The row.
	@return The tension text field.
	**/
	public JTextField getTensionTextField(final int row) {
		return bodyParts.get(row - 1).getTensionTextField();
	}

	/**
	Returns the insert button between two rows.

	@param firstRow The first row.
	@param secondRow The second row.
	@return The insert button.
	**/
	public JButton getInsertButton(final int firstRow, final int secondRow) {
		final int row = firstRow + secondRow >>> 1;
		if (row == 0) return body.getInsertButton();
		return bodyParts.get(row - 1).getInsertButton();
	}

	/**
	@param row The row.
	@return The delete button.
	**/
	public JButton getDeleteButton(final int row) {
		return bodyParts.get(row - 1).getDeleteButton();
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
