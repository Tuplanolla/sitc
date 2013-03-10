package org.sitdb.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

import org.sitdb.Part;
import org.sitdb.model.Instrument;
import org.sitdb.model.Model;
import org.sitdb.model.String;
import org.sitdb.view.EditorInterfacePanel;
import org.sitdb.view.InstrumentEditorPanel;
import org.sitdb.view.InstrumentMagicPanel;
import org.sitdb.view.LocalInterfacePanel;
import org.sitdb.view.LocalPanel;
import org.sitdb.view.MainFrame;
import org.sitdb.view.MainPane;
import org.sitdb.view.ManagerPanel;
import org.sitdb.view.MenuBar;
import org.sitdb.view.RemotePanel;
import org.sitdb.view.StackTracePanel;
import org.sitdb.view.View;

/**
Represents an immutable controller.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Controller implements Part {//TODO split
	private final Model model;
	private final View view;

	/**
	Creates a view and links it with a model and a view.

	@param model The model.
	@param view The view.
	**/
	public Controller(final Model model, final View view) {
		this.model = model;
		this.view = view;
	}

	/**
	Activates the menu bar.
	**/
	private void activateMenuBar() {
		final MenuBar menuBar = view.getMainFrame().getJMenuBar();

		menuBar.getExitMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				view.getMainFrame().dispose();
			}
		});

		menuBar.getManualMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(view.getMainFrame(), "Push buttons.", "Manual", JOptionPane.PLAIN_MESSAGE);
			}
		});

		menuBar.getAboutMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(view.getMainFrame(), "This program is the best thing since sliced bread.", "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	/**
	Activates the remote panels.
	**/
	private void activateRemotePanels() {
		final MainFrame mainFrame = view.getMainFrame();
		final MainPane mainPane = mainFrame.getMainPane();

		final RemotePanel remoteInstrumentPanel = mainPane.getInstrumentManagerPanel().getRemotePanel();
		final ActionListener remoteInstrumentActionListener = new BrowseActionListener(mainFrame, remoteInstrumentPanel.getPathTextField());
		remoteInstrumentPanel.getBrowseButton().addActionListener(remoteInstrumentActionListener);

		final RemotePanel remoteTuningPanel = mainPane.getTuningManagerPanel().getRemotePanel();
		final ActionListener remoteTuningActionListener = new BrowseActionListener(mainFrame, remoteTuningPanel.getPathTextField());
		remoteTuningPanel.getBrowseButton().addActionListener(remoteTuningActionListener);

		final RemotePanel remoteSequencePanel = mainPane.getSequenceManagerPanel().getRemotePanel();
		final ActionListener remoteSequenceActionListener = new BrowseActionListener(mainFrame, remoteSequencePanel.getPathTextField());
		remoteSequencePanel.getBrowseButton().addActionListener(remoteSequenceActionListener);
	}

	/**
	Rebuilds the instrument list from the model.

	Triggered manually to avoid sequential operations from causing
	 redundant calls and
	 slowness.

	@param interactive Whether an incorrect search pattern is reported.
	**/
	public void updateInstrumentList(final boolean interactive) {
		final List<Instrument> instruments = model.getInstruments();

		final MainFrame mainFrame = view.getMainFrame();
		final LocalPanel<Instrument> localInstrumentPanel = mainFrame.getMainPane().getInstrumentManagerPanel().getLocalPanel();
		final DefaultListModel<Instrument> listModel = localInstrumentPanel.getListModel();
		try {
			final java.lang.String regex = localInstrumentPanel.getSearchTextField().getText();
			final Pattern pattern = Pattern.compile(regex);
			listModel.clear();
			for (final Instrument instrument : instruments) {
				final Matcher matcher = pattern.matcher(instrument.getName());
				if (matcher.find()) listModel.addElement(instrument);
			}
		}
		catch (final PatternSyntaxException exception) {
			if (interactive) {
				StackTracePanel.showErrorDialog(mainFrame, new StackTracePanel(exception,
						"Compiling the search pattern failed. Make sure your regular expression is correct."));
			}
			else {
				listModel.clear();
				for (final Instrument instrument : instruments) {
					listModel.addElement(instrument);
				}
			}
		}

		final int existing = instruments.size(),
				showing = listModel.size();
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Showing ");
		stringBuilder.append(showing);
		if (showing != existing) {
			stringBuilder.append(" of ");
			stringBuilder.append(existing);
		}
		if (showing == 1 && existing == 1) stringBuilder.append(" Instrument");
		else stringBuilder.append(" Instruments");
		localInstrumentPanel.getSearchLabel().setText(stringBuilder.toString());
	}

	/**
	Activates the local panels.
	**/
	private void activateLocalPanels() {
		final MainFrame mainFrame = view.getMainFrame();
		final MainPane mainPane = mainFrame.getMainPane();

		final ManagerPanel<Instrument> instrumentManagerPanel = mainPane.getInstrumentManagerPanel();
		final RemotePanel remoteInstrumentPanel = instrumentManagerPanel.getRemotePanel();
		final JTextField instrumentPathTextField = remoteInstrumentPanel.getPathTextField();
		final LocalPanel<Instrument> localInstrumentPanel = instrumentManagerPanel.getLocalPanel();
		final LocalInterfacePanel instrumentInterfacePanel = localInstrumentPanel.getInterfacePanel();
		instrumentInterfacePanel.getLoadButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					model.loadInstruments(instrumentPathTextField.getText());
					updateInstrumentList(false);
				}
				catch (final JAXBException exception) {
					StackTracePanel.showErrorDialog(mainFrame, new StackTracePanel(exception,
							"Parsing the file failed. Make sure your data is well-formed and conforms to the appropriate schema."));
				}
			}
		});

		instrumentInterfacePanel.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					model.saveInstruments(instrumentPathTextField.getText());
				}
				catch (final JAXBException exception) {
					StackTracePanel.showErrorDialog(mainFrame, new StackTracePanel(exception,
							"Formatting the file failed. Make sure your data is sensible."));
				}
			}
		});

		localInstrumentPanel.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				updateInstrumentList(true);
			}
		});

		localInstrumentPanel.getNewButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				model.getInstruments().add(new Instrument("New Instrument"));
				model.sortInstruments();
				updateInstrumentList(false);
			}
		});

		localInstrumentPanel.getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final JList<Instrument> list = localInstrumentPanel.getList();
				final Instrument value = list.getSelectedValue();
				if (value != null) {
					model.getInstruments().remove(value);
					updateInstrumentList(false);
				}
			}
		});
	}

	/**
	Activates the editor panels.
	**/
	private void activateEditorPanels() {
		final MainFrame mainFrame = view.getMainFrame();
		final MainPane mainPane = mainFrame.getMainPane();

		final LocalPanel<Instrument> localInstrumentPanel = mainPane.getInstrumentManagerPanel().getLocalPanel();
		final InstrumentEditorPanel instrumentEditorPanel = mainPane.getInstrumentEditorPanel();
		final EditorInterfacePanel instrumentInterfacePanel = instrumentEditorPanel.getInterfacePanel();

		instrumentInterfacePanel.getRevertButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final JList<Instrument> list = localInstrumentPanel.getList();
				final Instrument value = list.getSelectedValue();
				if (value != null) {
					instrumentEditorPanel.getNameTextField().setText(value.getName());
					instrumentEditorPanel.getSystemComboBox().setSelectedItem(value.getTuningSystem());
					instrumentEditorPanel.getTensionTextField().setText(value.getMaximumTension().toString());
					final InstrumentMagicPanel instrumentMagicPanel = instrumentEditorPanel.getMagicPanel();
					final List<String> strings = value.getStrings();
					final int rows = strings.size();
					instrumentMagicPanel.setRows(rows);
					for (int row = 0; row < rows; row++) {
						instrumentMagicPanel.getLengthTextField(row).setText(strings.get(row).getVibratingLength().toString());
						instrumentMagicPanel.getDensityTextField(row).setText(strings.get(row).getLinearDensity().toString());
						final BigDecimal maximumTension = strings.get(row).getMaximumTension();
						if (maximumTension != null) instrumentMagicPanel.getTensionTextField(row).setText(maximumTension.toString());
					}
				}
			}
		});

		instrumentInterfacePanel.getApplyButton();//TODO save into local
	}

	@Deprecated
	private void addTestActions() {//TODO move away
		final SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
			private final Random random = new Random();
			private int progress = 0;

			@SuppressWarnings("unused")
			@Override
			protected void process(final List<Integer> chunks) {
				for (final Integer chunk : chunks) {
					//System.out.println("Processed " + chunk + "...");
				}
			}

			@Override
			protected void done() {
				//System.out.println(isCancelled() ? "...cancelled!" : "...finished!");
			}

			@Override
			public Integer doInBackground() throws InterruptedException {
				while (!isCancelled()) {
					final int result = random.nextInt(Byte.MAX_VALUE);
					if (result < 1) cancel(false);
					publish(result);
					setProgress(++progress);
					Thread.sleep(42);
				}
				return 0;
			}
		};
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(final PropertyChangeEvent event) {
				if (event.getPropertyName().equals("progress")) {
					view.getMainFrame().getStatusPanel().getProgressBar().setValue((Integer )event.getNewValue());
				}
			}
		});
		worker.execute();
	}

	/**
	Activates some optimizations.
	**/
	private void activateOptimizations() {
		final MainFrame mainFrame = view.getMainFrame();
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(final WindowEvent event) {
				System.gc();
			}
		});
	}

	@Override
	public void activate() {
		addTestActions();

		activateMenuBar();
		activateRemotePanels();
		activateLocalPanels();
		activateEditorPanels();
		activateOptimizations();
	}
}
