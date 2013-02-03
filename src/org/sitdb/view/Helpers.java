package org.sitdb.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Helpers {
	private Helpers() {
		throw new InstantiationError();
	}

	/**
	Sets the icon of a button,
	 scales it to the button's preferred size and
	 aligns it to the given orientation.

	<pre>
Helpers.setScaledIcon(new JButton("Example"), new ImageIcon("example.png"), SwingConstants.VERTICAL);
</pre>

	@param button The button.
	@param icon The icon.
	@param orientation Either <code>SwingConstants.HORIZONTAL</code> or <code>SwingConstants.VERTICAL</code>.
	**/
	public static void setScaledIcon(final AbstractButton button, final ImageIcon icon, final int orientation) {
		final Dimension size = button.getPreferredSize();
		final Insets margin = button.getMargin();
		final int minimumSize = Math.min(size.width - margin.left - margin.right,
				size.height - margin.top - margin.bottom);
		final Image image = icon.getImage().getScaledInstance(minimumSize, minimumSize, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(image));
		switch (orientation) {
		case SwingConstants.HORIZONTAL:
			button.setVerticalTextPosition(SwingConstants.CENTER);
			button.setHorizontalTextPosition(SwingConstants.TRAILING);
			break;
		case SwingConstants.VERTICAL:
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}
