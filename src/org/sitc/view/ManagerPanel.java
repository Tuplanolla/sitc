package org.sitc.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a manager panel that
 combines a remote (file) panel and a local (list) panel.

@param <Type> The type of the list model.
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class ManagerPanel<Type> extends JPanel {
	private static final long serialVersionUID = 1;

	private final TitledBorder titledBorder;
	private final RemotePanel remotePanel;
	private final LocalPanel<Type> localPanel;

	/**
	Creates a manager panel.
	**/
	public ManagerPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			titledBorder = new TitledBorder((String )null);

				remotePanel = new RemotePanel();

				localPanel = new LocalPanel<Type>();

			final JPanel managerPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			managerPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
			managerPanel.add(remotePanel, BorderLayout.NORTH);
			managerPanel.add(localPanel, BorderLayout.CENTER);

		setBorder(titledBorder);
		add(managerPanel, BorderLayout.CENTER);
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
	@return The remote (file) panel.
	**/
	public RemotePanel getRemotePanel() {
		return remotePanel;
	}

	/**
	@return The local (list) panel.
	**/
	public LocalPanel<Type> getLocalPanel() {
		return localPanel;
	}
}
