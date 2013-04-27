package org.sitc.views.swingview;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
Represents a menu bar.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1;

	private final JMenuItem exitMenuItem;
	private final JMenuItem tensionMenuItem;
	private final JCheckBoxMenuItem microtonalMenuCheckbox;
	private final JCheckBoxMenuItem notationMenuCheckbox;
	private final JMenuItem manualMenuItem;
	private final JMenuItem aboutMenuItem;

	/**
	Creates a menu bar.
	**/
	public MenuBar() {
				exitMenuItem = new JMenuItem("Exit");

			final JMenu fileMenu = new JMenu("File");
			fileMenu.add(exitMenuItem);

				tensionMenuItem = new JMenuItem("Tension Calculator");

			final JMenu toolsMenu = new JMenu("Tools");
			toolsMenu.add(tensionMenuItem);

				microtonalMenuCheckbox = new JCheckBoxMenuItem("Hide Microtonals");

				notationMenuCheckbox = new JCheckBoxMenuItem("Use European Notation");

			final JMenu settingsMenu = new JMenu("Settings");
			settingsMenu.add(microtonalMenuCheckbox);
			settingsMenu.add(notationMenuCheckbox);

				manualMenuItem = new JMenuItem("Manual");

				aboutMenuItem = new JMenuItem("About");

			final JMenu helpMenu = new JMenu("Help");
			helpMenu.add(manualMenuItem);
			helpMenu.add(aboutMenuItem);

		setBorderPainted(false);
		add(fileMenu);
		//add(toolsMenu);//TODO tools
		add(settingsMenu);
		add(helpMenu);
	}

	/**
	@return The exit menu item.
	**/
	public JMenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	/**
	@return The microtonal menu checkbox.
	**/
	public JCheckBoxMenuItem getMicrotonalMenuCheckbox() {
		return microtonalMenuCheckbox;
	}

	/**
	@return The notation menu checkbox.
	**/
	public JCheckBoxMenuItem getNotationMenuCheckbox() {
		return notationMenuCheckbox;
	}

	/**
	@return The manual menu item.
	**/
	public JMenuItem getManualMenuItem() {
		return manualMenuItem;
	}

	/**
	@return The about menu item.
	**/
	public JMenuItem getAboutMenuItem() {
		return aboutMenuItem;
	}
}
