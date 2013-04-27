package org.sitc.views.swingview;

import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sitc.models.AbstractModel;
import org.sitc.models.standardmodel.StandardModel;
import org.sitc.views.AbstractView;

/**
Represents a graphical view.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class SwingView extends AbstractView {
	private final MainFrame mainFrame;

	/**
	Creates a view and links it with a model.

	@param model The model.
	**/
	public SwingView(final AbstractModel model) {
		super(model);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final ClassNotFoundException
				| InstantiationException
				| IllegalAccessException
				| UnsupportedLookAndFeelException exception) {
			//nothing can save us now
		}

		mainFrame = new MainFrame();
		new Controller((StandardModel )model, this).activate();//TODO remove
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setSize(new Dimension(800, 800));
		mainFrame.setVisible(true);
	}

	/**
	@return The main frame.
	**/
	public MainFrame getMainFrame() {
		return mainFrame;
	}
}
