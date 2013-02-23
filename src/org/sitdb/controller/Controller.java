package org.sitdb.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.sitdb.Part;
import org.sitdb.model.Model;
import org.sitdb.view.OptionBar;
import org.sitdb.view.View;


/**
Represents an immutable controller.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Controller implements Part {
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

	@Deprecated
	private void addTestControls() {
		final OptionBar optionBar = view.getMainFrame().getExtraMenuBar();
		optionBar.getExitMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				view.getMainFrame().dispose();
			}
		});
		optionBar.getManualMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(view.getMainFrame(), "Push buttons.", "Manual", JOptionPane.PLAIN_MESSAGE);
			}
		});
		optionBar.getAboutMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(view.getMainFrame(), "This program is the best thing since sliced bread.", "About", JOptionPane.PLAIN_MESSAGE);
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

	@Override
	public void activate() {
		addTestControls();
		addTestActions();
		addTestData();
	}
}
