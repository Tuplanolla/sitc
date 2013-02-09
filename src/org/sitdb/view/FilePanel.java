package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to load and save data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class FilePanel extends JPanel {
	private static final long serialVersionUID = 1569711177821808903l;

	private final JTextField pathTextField;
	private final JButton browseButton;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public FilePanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		pathTextField = new JTextField();

		browseButton = new JButton("Browse");
		Utilities.setScaledIcon(browseButton, Resources.BROWSE_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		final JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		filePanel.add(pathTextField, BorderLayout.CENTER);
		filePanel.add(browseButton, BorderLayout.EAST);

		titledBorder = new TitledBorder((String )null);

		setBorder(titledBorder);
		add(filePanel, BorderLayout.CENTER);
	}

	/**
	@return The path text field.
	**/
	public JTextField getPathTextField() {
		return pathTextField;
	}

	/**
	@return The browse button.
	**/
	public JButton getBrowseButton() {
		return browseButton;
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
