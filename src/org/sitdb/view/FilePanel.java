package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 load data,
 manage a local copy of the data and
 eventually save data.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class FilePanel extends JPanel {
	private static final long serialVersionUID = 1l;

	public final JTextField pathTextField;//TODO encapsulate
	public final JButton browseButton;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public FilePanel() {
		pathTextField = new JTextField();

		browseButton = new JButton("Browse");
		Utilities.setScaledIcon(browseButton, Resources.BROWSE_ICON, SwingConstants.HORIZONTAL, Constants.ICON_SCALE);

		final JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		filePanel.add(pathTextField, BorderLayout.CENTER);
		filePanel.add(browseButton, BorderLayout.EAST);

		titledBorder = new TitledBorder("File");

		setLayout(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		setBorder(titledBorder);
		add(filePanel, BorderLayout.CENTER);
	}

	/**
	Sets the title of this panel.

	@param title The title.
	**/
	public void setTitle(final String title) {
		titledBorder.setTitle(title);
	}
}
