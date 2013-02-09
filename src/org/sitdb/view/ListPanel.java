package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 load data,
 manage a local copy of the data and
 eventually save data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ListPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	public final JButton loadButton,//TODO encapsulate
			saveButton;
	public final JTextField searchTextField;
	public final JButton searchButton;
	public final JList<String> list;//TODO <org.sitdb.model.*>
	public final JButton newButton,
			deleteButton;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public ListPanel() {
		loadButton = new JButton("Load");
		Utilities.setScaledIcon(loadButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		saveButton = new JButton("Save");
		Utilities.setScaledIcon(saveButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		final JPanel interfacePanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		interfacePanel.add(loadButton);
		interfacePanel.add(saveButton);

		searchTextField = new JTextField();

		searchButton = new JButton("Search");
		Utilities.setScaledIcon(searchButton, Resources.SEARCH_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		final JPanel searchPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		searchPanel.add(searchTextField, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);

		list = new JList<String>(new String[] {
			"List Item",
			"Another List Item",
			"Yet Another List Item"
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED));
		list.setBorder(new JTextField().getBorder());//TODO test

		newButton = new JButton("New");
		Utilities.setScaledIcon(newButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		deleteButton = new JButton("Delete");
		Utilities.setScaledIcon(deleteButton, Resources.MINUS_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		final JPanel existencePanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		existencePanel.add(newButton);
		existencePanel.add(deleteButton);

		final JPanel listPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		listPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		listPanel.add(searchPanel, BorderLayout.NORTH);
		listPanel.add(list, BorderLayout.CENTER);
		listPanel.add(existencePanel, BorderLayout.SOUTH);

		titledBorder = new TitledBorder("List");

		final JPanel titledListPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledListPanel.setBorder(titledBorder);
		titledListPanel.add(listPanel, BorderLayout.CENTER);

		setLayout(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		add(interfacePanel, BorderLayout.NORTH);
		add(titledListPanel, BorderLayout.CENTER);
	}

	/**
	Sets the title of this panel.

	@param title The title.
	**/
	public void setTitle(final String title) {
		titledBorder.setTitle(title);
	}
}
