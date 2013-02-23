package org.sitdb.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

/**
Is a <code>JPanel</code>.

@author Sampsa "Tuplanolla" Kiiskinen
**/
@Deprecated
public class JayPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1l;

	public JayPanel(final LayoutManager layout, final boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public JayPanel(final LayoutManager layout) {
		super(layout);
	}

	public JayPanel(final boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public JayPanel() {
		super();
	}

	@Override
	protected void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);

		graphics.setColor(Color.PINK);
		graphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
}
