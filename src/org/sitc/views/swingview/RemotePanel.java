package org.sitc.views.swingview;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a remote (file) panel that
 manages a database connection.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class RemotePanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final TitledBorder titledBorder;
	private final JTextField pathTextField;
	private final JButton browseButton;

	/**
	Creates a remote panel.
	**/
	public RemotePanel() {
		super(new BorderLayout());

			titledBorder = new TitledBorder((String )null);

				pathTextField = new JTextField();

				browseButton = new JButton("Browse");
				Utilities.setScaledIcon(browseButton, Resources.BROWSE_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			final JPanel filePanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			filePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
			filePanel.add(pathTextField, BorderLayout.CENTER);
			filePanel.add(browseButton, BorderLayout.EAST);

		setBorder(titledBorder);
		add(filePanel, BorderLayout.CENTER);
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
}
