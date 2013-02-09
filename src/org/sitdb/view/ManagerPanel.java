package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 load data,
 manage a local copy of the data and
 eventually save the data.

The panel has the following structural hierarchy:

<pre>
+-------------------+
| ManagerPanel      |
| +---------------+ |
| | FilePanel     | |
| +---------------+ |
| +---------------+ |
| | JPanel        | |
| | +-----------+ | |
| | | JPanel    | | |
| | +-----------+ | |
| | +-----------+ | |
| | | ListPanel | | |
| | +-----------+ | |
| +---------------+ |
+-------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ManagerPanel extends JPanel {
	private static final long serialVersionUID = 7145893573631860866l;

	private final JButton loadButton,
			saveButton;
	private final FilePanel filePanel;
	private final ListPanel listPanel;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public ManagerPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		filePanel = new FilePanel();

		loadButton = new JButton("Load");
		Utilities.setScaledIcon(loadButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		saveButton = new JButton("Save");
		Utilities.setScaledIcon(saveButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		final JPanel interfacePanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		interfacePanel.add(loadButton);
		interfacePanel.add(saveButton);

		listPanel = new ListPanel();

		final JPanel somePanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		somePanel.add(interfacePanel, BorderLayout.NORTH);
		somePanel.add(listPanel, BorderLayout.CENTER);

		final JPanel managerPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		managerPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		managerPanel.add(filePanel, BorderLayout.NORTH);
		managerPanel.add(somePanel, BorderLayout.CENTER);

		titledBorder = new TitledBorder((String )null);

		setBorder(titledBorder);
		add(managerPanel, BorderLayout.CENTER);
	}

	/**
	@return The file panel.
	**/
	public FilePanel getFilePanel() {
		return filePanel;
	}

	/**
	@return The load button.
	**/
	public JButton getLoadButton() {
		return loadButton;
	}

	/**
	@return The save button.
	**/
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	@return The list panel.
	**/
	public ListPanel getListPanel() {
		return listPanel;
	}

	/**
	@return The title.
	**/
	public String getTitle() {
		return titledBorder.getTitle();
	}

	/**
	@param title The new title.
	**/
	public void setTitle(final String title) {
		titledBorder.setTitle(title);
	}
}
