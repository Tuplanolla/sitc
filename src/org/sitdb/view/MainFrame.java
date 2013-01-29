package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 1l;

	private JPanel createFilePanel(final String plural, final String singular) {
		final JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.setBorder(new TitledBorder(plural));
		return filePanel;
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
		final JPanel instrumentFilePanel = createFilePanel("Instruments", "Instrument"),
				tuningFilePanel = createFilePanel("Tunings", "Tuning"),
				transitionFilePanel = createFilePanel("Transitions", "Transition");

		{
			final JButton testButton = new JButton("Test");
			final Dimension preferredSize = testButton.getPreferredSize();
			final int minimumSize = Math.min(preferredSize.width, preferredSize.height);
			final Image image = Assets.RIGHT_ICON.getImage().getScaledInstance(minimumSize, minimumSize, Image.SCALE_SMOOTH);
			testButton.setIcon(new ImageIcon(image));
			testButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			testButton.setHorizontalTextPosition(SwingConstants.CENTER);
			instrumentFilePanel.setLayout(new FlowLayout());
			instrumentFilePanel.add(testButton);
		}

		final GridLayout sidePanelLayout = new GridLayout(3, 1, 0, Constants.BIG_INSET);
		final JPanel sidePanel = new JPanel(sidePanelLayout);
		sidePanel.setBorder(new EmptyBorder(Constants.BIG_INSETS));
		sidePanel.add(instrumentFilePanel);
		sidePanel.add(tuningFilePanel);
		sidePanel.add(transitionFilePanel);

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
					sidePanel.add(instrumentFilePanel);
				}
				else if (selectedComponent == tuningEditorPanel) {
					sidePanelLayout.setRows(1);
					sidePanel.add(tuningFilePanel);
				}
				else if (selectedComponent == transitionEditorPanel) {
					sidePanelLayout.setRows(3);
					sidePanel.add(instrumentFilePanel);
					sidePanel.add(tuningFilePanel);
					sidePanel.add(transitionFilePanel);
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
		splitPane.setResizeWeight(0.5);
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
		{
			setMinimumSize(new Dimension(800, 600));
		}
		pack();
		setMinimumSize(getSize());
		setSize(new Dimension(800, 600));
		setVisible(true);
	}
}
