package org.sitdb;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sitdb.controller.Controller;
import org.sitdb.model.Model;
import org.sitdb.view.View;

/**
Serves as the main entry point.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main implements Runnable {
	private final List<String> arguments;

	/**
	Creates a new main class.

	@param arguments The command line arguments.
	**/
	public Main(final String[] arguments) {
		final String[] array = new String[arguments.length]; 
		System.arraycopy(arguments, 0, array, 0, arguments.length);
		this.arguments = Collections.unmodifiableList(Arrays.asList(array));
	}

	@Override
	public void run() {
		final Model model = new Model(arguments);
		final View view = new View(model);
		final Controller controller = new Controller(model, view);
		model.activate();
		controller.activate();
		view.activate();
	}

	/**
	Creates a new main class and schedules it to be ran in the event dispatch thread.

	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		EventQueue.invokeLater(new Main(arguments));
	}
}
