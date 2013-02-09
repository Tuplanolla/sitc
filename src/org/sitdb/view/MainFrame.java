package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
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
/---------------------------------------------------------------------------\
| [ ] MainFrame                                                 [_] [#] [X] |
+---------------------------------------------------------------------------+
| menuBar                                                                   |
|---------------------------------------------------------------------------|
| splitPane               |                                                 |
| +---------------------+ | +---------------------------------------------+ |
| | sidePanel           | | | mainPanel                                   | |
| | +-----------------+ | | | +-------------+-------------+-------------+ | |
| | | ManagementPanel | | | | | EditorPanel | EditorPanel | EditorPanel | | |
| | +-----------------+ | | | |             +-------------+-------------+ | |
| | +-----------------+ | | | |                                         | | |
| | | ManagementPanel | | | | |                                         | | |
| | +-----------------+ | | | |                                         | | |
| | +-----------------+ | | | |                                         | | |
| | | ManagementPanel | | | | |                                         | | |
| | +-----------------+ | | | +-----------------------------------------+ | |
| +---------------------+ | +---------------------------------------------+ |
|---------------------------------------------------------------------------|
| statusBar                                                                 |
+---------------------------------------------------------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 2641621164911831151l;

	private JMenuItem exitMenuItem;
	private JCheckBoxMenuItem microtonalMenuCheckbox;
	private JCheckBoxMenuItem notationMenuCheckbox;
	private JMenuItem manualMenuItem;
	private JMenuItem aboutMenuItem;

	private ManagementPanel instrumentManagementPanel;
	private EditorPanel instrumentEditorPanel;

	private JProgressBar progressBar;

	private void addTestControls() {//TODO move into the controller
		instrumentManagementPanel.getFilePanel().getBrowseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument file browser goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentManagementPanel.getListPanel().getLoadButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument file loader goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentManagementPanel.getListPanel().getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument file saver goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentManagementPanel.getListPanel().getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument list searcher goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentManagementPanel.getListPanel().getNewButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument list item creator goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentManagementPanel.getListPanel().getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument list item disposer goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});

		instrumentEditorPanel.getRevertButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument editor change reverter goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentEditorPanel.getApplyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument editor change applier goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentEditorPanel.getImportButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument editor importer goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		instrumentEditorPanel.getExportButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument editor exporter goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});

		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				MainFrame.this.dispose();
			}
		});
		manualMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The manual goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The about dialog goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	/**
	Adds the menu bar to this window.
	**/
	private JComponent buildMenuBar() {
		exitMenuItem = new JMenuItem("Exit");

		microtonalMenuCheckbox = new JCheckBoxMenuItem("Hide Microtonals");

		notationMenuCheckbox = new JCheckBoxMenuItem("Use European Notation");

		manualMenuItem = new JMenuItem("Manual");

		aboutMenuItem = new JMenuItem("About");

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

		return menuBar;
	}

	/**
	Adds the actual layout to this window.
	**/
	private JComponent buildSplitPane() {
		instrumentManagementPanel = new ManagementPanel();
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

		instrumentEditorPanel = new EditorPanel();
		instrumentEditorPanel.setTitle("Instrument");

		final JPanel tuningEditorPanel = new JPanel();

		final JPanel transitionEditorPanel = new JPanel();

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

		return splitPane;
	}

	/**
	Adds the status bar to this window.
	**/
	private JComponent buildStatusBar() {
		progressBar = new JProgressBar(SwingConstants.HORIZONTAL, Byte.MIN_VALUE, Byte.MAX_VALUE);

		final JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.add(progressBar);

		return statusPanel;
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
		final Container contentPane = getContentPane();
		contentPane.add(buildMenuBar(), BorderLayout.NORTH);
		contentPane.add(buildSplitPane(), BorderLayout.CENTER);//TODO consider splitting
		contentPane.add(buildStatusBar(), BorderLayout.SOUTH);
		addTestControls();
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
