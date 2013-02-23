package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
Represents a sequence editor panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class SequenceEditorPanel extends JPanel {
	private static final long serialVersionUID = 1l;

	private final ExtendedEditorInterfacePanel interfacePanel;
	private final SequenceMagicPanel magicPanel;
	private final PortPanel portPanel;

	/**
	Creates a sequence editor panel.
	**/
	public SequenceEditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			interfacePanel = new ExtendedEditorInterfacePanel();

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

						final JPanel titledInstrumentPanel = new JPanel(new BorderLayout());
						titledInstrumentPanel.setBorder(new TitledBorder("Instrument"));
						titledInstrumentPanel.add(instrumentPanel, BorderLayout.CENTER);

									magicPanel = new SequenceMagicPanel();

								final JScrollPane scrollPane = new JScrollPane(magicPanel);
								scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
								scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

							final JPanel transitionPanel = new JPanel(new BorderLayout());
							transitionPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
							transitionPanel.add(scrollPane);

						final JPanel titledTransitionPanel = new JPanel(new BorderLayout());
						titledTransitionPanel.setBorder(new TitledBorder("Tunings"));
						titledTransitionPanel.add(transitionPanel, BorderLayout.CENTER);

											final JRadioButton nameRadioButton = new JRadioButton("Sort by Name");

											final JRadioButton tuningRadioButton = new JRadioButton("Sort by Tuning");

											final JRadioButton currentOrderRadioButton = new JRadioButton("Sort by Current Order");

										final JPanel sortPanel = new JPanel(new GridLayout(3, 1));
										sortPanel.add(nameRadioButton);
										sortPanel.add(tuningRadioButton);
										sortPanel.add(currentOrderRadioButton);

											final JRadioButton ascendingRadioButton = new JRadioButton("Ascending");
											ascendingRadioButton.setSelected(true);

											final JRadioButton descendingRadioButton = new JRadioButton("Descending");

											final JRadioButton randomRadioButton = new JRadioButton("Random");

										final ButtonGroup orderButtonGroup = new ButtonGroup();
										orderButtonGroup.add(ascendingRadioButton);
										orderButtonGroup.add(descendingRadioButton);
										orderButtonGroup.add(randomRadioButton);

										final JPanel orderPanel = new JPanel(new GridLayout(3, 1));
										orderPanel.add(ascendingRadioButton);
										orderPanel.add(descendingRadioButton);
										orderPanel.add(randomRadioButton);

									final JPanel sortGroupPanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
									sortGroupPanel.add(sortPanel);
									sortGroupPanel.add(orderPanel);

											final JRadioButton transpositionRadioButton = new JRadioButton("Minimize Transpositions");
											transpositionRadioButton.setSelected(true);

											final JRadioButton tensionRadioButton = new JRadioButton("Minimize Tension Changes");

										final JPanel minimizePanel = new JPanel(new GridLayout(2, 1));
										minimizePanel.add(transpositionRadioButton);
										minimizePanel.add(tensionRadioButton);

											final JRadioButton exactRadioButton = new JRadioButton("Exact (Slow)");
											exactRadioButton.setSelected(true);

											final JRadioButton approximateRadioButton = new JRadioButton("Approximate (Fast)");

										final ButtonGroup methodButtonGroup = new ButtonGroup();
										methodButtonGroup.add(exactRadioButton);
										methodButtonGroup.add(approximateRadioButton);

										final JPanel methodPanel = new JPanel(new GridLayout(2, 1));
										methodPanel.add(exactRadioButton);
										methodPanel.add(approximateRadioButton);

									final JPanel minimizeGroupPanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
									minimizeGroupPanel.add(minimizePanel);
									minimizeGroupPanel.add(methodPanel);

								final ButtonGroup optionButtonGroup = new ButtonGroup();//reversed intentionally
								optionButtonGroup.add(transpositionRadioButton);
								optionButtonGroup.add(tensionRadioButton);
								optionButtonGroup.add(nameRadioButton);
								optionButtonGroup.add(tuningRadioButton);
								optionButtonGroup.add(currentOrderRadioButton);

								for (final AbstractButton button : Collections.list(optionButtonGroup.getElements())) {
									button.addChangeListener(new ChangeListener() {
										@Override
										public void stateChanged(final ChangeEvent event) {
											boolean enableOrderButtonGroup = false,
													enableMethodButtonGroup = false;
											if (nameRadioButton.isSelected()
													|| tuningRadioButton.isSelected()
													|| currentOrderRadioButton.isSelected()) {
												enableOrderButtonGroup = true;
												enableMethodButtonGroup = false;
											}
											else if (transpositionRadioButton.isSelected()
													|| tensionRadioButton.isSelected()) {
												enableOrderButtonGroup = false;
												enableMethodButtonGroup = true;
											}
											Utilities.setAllEnabled(orderButtonGroup, enableOrderButtonGroup);
											Utilities.setAllEnabled(methodButtonGroup, enableMethodButtonGroup);
										}
									});
								}

								final JPanel groupPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
								groupPanel.add(sortGroupPanel, BorderLayout.NORTH);
								groupPanel.add(minimizeGroupPanel, BorderLayout.SOUTH);

								final JButton actionButton = new JButton("Run");
								Utilities.setScaledIcon(actionButton, Resources.CALCULATE_ICON, SwingConstants.HORIZONTAL, Constants.SMALL_SCALE);

							final JPanel actionPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
							actionPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
							actionPanel.add(groupPanel, BorderLayout.CENTER);
							actionPanel.add(actionButton, BorderLayout.SOUTH);

						final JPanel titledActionPanel = new JPanel(new BorderLayout());
						titledActionPanel.setBorder(new TitledBorder("Actions"));
						titledActionPanel.add(actionPanel, BorderLayout.CENTER);

					final JPanel containerPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
					containerPanel.add(titledInstrumentPanel, BorderLayout.NORTH);
					containerPanel.add(titledTransitionPanel, BorderLayout.CENTER);
					containerPanel.add(titledActionPanel, BorderLayout.SOUTH);

					portPanel = new PortPanel();

				final JPanel editorPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
				editorPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
				editorPanel.add(titledNamePanel, BorderLayout.NORTH);
				editorPanel.add(containerPanel, BorderLayout.CENTER);
				editorPanel.add(portPanel, BorderLayout.SOUTH);

			final JPanel titledEditorPanel = new JPanel(new BorderLayout());
			titledEditorPanel.setBorder(new TitledBorder("Sequence"));
			titledEditorPanel.add(editorPanel, BorderLayout.CENTER);

		setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		add(interfacePanel, BorderLayout.WEST);
		add(titledEditorPanel, BorderLayout.CENTER);
	}

	/**
	@return The interface panel.
	**/
	public ExtendedEditorInterfacePanel getInterfacePanel() {
		return interfacePanel;
	}

	/**
	@return The magic panel.
	**/
	public SequenceMagicPanel getMagicPanel() {
		return magicPanel;
	}

	/**
	@return The port panel.
	**/
	public PortPanel getPortPanel() {
		return portPanel;
	}
}
