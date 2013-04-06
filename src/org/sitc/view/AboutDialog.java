package org.sitc.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.sitc.Assembly;

/**
Represents an about dialog.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public class AboutDialog extends JDialog {
	private static final long serialVersionUID = 1;

	/**
	Creates and initializes an about dialog.

	@param owner The parent window.
	**/
	public AboutDialog(final Window owner) {
		super(owner);

			final JLabel iconPanel = new JLabel();
			iconPanel.setIcon(Resources.ICON);
			iconPanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));

					final JLabel nameLabel = new JLabel(Assembly.NAME);
					final Font font = nameLabel.getFont().deriveFont(Font.BOLD);
					nameLabel.setFont(font);

					final JLabel versionLabel = new JLabel("Version: " + Assembly.VERSION);

					final JLabel authorLabel = new JLabel("Author: " + Assembly.AUTHOR);

					final JLabel licenseLabel = new JLabel("License: " + Assembly.LICENSE);

				final JPanel infoPanel = new JPanel(new GridLayout(4, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
				infoPanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
				infoPanel.add(nameLabel);
				infoPanel.add(versionLabel);
				infoPanel.add(authorLabel);
				infoPanel.add(licenseLabel);

			final JPanel containerPanel = new JPanel(new GridBagLayout());
			containerPanel.add(infoPanel);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("About " + Assembly.ABBREVIATION);
		setIconImages(Resources.ICON_IMAGES);
		setLayout(new BorderLayout());
		add(iconPanel, BorderLayout.WEST);
		add(containerPanel, BorderLayout.CENTER);

		pack();
		Utilities.allStatesChanged(this);
	}
}
