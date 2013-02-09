package org.sitdb.view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
Represents a panel that does something.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1247479376441738475l;

	private final JProgressBar progressBar;

	/**
	Constructs a new panel.
	**/
	public StatusPanel() {
		super(new BorderLayout());

		progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		progressBar.setStringPainted(true);
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
					if (result < 2) cancel(false);
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
					progressBar.setValue((Integer )event.getNewValue());
				}
			}
		});
		worker.execute();

		add(progressBar);
	}

	/**
	@return The progress bar.
	**/
	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
