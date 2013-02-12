package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
The panel has the following structural hierarchy:

<pre>
+---------------------+
| SequencePanel     |
| +-----------------+ |
| | SequencePanel | |
| +-----------------+ |
| +-----------------+ |
| | JPanel          | |
| | +-------------+ | |
| | | JPanel      | | |
| | +-------------+ | |
| | +-------------+ | |
| | | MagicPanel  | | |
| | +-------------+ | |
| | +-------------+ | |
| | | JPanel      | | |
| | +-------------+ | |
| +-----------------+ |
| +-----------------+ |
| | PortPanel       | |
| +-----------------+ |
+---------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class SequenceEditorPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final SequenceEditorMagicPanel magicPanel;

	/**
	Constructs a new panel.
	**/
	public SequenceEditorPanel() {//TODO reorder
		super(new BorderLayout());

		final JTextField nameTextField = new JTextField();

		final JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		namePanel.add(nameTextField);

		final JPanel titledNamePanel = new JPanel(new BorderLayout());
		titledNamePanel.setBorder(new TitledBorder("Name"));
		titledNamePanel.add(namePanel, BorderLayout.CENTER);

		final JTextField instrumentTextField = new JTextField();

		final JPanel instrumentPanel = new JPanel(new BorderLayout());
		instrumentPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		instrumentPanel.add(instrumentTextField);

		final JPanel titledSequencePanel = new JPanel(new BorderLayout());
		titledSequencePanel.setBorder(new TitledBorder("Instrument"));
		titledSequencePanel.add(instrumentPanel, BorderLayout.CENTER);

		final JPanel somePanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		somePanel.add(titledNamePanel, BorderLayout.NORTH);
		somePanel.add(titledSequencePanel, BorderLayout.CENTER);

		magicPanel = new SequenceEditorMagicPanel();

		final JScrollPane scrollPane = new JScrollPane(magicPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		final JRadioButton nameRadioButton = new JRadioButton("Sort by Name");

		final JRadioButton tuningRadioButton = new JRadioButton("Sort by Tuning");

		final JRadioButton currentOrderRadioButton = new JRadioButton("Sort by Current Order");

		final JRadioButton transpositionRadioButton = new JRadioButton("Minimize Transpositions");

		final JRadioButton tensionRadioButton = new JRadioButton("Minimize Tension Changes");

		final JRadioButton ascendingRadioButton = new JRadioButton("Ascending");

		final JRadioButton descendingRadioButton = new JRadioButton("Descending");

		final JRadioButton randomRadioButton = new JRadioButton("Random");

		final JRadioButton exactRadioButton = new JRadioButton("Exact (Slow)");

		final JRadioButton approximateRadioButton = new JRadioButton("Approximate (Fast)");

		final JPanel actionButtonPanel = new JPanel(new GridLayout(5, 2, Constants.MEDIUM_INSET, 0));
		actionButtonPanel.add(nameRadioButton);
		actionButtonPanel.add(ascendingRadioButton);
		actionButtonPanel.add(tuningRadioButton);
		actionButtonPanel.add(descendingRadioButton);
		actionButtonPanel.add(currentOrderRadioButton);
		actionButtonPanel.add(randomRadioButton);
		actionButtonPanel.add(transpositionRadioButton);
		actionButtonPanel.add(exactRadioButton);
		actionButtonPanel.add(tensionRadioButton);
		actionButtonPanel.add(approximateRadioButton);

		final JButton actionButton = new JButton("Organize");
		Utilities.setScaledIcon(actionButton, Resources.CALCULATE_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

		final JPanel actionPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		actionPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		actionPanel.add(actionButtonPanel, BorderLayout.CENTER);
		actionPanel.add(actionButton, BorderLayout.SOUTH);

		final JPanel titledActionPanel = new JPanel(new BorderLayout());
		titledActionPanel.setBorder(new TitledBorder("Actions"));
		titledActionPanel.add(actionPanel, BorderLayout.CENTER);

		final JPanel changePanel = new JPanel(new BorderLayout());
		changePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		changePanel.add(scrollPane, BorderLayout.CENTER);

		final JPanel titledChangePanel = new JPanel(new BorderLayout());
		titledChangePanel.setBorder(new TitledBorder("Tunings"));
		titledChangePanel.add(changePanel, BorderLayout.CENTER);

		final JPanel thisPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		thisPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		thisPanel.add(somePanel, BorderLayout.NORTH);
		thisPanel.add(titledChangePanel, BorderLayout.CENTER);
		thisPanel.add(titledActionPanel, BorderLayout.SOUTH);

		setBorder(new TitledBorder("Sequence"));
		add(thisPanel, BorderLayout.CENTER);
	}
}
