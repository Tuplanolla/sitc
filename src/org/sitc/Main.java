package org.sitc;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import org.sitc.controller.Controller;
import org.sitc.model.Model;
import org.sitc.view.View;

/**
Serves as the main entry point.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Main implements Runnable {
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

	@Override
	public void run() {
		/*
		Where I deadlocked, I softer thread ---
		I sow sweet synch block --- From standard read ---
		I pause above that line ahead
			And assert.
		*/
		assert SwingUtilities.isEventDispatchThread();
		/*
		Whom I deadlocked, I cryptic bard
		From syntax harsh, or ill keyword ---
		Feeling as if their anger seared,
			Though vain!
		*/
		final Model model = new Model(arguments);
		final View view = new View(model);
		final Controller controller = new Controller(model, view);
		/*
		When I deadlock, you'll know by this ---
		A buffer black --- Flickers amiss ---
		A static tremor as a hiss
			Like piss!
		*/
		model.activate();
		controller.activate();
		view.activate();
		/*
		Why, I deadlocked, the people know
		Who thought this program isn't slow
		Went home a century ago
			Next Bliss!
		*/
	}

	/**
	Creates a main class and schedules it to be ran in the event dispatch thread.

	@param arguments The command line arguments.
	**/
	public static void main(final String[] arguments) {
		EventQueue.invokeLater(new Main(arguments));
	}
}
