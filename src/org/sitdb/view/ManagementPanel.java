package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 load data,
 manage a local copy of a portion of the data and
 eventually save the data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ManagementPanel extends JPanel {
	private static final long serialVersionUID = 7145893573631860866l;

	private final FilePanel filePanel;
	private final ListPanel listPanel;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public ManagementPanel() {
		filePanel = new FilePanel();

		listPanel = new ListPanel();

		final JPanel managementPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		managementPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		managementPanel.add(filePanel, BorderLayout.NORTH);
		managementPanel.add(listPanel, BorderLayout.CENTER);
	
		titledBorder = new TitledBorder((String )null);
	
		setLayout(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		setBorder(titledBorder);
		add(managementPanel, BorderLayout.CENTER);
	}

	/**
	@return The file panel.
	**/
	public FilePanel getFilePanel() {
		return filePanel;
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
