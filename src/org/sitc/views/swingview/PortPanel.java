package org.sitc.views.swingview;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
Represents a port panel that
 manages data conversion.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class PortPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final JButton importButton,
			exportButton;

	/**
	Creates a port panel.
	**/
	public PortPanel() {
		super(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			importButton = new JButton("Import");
			Utilities.setScaledIcon(importButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

			exportButton = new JButton("Export");
			Utilities.setScaledIcon(exportButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		add(importButton);
		add(exportButton);
	}

	/**
	@return The import button.
	**/
	public JButton getImportButton() {
		return importButton;
	}

	/**
	@return The export button.
	**/
	public JButton getExportButton() {
		return exportButton;
	}
}