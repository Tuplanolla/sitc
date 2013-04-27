package org.sitc.views.swingview;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
Contains utility methods for Swing, much like <code>SwingUtilities</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Utilities {
	private Utilities() {
		throw new InstantiationError();
	}

	/**
	Gets all the components in a container recursively.

	@param container The container.
	@return The components.
	**/
	public static List<Component> getAllComponents(final Container container) {
		final Component[] components = container.getComponents();
		final List<Component> result = new ArrayList<>();
		for (final Component component : components) {
			result.add(component);
			if (component instanceof Container) {
				result.addAll(getAllComponents((Container )component));
			}
		}
		return result;
	}

	/**
	Sets the button's default icon,
	 scales it to a given factor of the button's preferred size and
	 aligns it to the given orientation.

	Use with care:

	<pre>
Helpers.setScaledIcon(new JButton("Example"), new ImageIcon("example.png"), SwingConstants.VERTICAL, 0.5);
</pre>

	@param button The button.
	@param icon The icon.
	@param orientation Either <code>SwingConstants.HORIZONTAL</code> or <code>SwingConstants.VERTICAL</code>.
	@param scale The scale of the icon.
	**/
	public static void setScaledIcon(final AbstractButton button, final ImageIcon icon, final int orientation, final double scale) {
		final Dimension preferredSize = button.getPreferredSize();
		final Insets margin = button.getMargin();
		final int minimumSize = Math.min(preferredSize.width - margin.left - margin.right,
				preferredSize.height - margin.top - margin.bottom);
		int size = minimumSize;
		final double actualScale = Math.abs(scale);
		if (actualScale != 1) {
			final double scaledSize = actualScale * size;
			if (scaledSize <= Integer.MAX_VALUE) size = (int )scaledSize;
		}
		final Image image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
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

	/**
	Sets the button's default icon,
	 scales it to the button's preferred size and
	 aligns it to the given orientation.

	Use with care:

	<pre>
Helpers.setScaledIcon(new JButton("Example"), new ImageIcon("example.png"), SwingConstants.VERTICAL);
</pre>

	@param button The button.
	@param icon The icon.
	@param orientation Either <code>SwingConstants.HORIZONTAL</code> or <code>SwingConstants.VERTICAL</code>.
	**/
	public static void setScaledIcon(final AbstractButton button, final ImageIcon icon, final int orientation) {
		setScaledIcon(button, icon, orientation, 1);
	}

	/**
	Enables (or disables) all the buttons in a button group.

	@param buttonGroup The button group.
	@param enabled Whether the button should be enabled.
	**/
	public static void setAllEnabled(final ButtonGroup buttonGroup, final boolean enabled) {
		for (final AbstractButton button : Collections.list(buttonGroup.getElements())) {
			button.setEnabled(enabled);
		}
	}

	/**
	Sets the minimum, maximum and preferred size of a component.

	@param component The component.
	@param size The sizes.
	**/
	public static void setAllSizes(final Component component, final Dimension size) {
		component.setMinimumSize(size);
		component.setMaximumSize(size);
		component.setPreferredSize(size);
	}

	/**
	Invokes all the change listeners in a container recursively.

	@param container The container.
	**/
	public static void allStatesChanged(final Container container) {
		for (final Component component : getAllComponents(container)) {
			for (final ChangeListener listener : component.getListeners(ChangeListener.class)) {
				listener.stateChanged(new ChangeEvent(component));
			}
		}
	}
}
