package org.sitc.views;

import java.util.Observable;
import java.util.Observer;

import org.sitc.models.AbstractModel;

/**
Represents a view.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public abstract class AbstractView implements Observer, Runnable {
	private final AbstractModel model;

	/**
	Creates a view and links it with a model.

	@param model The model.
	**/
	public AbstractView(final AbstractModel model) {
		this.model = model;

		model.addObserver(this);
	}

	/**
	Returns the model.

	@return The model.
	**/
	public AbstractModel getModel() {
		return model;
	}

	@Override
	public void update(final Observable observable, final Object argument) {}

	@Override
	public void run() {}

	/*
	The rest of the class is for the implementation.
	*/

	/**
	Returns the name text.

	@return The name text.
	**/
	//public abstract String getNameText();

	/**
	Changes the name text.

	@param name The new name text.
	**/
	//public abstract void setNameText(final String name);
}
