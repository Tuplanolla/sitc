package org.sitc.views.swingview;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import org.sitc.model.Instrument;
import org.sitc.model.Named;
import org.sitc.model.Sequence;
import org.sitc.model.Model;
import org.sitc.model.String;
import org.sitc.model.Tuning;
import org.sitc.model.TuningSystem;
import org.sitc.util.StringFormatException;

/**
Represents a controller.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Controller {
	protected final Model model;
	protected final SwingView view;

	/**
	Creates a view and links it with a model and a view.

	@param model The model.
	@param view The view.
	**/
	public Controller(final Model model, final SwingView view) {
		this.model = model;
		this.view = view;
	}

	/**
	Activates the menu bar.
	**/
	protected void activateMenuBar() {
		final MainFrame mainFrame = view.getMainFrame();
		final MenuBar menuBar = mainFrame.getJMenuBar();

		menuBar.getExitMenuItem().addActionListener(new CloseActionListener(mainFrame));

		menuBar.getManualMenuItem().addActionListener(new ManualActionListener(mainFrame));

		menuBar.getAboutMenuItem().addActionListener(new AboutActionListener(mainFrame));
	}

	/**
	Activates the remote panels.
	**/
	protected void activateRemotePanels() {
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
	Rebuilds a list from the model.

	Triggered manually to avoid sequential operations from causing
	 redundant calls and
	 slowness.

	@param <Type> The type of the list.
	@param parentComponent The parent component.
	@param localPanel The local panel.
	@param interactive Whether an incorrect search pattern is reported.
	**/
	private static <Type extends Named> void updateList(final Component parentComponent,
			final LocalPanel<Type> localPanel,
			final List<Type> list,
			final boolean interactive) {//TODO something
		final DefaultListModel<Type> listModel = localPanel.getListModel();

		try {
			final java.lang.String regex = localPanel.getSearchTextField().getText();
			final Pattern pattern = Pattern.compile(regex);
			listModel.clear();
			for (final Type item : list) {
				final Matcher matcher = pattern.matcher(item.getName());
				if (matcher.find()) listModel.addElement(item);
			}
		}
		catch (final PatternSyntaxException exception) {
			if (interactive) {
				Dialogs.showErrorDialog(parentComponent, new StackTracePanel(exception,
						"Compiling the search pattern failed. Make sure your regular expression is correct."));
			}
			else {
				listModel.clear();
				for (final Type item : list) {
					listModel.addElement(item);
				}
			}
		}

		final int existing = list.size(),
				showing = listModel.size();
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Showing ");
		stringBuilder.append(showing);
		if (showing != existing) {
			stringBuilder.append(" of ");
			stringBuilder.append(existing);
		}
		stringBuilder.append(" List Item");
		if (existing != 1) stringBuilder.append("s");
		localPanel.getSearchLabel().setText(stringBuilder.toString());
	}

	/**
	Rebuilds the instrument list from the model.

	@param interactive Whether an incorrect search pattern is reported.
	**/
	protected void updateInstrumentList(final boolean interactive) {
		final MainFrame mainFrame = view.getMainFrame();
		final LocalPanel<Instrument> localPanel = mainFrame.getMainPane().getInstrumentManagerPanel().getLocalPanel();
		updateList(mainFrame, localPanel, model.getInstruments(), interactive);
	}

	/**
	Rebuilds the tuning list from the model.

	@param interactive Whether an incorrect search pattern is reported.
	**/
	protected void updateTuningList(final boolean interactive) {
		final MainFrame mainFrame = view.getMainFrame();
		final LocalPanel<Tuning> localPanel = mainFrame.getMainPane().getTuningManagerPanel().getLocalPanel();
		updateList(mainFrame, localPanel, model.getTunings(), interactive);
	}

	/**
	Rebuilds the sequence list from the model.

	@param interactive Whether an incorrect search pattern is reported.
	**/
	protected void updateSequenceList(final boolean interactive) {
		final MainFrame mainFrame = view.getMainFrame();
		final LocalPanel<Sequence> localPanel = mainFrame.getMainPane().getSequenceManagerPanel().getLocalPanel();
		updateList(mainFrame, localPanel, model.getSequences(), interactive);
	}

	/**
	Activates the local panels.
	**/
	protected void activateLocalPanels() {
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
					model.loadInstruments(new File(instrumentPathTextField.getText()));

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
					final File file = new File(instrumentPathTextField.getText());
					int option = JOptionPane.YES_OPTION;
					if (file.exists()) {//TODO track changes
						option = Dialogs.showOverwriteDialog(mainFrame,
								"The file already exists. Do you want to overwrite it?");
					}
					if (option == JOptionPane.YES_OPTION) {
						model.saveInstruments(file);
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
	protected void activateEditorPanels() {
		final MainFrame mainFrame = view.getMainFrame();
		final MainPane mainPane = mainFrame.getMainPane();

		final LocalPanel<Instrument> localInstrumentPanel = mainPane.getInstrumentManagerPanel().getLocalPanel();
		final InstrumentEditorPanel instrumentEditorPanel = mainPane.getInstrumentEditorPanel();
		final EditorInterfacePanel instrumentInterfacePanel = instrumentEditorPanel.getInterfacePanel();
		final InstrumentMagicPanel instrumentMagicPanel = instrumentEditorPanel.getMagicPanel();

		final DefaultComboBoxModel<TuningSystem> systemComboBoxModel = instrumentEditorPanel.getSystemComboBoxModel();
		systemComboBoxModel.addElement(null);
		for (final TuningSystem system : model.getTuningSystems()) {
			systemComboBoxModel.addElement(system);
		}
		systemComboBoxModel.setSelectedItem(null);

		instrumentInterfacePanel.getEditButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final JList<Instrument> list = localInstrumentPanel.getList();
				final Instrument currentInstrument = list.getSelectedValue();

				if (currentInstrument == null) return;

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
		});

		instrumentInterfacePanel.getApplyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final Instrument currentInstrument = model.getCurrentInstrument();

				if (currentInstrument == null) return;

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

	/**
	Activates some optimizations.
	**/
	protected void activateOptimizations() {
		final MainFrame mainFrame = view.getMainFrame();
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(final WindowEvent event) {
				System.gc();
			}
		});
	}

	public void activate() {
		activateMenuBar();
		activateRemotePanels();
		activateLocalPanels();
		activateEditorPanels();
		activateOptimizations();
	}
}
