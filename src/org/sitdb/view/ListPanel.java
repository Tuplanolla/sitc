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
Represents a panel that's used to manage a local copy of data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ListPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final JTextField searchTextField;
	private final JButton searchButton;
	private final JList<String> list;//TODO <org.sitdb.model.*>
	private final JButton newButton,
			deleteButton;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public ListPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		searchTextField = new JTextField();

		searchButton = new JButton("Search");
		Utilities.setScaledIcon(searchButton, Resources.SEARCH_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

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
		Utilities.setScaledIcon(newButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		deleteButton = new JButton("Delete");
		Utilities.setScaledIcon(deleteButton, Resources.MINUS_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		final JPanel existencePanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		existencePanel.add(newButton);
		existencePanel.add(deleteButton);

		final JPanel listPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		listPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		listPanel.add(searchPanel, BorderLayout.NORTH);
		listPanel.add(list, BorderLayout.CENTER);
		listPanel.add(existencePanel, BorderLayout.SOUTH);

		titledBorder = new TitledBorder((String )null);

		setBorder(titledBorder);
		add(listPanel, BorderLayout.CENTER);
	}

	/**
	@return The search text field.
	**/
	public JTextField getSearchTextField() {
		return searchTextField;
	}

	/**
	@return The search button.
	**/
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	@return The list.
	**/
	public JList<String> getList() {
		return list;
	}

	/**
	@return The new button.
	**/
	public JButton getNewButton() {
		return newButton;
	}

	/**
	@return The delete button.
	**/
	public JButton getDeleteButton() {
		return deleteButton;
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
