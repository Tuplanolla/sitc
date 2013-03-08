package org.sitdb.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.sitdb.Part;
import org.sitdb.model.Model;
import org.sitdb.view.MainFrame;
import org.sitdb.view.MainPane;
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

		final JFileChooser fileChooser = new JFileChooser();

		final RemotePanel remoteInstrumentPanel = mainPane.getInstrumentManagerPanel().getRemotePanel();
		remoteInstrumentPanel.getBrowseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				int result = fileChooser.showOpenDialog(mainFrame);
				if (result == JFileChooser.APPROVE_OPTION) {
					final File file = fileChooser.getSelectedFile();
					try {
						final String path = file.getCanonicalPath();
						remoteInstrumentPanel.getPathTextField().setText(path);
					}
					catch (final IOException exception) {
						StackTracePanel.showErrorDialog(mainFrame, new StackTracePanel(exception, "Resolving the file failed. Make sure your file system is working properly."));
					}
					catch (final SecurityException exception) {
						StackTracePanel.showErrorDialog(mainFrame, new StackTracePanel(exception, "Accessing the file failed. Make sure your permissions are sufficient."));
					}
				}
			}
		});
	}

	@Deprecated
	private void addTestActions() {
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

	@Deprecated
	private void addTestData() {
		final DefaultListModel<String> instrumentListModel = view.getMainFrame().getMainPane().getInstrumentManagerPanel().getLocalPanel().getListModel();
		instrumentListModel.addElement("Instrument");

		final DefaultListModel<String> tuningListModel = view.getMainFrame().getMainPane().getTuningManagerPanel().getLocalPanel().getListModel();
		tuningListModel.addElement("Tuning");
		tuningListModel.addElement("Another Tuning");

		final DefaultListModel<String> sequenceListModel = view.getMainFrame().getMainPane().getSequenceManagerPanel().getLocalPanel().getListModel();
		sequenceListModel.addElement("Sequence");
		sequenceListModel.addElement("Another Sequence");
		sequenceListModel.addElement("Yet Another Sequence");
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
		activateMenuBar();
		addTestActions();
		addTestData();
		activateRemotePanels();
		activateOptimizations();
	}
}
