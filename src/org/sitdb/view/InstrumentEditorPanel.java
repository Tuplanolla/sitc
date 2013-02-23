package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents an instrument editor panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class InstrumentEditorPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final EditorInterfacePanel interfacePanel;
	private final InstrumentMagicPanel magicPanel;
	private final PortPanel portPanel;

	/**
	Creates an instrument editor panel.
	**/
	public InstrumentEditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			interfacePanel = new EditorInterfacePanel();

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

					portPanel = new PortPanel();

				final JPanel editorPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
				editorPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
				editorPanel.add(titledNamePanel, BorderLayout.NORTH);
				editorPanel.add(titledStringPanel, BorderLayout.CENTER);
				editorPanel.add(portPanel, BorderLayout.SOUTH);

			final JPanel titledEditorPanel = new JPanel(new BorderLayout());
			titledEditorPanel.setBorder(new TitledBorder("Instrument"));
			titledEditorPanel.add(editorPanel, BorderLayout.CENTER);

		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		add(interfacePanel, BorderLayout.WEST);
		add(titledEditorPanel, BorderLayout.CENTER);
	}

	/**
	@return The interface panel.
	**/
	public EditorInterfacePanel getInterfacePanel() {
		return interfacePanel;
	}

	/**
	@return The magic panel.
	**/
	public InstrumentMagicPanel getMagicPanel() {
		return magicPanel;
	}

	/**
	@return The port panel.
	**/
	public PortPanel getPortPanel() {
		return portPanel;
	}
}
