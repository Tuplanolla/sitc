package org.sitdb.view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
Represents a tuning editor panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class TuningEditorPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final EditorInterfacePanel interfacePanel;
	private final TuningMagicPanel magicPanel;
	private final PortPanel portPanel;

	/**
	Creates a tuning editor panel.
	**/
	public TuningEditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			interfacePanel = new EditorInterfacePanel();

						final JComboBox<String> soundComboBox = new JComboBox<String>();

					final JPanel soundPanel = new JPanel(new BorderLayout());
					soundPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
					soundPanel.add(soundComboBox);

				final JPanel titledSoundPanel = new JPanel(new BorderLayout());
				titledSoundPanel.setBorder(new TitledBorder("Test Sound"));
				titledSoundPanel.add(soundPanel, BorderLayout.CENTER);

									final JTextField nameTextField = new JTextField();

								final JPanel namePanel = new JPanel(new BorderLayout());
								namePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
								namePanel.add(nameTextField);

							final JPanel titledNamePanel = new JPanel(new BorderLayout());
							titledNamePanel.setBorder(new TitledBorder("Name"));
							titledNamePanel.add(namePanel, BorderLayout.CENTER);

									final JComboBox<String> systemComboBox = new JComboBox<String>();

								final JPanel systemPanel = new JPanel(new BorderLayout());
								systemPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
								systemPanel.add(systemComboBox);

							final JPanel titledSystemPanel = new JPanel(new BorderLayout());
							titledSystemPanel.setBorder(new TitledBorder("Pitch System"));
							titledSystemPanel.add(systemPanel, BorderLayout.CENTER);

						final JPanel topPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
						topPanel.add(titledNamePanel, BorderLayout.NORTH);
						topPanel.add(titledSystemPanel, BorderLayout.CENTER);

									magicPanel = new TuningMagicPanel(6);

								final JScrollPane scrollPane = new JScrollPane(magicPanel);
								scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
								scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								scrollPane.getVerticalScrollBar().setUnitIncrement(8);
								scrollPane.getHorizontalScrollBar().setUnitIncrement(8);

							final JPanel stringPanel = new JPanel(new BorderLayout());
							stringPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
							stringPanel.add(scrollPane);

						final JPanel titledStringPanel = new JPanel(new BorderLayout());
						titledStringPanel.setBorder(new TitledBorder("Strings"));
						titledStringPanel.add(stringPanel, BorderLayout.CENTER);

						portPanel = new PortPanel();

					final JPanel editorPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
					editorPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
					editorPanel.add(topPanel, BorderLayout.NORTH);
					editorPanel.add(titledStringPanel, BorderLayout.CENTER);
					editorPanel.add(portPanel, BorderLayout.SOUTH);

				final JPanel titledEditorPanel = new JPanel(new BorderLayout());
				titledEditorPanel.setBorder(new TitledBorder("Tuning"));
				titledEditorPanel.add(editorPanel, BorderLayout.CENTER);

			final JPanel containerPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
			containerPanel.add(titledSoundPanel, BorderLayout.NORTH);
			containerPanel.add(titledEditorPanel, BorderLayout.CENTER);

		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		add(interfacePanel, BorderLayout.WEST);
		add(containerPanel, BorderLayout.CENTER);
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
	public TuningMagicPanel getMagicPanel() {
		return magicPanel;
	}

	/**
	@return The port panel.
	**/
	public PortPanel getPortPanel() {
		return portPanel;
	}
}
