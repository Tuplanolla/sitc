package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
Represents a panel that does something.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainPanel extends JSplitPane {
	private static final long serialVersionUID = 7394003596145374355l;

	private final ManagerPanel instrumentManagerPanel,
			tuningManagerPanel,
			transitionManagerPanel;
	private final EditorPanel instrumentEditorPanel,
			tuningEditorPanel,
			transitionEditorPanel;

	/**
	Constructs a new panel.
	**/
	public MainPanel() {
		super(JSplitPane.HORIZONTAL_SPLIT);

		instrumentManagerPanel = new ManagerPanel();
		instrumentManagerPanel.setTitle("Instruments");
		instrumentManagerPanel.getFilePanel().setTitle("Instrument File");
		instrumentManagerPanel.getListPanel().setTitle("Instrument List");

		tuningManagerPanel = new ManagerPanel();

		transitionManagerPanel = new ManagerPanel();

		final GridLayout sidePanelLayout = new GridLayout(3, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET);
		final JPanel sidePanel = new JPanel(sidePanelLayout);
		sidePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		sidePanel.add(instrumentManagerPanel);
		sidePanel.add(tuningManagerPanel);
		sidePanel.add(transitionManagerPanel);

		instrumentEditorPanel = new EditorPanel();
		instrumentEditorPanel.setTitle("Instrument");

		tuningEditorPanel = new EditorPanel();

		transitionEditorPanel = new EditorPanel();

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
					sidePanel.add(instrumentManagerPanel);
				}
				else if (selectedComponent == tuningEditorPanel) {
					sidePanelLayout.setRows(1);
					sidePanel.add(tuningManagerPanel);
				}
				else if (selectedComponent == transitionEditorPanel) {
					sidePanelLayout.setRows(3);
					sidePanel.add(instrumentManagerPanel);
					sidePanel.add(tuningManagerPanel);
					sidePanel.add(transitionManagerPanel);
				}
				sidePanel.revalidate();
				sidePanel.repaint();
			}
		});

		final JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		setResizeWeight(0);
		setContinuousLayout(true);
		setRightComponent(mainPanel);
		setLeftComponent(sidePanel);
	}

	/**
	@return The instrument manager panel.
	**/
	public ManagerPanel getInstrumentManagerPanel() {
		return instrumentManagerPanel;
	}

	/**
	@return The tuning manager panel.
	**/
	public ManagerPanel getTuningManagerPanel() {
		return tuningManagerPanel;
	}

	/**
	@return The transition manager panel.
	**/
	public ManagerPanel getTransitionManagerPanel() {
		return transitionManagerPanel;
	}

	/**
	@return The instrument editor panel.
	**/
	public EditorPanel getInstrumentEditorPanel() {
		return instrumentEditorPanel;
	}

	/**
	@return The tuning editor panel.
	**/
	public EditorPanel getTuningEditorPanel() {
		return tuningEditorPanel;
	}

	/**
	@return The transition editor panel.
	**/
	public EditorPanel getTransitionEditorPanel() {
		return transitionEditorPanel;
	}
}
