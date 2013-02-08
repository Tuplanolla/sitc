package org.sitdb;

import org.sitdb.controller.DefaultController;
import org.sitdb.model.DefaultModel;
import org.sitdb.view.DefaultView;

/**
Serves as the main entry point.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main {
	/**
	Creates the main window.

	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		final Model model = new DefaultModel(arguments);
		final View view = new DefaultView(model);
		final Controller controller = new DefaultController(model, view);
		model.activate();
		controller.activate();
		view.activate();
	}
}
