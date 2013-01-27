package org.sitdb;

import java.awt.EventQueue;

import javax.swing.UIManager;

/**
Sets the look and feel and creates the main window.

The classes have the following structural hierarchy:

<pre>
+---------------------------------------------------------------------+
| MainFrame                                                           |
+---------------------------------------------------------------------+
| MenuBar                                                             |
|---------------------------------------------------------------------|
| SidePanel         | MainPanel                                       |
| +---------------+ | +----------+----------+----------+              |
| | DatabasePanel | | | TabPanel |          |          |              |
| |               | | |          +----------+----------+------------+ |
| |               | | | +-----------------------------------------+ | |
| +---------------+ | | | EditorPanel                             | | |
| +---------------+ | | | +-----------------+ +-----------------+ | | |
| | DatabasePanel | | | | | EditorSidePanel | | EditorMainPanel | | | |
| |               | | | | |                 | |                 | | | |
| |               | | | | |                 | |                 | | | |
| +---------------+ | | | |                 | |                 | | | |
| +---------------+ | | | |                 | |                 | | | |
| | DatabasePanel | | | | |                 | |                 | | | |
| |               | | | | +-----------------+ +-----------------+ | | |
| |               | | | +-----------------------------------------+ | |
| +---------------+ | +---------------------------------------------+ |
|---------------------------------------------------------------------|
| StatusBar                                                           |
+---------------------------------------------------------------------+
</pre>

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main {
	/**
	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final Exception exception) {
			exception.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainFrame();
				}
				catch (final Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}
}
