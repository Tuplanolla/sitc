package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 import data,
 edit a local copy of the data and
 eventually export the data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class EditorPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	public final JButton revertButton,
			applyButton;
	public final JButton importButton,
			exportButton;
	public final JPanel contentPanel;
	private final TitledBorder border;

	/**
	Constructs a new panel.
	**/
	public EditorPanel() {
		revertButton = new JButton("Revert");
		Utilities.setScaledIcon(revertButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, Constants.ICON_SCALE);

		applyButton = new JButton("Apply");
		Utilities.setScaledIcon(applyButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, Constants.ICON_SCALE);

		final GridBagConstraints revertButtonConstraints = new GridBagConstraints();
		revertButtonConstraints.gridx = 0;
		revertButtonConstraints.gridy = 0;
		revertButtonConstraints.weightx = 1;
		revertButtonConstraints.weighty = 0;
		revertButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		revertButtonConstraints.insets = new Insets(0, 0, Constants.MEDIUM_INSET / 2, 0);

		final GridBagConstraints applyButtonConstraints = new GridBagConstraints();
		applyButtonConstraints.gridx = 0;
		applyButtonConstraints.gridy = 1;
		applyButtonConstraints.weightx = 1;
		applyButtonConstraints.weighty = 0;
		applyButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		applyButtonConstraints.insets = new Insets(Constants.MEDIUM_INSET / 2, 0, 0, 0);

		final JPanel sidePanel = new JPanel(new GridBagLayout());
		sidePanel.add(revertButton, revertButtonConstraints);
		sidePanel.add(applyButton, applyButtonConstraints);

		contentPanel = new JPanel();

		importButton = new JButton("Import");
		Utilities.setScaledIcon(importButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		exportButton = new JButton("Export");
		Utilities.setScaledIcon(exportButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		final JPanel portPanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		portPanel.add(importButton);
		portPanel.add(exportButton);

		final JPanel mainPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		mainPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(portPanel, BorderLayout.SOUTH);

		border = new TitledBorder((String )null);

		final JPanel titledMainPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledMainPanel.setBorder(border);
		titledMainPanel.add(mainPanel, BorderLayout.CENTER);

		setLayout(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		add(sidePanel, BorderLayout.WEST);
		add(titledMainPanel, BorderLayout.CENTER);
	}

	/**
	Sets the title of this panel.

	@param title The title.
	**/
	public void setTitle(final String title) {
		border.setTitle(title);
	}
}