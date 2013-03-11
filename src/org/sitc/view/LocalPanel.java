package org.sitc.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a list panel that
 manages a local copy of a database.

@param <Type> The type of the list model.
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class LocalPanel<Type> extends JPanel {
	private static final long serialVersionUID = 1;

	private final LocalInterfacePanel interfacePanel;
	private final TitledBorder titledBorder;
	private final JTextField searchTextField;
	private final JButton searchButton;
	private final JLabel searchLabel;
	private final DefaultListModel<Type> listModel;
	private final JList<Type> list;
	private final JButton newButton,
			deleteButton;

	/**
	Creates a list panel.
	**/
	public LocalPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			interfacePanel = new LocalInterfacePanel();

				titledBorder = new TitledBorder((String )null);

						searchTextField = new JTextField();

						searchButton = new JButton("Search");
						Utilities.setScaledIcon(searchButton, Resources.SEARCH_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

						searchLabel = new JLabel("Use Regular Expressions");

					final JPanel searchPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
					searchPanel.add(searchTextField, BorderLayout.CENTER);
					searchPanel.add(searchButton, BorderLayout.EAST);
					searchPanel.add(searchLabel, BorderLayout.SOUTH);

							listModel = new DefaultListModel<Type>();

						list = new JList<Type>(listModel);
						list.setLayoutOrientation(JList.VERTICAL);
						list.setVisibleRowCount(2);
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					final JScrollPane scrollPane = new JScrollPane(list);
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
				listPanel.add(scrollPane, BorderLayout.CENTER);
				listPanel.add(existencePanel, BorderLayout.SOUTH);

			final JPanel titledListPanel = new JPanel(new BorderLayout());
			titledListPanel.setBorder(titledBorder);
			titledListPanel.add(listPanel, BorderLayout.CENTER);

		add(interfacePanel, BorderLayout.NORTH);
		add(titledListPanel, BorderLayout.CENTER);
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

	/**
	@return The interface panel.
	**/
	public LocalInterfacePanel getInterfacePanel() {
		return interfacePanel;
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
	@return The search label.
	**/
	public JLabel getSearchLabel() {
		return searchLabel;
	}

	/**
	@return The list.
	**/
	public JList<Type> getList() {
		return list;
	}

	/**
	@return The list model.
	**/
	public DefaultListModel<Type> getListModel() {
		return listModel;
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
}
