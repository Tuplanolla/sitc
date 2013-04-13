package org.sitc.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
Represents a dynamically adjusting panel that
 can be used to edit a tuning.

It's magical.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class TuningMagicPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private static final class Header {
		private final JLabel useLabel,
				playLabel,
				noteLabel,
				frequencyLabel,
				transposeLabel,
				editLabel;

		public Header() {
			useLabel = new JLabel("Use");
			useLabel.setHorizontalAlignment(SwingConstants.CENTER);

			playLabel = new JLabel("Play");
			playLabel.setHorizontalAlignment(SwingConstants.CENTER);

			noteLabel = new JLabel("Note");
			noteLabel.setHorizontalAlignment(SwingConstants.CENTER);

			frequencyLabel = new JLabel("Frequency (Hz)");
			frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);

			transposeLabel = new JLabel("Transpose");
			transposeLabel.setHorizontalAlignment(SwingConstants.CENTER);

			editLabel = new JLabel("Edit");
			editLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}

		public JLabel getUseLabel() {
			return useLabel;
		}

		public JLabel getPlayLabel() {
			return playLabel;
		}

		public JLabel getNoteLabel() {
			return noteLabel;
		}

		public JLabel getFrequencyLabel() {
			return frequencyLabel;
		}

		public JLabel getTransposeLabel() {
			return transposeLabel;
		}

		public JLabel getEditLabel() {
			return editLabel;
		}
	}

	private static final class Body {
		private final JButton playAllButton,
				allUpButton,
				allDownButton,
				insertButton;
		private final Component topStrut,
				bottomStrut;

		public Body() {
			playAllButton = new JButton("Play");
			Utilities.setScaledIcon(playAllButton, Resources.PLAY_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			allUpButton = new JButton("Up");
			Utilities.setScaledIcon(allUpButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			allDownButton = new JButton("Down");
			Utilities.setScaledIcon(allDownButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			insertButton = new JButton("Insert");
			Utilities.setScaledIcon(insertButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			topStrut = Box.createVerticalStrut(0);

			bottomStrut = Box.createVerticalStrut(0);
		}

		public JButton getPlayAllButton() {
			return playAllButton;
		}

		public JButton getAllUpButton() {
			return allUpButton;
		}

		public JButton getAllDownButton() {
			return allDownButton;
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
		private final JCheckBox useCheckBox;
		private final JButton playButton;
		private final JTextField noteTextField,
				frequencyTextField;
		private final JButton upButton,
				downButton;
		private final JButton insertButton,
				deleteButton,
				swapButton;
		private final Component topStrut,
				bottomStrut;

		public BodyPart(final int row) {
			last = true;

			numberLabel = new JLabel("#" + row);
			numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			useCheckBox = new JCheckBox();
			useCheckBox.setSelected(true);

			playButton = new JButton("Play");
			Utilities.setScaledIcon(playButton, Resources.PLAY_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			noteTextField = new JTextField();
			noteTextField.setHorizontalAlignment(SwingConstants.CENTER);

			frequencyTextField = new JTextField();
			frequencyTextField.setHorizontalAlignment(SwingConstants.CENTER);

			upButton = new JButton("Up");
			Utilities.setScaledIcon(upButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			downButton = new JButton("Down");
			Utilities.setScaledIcon(downButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			insertButton = new JButton("Insert");
			Utilities.setScaledIcon(insertButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			deleteButton = new JButton("Delete");
			Utilities.setScaledIcon(deleteButton, Resources.MINUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			swapButton = new JButton("Swap");
			Utilities.setScaledIcon(swapButton, Resources.UP_DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

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

		public JCheckBox getUseCheckBox() {
			return useCheckBox;
		}

		public JButton getPlayButton() {
			return playButton;
		}

		public JTextField getNoteTextField() {
			return noteTextField;
		}

		public JTextField getFrequencyTextField() {
			return frequencyTextField;
		}

		public JButton getUpButton() {
			return upButton;
		}

		public JButton getDownButton() {
			return downButton;
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
			componentHeights.add(bodyPart.getUseCheckBox().getPreferredSize().height);
			componentHeights.add(bodyPart.getPlayButton().getPreferredSize().height);
			componentHeights.add(bodyPart.getNoteTextField().getPreferredSize().height);
			componentHeights.add(bodyPart.getFrequencyTextField().getPreferredSize().height);
			componentHeights.add(bodyPart.getUpButton().getPreferredSize().height
					+ bodyPart.getDownButton().getPreferredSize().height);
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
				final GridBagConstraints useConstraints = new GridBagConstraints();
				useConstraints.gridx = 1;
				useConstraints.gridy = gridy;
				useConstraints.fill = GridBagConstraints.BOTH;
				useConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints playConstraints = new GridBagConstraints();
				playConstraints.gridx = 2;
				playConstraints.gridy = gridy;
				playConstraints.gridwidth = 2;
				playConstraints.gridheight = 1;
				playConstraints.fill = GridBagConstraints.BOTH;
				playConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints noteConstraints = new GridBagConstraints();
				noteConstraints.gridx = 4;
				noteConstraints.gridy = gridy;
				noteConstraints.weightx = 1;
				noteConstraints.weighty = 0;
				noteConstraints.fill = GridBagConstraints.BOTH;
				noteConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints frequencyConstraints = new GridBagConstraints();
				frequencyConstraints.gridx = 5;
				frequencyConstraints.gridy = gridy;
				frequencyConstraints.weightx = 1;
				frequencyConstraints.weighty = 0;
				frequencyConstraints.fill = GridBagConstraints.BOTH;
				frequencyConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints transposeConstraints = new GridBagConstraints();
				transposeConstraints.gridx = 6;
				transposeConstraints.gridy = gridy;
				transposeConstraints.gridwidth = 2;
				transposeConstraints.gridheight = 1;
				transposeConstraints.fill = GridBagConstraints.BOTH;
				transposeConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints editConstraints = new GridBagConstraints();
				editConstraints.gridx = 8;
				editConstraints.gridy = gridy;
				editConstraints.gridwidth = 3;
				editConstraints.gridheight = 1;
				editConstraints.fill = GridBagConstraints.BOTH;
				editConstraints.insets = Constants.SMALL_INSETS;

			add(header.getUseLabel(), useConstraints);
			add(header.getPlayLabel(), playConstraints);
			add(header.getNoteLabel(), noteConstraints);
			add(header.getFrequencyLabel(), frequencyConstraints);
			add(header.getTransposeLabel(), transposeConstraints);
			add(header.getEditLabel(), editConstraints);

			gridy++;
		}

		/*body: */{
				final GridBagConstraints firstInsertConstraints = new GridBagConstraints();
				firstInsertConstraints.gridx = 8;
				firstInsertConstraints.gridy = gridy;
				firstInsertConstraints.gridwidth = 1;
				firstInsertConstraints.gridheight = 2;
				firstInsertConstraints.fill = GridBagConstraints.BOTH;
				firstInsertConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints topmostConstraints = new GridBagConstraints();
				topmostConstraints.gridx = 11;
				topmostConstraints.gridy = gridy;
				topmostConstraints.fill = GridBagConstraints.VERTICAL;

			add(body.getInsertButton(), firstInsertConstraints);
			add(body.getTopStrut(), topmostConstraints);

			gridy++;

				final Insets upInsets = new Insets(Constants.SMALL_INSET, Constants.SMALL_INSET, 0, Constants.SMALL_INSET),
						downInsets = new Insets(0, Constants.SMALL_INSET, Constants.SMALL_INSET, Constants.SMALL_INSET);

				final int gridx,
						gridwidth;
				if (rows > 1) {
					gridx = 7;
					gridwidth = 1;
				}
				else {
					gridx = 6;
					gridwidth = 2;
				}

				final GridBagConstraints playAllConstraints = new GridBagConstraints();
				playAllConstraints.gridx = 2;
				playAllConstraints.gridy = gridy;
				playAllConstraints.gridwidth = gridwidth;
				playAllConstraints.gridheight = 2 * rows;
				playAllConstraints.fill = GridBagConstraints.BOTH;
				playAllConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints allUpConstraints = new GridBagConstraints();
				allUpConstraints.gridx = gridx;
				allUpConstraints.gridy = gridy;
				allUpConstraints.gridwidth = gridwidth;
				allUpConstraints.gridheight = rows;
				allUpConstraints.fill = GridBagConstraints.BOTH;
				allUpConstraints.insets = upInsets;

				final GridBagConstraints allDownConstraints = new GridBagConstraints();
				allDownConstraints.gridx = gridx;
				allDownConstraints.gridy = gridy + rows;
				allDownConstraints.gridwidth = gridwidth;
				allDownConstraints.gridheight = rows;
				allDownConstraints.fill = GridBagConstraints.BOTH;
				allDownConstraints.insets = downInsets;

			add(body.getPlayAllButton(), playAllConstraints);
			add(body.getAllUpButton(), allUpConstraints);
			add(body.getAllDownButton(), allDownConstraints);

			for (int row = 0; row < rows; row++) {
				final BodyPart bodyPart = bodyParts.get(row);

					final GridBagConstraints numberConstraints = new GridBagConstraints();
					numberConstraints.gridx = 0;
					numberConstraints.gridy = gridy;
					numberConstraints.gridwidth = 1;
					numberConstraints.gridheight = 2;
					numberConstraints.fill = GridBagConstraints.BOTH;
					numberConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints useConstraints = new GridBagConstraints();
					useConstraints.gridx = 1;
					useConstraints.gridy = gridy;
					useConstraints.gridwidth = 1;
					useConstraints.gridheight = 2 * rows;
					useConstraints.fill = GridBagConstraints.BOTH;
					useConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints noteConstraints = new GridBagConstraints();
					noteConstraints.gridx = 4;
					noteConstraints.gridy = gridy;
					noteConstraints.gridwidth = 1;
					noteConstraints.gridheight = 2;
					noteConstraints.weightx = 1;
					noteConstraints.weighty = 0;
					noteConstraints.fill = GridBagConstraints.BOTH;
					noteConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints frequencyConstraints = new GridBagConstraints();
					frequencyConstraints.gridx = 5;
					frequencyConstraints.gridy = gridy;
					frequencyConstraints.gridwidth = 1;
					frequencyConstraints.gridheight = 2;
					frequencyConstraints.weightx = 1;
					frequencyConstraints.weighty = 0;
					frequencyConstraints.fill = GridBagConstraints.BOTH;
					frequencyConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints deleteConstraints = new GridBagConstraints();
					deleteConstraints.gridx = 9;
					deleteConstraints.gridy = gridy;
					deleteConstraints.gridwidth = 1;
					deleteConstraints.gridheight = 2;
					deleteConstraints.fill = GridBagConstraints.BOTH;
					deleteConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints topConstraints = new GridBagConstraints();
					topConstraints.gridx = 11;
					topConstraints.gridy = gridy;
					topConstraints.fill = GridBagConstraints.VERTICAL;

				add(bodyPart.getNumberLabel(), numberConstraints);
				add(bodyPart.getUseCheckBox(), useConstraints);
				add(bodyPart.getNoteTextField(), noteConstraints);
				add(bodyPart.getFrequencyTextField(), frequencyConstraints);
				add(bodyPart.getDeleteButton(), deleteConstraints);
				add(bodyPart.getTopStrut(), topConstraints);

				gridy++;

					final GridBagConstraints insertConstraints = new GridBagConstraints();
					insertConstraints.gridx = 8;
					insertConstraints.gridy = gridy;
					insertConstraints.gridwidth = 1;
					insertConstraints.gridheight = 2;
					insertConstraints.fill = GridBagConstraints.BOTH;
					insertConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints bottomConstraints = new GridBagConstraints();
					bottomConstraints.gridx = 11;
					bottomConstraints.gridy = gridy;
					bottomConstraints.fill = GridBagConstraints.VERTICAL;

				add(bodyPart.getInsertButton(), insertConstraints);
				add(bodyPart.getBottomStrut(), bottomConstraints);

				if (rows > 1) {
					gridy--;

						final GridBagConstraints playConstraints = new GridBagConstraints();
						playConstraints.gridx = 3;
						playConstraints.gridy = gridy;
						playConstraints.gridwidth = 1;
						playConstraints.gridheight = 2;
						playConstraints.fill = GridBagConstraints.BOTH;
						playConstraints.insets = Constants.SMALL_INSETS;

						final GridBagConstraints upConstraints = new GridBagConstraints();
						upConstraints.gridx = 6;
						upConstraints.gridy = gridy;
						upConstraints.fill = GridBagConstraints.BOTH;
						upConstraints.insets = upInsets;

					add(bodyPart.getPlayButton(), playConstraints);
					add(bodyPart.getUpButton(), upConstraints);

					gridy++;

						final GridBagConstraints downConstraints = new GridBagConstraints();
						downConstraints.gridx = 6;
						downConstraints.gridy = gridy;
						downConstraints.fill = GridBagConstraints.BOTH;
						downConstraints.insets = downInsets;

					add(bodyPart.getDownButton(), downConstraints);
				}

				if (!bodyPart.getLast()) {
						final GridBagConstraints swapConstraints = new GridBagConstraints();
						swapConstraints.gridx = 10;
						swapConstraints.gridy = gridy;
						swapConstraints.gridwidth = 1;
						swapConstraints.gridheight = 2;
						swapConstraints.fill = GridBagConstraints.BOTH;
						swapConstraints.insets = Constants.SMALL_INSETS;

					add(bodyPart.getSwapButton(), swapConstraints);
				}

				gridy++;
			}

				final GridBagConstraints bottommostConstraints = new GridBagConstraints();
				bottommostConstraints.gridx = 11;
				bottommostConstraints.gridy = gridy;
				bottommostConstraints.fill = GridBagConstraints.VERTICAL;

			add(body.getBottomStrut(), bottommostConstraints);

			gridy++;
		}

		/*footer: */{
				final GridBagConstraints glueConstraints = new GridBagConstraints();
				glueConstraints.gridx = 11;
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
	Creates a tuning magic panel.
	**/
	public TuningMagicPanel() {
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
		while (this.rows < rows) addRow();
		while (this.rows > rows) removeRow();
		build();
	}

	/**
	@param row The row.
	@return The use check box.
	**/
	public JCheckBox getUseCheckBox(final int row) {
		return bodyParts.get(row).getUseCheckBox();
	}

	/**
	@return The play all button.
	**/
	public JButton getPlayAllButton() {
		return body.getPlayAllButton();
	}

	/**
	@param row The row.
	@return The play button.
	**/
	public JButton getPlayButton(final int row) {
		return bodyParts.get(row).getPlayButton();
	}

	/**
	@param row The row.
	@return The note text field.
	**/
	public JTextField getNoteTextField(final int row) {
		return bodyParts.get(row - 1).getNoteTextField();
	}

	/**
	@param row The row.
	@return The frequency text field.
	**/
	public JTextField getFrequencyTextField(final int row) {
		return bodyParts.get(row - 1).getFrequencyTextField();
	}

	/**
	@param row The row.
	@return The up button.
	**/
	public JButton getUpButton(final int row) {
		return bodyParts.get(row).getUpButton();
	}

	/**
	@param row The row.
	@return The down button.
	**/
	public JButton getDownButton(final int row) {
		return bodyParts.get(row).getDownButton();
	}

	/**
	@return The all up button.
	**/
	public JButton getAllUpButton() {
		return body.getAllUpButton();
	}

	/**
	@return The all down button.
	**/
	public JButton getAllDownButton() {
		return body.getAllDownButton();
	}

	/**
	Returns the insert button between two rows.

	@param firstRow The first row.
	@param secondRow The second row.
	@return The insert button.
	**/
	public JButton getInsertButton(final int firstRow, final int secondRow) {
		final int row = firstRow + secondRow >>> 1;
		if (row == 1) return body.getInsertButton();
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
