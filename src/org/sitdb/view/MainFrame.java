package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
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

	private JProgressBar progressBar;

	/*
	browseButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The file browser goes here.", "File Browser", JOptionPane.PLAIN_MESSAGE);
		}
	});
	loadButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The file loader goes here.", "File Loader", JOptionPane.PLAIN_MESSAGE);
		}
	});
	saveButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The file saver goes here.", "File Saver", JOptionPane.PLAIN_MESSAGE);
		}
	});
	searchButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The list searcher goes here.", "List Searcher", JOptionPane.PLAIN_MESSAGE);
		}
	});
	newButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The list creator goes here.", "List Creator", JOptionPane.PLAIN_MESSAGE);
		}
	});
	deleteButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The list destroyer goes here.", "List Destroyer", JOptionPane.PLAIN_MESSAGE);
		}
	});
	*/

	/*
	revertButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The reverter goes here.", "Reverter", JOptionPane.PLAIN_MESSAGE);
		}
	});
	applyButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The applier goes here.", "Applier", JOptionPane.PLAIN_MESSAGE);
		}
	});
	importButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The importer goes here.", "Importer", JOptionPane.PLAIN_MESSAGE);
		}
	});
	exportButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this, "The exporter goes here.", "Exporter", JOptionPane.PLAIN_MESSAGE);
		}
	});
	*/

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
		final ManagementPanel instrumentManagementPanel = new ManagementPanel();
		instrumentManagementPanel.setTitle("Instruments");
		instrumentManagementPanel.getFilePanel().setTitle("Instrument File");
		instrumentManagementPanel.getListPanel().setTitle("Instrument List");

		final JPanel tuningManagementPanel = new JPanel();

		final JPanel transitionManagementPanel = new JPanel();

		final GridLayout sidePanelLayout = new GridLayout(3, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET);
		final JPanel sidePanel = new JPanel(sidePanelLayout);
		sidePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		sidePanel.add(instrumentManagementPanel);
		sidePanel.add(tuningManagementPanel);
		sidePanel.add(transitionManagementPanel);

		final EditorPanel instrumentEditorPanel = new EditorPanel();
		instrumentEditorPanel.setTitle("Instrument");

		final EditorPanel tuningEditorPanel = new EditorPanel();

		final EditorPanel transitionEditorPanel = new EditorPanel();

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
		splitPane.setRightComponent(mainPanel);
		splitPane.setLeftComponent(sidePanel);

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
