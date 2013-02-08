package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
Represents the main window.

The window has the following structural hierarchy:

<pre>
+-------------------------------------------------------------------+
| MainFrame                                                         |
+-------------------------------------------------------------------+
| MenuBar                                                           |
|-------------------------------------------------------------------|
| SidePanel           | MainPanel                                   |
| +-----------------+ | +-------------+-------------+-------------+ |
| | ManagementPanel | | | EditorPanel | EditorPanel | EditorPanel | |
| |                 | | |             +-------------+-------------+ |
| |                 | | | +-----------------+ +-----------------+ | |
| +-----------------+ | | | EditorSidePanel | | EditorMainPanel | | |
| +-----------------+ | | |                 | |                 | | |
| | ManagementPanel | | | |                 | |                 | | |
| |                 | | | |                 | |                 | | |
| |                 | | | |                 | |                 | | |
| +-----------------+ | | |                 | |                 | | |
| +-----------------+ | | |                 | |                 | | |
| | ManagementPanel | | | |                 | |                 | | |
| |                 | | | |                 | |                 | | |
| |                 | | | +-----------------+ +-----------------+ | |
| +-----------------+ | +-----------------------------------------+ |
|-------------------------------------------------------------------|
| StatusBar                                                         |
+-------------------------------------------------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {//TODO move the listeners into the controller
	private static final long serialVersionUID = 1l;

	private static final double iconScale = 0.75;
	private JProgressBar progressBar;

	/**
	Creates a panel that's used to
	 load data,
	 manage a local copy of the data and
	 eventually save data.

	@param title The title of the whole panel.
	@param fileTitle The title of the file manager.
	@param listTitle The title of the list manager.
	@return The panel.
	**/
	private JPanel createManagementPanel(final String title, final String fileTitle, final String listTitle) {
		final JTextField pathTextField = new JTextField();

		final JButton browseButton = new JButton("Browse");
		Utilities.setScaledIcon(browseButton, Resources.BROWSE_ICON, SwingConstants.HORIZONTAL, iconScale);
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The file browser goes here.", "File Browser", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		filePanel.add(pathTextField, BorderLayout.CENTER);
		filePanel.add(browseButton, BorderLayout.EAST);

		final JPanel titledFilePanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledFilePanel.setBorder(new TitledBorder(fileTitle));
		titledFilePanel.add(filePanel, BorderLayout.CENTER);

		final JButton loadButton = new JButton("Load");
		Utilities.setScaledIcon(loadButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, iconScale);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The file loader goes here.", "File Loader", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JButton saveButton = new JButton("Save");
		Utilities.setScaledIcon(saveButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, iconScale);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The file saver goes here.", "File Saver", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel interfacePanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		interfacePanel.add(loadButton);
		interfacePanel.add(saveButton);

		final JTextField searchTextField = new JTextField();

		final JButton searchButton = new JButton("Search");
		Utilities.setScaledIcon(searchButton, Resources.SEARCH_ICON, SwingConstants.HORIZONTAL, iconScale);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The list searcher goes here.", "List Searcher", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel searchPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		searchPanel.add(searchTextField, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);

		final JList<String> list = new JList<String>(new String[] {
			"One of the " + title,
			"Another One of the " + title,
			"Yet Another One of the " + title
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED));
		list.setBorder(new JTextField().getBorder());//TODO test

		final JButton newButton = new JButton("New");
		Utilities.setScaledIcon(newButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL, iconScale);
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The list creator goes here.", "List Creator", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JButton deleteButton = new JButton("Delete");
		Utilities.setScaledIcon(deleteButton, Resources.MINUS_ICON, SwingConstants.HORIZONTAL, iconScale);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The list destroyer goes here.", "List Destroyer", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel existencePanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		existencePanel.add(newButton);
		existencePanel.add(deleteButton);

		final JPanel listPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		listPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		listPanel.add(searchPanel, BorderLayout.NORTH);
		listPanel.add(list, BorderLayout.CENTER);
		listPanel.add(existencePanel, BorderLayout.SOUTH);

		final JPanel titledListPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledListPanel.setBorder(new TitledBorder(listTitle));
		titledListPanel.add(listPanel, BorderLayout.CENTER);

		final JPanel selectionPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		selectionPanel.add(interfacePanel, BorderLayout.NORTH);
		selectionPanel.add(titledListPanel, BorderLayout.CENTER);

		final JPanel managementPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		managementPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		managementPanel.add(titledFilePanel, BorderLayout.NORTH);
		managementPanel.add(selectionPanel, BorderLayout.CENTER);

		final JPanel titledManagementPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledManagementPanel.setBorder(new TitledBorder(title));
		titledManagementPanel.add(managementPanel, BorderLayout.CENTER);

		return titledManagementPanel;
	}

	/**
	Creates a panel that's used to
	 import data,
	 edit a local copy of the data and
	 eventually export the data.

	@param title The title of the whole panel.
	@return The panel.
	**/
	private JPanel createEditorPanel(final String title) {
		final JButton revertButton = new JButton("Revert");
		Utilities.setScaledIcon(revertButton, Resources.RIGHT_ICON, SwingConstants.VERTICAL, iconScale);
		revertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The reverter goes here.", "Reverter", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JButton applyButton = new JButton("Apply");
		Utilities.setScaledIcon(applyButton, Resources.LEFT_ICON, SwingConstants.VERTICAL, iconScale);
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The applier goes here.", "Applier", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final GridBagConstraints revertButtonConstraints = new GridBagConstraints();
		revertButtonConstraints.gridx = 0;
		revertButtonConstraints.gridy = 0;
		revertButtonConstraints.weightx = 1;
		revertButtonConstraints.weighty = 0;
		revertButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		revertButtonConstraints.insets = new Insets(0, 0, Constants.MEDIUM_INSET / 2, 0);

		final GridBagConstraints applyButtonConstraints = new GridBagConstraints();
		applyButtonConstraints.gridx = 0;
		applyButtonConstraints.gridy = 1;
		applyButtonConstraints.weightx = 1;
		applyButtonConstraints.weighty = 0;
		applyButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		applyButtonConstraints.insets = new Insets(Constants.MEDIUM_INSET / 2, 0, 0, 0);

		final JPanel sidePanel = new JPanel(new GridBagLayout());
		sidePanel.add(revertButton, revertButtonConstraints);
		sidePanel.add(applyButton, applyButtonConstraints);

		final JButton importButton = new JButton("Import");
		Utilities.setScaledIcon(importButton, Resources.UP_ICON, SwingConstants.HORIZONTAL, iconScale);
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The importer goes here.", "Importer", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JButton exportButton = new JButton("Export");
		Utilities.setScaledIcon(exportButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL, iconScale);
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The exporter goes here.", "Exporter", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel controlPanel = new JPanel();

		final JPanel portPanel = new JPanel(new GridLayout(1, 2, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		portPanel.add(importButton);
		portPanel.add(exportButton);

		final JPanel mainPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		mainPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		mainPanel.add(controlPanel, BorderLayout.CENTER);
		mainPanel.add(portPanel, BorderLayout.SOUTH);

		final JPanel titledMainPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		titledMainPanel.setBorder(new TitledBorder(title));
		titledMainPanel.add(mainPanel, BorderLayout.CENTER);

		final JPanel editorPanel = new JPanel(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));
		editorPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		editorPanel.add(sidePanel, BorderLayout.WEST);
		editorPanel.add(titledMainPanel, BorderLayout.CENTER);

		return editorPanel;
	}

	/**
	Adds the menu bar to this window.
	**/
	private void addMenuBar() {
		final JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				MainFrame.this.dispose();
			}
		});

		final JCheckBoxMenuItem microtonalMenuCheckbox = new JCheckBoxMenuItem("Hide Microtonals");

		final JCheckBoxMenuItem notationMenuCheckbox = new JCheckBoxMenuItem("Use European Notation");

		final JMenuItem manualMenuItem = new JMenuItem("Manual");
		manualMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The manual goes here.", "Manual", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The about dialog goes here.", "About", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JMenu fileMenu = new JMenu("File");
		fileMenu.add(exitMenuItem);

		final JMenu settingsMenu = new JMenu("Settings");
		settingsMenu.add(microtonalMenuCheckbox);
		settingsMenu.add(notationMenuCheckbox);

		final JMenu helpMenu = new JMenu("Help");
		helpMenu.add(manualMenuItem);
		helpMenu.add(aboutMenuItem);

		final JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.add(fileMenu);
		menuBar.add(settingsMenu);
		menuBar.add(helpMenu);

		final Container contentPane = getContentPane();
		contentPane.add(menuBar, BorderLayout.NORTH);
	}

	/**
	Adds the actual layout to this window.
	**/
	private void addSplitPane() {
		final JPanel instrumentManagementPanel = createManagementPanel("Instruments", "Instrument File", "Instrument List"),
				tuningManagementPanel = createManagementPanel("Tunings", "Tuning File", "Tuning List"),
				transitionManagementPanel = createManagementPanel("Transitions", "Transition File", "Transition List");

		final GridLayout sidePanelLayout = new GridLayout(3, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET);
		final JPanel sidePanel = new JPanel(sidePanelLayout);
		sidePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		sidePanel.add(instrumentManagementPanel);
		sidePanel.add(tuningManagementPanel);
		sidePanel.add(transitionManagementPanel);

		final JPanel instrumentEditorPanel = createEditorPanel("Instrument"),
				tuningEditorPanel = createEditorPanel("Tuning"),
				transitionEditorPanel = createEditorPanel("Transition");

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.addTab("Instrument Editor", null, instrumentEditorPanel);
		tabbedPane.addTab("Tuning Editor", null, tuningEditorPanel);
		tabbedPane.addTab("Transition Editor", null, transitionEditorPanel);

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent event) {
				final Component selectedComponent = tabbedPane.getSelectedComponent();
				sidePanel.removeAll();
				if (selectedComponent == instrumentEditorPanel) {
					sidePanelLayout.setRows(1);
					sidePanel.add(instrumentManagementPanel);
				}
				else if (selectedComponent == tuningEditorPanel) {
					sidePanelLayout.setRows(1);
					sidePanel.add(tuningManagementPanel);
				}
				else if (selectedComponent == transitionEditorPanel) {
					sidePanelLayout.setRows(3);
					sidePanel.add(instrumentManagementPanel);
					sidePanel.add(tuningManagementPanel);
					sidePanel.add(transitionManagementPanel);
				}
				sidePanel.revalidate();
				sidePanel.repaint();
			}
		});

		final JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0);
		splitPane.setContinuousLayout(true);
		splitPane.setLeftComponent(sidePanel);
		splitPane.setRightComponent(mainPanel);

		final Container contentPane = getContentPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
	}

	/**
	Adds the status bar to this window.
	**/
	private void addStatusBar() {
		progressBar = new JProgressBar(SwingConstants.HORIZONTAL, Byte.MIN_VALUE, Byte.MAX_VALUE);

		final JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.add(progressBar);

		final Container contentPane = getContentPane();
		contentPane.add(statusPanel, BorderLayout.SOUTH);
	}

	/**
	Constructs and initializes a new window.
	**/
	public MainFrame() {
		/*
		Where I deadlocked, I softer thread ---
		I sow sweet synch block --- From standard read ---
		I pause above that line ahead
			And assert.
		*/
		assert SwingUtilities.isEventDispatchThread();

		/*
		Whom I deadlocked, I cryptic bard
		From syntax harsh, or ill keyword ---
		Feeling as if their anger seared,
			Though vain!
		*/
		addMenuBar();
		addSplitPane();
		addStatusBar();
		setTitle("String Instrument Tuning Database");
		setIconImages(Resources.ICON_IMAGES);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		/*
		When I deadlock, you'll know by this ---
		A buffer black --- Flickers amiss ---
		A static tremor as a hiss
			Like piss!
		*/
		pack();
		setMinimumSize(getSize());
		setSize(new Dimension(800, 600));
		Utilities.allStatesChanged(this);

		/*
		Why, I deadlocked, the people know
		Who thought this program isn't slow
		Went home a century ago
			Next Bliss!
		*/
		setVisible(true);
	}
}
