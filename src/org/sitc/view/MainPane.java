package org.sitc.view;

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

import org.sitc.model.Instrument;
import org.sitc.model.Sequence;
import org.sitc.model.Tuning;

/**
Represents a main pane that
 combines manager panels and editor panels.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MainPane extends JSplitPane {
	private static final long serialVersionUID = 1;

	private final ManagerPanel<Instrument> instrumentManagerPanel;
	private final ManagerPanel<Tuning> tuningManagerPanel;
	private final ManagerPanel<Sequence> sequenceManagerPanel;
	private final InstrumentEditorPanel instrumentEditorPanel;
	private final TuningEditorPanel tuningEditorPanel;
	private final SequenceEditorPanel sequenceEditorPanel;

	/**
	Creates a main pane.
	**/
	public MainPane() {
		super(JSplitPane.HORIZONTAL_SPLIT);

				instrumentManagerPanel = new ManagerPanel<Instrument>();
				instrumentManagerPanel.setTitle("Instruments");
				instrumentManagerPanel.getRemotePanel().setTitle("Instrument File");
				instrumentManagerPanel.getLocalPanel().setTitle("Instrument List");

				tuningManagerPanel = new ManagerPanel<Tuning>();
				tuningManagerPanel.setTitle("Tunings");
				tuningManagerPanel.getRemotePanel().setTitle("Tuning File");
				tuningManagerPanel.getLocalPanel().setTitle("Tuning List");

				sequenceManagerPanel = new ManagerPanel<Sequence>();
				sequenceManagerPanel.setTitle("Sequences");
				sequenceManagerPanel.getRemotePanel().setTitle("Sequence File");
				sequenceManagerPanel.getLocalPanel().setTitle("Sequence List");

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
	public ManagerPanel<Instrument> getInstrumentManagerPanel() {
		return instrumentManagerPanel;
	}

	/**
	@return The tuning manager panel.
	**/
	public ManagerPanel<Tuning> getTuningManagerPanel() {
		return tuningManagerPanel;
	}

	/**
	@return The sequence manager panel.
	**/
	public ManagerPanel<Sequence> getSequenceManagerPanel() {
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
