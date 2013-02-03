package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 1l;

	private JPanel createManagementPanel(final String title, final String fileTitle, final String listTitle) {
		final JTextField pathTextField = new JTextField();

		final JButton browseButton = new JButton("Browse");
		Helpers.setScaledIcon(browseButton, Resources.BROWSE_ICON, SwingConstants.HORIZONTAL);
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The file browser goes here.", "File Browser", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
		filePanel.add(pathTextField, BorderLayout.CENTER);
		filePanel.add(browseButton, BorderLayout.EAST);

		final JPanel titledFilePanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
		titledFilePanel.setBorder(new TitledBorder(fileTitle));
		titledFilePanel.add(filePanel, BorderLayout.CENTER);

		final JButton loadButton = new JButton("Load");
		Helpers.setScaledIcon(loadButton, Resources.DOWN_ICON, SwingConstants.HORIZONTAL);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The file loader goes here.", "File Loader", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JButton saveButton = new JButton("Save");
		Helpers.setScaledIcon(saveButton, Resources.UP_ICON, SwingConstants.HORIZONTAL);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The file saver goes here.", "File Saver", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel interfacePanel = new JPanel(new GridLayout(1, 2, Constants.BIG_INSET, Constants.BIG_INSET));
		interfacePanel.add(loadButton);
		interfacePanel.add(saveButton);

		final JTextField searchTextField = new JTextField();

		final JButton searchButton = new JButton("Search");
		Helpers.setScaledIcon(searchButton, Resources.SEARCH_ICON, SwingConstants.HORIZONTAL);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The list searcher goes here.", "List Searcher", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel searchPanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
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
		Helpers.setScaledIcon(newButton, Resources.PLUS_ICON, SwingConstants.HORIZONTAL);
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The creator goes here.", "Creator", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JButton deleteButton = new JButton("Delete");
		Helpers.setScaledIcon(deleteButton, Resources.MINUS_ICON, SwingConstants.HORIZONTAL);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The disposer goes here.", "Disposer", JOptionPane.PLAIN_MESSAGE);
			}
		});

		final JPanel existencePanel = new JPanel(new GridLayout(1, 2, Constants.BIG_INSET, Constants.BIG_INSET));
		existencePanel.add(newButton);
		existencePanel.add(deleteButton);

		final JPanel listPanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
		listPanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
		listPanel.add(searchPanel, BorderLayout.NORTH);
		listPanel.add(list, BorderLayout.CENTER);
		listPanel.add(existencePanel, BorderLayout.SOUTH);

		final JPanel titledListPanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
		titledListPanel.setBorder(new TitledBorder(listTitle));
		titledListPanel.add(listPanel, BorderLayout.CENTER);

		final JPanel selectionPanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
		selectionPanel.add(interfacePanel, BorderLayout.NORTH);
		selectionPanel.add(titledListPanel, BorderLayout.CENTER);

		final JPanel managementPanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
		managementPanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
		managementPanel.add(titledFilePanel, BorderLayout.NORTH);
		managementPanel.add(selectionPanel, BorderLayout.CENTER);

		final JPanel titledManagementPanel = new JPanel(new BorderLayout(Constants.BIG_INSET, Constants.BIG_INSET));
		titledManagementPanel.setBorder(new TitledBorder(title));
		titledManagementPanel.add(managementPanel, BorderLayout.CENTER);

		return titledManagementPanel;
	}

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

	private void addSplitPane() {
		final JPanel instrumentManagementPanel = createManagementPanel("Instruments", "Instrument File", "Instrument List"),
				tuningManagementPanel = createManagementPanel("Tunings", "Tuning File", "Tuning List"),
				transitionManagementPanel = createManagementPanel("Transitions", "Transition File", "Transition List");

		final GridLayout sidePanelLayout = new GridLayout(3, 1, Constants.BIG_INSET, Constants.BIG_INSET);
		final JPanel sidePanel = new JPanel(sidePanelLayout);
		sidePanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
		sidePanel.add(instrumentManagementPanel);
		sidePanel.add(tuningManagementPanel);
		sidePanel.add(transitionManagementPanel);

		final JPanel instrumentEditorPanel = new JPanel(),
				tuningEditorPanel = new JPanel(),
				transitionEditorPanel = new JPanel();

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.addTab("Instrument Editor", null, instrumentEditorPanel);
		tabbedPane.addTab("Tuning Editor", null, tuningEditorPanel);
		tabbedPane.addTab("Transition Editor", null, transitionEditorPanel);

		final ChangeListener tabbedPaneChangeListener = new ChangeListener() {
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
		};
		tabbedPane.addChangeListener(tabbedPaneChangeListener);
		tabbedPaneChangeListener.stateChanged(new ChangeEvent(tabbedPane));

		final JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0);
		splitPane.setContinuousLayout(true);
		splitPane.setLeftComponent(sidePanel);
		splitPane.setRightComponent(mainPanel);

		final Container contentPane = getContentPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
	}

	private void addStatusBar() {
		final JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL, Byte.MIN_VALUE, Byte.MAX_VALUE);

		final JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.add(progressBar);

		final Container contentPane = getContentPane();
		contentPane.add(statusPanel, BorderLayout.SOUTH);
	}

	public MainFrame() {
		addMenuBar();
		addSplitPane();
		addStatusBar();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("String Instrument Tuning Database");
		setIconImages(Resources.ICON_IMAGES);
		pack();
		setMinimumSize(getSize());
		setSize(new Dimension(800, 600));
		setVisible(true);
	}
}
