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
Represents a panel that combines manager panels with editor panels.

The panel has the following structural hierarchy:

<pre>
+----------------------------------------------------------------+
| MainPanel        |                                             |
| +--------------+ | +-------------+-------------+-------------+ |
| | ManagerPanel | | | EditorPanel | EditorPanel | EditorPanel | |
| +--------------+ | |             +-------------+-------------+ |
| +--------------+ | |                                         | |
| | ManagerPanel | | |                                         | |
| +--------------+ | |                                         | |
| +--------------+ | |                                         | |
| | ManagerPanel | | |                                         | |
| +--------------+ | +-----------------------------------------+ |
+----------------------------------------------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainPanel extends JSplitPane {
	private static final long serialVersionUID = 7394003596145374355l;

	private final ManagerPanel instrumentManagerPanel;
	private final ManagerPanel tuningManagerPanel;
	private final ManagerPanel sequenceManagerPanel;
	private final InstrumentEditorPanel instrumentEditorPanel;
	private final TuningEditorPanel tuningEditorPanel;
	private final SequenceEditorPanel sequenceEditorPanel;

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
		tuningManagerPanel.setTitle("Tunings");
		tuningManagerPanel.getFilePanel().setTitle("Tuning File");
		tuningManagerPanel.getListPanel().setTitle("Tuning List");

		sequenceManagerPanel = new ManagerPanel();
		sequenceManagerPanel.setTitle("Sequences");
		sequenceManagerPanel.getFilePanel().setTitle("Sequence File");
		sequenceManagerPanel.getListPanel().setTitle("Sequence List");

		final GridLayout sidePanelLayout = new GridLayout(3, 1, Constants.MEDIUM_INSET, Constants.MEDIUM_INSET);

		final JPanel sidePanel = new JPanel(sidePanelLayout);
		sidePanel.setBorder(new EmptyBorder(Constants.MEDIUM_INSETS));
		sidePanel.add(instrumentManagerPanel);
		sidePanel.add(tuningManagerPanel);
		sidePanel.add(sequenceManagerPanel);

		instrumentEditorPanel = new InstrumentEditorPanel();

		tuningEditorPanel = new TuningEditorPanel();

		sequenceEditorPanel = new SequenceEditorPanel();

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.addTab("Instrument Editor", null, instrumentEditorPanel);
		tabbedPane.addTab("Tuning Editor", null, tuningEditorPanel);
		tabbedPane.addTab("Sequence Editor", null, sequenceEditorPanel);

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
				else if (selectedComponent == sequenceEditorPanel) {
					sidePanelLayout.setRows(3);
					sidePanel.add(instrumentManagerPanel);
					sidePanel.add(tuningManagerPanel);
					sidePanel.add(sequenceManagerPanel);
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
	@return The sequence manager panel.
	**/
	public ManagerPanel getSequenceManagerPanel() {
		return sequenceManagerPanel;
	}

	/**
	@return The instrument editor panel.
	**/
	public InstrumentEditorPanel getInstrumentEditorPanel() {
		return instrumentEditorPanel;
	}

	/**
	@return The tuning editor panel.
	**/
	public TuningEditorPanel getTuningEditorPanel() {
		return tuningEditorPanel;
	}

	/**
	@return The sequence editor panel.
	**/
	public SequenceEditorPanel getSequenceEditorPanel() {
		return sequenceEditorPanel;
	}
}
