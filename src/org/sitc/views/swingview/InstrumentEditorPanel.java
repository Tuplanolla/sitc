package org.sitc.views.swingview;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.sitc.models.standardmodel.TuningSystem;

/**
Represents an instrument editor panel.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class InstrumentEditorPanel extends JPanel {
	private static final long serialVersionUID = 1;

	private final EditorInterfacePanel interfacePanel;
	private final JTextField nameTextField;
	private final DefaultComboBoxModel<TuningSystem> systemComboBoxModel;
	private final JComboBox<TuningSystem> systemComboBox;
	private final JTextField tensionTextField;
	private final InstrumentMagicPanel magicPanel;
	private final PortPanel portPanel;

	/**
	Creates an instrument editor panel.
	**/
	public InstrumentEditorPanel() {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			interfacePanel = new EditorInterfacePanel();

								nameTextField = new JTextField();

							final JPanel namePanel = new JPanel(new BorderLayout());
							namePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
							namePanel.add(nameTextField);

						final JPanel titledNamePanel = new JPanel(new BorderLayout());
						titledNamePanel.setBorder(new TitledBorder("Name"));
						titledNamePanel.add(namePanel, BorderLayout.CENTER);

									systemComboBoxModel = new DefaultComboBoxModel<>();

								systemComboBox = new JComboBox<>(systemComboBoxModel);

							final JPanel systemPanel = new JPanel(new BorderLayout());
							systemPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
							systemPanel.add(systemComboBox);

						final JPanel titledSystemPanel = new JPanel(new BorderLayout());
						titledSystemPanel.setBorder(new TitledBorder("Tuning System"));
						titledSystemPanel.add(systemPanel, BorderLayout.CENTER);

								tensionTextField = new JTextField();

							final JPanel tensionPanel = new JPanel(new BorderLayout());
							tensionPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
							tensionPanel.add(tensionTextField);

						final JPanel titledTensionPanel = new JPanel(new BorderLayout());
						titledTensionPanel.setBorder(new TitledBorder("Maximum Tension (N)"));
						titledTensionPanel.add(tensionPanel, BorderLayout.CENTER);

					final JPanel topPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
					topPanel.add(titledNamePanel, BorderLayout.NORTH);
					topPanel.add(titledSystemPanel, BorderLayout.CENTER);
					topPanel.add(titledTensionPanel, BorderLayout.SOUTH);

								magicPanel = new InstrumentMagicPanel();
								magicPanel.setRows(1);

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
	@return The name text field.
	**/
	public JTextField getNameTextField() {
		return nameTextField;
	}

	/**
	@return The tuning system combo box model.
	**/
	public DefaultComboBoxModel<TuningSystem> getSystemComboBoxModel() {
		return systemComboBoxModel;
	}

	/**
	@return The tuning system combo box.
	**/
	public JComboBox<TuningSystem> getSystemComboBox() {
		return systemComboBox;
	}

	/**
	@return The tension text field.
	**/
	public JTextField getTensionTextField() {
		return tensionTextField;
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
