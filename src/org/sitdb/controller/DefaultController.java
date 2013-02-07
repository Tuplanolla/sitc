package org.sitdb.controller;

import org.sitdb.Controller;
import org.sitdb.Model;
import org.sitdb.View;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class DefaultController extends Controller {
	public DefaultController(final Model model, final View view) {
		super(model, view);
	}

	@Override
	public void activate() {}
}
