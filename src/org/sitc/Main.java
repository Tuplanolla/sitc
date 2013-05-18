package org.sitc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sitc.controllers.swingcontroller.Controller;
import org.sitc.model.Model;
import org.sitc.views.AbstractView;
import org.sitc.views.swingview.SwingView;

/**
Serves as the main entry point.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main implements Runnable {
	@SuppressWarnings("unused")
	private final List<String> arguments;

	/**
	Creates a main class.

	@param arguments The command line arguments.
	**/
	public Main(final String[] arguments) {
		final String[] array = new String[arguments.length];
		System.arraycopy(arguments, 0, array, 0, arguments.length);
		this.arguments = Collections.unmodifiableList(Arrays.asList(array));
	}

	/**
	Creates a main class and runs it.

	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		new Main(arguments).run();
	}

	/*
	The rest of the class is for the implementation.
	*/

	@Override
	public void run() {
		final Model model = new Model();

		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final AbstractView view = new SwingView(model);
				new Controller(model, view).run();
				view.run();
			}
		});
	}
}
