package org.sitc.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
 can be used to edit a tuning.

It's magical.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class TuningMagicPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final GridBagLayout layout;
	private int rows;
	private final JButton playAllButton;
	private final List<JButton> playButtons;
	private final List<JTextField> noteTextFields,
			frequencyTextFields;
	private final List<JButton> upButtons,
			downButtons;
	private final JButton allUpButton,
			allDownButton;
	private final List<JButton> insertButtons,
			deleteButtons,
			swapButtons;

	/**
	Creates a tuning magic panel.

	@param rows The amount of rows.
	**/
	public TuningMagicPanel(final int rows) {
		super(new GridBagLayout());

		layout = (GridBagLayout )getLayout();//avoids creating a disposable FlowLayout
		this.rows = rows;
		playButtons = new ArrayList<>(rows);
		noteTextFields = new ArrayList<>(rows);
		frequencyTextFields = new ArrayList<>(rows);
		upButtons = new ArrayList<>(rows);
		downButtons = new ArrayList<>(rows);
		insertButtons = new ArrayList<>(rows + 1);
		deleteButtons = new ArrayList<>(rows);
		swapButtons = new ArrayList<>(rows - 1);

		/*
		Tracks the position.
		*/
		int gridy = 0;

		/*
		Builds the header row.
		*/
		{
				final JLabel playLabel = new JLabel("Play");
				playLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel noteLabel = new JLabel("Note");
				noteLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel frequencyLabel = new JLabel("Frequency (Hz)");
				frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel transposeLabel = new JLabel("Transpose");
				transposeLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final JLabel editLabel = new JLabel("Edit");
				editLabel.setHorizontalAlignment(SwingConstants.CENTER);

				final GridBagConstraints playConstraints = new GridBagConstraints();
				playConstraints.gridx = 1;
				playConstraints.gridy = gridy;
				playConstraints.gridwidth = 2;
				playConstraints.weightx = 0;
				playConstraints.weighty = 0;
				playConstraints.fill = GridBagConstraints.BOTH;
				playConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints noteConstraints = new GridBagConstraints();
				noteConstraints.gridx = 3;
				noteConstraints.gridy = gridy;
				noteConstraints.weightx = 1;
				noteConstraints.weighty = 0;
				noteConstraints.fill = GridBagConstraints.BOTH;
				noteConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints frequencyConstraints = new GridBagConstraints();
				frequencyConstraints.gridx = 4;
				frequencyConstraints.gridy = gridy;
				frequencyConstraints.weightx = 1;
				frequencyConstraints.weighty = 0;
				frequencyConstraints.fill = GridBagConstraints.BOTH;
				frequencyConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints transposeConstraints = new GridBagConstraints();
				transposeConstraints.gridx = 5;
				transposeConstraints.gridy = gridy;
				transposeConstraints.gridwidth = 2;
				transposeConstraints.weightx = 0;
				transposeConstraints.weighty = 0;
				transposeConstraints.fill = GridBagConstraints.BOTH;
				transposeConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints editConstraints = new GridBagConstraints();
				editConstraints.gridx = 7;
				editConstraints.gridy = gridy;
				editConstraints.gridwidth = 3;
				editConstraints.weightx = 0;
				editConstraints.weighty = 0;
				editConstraints.fill = GridBagConstraints.BOTH;
				editConstraints.insets = Constants.SMALL_INSETS;

			add(playLabel, playConstraints);
			add(noteLabel, noteConstraints);
			add(frequencyLabel, frequencyConstraints);
			add(transposeLabel, transposeConstraints);
			add(editLabel, editConstraints);

			gridy++;
		}

		/*
		Tracks the maximum row height.
		*/
		int strutHeight;

		/*
		Builds the first half row.
		*/
		{
				final JButton topInsertButton = new JButton("Insert");
				Utilities.setScaledIcon(topInsertButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				final GridBagConstraints topInsertConstraints = new GridBagConstraints();
				topInsertConstraints.gridx = 7;
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
		{
			final Insets upInsets = new Insets(Constants.SMALL_INSET, Constants.SMALL_INSET, 0, Constants.SMALL_INSET),
					downInsets = new Insets(0, Constants.SMALL_INSET, Constants.SMALL_INSET, Constants.SMALL_INSET);

			for (int row = 1; row <= rows; row++) {
					final JLabel numberLabel = new JLabel("#" + row);
					numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

					final JButton playButton = new JButton("Play");
					Utilities.setScaledIcon(playButton, Resources.PLAY_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

					final JTextField noteTextField = new JTextField();
					noteTextField.setHorizontalAlignment(SwingConstants.CENTER);

					final JTextField frequencyTextField = new JTextField();
					frequencyTextField.setHorizontalAlignment(SwingConstants.CENTER);

					final JButton upButton = new JButton("Up");
					Utilities.setScaledIcon(upButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

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

					final GridBagConstraints playConstraints = new GridBagConstraints();
					playConstraints.gridx = 2;
					playConstraints.gridy = gridy;
					playConstraints.gridheight = 2;
					playConstraints.weightx = 0;
					playConstraints.weighty = 0;
					playConstraints.fill = GridBagConstraints.BOTH;
					playConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints noteConstraints = new GridBagConstraints();
					noteConstraints.gridx = 3;
					noteConstraints.gridy = gridy;
					noteConstraints.gridheight = 2;
					noteConstraints.weightx = 1;
					noteConstraints.weighty = 0;
					noteConstraints.fill = GridBagConstraints.BOTH;
					noteConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints frequencyConstraints = new GridBagConstraints();
					frequencyConstraints.gridx = 4;
					frequencyConstraints.gridy = gridy;
					frequencyConstraints.gridheight = 2;
					frequencyConstraints.weightx = 1;
					frequencyConstraints.weighty = 0;
					frequencyConstraints.fill = GridBagConstraints.BOTH;
					frequencyConstraints.insets = Constants.SMALL_INSETS;

					final GridBagConstraints upConstraints = new GridBagConstraints();
					upConstraints.gridx = 5;
					upConstraints.gridy = gridy;
					upConstraints.weightx = 0;
					upConstraints.weighty = 0;
					upConstraints.fill = GridBagConstraints.BOTH;
					upConstraints.insets = upInsets;

					final GridBagConstraints deleteConstraints = new GridBagConstraints();
					deleteConstraints.gridx = 8;
					deleteConstraints.gridy = gridy;
					deleteConstraints.gridheight = 2;
					deleteConstraints.weightx = 0;
					deleteConstraints.weighty = 0;
					deleteConstraints.fill = GridBagConstraints.BOTH;
					deleteConstraints.insets = Constants.SMALL_INSETS;

				add(numberLabel, numberConstraints);
				add(playButton, playConstraints);
				add(noteTextField, noteConstraints);
				add(frequencyTextField, frequencyConstraints);
				add(upButton, upConstraints);
				add(deleteButton, deleteConstraints);

				playButtons.add(playButton);
				upButtons.add(upButton);
				deleteButtons.add(deleteButton);

				strutHeight = Math.max(strutHeight, numberLabel.getPreferredSize().height);
				strutHeight = Math.max(strutHeight, playButton.getPreferredSize().height);
				strutHeight = Math.max(strutHeight, noteTextField.getPreferredSize().height);
				strutHeight = Math.max(strutHeight, frequencyTextField.getPreferredSize().height);
				strutHeight = Math.max(strutHeight, deleteButton.getPreferredSize().height);

				gridy++;

					final JButton downButton = new JButton("Down");
					Utilities.setScaledIcon(downButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

					final JButton insertButton = new JButton("Insert");
					Utilities.setScaledIcon(insertButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

					final GridBagConstraints downConstraints = new GridBagConstraints();
					downConstraints.gridx = 5;
					downConstraints.gridy = gridy;
					downConstraints.weightx = 0;
					downConstraints.weighty = 0;
					downConstraints.fill = GridBagConstraints.BOTH;
					downConstraints.insets = downInsets;

					final GridBagConstraints insertConstraints = new GridBagConstraints();
					insertConstraints.gridx = 7;
					insertConstraints.gridy = gridy;
					insertConstraints.gridheight = 2;
					insertConstraints.weightx = 0;
					insertConstraints.weighty = 0;
					insertConstraints.fill = GridBagConstraints.BOTH;
					insertConstraints.insets = Constants.SMALL_INSETS;

				add(downButton, downConstraints);
				add(insertButton, insertConstraints);

				downButtons.add(downButton);
				insertButtons.add(insertButton);

				strutHeight = Math.max(strutHeight, insertButton.getPreferredSize().height);
				strutHeight = Math.max(strutHeight, upButton.getPreferredSize().height
						+ downButton.getPreferredSize().height);

				if (row != rows) {//not last
						final JButton swapButton = new JButton("Swap");
						Utilities.setScaledIcon(swapButton, Resources.UP_DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

						final GridBagConstraints swapConstraints = new GridBagConstraints();
						swapConstraints.gridx = 9;
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
		}

		/*
		Builds the all-encompassing buttons.
		*/
		{
			final int gridheight = gridy - 2,
					halfGridheight = gridheight / 2;

				playAllButton = new JButton("Play");
				Utilities.setScaledIcon(playAllButton, Resources.PLAY_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				allUpButton = new JButton("Up");
				Utilities.setScaledIcon(allUpButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				allDownButton = new JButton("Down");
				Utilities.setScaledIcon(allDownButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

				final GridBagConstraints playConstraints = new GridBagConstraints();
				playConstraints.gridx = 1;
				playConstraints.gridy = 2;
				playConstraints.gridheight = gridheight;
				playConstraints.weightx = 0;
				playConstraints.weighty = 0;
				playConstraints.fill = GridBagConstraints.BOTH;
				playConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints upConstraints = new GridBagConstraints();
				upConstraints.gridx = 6;
				upConstraints.gridy = 2;
				upConstraints.gridheight = halfGridheight;
				upConstraints.weightx = 0;
				upConstraints.weighty = 0;
				upConstraints.fill = GridBagConstraints.BOTH;
				upConstraints.insets = Constants.SMALL_INSETS;

				final GridBagConstraints downConstraints = new GridBagConstraints();
				downConstraints.gridx = 6;
				downConstraints.gridy = 2 + halfGridheight;
				downConstraints.gridheight = halfGridheight;
				downConstraints.weightx = 0;
				downConstraints.weighty = 0;
				downConstraints.fill = GridBagConstraints.BOTH;
				downConstraints.insets = Constants.SMALL_INSETS;

			add(playAllButton, playConstraints);
			add(allUpButton, upConstraints);
			add(allDownButton, downConstraints);
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
				glueConstraints.gridx = 10;
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
	@return The play all button.
	**/
	public JButton getPlayAllButton() {
		return playAllButton;
	}

	/**
	@param row The row after.
	@return The play button.
	**/
	public JButton getPlayButton(final int row) {
		return playButtons.get(row);
	}

	/**
	@param row The row.
	@return The note text field.
	**/
	public JTextField getNoteTextField(final int row) {
		return noteTextFields.get(row);
	}

	/**
	@param row The row.
	@return The frequency text field.
	**/
	public JTextField getFrequencyTextField(final int row) {
		return frequencyTextFields.get(row);
	}

	/**
	@param row The row.
	@return The up button.
	**/
	public JButton getUpButton(final int row) {
		return upButtons.get(row);
	}

	/**
	@param row The row after.
	@return The down button.
	**/
	public JButton getDownButton(final int row) {
		return downButtons.get(row);
	}

	/**
	@return The all up button.
	**/
	public JButton getAllUpButton() {
		return allUpButton;
	}

	/**
	@return The all down button.
	**/
	public JButton getAllDownButton() {
		return allDownButton;
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
