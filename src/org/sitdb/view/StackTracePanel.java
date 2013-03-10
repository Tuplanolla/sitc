package org.sitdb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
Represents an stack trace panel used in error message dialogs.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class StackTracePanel extends JPanel {
	private static final long serialVersionUID = 1;

	/**
	Creates a new stack trace panel.

	@param throwable The object that contains the stack trace.
	@param message The error message.
	**/
	public StackTracePanel(final Throwable throwable, final String message) {
		super(new BorderLayout(Constants.MEDIUM_INSET, Constants.MEDIUM_INSET));

			final JLabel messageLabel = new JLabel(message);

			final JToggleButton showButton = new JToggleButton("Show Stack Trace");

					final StringWriter writer = new StringWriter();
					throwable.printStackTrace(new PrintWriter(writer));

				final JTextArea textArea = new JTextArea(writer.toString(), Constants.TERMINAL_ROWS, Constants.TERMINAL_COLUMNS);
				textArea.setEditable(false);

			final JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			showButton.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(final ItemEvent event) {
					final JComponent parent = StackTracePanel.this;
					final List<Component> componentList = Arrays.asList(StackTracePanel.this.getComponents());
					if (showButton.isSelected() && !componentList.contains(scrollPane)) {
						parent.add(scrollPane, BorderLayout.SOUTH);
					}
					else {
						parent.remove(scrollPane);
					}
					SwingUtilities.getWindowAncestor(parent).pack();
				}
			});

		add(messageLabel, BorderLayout.NORTH);
		add(showButton, BorderLayout.CENTER);
	}

	/**
	Brings up an error dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	**/
	public static void showErrorDialog(final Component parentComponent, final Object message) {
		JOptionPane.showOptionDialog(parentComponent,
				message,
				"Error",
				JOptionPane.OK_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,
				new String[] {"Dismiss"},
				null);
	}

	/**
	Brings up a warning dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	**/
	public static void showWarningDialog(final Component parentComponent, final Object message) {
		JOptionPane.showOptionDialog(parentComponent,
				message,
				"Warning",
				JOptionPane.OK_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				new String[] {"Ignore"},
				null);
	}

	/**
	Brings up an information dialog.

	@param parentComponent The parent of the dialog.
	@param message The object to display.
	**/
	public static void showInformationDialog(final Component parentComponent, final Object message) {
		JOptionPane.showOptionDialog(parentComponent,
				message,
				"Information",
				JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new String[] {"Acknowledge"},
				null);
	}
}
