package org.sitc.models;

import java.util.Observable;

/**
Represents a model.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public abstract class AbstractModel extends Observable implements Runnable {
	/**
	Creates a model.
	**/
	public AbstractModel() {}

	@Override
	public void run() {}

	/*
	The rest of the class is for the implementation.
	*/

	/**
	Returns the name.

	@return The name.
	**/
	//public abstract String getName();

	/**
	Changes the name.

	@param name The new name.
	**/
	//public abstract void setName(final String name);
}
