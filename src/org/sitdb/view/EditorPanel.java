package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 import data,
 edit a local copy of the data and
 eventually export the data.

The panel has the following structural hierarchy:

<pre>
+------------------------------------------+
| EditorPanel<SideType, ContentType>       |
| +--------------+ +---------------------+ |
| | JPanel       | | JPanel              | |
| |              | | +-----------------+ | |
| |              | | | JPanel          | | |
| |              | | | +-------------+ | | |
| | +----------+ | | | | ContentType | | | |
| | | SideType | | | | +-------------+ | | |
| | +----------+ | | +-----------------+ | |
| |              | | +-----------------+ | |
| |              | | | JPanel          | | |
| |              | | +-----------------+ | |
| +--------------+ +---------------------+ |
+------------------------------------------+
</pre>

@param <SideType> The type of the side panel.
@param <ContentType> The type of the main panel.
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class EditorPanel<SideType extends JPanel, ContentType extends JPanel> extends JPanel {
	private static final long serialVersionUID = 1584690058857036971l;

	private final JPanel sidePanelContainer;
	private SideType sidePanel;
	private final JButton importButton,
			exportButton;
	private final JPanel contentPanelContainer;
	private ContentType contentPanel;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public EditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		sidePanelContainer = new JPanel(new BorderLayout());

		importButton = new JButton("Import");
		Utilities.setScaledIcon(importButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		exportButton = new JButton("Export");
		Utilities.setScaledIcon(exportButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		contentPanelContainer = new JPanel(new BorderLayout());

		final JPanel portPanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		portPanel.add(importButton);
		portPanel.add(exportButton);

		final JPanel mainPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		mainPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		mainPanel.add(contentPanelContainer, BorderLayout.CENTER);
		mainPanel.add(portPanel, BorderLayout.SOUTH);

		titledBorder = new TitledBorder((String )null);

		final JPanel titledMainPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledMainPanel.setBorder(titledBorder);
		titledMainPanel.add(mainPanel, BorderLayout.CENTER);

		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		add(sidePanelContainer, BorderLayout.WEST);
		add(titledMainPanel, BorderLayout.CENTER);
	}

	/**
	@return The side panel.
	**/
	public SideType getSidePanel() {
		return sidePanel;
	}

	/**
	@param sidePanel The new side panel.
	**/
	public void setSidePanel(final SideType sidePanel) {
		sidePanelContainer.removeAll();
		sidePanelContainer.add(sidePanel, BorderLayout.CENTER);
		this.sidePanel = sidePanel;
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

	/**
	@return The content panel.
	**/
	public ContentType getContentPanel() {
		return contentPanel;
	}

	/**
	@param contentPanel The new content panel.
	**/
	public void setContentPanel(final ContentType contentPanel) {
		contentPanelContainer.removeAll();
		contentPanelContainer.add(contentPanel, BorderLayout.CENTER);
		this.contentPanel = contentPanel;
	}

	/**
	@param title The new title.
	**/
	public void setTitle(final String title) {
		titledBorder.setTitle(title);
	}
}
