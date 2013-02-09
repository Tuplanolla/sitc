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
public final class ManagerPanel extends JPanel {
	private static final long serialVersionUID = 7145893573631860866l;

	private final FilePanel filePanel;
	private final ListPanel listPanel;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public ManagerPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		filePanel = new FilePanel();

		listPanel = new ListPanel();

		final JPanel managerPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		managerPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		managerPanel.add(filePanel, BorderLayout.NORTH);
		managerPanel.add(listPanel, BorderLayout.CENTER);
	
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
