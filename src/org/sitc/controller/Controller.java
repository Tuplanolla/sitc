package org.sitc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

import org.sitc.Part;
import org.sitc.model.Instrument;
import org.sitc.model.Model;
import org.sitc.model.String;
import org.sitc.model.TuningSystem;
import org.sitc.view.Dialogs;
import org.sitc.view.EditorInterfacePanel;
import org.sitc.view.InstrumentEditorPanel;
import org.sitc.view.InstrumentMagicPanel;
import org.sitc.view.LocalInterfacePanel;
import org.sitc.view.LocalPanel;
import org.sitc.view.MainFrame;
import org.sitc.view.MainPane;
import org.sitc.view.ManagerPanel;
import org.sitc.view.MenuBar;
import org.sitc.view.RemotePanel;
import org.sitc.view.StackTracePanel;
import org.sitc.view.View;

/**
Represents an immutable controller.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Controller implements Part {//TODO split
	protected final Model model;
	protected final View view;

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
	protected void updateInstrumentList(final boolean interactive) {
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
				Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
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
					Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
							"Parsing the file failed. Make sure your data is well-formed and conforms to the appropriate schema."));
				}
			}
		});

		instrumentInterfacePanel.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					final java.lang.String path = instrumentPathTextField.getText();
					int option = JOptionPane.YES_OPTION;
					if (new File(path).exists()) {//TODO track changes
						option = Dialogs.showOverwriteDialog(mainFrame,
								"The file already exists. Do you want to overwrite it?");
					}
					if (option == JOptionPane.YES_OPTION) {
						model.saveInstruments(path);
					}
				}
				catch (final JAXBException exception) {
					Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
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
	Activates and reactivates the instrument magic panel.
	**/
	protected void reactivateInstrumentMagicPanel() {
		final InstrumentMagicPanel magicPanel = view.getMainFrame().getMainPane().getInstrumentEditorPanel().getMagicPanel();
		final int rows = magicPanel.getRows();

		for (int rowBefore = 0; rowBefore <= rows; rowBefore++) {
			final int rowAfter = rowBefore + 1;
			final JButton insertButton = magicPanel.getInsertButton(rowBefore, rowAfter);
			for (final ActionListener actionListener : insertButton.getActionListeners()) {
				insertButton.removeActionListener(actionListener);
			}
			insertButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent event) {
					final int nextRows = rows + 1;
					magicPanel.setRows(nextRows);
					for (int row = nextRows; row > rowAfter; row--) {
						final int rowBefore = row - 1;
						magicPanel.getLengthTextField(row).setText(magicPanel.getLengthTextField(rowBefore).getText());
						magicPanel.getDensityTextField(row).setText(magicPanel.getDensityTextField(rowBefore).getText());
						magicPanel.getTensionTextField(row).setText(magicPanel.getTensionTextField(rowBefore).getText());
					}
					magicPanel.getLengthTextField(rowAfter).setText(null);
					magicPanel.getDensityTextField(rowAfter).setText(null);
					magicPanel.getTensionTextField(rowAfter).setText(null);

					reactivateInstrumentMagicPanel();
				}
			});
		}

		for (int row = 1; row <= rows; row++) {
			final int rowAfter = row + 1;
			final JButton deleteButton = magicPanel.getDeleteButton(row);
			for (final ActionListener actionListener : deleteButton.getActionListeners()) {
				deleteButton.removeActionListener(actionListener);
			}
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent event) {
					final int nextRows = rows - 1;
					for (int row = rowAfter; row <= rows; row++) {
						final int rowBefore = row - 1;
						magicPanel.getLengthTextField(rowBefore).setText(magicPanel.getLengthTextField(row).getText());
						magicPanel.getDensityTextField(rowBefore).setText(magicPanel.getDensityTextField(row).getText());
						magicPanel.getTensionTextField(rowBefore).setText(magicPanel.getTensionTextField(row).getText());
					}
					magicPanel.setRows(nextRows);

					reactivateInstrumentMagicPanel();
				}
			});
		}

		for (int rowBefore = 1; rowBefore < rows; rowBefore++) {
			final int rowAfter = rowBefore + 1;
			final JButton swapButton = magicPanel.getSwapButton(rowBefore, rowAfter);
			for (final ActionListener actionListener : swapButton.getActionListeners()) {
				swapButton.removeActionListener(actionListener);
			}
			swapButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent event) {
					final int row = rowAfter - 1;
					final JTextField lengthTextFieldBefore = magicPanel.getLengthTextField(row),
							densityTextFieldBefore = magicPanel.getDensityTextField(row),
							tensionTextFieldBefore = magicPanel.getTensionTextField(row);
					final JTextField lengthTextFieldAfter = magicPanel.getLengthTextField(rowAfter),
							densityTextFieldAfter = magicPanel.getDensityTextField(rowAfter),
							tensionTextFieldAfter = magicPanel.getTensionTextField(rowAfter);

					final java.lang.String lengthTextBefore = lengthTextFieldBefore.getText(),
							densityTextBefore = densityTextFieldBefore.getText(),
							tensionTextBefore = tensionTextFieldBefore.getText();
					lengthTextFieldBefore.setText(lengthTextFieldAfter.getText());
					densityTextFieldBefore.setText(densityTextFieldAfter.getText());
					tensionTextFieldBefore.setText(tensionTextFieldAfter.getText());
					lengthTextFieldAfter.setText(lengthTextBefore);
					densityTextFieldAfter.setText(densityTextBefore);
					tensionTextFieldAfter.setText(tensionTextBefore);
				}
			});
		}
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
		final InstrumentMagicPanel instrumentMagicPanel = instrumentEditorPanel.getMagicPanel();

		for (final TuningSystem system : model.getTuningSystems()) {
			instrumentEditorPanel.getSystemComboBoxModel().addElement(system);
		}

		instrumentInterfacePanel.getRevertButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final JList<Instrument> list = localInstrumentPanel.getList();
				final Instrument currentInstrument = list.getSelectedValue();
				if (currentInstrument != null) {
					model.setCurrentInstrument(currentInstrument);

					instrumentEditorPanel.getNameTextField().setText(currentInstrument.getName());

					instrumentEditorPanel.getSystemComboBox().setSelectedItem(currentInstrument.getTuningSystem());

					final BigDecimal maximumInstrumentTension = currentInstrument.getMaximumTension();
					if (maximumInstrumentTension != null) instrumentEditorPanel.getTensionTextField().setText(maximumInstrumentTension.toString());

					final List<String> strings = currentInstrument.getStrings();
					final int rows = strings.size();
					instrumentMagicPanel.setRows(rows);
					for (int row = 1; row <= rows; row++) {
						final int index = row - 1;
						final String string = strings.get(index);

						instrumentMagicPanel.getLengthTextField(row).setText(string.getVibratingLength().toString());

						instrumentMagicPanel.getDensityTextField(row).setText(string.getLinearDensity().toString());

						final BigDecimal maximumStringTension = string.getMaximumTension();
						if (maximumStringTension != null) instrumentMagicPanel.getTensionTextField(row).setText(maximumStringTension.toString());
					}

					reactivateInstrumentMagicPanel();
				}
			}
		});

		instrumentInterfacePanel.getApplyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					final java.lang.String name;
					try {
						name = instrumentEditorPanel.getNameTextField().getText();
						if (name.isEmpty()) throw new StringFormatException();
					}
					catch (final StringFormatException exception) {
						Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
								"Interpreting the name field failed. Make sure your name input isn't empty."));
						throw exception;
					}

					final TuningSystem tuningSystem = (TuningSystem )instrumentEditorPanel.getSystemComboBox().getSelectedItem();

					final BigDecimal maximumInstrumentTension;
					try {
						final java.lang.String maximumInstrumentTensionString = instrumentEditorPanel.getTensionTextField().getText();
						if (maximumInstrumentTensionString.isEmpty()) maximumInstrumentTension = null;
						else {
							maximumInstrumentTension = new BigDecimal(maximumInstrumentTensionString);
							if (maximumInstrumentTension.compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
						}
					}
					catch (final NumberFormatException exception) {
						Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
								"Interpreting the tension field failed. Make sure your tension input is a positive decimal number or empty."));
						throw exception;
					}

					final int rows = instrumentMagicPanel.getRows();

					final List<String> strings = new ArrayList<>(rows);

					for (int row = 1; row <= rows; row++) {
						final BigDecimal vibratingLength;
						try {
							final java.lang.String vibratingLengthString = instrumentMagicPanel.getLengthTextField(row).getText();
							vibratingLength = new BigDecimal(vibratingLengthString);
							if (vibratingLength.compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
						}
						catch (final NumberFormatException exception) {
							Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
									"Interpreting the length field on row #" + row + " failed. Make sure your input is a positive decimal number."));
							throw exception;
						}

						final BigDecimal linearDensity;
						try {
							final java.lang.String linearDensityString = instrumentMagicPanel.getDensityTextField(row).getText();
							linearDensity = new BigDecimal(linearDensityString);
							if (linearDensity.compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
						}
						catch (final NumberFormatException exception) {
							Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
									"Interpreting the density field on row #" + row + " failed. Make sure your input is a positive decimal number."));
							throw exception;
						}

						final BigDecimal maximumStringTension;
						try {
							final java.lang.String maximumStringTensionString = instrumentMagicPanel.getTensionTextField(row).getText();
							if (maximumStringTensionString.isEmpty()) maximumStringTension = null;
							else {
								maximumStringTension = new BigDecimal(maximumStringTensionString);
								if (maximumStringTension.compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
							}
						}
						catch (final NumberFormatException exception) {
							Dialogs.showErrorDialog(mainFrame, new StackTracePanel(exception,
									"Interpreting the tension field on row #" + row + " failed. Make sure your tension input is a positive decimal number or empty."));
							throw exception;
						}

						final String string = new String(vibratingLength, linearDensity, maximumStringTension);
						strings.add(string);
					}

					final Instrument currentInstrument = model.getCurrentInstrument();
					currentInstrument.setName(name);
					currentInstrument.setTuningSystem(tuningSystem);
					currentInstrument.setMaximumTension(maximumInstrumentTension);
					currentInstrument.setStrings(strings);

					model.sortInstruments();
					updateInstrumentList(false);
				}
				catch (final IllegalArgumentException exception) {}//previously caught
			}
		});

		reactivateInstrumentMagicPanel();
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
		//view.getMainFrame().getStatusPanel().getProgressBar().setStringPainted(false);
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
