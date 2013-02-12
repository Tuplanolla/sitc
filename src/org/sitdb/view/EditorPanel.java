package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a panel that's used to
 import data,
 edit a local copy of the data and
 eventually export the data.

The panel has the following structural hierarchy:

<pre>
+--------------------------------------+
| EditorPanel<SideType, ContentType>   |
| +--------------+ +-----------------+ |
| | JPanel       | | JPanel          | |
| | +----------+ | | +-------------+ | |
| | | SideType | | | | ContentType | | |
| | +----------+ | | +-------------+ | |
| +--------------+ +-----------------+ |
+--------------------------------------+
</pre>

@param <SideType> The type of the side panel.
@param <ContentType> The type of the main panel.
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class EditorPanel<SideType extends JPanel, ContentType extends JPanel> extends JPanel {//TODO deprecate as somewhat pointless
	private static final long serialVersionUID = 1584690058857036971l;

	private final JPanel sidePanelContainer;
	private SideType sidePanel;
	private final JPanel contentPanelContainer;
	private ContentType contentPanel;
	private final TitledBorder titledBorder;

	/**
	Constructs a new panel.
	**/
	public EditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		sidePanelContainer = new JPanel(new BorderLayout());

		contentPanelContainer = new JPanel(new BorderLayout());

		titledBorder = new TitledBorder((String )null);

		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		add(sidePanelContainer, BorderLayout.WEST);
		add(contentPanelContainer, BorderLayout.CENTER);
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
