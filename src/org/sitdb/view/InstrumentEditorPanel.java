package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
The panel has the following structural hierarchy:

<pre>
+--------------------------------------+
| InstrumentPanel                      |
| +----------------+ +---------------+ |
| | InterfacePanel | | JPanel        | |
| |                | | +-----------+ | |
| |                | | | JPanel    | | |
| |                | | +-----------+ | |
| |                | | +-----------+ | |
| |                | | | PortPanel | | |
| |                | | +-----------+ | |
| +----------------+ +---------------+ |
+--------------------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class InstrumentEditorPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final InstrumentMagicPanel magicPanel;
	private final PortPanel portPanel;

	/**
	Constructs a new panel.
	**/
	public InstrumentEditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		final JTextField nameTextField = new JTextField();

		final JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		namePanel.add(nameTextField);

		final JPanel titledNamePanel = new JPanel(new BorderLayout());
		titledNamePanel.setBorder(new TitledBorder("Name"));
		titledNamePanel.add(namePanel, BorderLayout.CENTER);

		magicPanel = new InstrumentMagicPanel();

		final JScrollPane scrollPane = new JScrollPane(magicPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		final JPanel stringPanel = new JPanel(new BorderLayout());
		stringPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		stringPanel.add(scrollPane);

		final JPanel titledStringPanel = new JPanel(new BorderLayout());
		titledStringPanel.setBorder(new TitledBorder("Strings"));
		titledStringPanel.add(stringPanel, BorderLayout.CENTER);

		final JPanel thisPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		thisPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		thisPanel.add(titledNamePanel, BorderLayout.NORTH);
		thisPanel.add(titledStringPanel, BorderLayout.CENTER);

		portPanel = new PortPanel();

		setBorder(new TitledBorder("Instrument"));
		add(thisPanel, BorderLayout.CENTER);
		add(portPanel, BorderLayout.SOUTH);//TODO fix
	}
}
