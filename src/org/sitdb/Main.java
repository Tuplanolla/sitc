package org.sitdb;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Dimension;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main {
	private JFrame frame;
	private JTextField instrumentListSearchTextField;
	private JTextField instrumentFilePathTextField;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final Exception exception) {
			exception.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final Main window = new Main();
					window.frame.setVisible(true);
				}
				catch (final Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}

	/**
	**/
	public Main() {
		initialize();
	}

	/**
	**/
	private void initialize() {
		ImageIcon downIcon = new ImageIcon("gfx/down.png");
		ImageIcon upIcon = new ImageIcon("gfx/up.png");
		ImageIcon rightIcon = new ImageIcon("gfx/right.png");
		ImageIcon leftIcon = new ImageIcon("gfx/left.png");
		ImageIcon plusIcon = new ImageIcon("gfx/plus.png");
		ImageIcon minusIcon = new ImageIcon("gfx/minus.png");
		ImageIcon upDownIcon = new ImageIcon("gfx/updown.png");

		frame = new JFrame();
		frame.setTitle("String Instrument Tuning Database");
		frame.setBounds(320, 160, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		frame.getContentPane().setLayout(gridBagLayout);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.weightx = 1.0;
		gbc_menuBar.anchor = GridBagConstraints.NORTH;
		gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		frame.getContentPane().add(menuBar, gbc_menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
			}
		});
		fileMenu.add(exitMenuItem);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JCheckBoxMenuItem microtonalMenuCheckbox = new JCheckBoxMenuItem("Hide Microtonals");
		mnSettings.add(microtonalMenuCheckbox);

		JCheckBoxMenuItem notationMenuCheckbox = new JCheckBoxMenuItem("Use European Notation");
		mnSettings.add(notationMenuCheckbox);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem manualMenuItem = new JMenuItem("Manual");
		manualMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, "Nope.", "Nope", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(manualMenuItem);

		JMenuItem aboutMenuItem = new JMenuItem("About...");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, "Nope.", "Nope", JOptionPane.WARNING_MESSAGE);
			}
		});
		helpMenu.add(aboutMenuItem);


		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.weighty = 1.0;
		gbc_splitPane.weightx = 1.0;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		frame.getContentPane().add(splitPane, gbc_splitPane);


		final JPanel sidePanel = new JPanel();
		splitPane.setLeftComponent(sidePanel);
		final GridBagLayout gbl_sidePanel = new GridBagLayout();
		sidePanel.setLayout(gbl_sidePanel);


		final JPanel instrumentSidePanel = new JPanel();
		instrumentSidePanel.setBorder(new TitledBorder(null, "Instruments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		final GridBagConstraints gbc_instrumentSidePanel = new GridBagConstraints();
		gbc_instrumentSidePanel.weighty = 1.0;
		gbc_instrumentSidePanel.weightx = 1.0;
		gbc_instrumentSidePanel.fill = GridBagConstraints.BOTH;
		gbc_instrumentSidePanel.anchor = GridBagConstraints.NORTH;
		gbc_instrumentSidePanel.insets = new Insets(4, 4, 2, 4);
		gbc_instrumentSidePanel.gridx = 0;
		gbc_instrumentSidePanel.gridy = 0;
		sidePanel.add(instrumentSidePanel, gbc_instrumentSidePanel);
		final GridBagLayout gbl_instrumentSidePanel = new GridBagLayout();
		instrumentSidePanel.setLayout(gbl_instrumentSidePanel);

		JPanel instrumentFilePanel = new JPanel();
		instrumentFilePanel.setBorder(new TitledBorder(null, "Instrument File", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_instrumentFilePanel = new GridBagConstraints();
		gbc_instrumentFilePanel.insets = new Insets(4, 4, 2, 4);
		gbc_instrumentFilePanel.weightx = 1.0;
		gbc_instrumentFilePanel.anchor = GridBagConstraints.NORTH;
		gbc_instrumentFilePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentFilePanel.gridx = 0;
		gbc_instrumentFilePanel.gridy = 0;
		instrumentSidePanel.add(instrumentFilePanel, gbc_instrumentFilePanel);
		GridBagLayout gbl_instrumentFilePanel = new GridBagLayout();
		instrumentFilePanel.setLayout(gbl_instrumentFilePanel);

		instrumentFilePathTextField = new JTextField();
		GridBagConstraints gbc_instrumentFilePathTextField = new GridBagConstraints();
		gbc_instrumentFilePathTextField.weightx = 1.0;
		gbc_instrumentFilePathTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentFilePathTextField.insets = new Insets(4, 4, 4, 2);
		gbc_instrumentFilePathTextField.gridx = 0;
		gbc_instrumentFilePathTextField.gridy = 0;
		instrumentFilePanel.add(instrumentFilePathTextField, gbc_instrumentFilePathTextField);
		instrumentFilePathTextField.setColumns(4);

		JButton instrumentFileBrowseButton = new JButton("Browse...");
		instrumentFileBrowseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_instrumentFileBrowseButton = new GridBagConstraints();
		gbc_instrumentFileBrowseButton.insets = new Insets(4, 2, 4, 4);
		gbc_instrumentFileBrowseButton.anchor = GridBagConstraints.EAST;
		gbc_instrumentFileBrowseButton.fill = GridBagConstraints.BOTH;
		gbc_instrumentFileBrowseButton.gridx = 1;
		gbc_instrumentFileBrowseButton.gridy = 0;
		instrumentFilePanel.add(instrumentFileBrowseButton, gbc_instrumentFileBrowseButton);

		JPanel instrumentListPanel = new JPanel();
		GridBagConstraints gbc_instrumentListPanel = new GridBagConstraints();
		gbc_instrumentListPanel.anchor = GridBagConstraints.SOUTH;
		gbc_instrumentListPanel.weighty = 1.0;
		gbc_instrumentListPanel.insets = new Insets(2, 4, 4, 4);
		gbc_instrumentListPanel.weightx = 1.0;
		gbc_instrumentListPanel.fill = GridBagConstraints.BOTH;
		gbc_instrumentListPanel.gridx = 0;
		gbc_instrumentListPanel.gridy = 2;
		instrumentSidePanel.add(instrumentListPanel, gbc_instrumentListPanel);
		instrumentListPanel.setBorder(new TitledBorder(null, "Instrument List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_instrumentListPanel = new GridBagLayout();
		instrumentListPanel.setLayout(gbl_instrumentListPanel);

		JPanel instrumentListSearchPanel = new JPanel();
		GridBagConstraints gbc_instrumentListSearchPanel = new GridBagConstraints();
		gbc_instrumentListSearchPanel.insets = new Insets(4, 4, 2, 4);
		gbc_instrumentListSearchPanel.weightx = 1.0;
		gbc_instrumentListSearchPanel.anchor = GridBagConstraints.NORTH;
		gbc_instrumentListSearchPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentListSearchPanel.gridx = 0;
		gbc_instrumentListSearchPanel.gridy = 0;
		instrumentListPanel.add(instrumentListSearchPanel, gbc_instrumentListSearchPanel);
		GridBagLayout gbl_instrumentListSearchPanel = new GridBagLayout();
		instrumentListSearchPanel.setLayout(gbl_instrumentListSearchPanel);

		instrumentListSearchTextField = new JTextField();
		GridBagConstraints gbc_instrumentListSearchTextField = new GridBagConstraints();
		gbc_instrumentListSearchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentListSearchTextField.weighty = 1.0;
		gbc_instrumentListSearchTextField.weightx = 1.0;
		gbc_instrumentListSearchTextField.insets = new Insets(0, 0, 0, 2);
		gbc_instrumentListSearchTextField.gridx = 0;
		gbc_instrumentListSearchTextField.gridy = 0;
		instrumentListSearchPanel.add(instrumentListSearchTextField, gbc_instrumentListSearchTextField);
		instrumentListSearchTextField.setColumns(4);

		JButton instrumentListSearchButton = new JButton("Search");
		instrumentListSearchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_instrumentListSearchButton = new GridBagConstraints();
		gbc_instrumentListSearchButton.insets = new Insets(0, 2, 0, 0);
		gbc_instrumentListSearchButton.fill = GridBagConstraints.BOTH;
		gbc_instrumentListSearchButton.gridx = 1;
		gbc_instrumentListSearchButton.gridy = 0;
		instrumentListSearchPanel.add(instrumentListSearchButton, gbc_instrumentListSearchButton);

		JList<Object> instrumentList = new JList<Object>();
		instrumentList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_instrumentList = new GridBagConstraints();
		gbc_instrumentList.insets = new Insets(2, 4, 2, 4);
		gbc_instrumentList.weighty = 1.0;
		gbc_instrumentList.weightx = 1.0;
		gbc_instrumentList.fill = GridBagConstraints.BOTH;
		gbc_instrumentList.gridx = 0;
		gbc_instrumentList.gridy = 1;
		instrumentListPanel.add(instrumentList, gbc_instrumentList);
		instrumentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel instrumentListModPanel = new JPanel();
		GridBagConstraints gbc_instrumentListModPanel = new GridBagConstraints();
		gbc_instrumentListModPanel.insets = new Insets(2, 4, 4, 4);
		gbc_instrumentListModPanel.anchor = GridBagConstraints.SOUTH;
		gbc_instrumentListModPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentListModPanel.gridx = 0;
		gbc_instrumentListModPanel.gridy = 2;
		instrumentListPanel.add(instrumentListModPanel, gbc_instrumentListModPanel);
		GridBagLayout gbl_instrumentListModPanel = new GridBagLayout();
		instrumentListModPanel.setLayout(gbl_instrumentListModPanel);

		JButton newInstrumentButton = new JButton("New", plusIcon);
		newInstrumentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_newInstrumentButton = new GridBagConstraints();
		gbc_newInstrumentButton.weighty = 1.0;
		gbc_newInstrumentButton.weightx = 1.0;
		gbc_newInstrumentButton.fill = GridBagConstraints.BOTH;
		gbc_newInstrumentButton.insets = new Insets(0, 0, 0, 2);
		gbc_newInstrumentButton.gridx = 0;
		gbc_newInstrumentButton.gridy = 0;
		instrumentListModPanel.add(newInstrumentButton, gbc_newInstrumentButton);

		JButton deleteInstrumentButton = new JButton("Delete", minusIcon);
		deleteInstrumentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_deleteInstrumentButton = new GridBagConstraints();
		gbc_deleteInstrumentButton.insets = new Insets(0, 2, 0, 0);
		gbc_deleteInstrumentButton.weightx = 1.0;
		gbc_deleteInstrumentButton.weighty = 1.0;
		gbc_deleteInstrumentButton.fill = GridBagConstraints.BOTH;
		gbc_deleteInstrumentButton.gridx = 1;
		gbc_deleteInstrumentButton.gridy = 0;
		instrumentListModPanel.add(deleteInstrumentButton, gbc_deleteInstrumentButton);

		JPanel instrumentListBoundaryPanel = new JPanel();
		GridBagConstraints gbc_instrumentListBoundaryPanel = new GridBagConstraints();
		gbc_instrumentListBoundaryPanel.weightx = 1.0;
		gbc_instrumentListBoundaryPanel.insets = new Insets(2, 4, 2, 4);
		gbc_instrumentListBoundaryPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentListBoundaryPanel.gridx = 0;
		gbc_instrumentListBoundaryPanel.gridy = 1;
		instrumentSidePanel.add(instrumentListBoundaryPanel, gbc_instrumentListBoundaryPanel);
		GridBagLayout gbl_instrumentListBoundaryPanel = new GridBagLayout();
		instrumentListBoundaryPanel.setLayout(gbl_instrumentListBoundaryPanel);

		JButton instrumentListLoadButton = new JButton("Load", downIcon);
		instrumentListLoadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_instrumentListLoadButton = new GridBagConstraints();
		gbc_instrumentListLoadButton.fill = GridBagConstraints.BOTH;
		gbc_instrumentListLoadButton.weightx = 1.0;
		gbc_instrumentListLoadButton.weighty = 1.0;
		gbc_instrumentListLoadButton.insets = new Insets(0, 0, 0, 2);
		gbc_instrumentListLoadButton.gridx = 0;
		gbc_instrumentListLoadButton.gridy = 0;
		instrumentListBoundaryPanel.add(instrumentListLoadButton, gbc_instrumentListLoadButton);

		JButton instrumentListSaveButton = new JButton("Save", upIcon);
		instrumentListSaveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_instrumentListSaveButton = new GridBagConstraints();
		gbc_instrumentListSaveButton.insets = new Insets(0, 2, 0, 0);
		gbc_instrumentListSaveButton.weighty = 1.0;
		gbc_instrumentListSaveButton.weightx = 1.0;
		gbc_instrumentListSaveButton.fill = GridBagConstraints.BOTH;
		gbc_instrumentListSaveButton.gridx = 1;
		gbc_instrumentListSaveButton.gridy = 0;
		instrumentListBoundaryPanel.add(instrumentListSaveButton, gbc_instrumentListSaveButton);

		final JPanel tuningSidePanel = new JPanel();
		tuningSidePanel.setBorder(new TitledBorder(null, "Tunings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_tuningSidePanel = new GridBagConstraints();
		gbc_tuningSidePanel.insets = new Insets(2, 4, 4, 4);
		gbc_tuningSidePanel.fill = GridBagConstraints.BOTH;
		gbc_tuningSidePanel.anchor = GridBagConstraints.SOUTH;
		gbc_tuningSidePanel.weighty = 1.0;
		gbc_tuningSidePanel.weightx = 1.0;
		gbc_tuningSidePanel.gridx = 0;
		gbc_tuningSidePanel.gridy = 1;
		sidePanel.add(tuningSidePanel, gbc_tuningSidePanel);
		tuningSidePanel.setLayout(new BorderLayout(0, 0));

		JPanel tuningListPlaceholderPanel = new JPanel();
		tuningSidePanel.add(tuningListPlaceholderPanel, BorderLayout.CENTER);
		tuningListPlaceholderPanel.setLayout(new BorderLayout(0, 0));

		JPanel crappedPane = new JPanel();
		splitPane.setRightComponent(crappedPane);
		GridBagLayout gbl_crappedPane = new GridBagLayout();
		crappedPane.setLayout(gbl_crappedPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.weightx = 1.0;
		gbc_tabbedPane.weighty = 1.0;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.insets = new Insets(4, 4, 4, 4);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		crappedPane.add(tabbedPane, gbc_tabbedPane);

		final JPanel instrumentEditorContainerPanel = new JPanel();
		tabbedPane.addTab("Instrument Editor", null, instrumentEditorContainerPanel, null);
		GridBagLayout gbl_instrumentEditorContainerPanel = new GridBagLayout();
		instrumentEditorContainerPanel.setLayout(gbl_instrumentEditorContainerPanel);

		JPanel instrumentEditorBoundaryPanel = new JPanel();
		GridBagConstraints gbc_instrumentEditorBoundaryPanel = new GridBagConstraints();
		gbc_instrumentEditorBoundaryPanel.anchor = GridBagConstraints.WEST;
		gbc_instrumentEditorBoundaryPanel.fill = GridBagConstraints.VERTICAL;
		gbc_instrumentEditorBoundaryPanel.insets = new Insets(4, 4, 4, 2);
		gbc_instrumentEditorBoundaryPanel.gridx = 0;
		gbc_instrumentEditorBoundaryPanel.gridy = 0;
		instrumentEditorContainerPanel.add(instrumentEditorBoundaryPanel, gbc_instrumentEditorBoundaryPanel);
		GridBagLayout gbl_instrumentEditorBoundaryPanel = new GridBagLayout();
		instrumentEditorBoundaryPanel.setLayout(gbl_instrumentEditorBoundaryPanel);

		JButton instrumentEditorLoadButton = new JButton("Revert", rightIcon);
		instrumentEditorLoadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		instrumentEditorLoadButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		instrumentEditorLoadButton.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_instrumentEditorLoadButton = new GridBagConstraints();
		gbc_instrumentEditorLoadButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentEditorLoadButton.insets = new Insets(0, 0, 2, 0);
		gbc_instrumentEditorLoadButton.gridx = 0;
		gbc_instrumentEditorLoadButton.gridy = 0;
		instrumentEditorBoundaryPanel.add(instrumentEditorLoadButton, gbc_instrumentEditorLoadButton);

		JButton instrumentEditorSaveButton = new JButton("Apply", leftIcon);
		instrumentEditorSaveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		instrumentEditorSaveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		instrumentEditorSaveButton.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_instrumentEditorSaveButton = new GridBagConstraints();
		gbc_instrumentEditorSaveButton.insets = new Insets(2, 0, 0, 0);
		gbc_instrumentEditorSaveButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentEditorSaveButton.gridx = 0;
		gbc_instrumentEditorSaveButton.gridy = 1;
		instrumentEditorBoundaryPanel.add(instrumentEditorSaveButton, gbc_instrumentEditorSaveButton);

		JPanel instrumentEditorPanel = new JPanel();
		instrumentEditorPanel.setBorder(new TitledBorder(null, "Instrument", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_instrumentEditorPanel = new GridBagConstraints();
		gbc_instrumentEditorPanel.insets = new Insets(4, 2, 4, 4);
		gbc_instrumentEditorPanel.weighty = 1.0;
		gbc_instrumentEditorPanel.weightx = 1.0;
		gbc_instrumentEditorPanel.fill = GridBagConstraints.BOTH;
		gbc_instrumentEditorPanel.gridx = 1;
		gbc_instrumentEditorPanel.gridy = 0;
		instrumentEditorContainerPanel.add(instrumentEditorPanel, gbc_instrumentEditorPanel);
		GridBagLayout gbl_instrumentEditorPanel = new GridBagLayout();
		instrumentEditorPanel.setLayout(gbl_instrumentEditorPanel);

		JPanel instrumentEditorNamePanel = new JPanel();
		instrumentEditorNamePanel.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_instrumentEditorNamePanel = new GridBagConstraints();
		gbc_instrumentEditorNamePanel.anchor = GridBagConstraints.NORTH;
		gbc_instrumentEditorNamePanel.insets = new Insets(4, 4, 2, 4);
		gbc_instrumentEditorNamePanel.weightx = 1.0;
		gbc_instrumentEditorNamePanel.fill = GridBagConstraints.BOTH;
		gbc_instrumentEditorNamePanel.gridx = 0;
		gbc_instrumentEditorNamePanel.gridy = 0;
		instrumentEditorPanel.add(instrumentEditorNamePanel, gbc_instrumentEditorNamePanel);
		GridBagLayout gbl_instrumentEditorNamePanel = new GridBagLayout();
		instrumentEditorNamePanel.setLayout(gbl_instrumentEditorNamePanel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.weighty = 1.0;
		gbc_textField.weightx = 1.0;
		gbc_textField.insets = new Insets(4, 4, 4, 4);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		instrumentEditorNamePanel.add(textField, gbc_textField);
		textField.setColumns(10);

		JPanel instrumentEditorPortPanel = new JPanel();
		GridBagConstraints gbc_instrumentEditorPortPanel = new GridBagConstraints();
		gbc_instrumentEditorPortPanel.insets = new Insets(2, 4, 4, 4);
		gbc_instrumentEditorPortPanel.weightx = 1.0;
		gbc_instrumentEditorPortPanel.anchor = GridBagConstraints.SOUTH;
		gbc_instrumentEditorPortPanel.fill = GridBagConstraints.BOTH;
		gbc_instrumentEditorPortPanel.gridx = 0;
		gbc_instrumentEditorPortPanel.gridy = 2;
		instrumentEditorPanel.add(instrumentEditorPortPanel, gbc_instrumentEditorPortPanel);
		GridBagLayout gbl_instrumentEditorPortPanel = new GridBagLayout();
		instrumentEditorPortPanel.setLayout(gbl_instrumentEditorPortPanel);

		JButton instrumentEditorImportButton = new JButton("Import", upIcon);
		instrumentEditorImportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, "Nope.", "Nope", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_instrumentEditorImportButton = new GridBagConstraints();
		gbc_instrumentEditorImportButton.fill = GridBagConstraints.BOTH;
		gbc_instrumentEditorImportButton.insets = new Insets(0, 0, 0, 2);
		gbc_instrumentEditorImportButton.weighty = 1.0;
		gbc_instrumentEditorImportButton.weightx = 1.0;
		gbc_instrumentEditorImportButton.gridx = 0;
		gbc_instrumentEditorImportButton.gridy = 0;
		instrumentEditorPortPanel.add(instrumentEditorImportButton, gbc_instrumentEditorImportButton);

		JButton instrumentEditorExportButton = new JButton("Export", downIcon);
		instrumentEditorExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, "Nope.", "Nope", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_instrumentEditorExportButton = new GridBagConstraints();
		gbc_instrumentEditorExportButton.weighty = 1.0;
		gbc_instrumentEditorExportButton.weightx = 1.0;
		gbc_instrumentEditorExportButton.fill = GridBagConstraints.BOTH;
		gbc_instrumentEditorExportButton.insets = new Insets(0, 2, 0, 0);
		gbc_instrumentEditorExportButton.gridx = 1;
		gbc_instrumentEditorExportButton.gridy = 0;
		instrumentEditorPortPanel.add(instrumentEditorExportButton, gbc_instrumentEditorExportButton);

		JPanel instrumentEditorStringPanel = new JPanel();
		instrumentEditorStringPanel.setBorder(new TitledBorder(null, "Strings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_instrumentEditorStringPanel = new GridBagConstraints();
		gbc_instrumentEditorStringPanel.weighty = 1.0;
		gbc_instrumentEditorStringPanel.weightx = 1.0;
		gbc_instrumentEditorStringPanel.insets = new Insets(2, 4, 2, 4);
		gbc_instrumentEditorStringPanel.fill = GridBagConstraints.BOTH;
		gbc_instrumentEditorStringPanel.gridx = 0;
		gbc_instrumentEditorStringPanel.gridy = 1;
		instrumentEditorPanel.add(instrumentEditorStringPanel, gbc_instrumentEditorStringPanel);
		GridBagLayout gbl_instrumentEditorStringPanel = new GridBagLayout();
		instrumentEditorStringPanel.setLayout(gbl_instrumentEditorStringPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.insets = new Insets(4, 4, 4, 4);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		instrumentEditorStringPanel.add(scrollPane, gbc_scrollPane);

		JPanel instrumentEditorStringsScrollPanel = new JPanel();
		scrollPane.setViewportView(instrumentEditorStringsScrollPanel);
		GridBagLayout gbl_instrumentEditorStringsScrollPanel = new GridBagLayout();
		instrumentEditorStringsScrollPanel.setLayout(gbl_instrumentEditorStringsScrollPanel);

		JLabel lblLength = new JLabel("Length");
		lblLength.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblLength = new GridBagConstraints();
		gbc_lblLength.fill = GridBagConstraints.BOTH;
		gbc_lblLength.insets = new Insets(4, 0, 4, 2);
		gbc_lblLength.gridx = 1;
		gbc_lblLength.gridy = 0;
		instrumentEditorStringsScrollPanel.add(lblLength, gbc_lblLength);

		JLabel lblDensity = new JLabel("Density");
		lblDensity.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDensity = new GridBagConstraints();
		gbc_lblDensity.fill = GridBagConstraints.BOTH;
		gbc_lblDensity.insets = new Insets(4, 2, 4, 2);
		gbc_lblDensity.gridx = 2;
		gbc_lblDensity.gridy = 0;
		instrumentEditorStringsScrollPanel.add(lblDensity, gbc_lblDensity);

		{
			JLabel label = new JLabel("#1");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 4, 0, 2);
			gbc_label.gridheight = 4;
			gbc_label.fill = GridBagConstraints.BOTH;
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			instrumentEditorStringsScrollPanel.add(label, gbc_label);
			label.setHorizontalAlignment(SwingConstants.CENTER);

			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 2, 0, 2);
			gbc_panel.gridheight = 4;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			instrumentEditorStringsScrollPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			panel.setLayout(gbl_panel);

			{
				textField_1 = new JTextField();
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.weightx = 1.0;
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.insets = new Insets(0, 0, 0, 2);
				gbc_textField_1.gridx = 0;
				gbc_textField_1.gridy = 0;
				panel.add(textField_1, gbc_textField_1);

				JLabel label_1 = new JLabel("cm");
				GridBagConstraints gbc_label_1 = new GridBagConstraints();
				gbc_label_1.weighty = 1.0;
				gbc_label_1.insets = new Insets(0, 2, 0, 0);
				gbc_label_1.fill = GridBagConstraints.BOTH;
				gbc_label_1.gridx = 1;
				gbc_label_1.gridy = 0;
				panel.add(label_1, gbc_label_1);
			}

			JPanel panel_1 = new JPanel();
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.insets = new Insets(0, 2, 0, 2);
			gbc_panel_1.gridheight = 4;
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 2;
			gbc_panel_1.gridy = 2;
			instrumentEditorStringsScrollPanel.add(panel_1, gbc_panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			panel_1.setLayout(gbl_panel_1);

			{
				textField_2 = new JTextField();
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.weightx = 1.0;
				gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_2.insets = new Insets(0, 2, 0, 2);
				gbc_textField_2.gridx = 0;
				gbc_textField_2.gridy = 0;
				panel_1.add(textField_2, gbc_textField_2);

				JLabel label_3 = new JLabel("g/m");
				GridBagConstraints gbc_label_3 = new GridBagConstraints();
				gbc_label_3.weighty = 1.0;
				gbc_label_3.insets = new Insets(0, 2, 0, 0);
				gbc_label_3.fill = GridBagConstraints.BOTH;
				gbc_label_3.gridx = 1;
				gbc_label_3.gridy = 0;
				panel_1.add(label_3, gbc_label_3);
				label.setHorizontalAlignment(SwingConstants.CENTER);
			}

			JLabel label_2 = new JLabel("#2");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_label_2 = new GridBagConstraints();
			gbc_label_2.insets = new Insets(0, 4, 0, 2);
			gbc_label_2.gridheight = 4;
			gbc_label_2.fill = GridBagConstraints.BOTH;
			gbc_label_2.gridx = 0;
			gbc_label_2.gridy = 6;
			instrumentEditorStringsScrollPanel.add(label_2, gbc_label_2);

			JPanel lengthPanel = new JPanel();
			GridBagConstraints gbc_lengthPanel = new GridBagConstraints();
			gbc_lengthPanel.insets = new Insets(0, 2, 0, 2);
			gbc_lengthPanel.gridheight = 4;
			gbc_lengthPanel.weightx = 1.0;
			gbc_lengthPanel.fill = GridBagConstraints.BOTH;
			gbc_lengthPanel.gridx = 1;
			gbc_lengthPanel.gridy = 6;
			instrumentEditorStringsScrollPanel.add(lengthPanel, gbc_lengthPanel);
			GridBagLayout gbl_lengthPanel = new GridBagLayout();
			lengthPanel.setLayout(gbl_lengthPanel);

			{
				textField_3 = new JTextField();
				GridBagConstraints gbc_textField_3 = new GridBagConstraints();
				gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_3.insets = new Insets(0, 0, 0, 2);
				gbc_textField_3.weightx = 1.0;
				gbc_textField_3.gridx = 0;
				gbc_textField_3.gridy = 0;
				lengthPanel.add(textField_3, gbc_textField_3);

				JLabel lblNewLabel = new JLabel("cm");
				lblNewLabel.setLabelFor(textField_3);
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 2, 0, 0);
				gbc_lblNewLabel.weighty = 1.0;
				gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 0;
				lengthPanel.add(lblNewLabel, gbc_lblNewLabel);
			}

			JPanel densityPanel = new JPanel();
			GridBagConstraints gbc_densityPanel = new GridBagConstraints();
			gbc_densityPanel.insets = new Insets(0, 2, 0, 2);
			gbc_densityPanel.gridheight = 4;
			gbc_densityPanel.weightx = 1.0;
			gbc_densityPanel.fill = GridBagConstraints.BOTH;
			gbc_densityPanel.gridx = 2;
			gbc_densityPanel.gridy = 6;
			instrumentEditorStringsScrollPanel.add(densityPanel, gbc_densityPanel);
			GridBagLayout gbl_densityPanel = new GridBagLayout();
			densityPanel.setLayout(gbl_densityPanel);

			{
				textField_7 = new JTextField();
				GridBagConstraints gbc_textField_7 = new GridBagConstraints();
				gbc_textField_7.weightx = 1.0;
				gbc_textField_7.insets = new Insets(0, 2, 0, 2);
				gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_7.gridx = 0;
				gbc_textField_7.gridy = 0;
				densityPanel.add(textField_7, gbc_textField_7);

				JLabel densityUnitLabel = new JLabel("g/m");
				densityUnitLabel.setLabelFor(textField_7);
				GridBagConstraints gbc_densityUnitLabel = new GridBagConstraints();
				gbc_densityUnitLabel.insets = new Insets(0, 2, 0, 0);
				gbc_densityUnitLabel.weighty = 1.0;
				gbc_densityUnitLabel.fill = GridBagConstraints.BOTH;
				gbc_densityUnitLabel.gridx = 1;
				gbc_densityUnitLabel.gridy = 0;
				densityPanel.add(densityUnitLabel, gbc_densityUnitLabel);
			}

			JButton btnSwap = new JButton("Swap", upDownIcon);
			GridBagConstraints gbc_btnSwap = new GridBagConstraints();
			gbc_btnSwap.insets = new Insets(0, 2, 0, 2);
			gbc_btnSwap.gridheight = 4;
			gbc_btnSwap.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSwap.gridx = 5;
			gbc_btnSwap.gridy = 4;
			instrumentEditorStringsScrollPanel.add(btnSwap, gbc_btnSwap);

			JButton btnInsert_1 = new JButton("Insert", plusIcon);
			GridBagConstraints gbc_btnInsert_1 = new GridBagConstraints();
			gbc_btnInsert_1.gridheight = 3;
			gbc_btnInsert_1.insets = new Insets(0, 2, 0, 2);
			gbc_btnInsert_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnInsert_1.gridx = 3;
			gbc_btnInsert_1.gridy = 1;
			instrumentEditorStringsScrollPanel.add(btnInsert_1, gbc_btnInsert_1);

			JButton button_1 = new JButton("Insert", plusIcon);
			GridBagConstraints gbc_button_1 = new GridBagConstraints();
			gbc_button_1.insets = new Insets(0, 2, 0, 2);
			gbc_button_1.gridheight = 4;
			gbc_button_1.gridx = 3;
			gbc_button_1.gridy = 4;
			instrumentEditorStringsScrollPanel.add(button_1, gbc_button_1);

			JButton button_6 = new JButton("Insert", plusIcon);
			GridBagConstraints gbc_button_6 = new GridBagConstraints();
			gbc_button_6.insets = new Insets(0, 2, 0, 2);
			gbc_button_6.gridheight = 3;
			gbc_button_6.gridx = 3;
			gbc_button_6.gridy = 8;
			instrumentEditorStringsScrollPanel.add(button_6, gbc_button_6);

			JButton button_2 = new JButton("Delete", minusIcon);
			GridBagConstraints gbc_button_2 = new GridBagConstraints();
			gbc_button_2.insets = new Insets(0, 2, 0, 4);
			gbc_button_2.gridheight = 4;
			gbc_button_2.gridx = 4;
			gbc_button_2.gridy = 2;
			instrumentEditorStringsScrollPanel.add(button_2, gbc_button_2);

			JButton button_4 = new JButton("Delete", minusIcon);
			GridBagConstraints gbc_button_4 = new GridBagConstraints();
			gbc_button_4.insets = new Insets(0, 2, 0, 4);
			gbc_button_4.gridheight = 4;
			gbc_button_4.gridx = 4;
			gbc_button_4.gridy = 6;
			instrumentEditorStringsScrollPanel.add(button_4, gbc_button_4);

			Dimension fullRow = new Dimension(0, button_2.getPreferredSize().height / 2);
			Dimension halfRow = new Dimension(button_2.getPreferredSize().width / 4, button_2.getPreferredSize().height / 4);

			JPanel bottomostFiller = new JPanel();
			bottomostFiller.setMinimumSize(fullRow); bottomostFiller.setPreferredSize(fullRow);
			GridBagConstraints gbc_bottomostFiller = new GridBagConstraints();
			gbc_bottomostFiller.fill = GridBagConstraints.BOTH;
			gbc_bottomostFiller.gridx = 6;
			gbc_bottomostFiller.gridy = 10;
			instrumentEditorStringsScrollPanel.add(bottomostFiller, gbc_bottomostFiller);

			JPanel topmostFiller = new JPanel();
			topmostFiller.setMinimumSize(fullRow); topmostFiller.setPreferredSize(fullRow);
			GridBagConstraints gbc_topmostFiller = new GridBagConstraints();
			gbc_topmostFiller.fill = GridBagConstraints.BOTH;
			gbc_topmostFiller.gridx = 6;
			gbc_topmostFiller.gridy = 1;
			instrumentEditorStringsScrollPanel.add(topmostFiller, gbc_topmostFiller);

			JPanel fillerPanel = new JPanel();
			fillerPanel.setMinimumSize(halfRow); fillerPanel.setPreferredSize(halfRow);
			GridBagConstraints gbc_fillerPanel = new GridBagConstraints();
			gbc_fillerPanel.weighty = 1.0;
			gbc_fillerPanel.fill = GridBagConstraints.BOTH;
			gbc_fillerPanel.insets = new Insets(4, 0, 4, 0);
			gbc_fillerPanel.gridx = 6;
			gbc_fillerPanel.gridy = 11;
			instrumentEditorStringsScrollPanel.add(fillerPanel, gbc_fillerPanel);

			JPanel panel_10 = new JPanel();
			panel_10.setMinimumSize(halfRow); panel_10.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_10 = new GridBagConstraints();
			gbc_panel_10.fill = GridBagConstraints.BOTH;
			gbc_panel_10.gridx = 6;
			gbc_panel_10.gridy = 9;
			instrumentEditorStringsScrollPanel.add(panel_10, gbc_panel_10);

			JPanel panel_9 = new JPanel();
			panel_9.setMinimumSize(halfRow); panel_9.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_9 = new GridBagConstraints();
			gbc_panel_9.fill = GridBagConstraints.BOTH;
			gbc_panel_9.gridx = 6;
			gbc_panel_9.gridy = 8;
			instrumentEditorStringsScrollPanel.add(panel_9, gbc_panel_9);

			JPanel panel_8 = new JPanel();
			panel_8.setMinimumSize(halfRow); panel_8.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_8 = new GridBagConstraints();
			gbc_panel_8.fill = GridBagConstraints.BOTH;
			gbc_panel_8.gridx = 6;
			gbc_panel_8.gridy = 7;
			instrumentEditorStringsScrollPanel.add(panel_8, gbc_panel_8);

			JPanel panel_7 = new JPanel();
			panel_7.setMinimumSize(halfRow); panel_7.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_7 = new GridBagConstraints();
			gbc_panel_7.fill = GridBagConstraints.BOTH;
			gbc_panel_7.gridx = 6;
			gbc_panel_7.gridy = 6;
			instrumentEditorStringsScrollPanel.add(panel_7, gbc_panel_7);

			JPanel panel_6 = new JPanel();
			panel_6.setMinimumSize(halfRow); panel_6.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_6 = new GridBagConstraints();
			gbc_panel_6.fill = GridBagConstraints.BOTH;
			gbc_panel_6.gridx = 6;
			gbc_panel_6.gridy = 5;
			instrumentEditorStringsScrollPanel.add(panel_6, gbc_panel_6);

			JPanel panel_5 = new JPanel();
			panel_5.setMinimumSize(halfRow); panel_5.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_5 = new GridBagConstraints();
			gbc_panel_5.fill = GridBagConstraints.BOTH;
			gbc_panel_5.gridx = 6;
			gbc_panel_5.gridy = 4;
			instrumentEditorStringsScrollPanel.add(panel_5, gbc_panel_5);

			JPanel panel_4 = new JPanel();
			panel_4.setMinimumSize(halfRow); panel_4.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.fill = GridBagConstraints.BOTH;
			gbc_panel_4.gridx = 6;
			gbc_panel_4.gridy = 3;
			instrumentEditorStringsScrollPanel.add(panel_4, gbc_panel_4);

			JPanel panel_3 = new JPanel();
			panel_3.setMinimumSize(halfRow); panel_3.setPreferredSize(halfRow);
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 6;
			gbc_panel_3.gridy = 2;
			instrumentEditorStringsScrollPanel.add(panel_3, gbc_panel_3);
		}

		final JPanel tuningEditorPlaceholderPanel = new JPanel();
		tabbedPane.addTab("Tuning Editor", null, tuningEditorPlaceholderPanel, null);
		tuningEditorPlaceholderPanel.setLayout(new BorderLayout(0, 0));


		final JPanel transitionCalculatorPanel = new JPanel();
		tabbedPane.addTab("Transition Calculator", null, transitionCalculatorPanel, null);

		final JPanel tensionCalculatorPanel = new JPanel();
		tabbedPane.addTab("Tension Calculator", null, tensionCalculatorPanel, null);


		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				JTabbedPane source = (JTabbedPane )event.getSource();
				Component component = source.getSelectedComponent();
				GridBagConstraints igbc = gbl_sidePanel.getConstraints(instrumentSidePanel);
				GridBagConstraints tgbc = gbl_sidePanel.getConstraints(tuningSidePanel);
				if (component == instrumentEditorContainerPanel) {
					instrumentSidePanel.setVisible(true);
					tuningSidePanel.setVisible(false);
					igbc.insets = new Insets(4, 4, 4, 4);
				}
				else if (component == tuningEditorPlaceholderPanel) {
					instrumentSidePanel.setVisible(false);
					tuningSidePanel.setVisible(true);
					tgbc.insets = new Insets(4, 4, 4, 4);
				}
				else if (component == transitionCalculatorPanel) {
					instrumentSidePanel.setVisible(false);
					tuningSidePanel.setVisible(true);
					tgbc.insets = new Insets(4, 4, 4, 4);
				}
				else if (component == tensionCalculatorPanel) {
					instrumentSidePanel.setVisible(true);
					tuningSidePanel.setVisible(true);
					igbc.insets = new Insets(4, 4, 2, 4);
					tgbc.insets = new Insets(2, 4, 4, 4);
				}
				else return;
				gbl_sidePanel.setConstraints(instrumentSidePanel, igbc);
				gbl_sidePanel.setConstraints(tuningSidePanel, tgbc);
				sidePanel.revalidate();
				sidePanel.repaint();
			}
		});

		for (ChangeListener changeListener : tabbedPane.getChangeListeners()) {
			changeListener.stateChanged(new ChangeEvent(tabbedPane));
		}
	}
}
