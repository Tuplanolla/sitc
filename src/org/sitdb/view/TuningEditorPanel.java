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
+--------------------+
| TuningPanel        |
| +----------------+ |
| | JPanel         | |
| +----------------+ |
| +----------------+ |
| | JPanel         | |
| | +------------+ | |
| | | JPanel     | | |
| | +------------+ | |
| | +------------+ | |
| | | MagicPanel | | |
| | +------------+ | |
| | +------------+ | |
| | | PortPanel  | | |
| | +------------+ | |
| +----------------+ |
+--------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class TuningEditorPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final TuningMagicPanel magicPanel;

	/**
	Constructs a new panel.
	**/
	public TuningEditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

		final JTextField midiTextField = new JTextField();

		final JPanel midiPanel = new JPanel(new BorderLayout());
		midiPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		midiPanel.add(midiTextField);

		final JPanel titledMidiPanel = new JPanel(new BorderLayout());
		titledMidiPanel.setBorder(new TitledBorder("Sound"));
		titledMidiPanel.add(midiPanel, BorderLayout.CENTER);

		final JTextField nameTextField = new JTextField();

		final JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		namePanel.add(nameTextField);

		final JPanel titledNamePanel = new JPanel(new BorderLayout());
		titledNamePanel.setBorder(new TitledBorder("Name"));
		titledNamePanel.add(namePanel, BorderLayout.CENTER);

		magicPanel = new TuningMagicPanel();

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

		final JPanel superPanel = new JPanel(new BorderLayout());
		superPanel.setBorder(new TitledBorder("Tuning"));
		superPanel.add(thisPanel, BorderLayout.CENTER);

		add(titledMidiPanel, BorderLayout.NORTH);
		add(superPanel, BorderLayout.CENTER);
	}
}
