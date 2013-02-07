package org.sitdb;

/**
Contains the logic.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public abstract class Controller implements Part {
	protected final Model model;
	protected final View view;

	public Controller(final Model model, final View view) {
		this.model = model;
		this.view = view;
	}
}

