package org.sitdb;

/**
Contains the presentation.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public abstract class View implements Part {
	protected final Model model;

	public View(final Model model) {
		this.model = model;
	}
}
