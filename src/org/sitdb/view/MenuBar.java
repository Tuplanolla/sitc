package org.sitdb.view;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
Represents a panel that does something.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 5495273314694096920l;

	private final JMenuItem exitMenuItem;
	private final JCheckBoxMenuItem microtonalMenuCheckbox;
	private final JCheckBoxMenuItem notationMenuCheckbox;
	private final JMenuItem manualMenuItem;
	private final JMenuItem aboutMenuItem;;

	/**
	Constructs a new panel.
	**/
	public MenuBar() {
		exitMenuItem = new JMenuItem("Exit");

		microtonalMenuCheckbox = new JCheckBoxMenuItem("Hide Microtonals");

		notationMenuCheckbox = new JCheckBoxMenuItem("Use European Notation");

		manualMenuItem = new JMenuItem("Manual");

		aboutMenuItem = new JMenuItem("About");

		final JMenu fileMenu = new JMenu("File");
		fileMenu.add(exitMenuItem);

		final JMenu settingsMenu = new JMenu("Settings");
		settingsMenu.add(microtonalMenuCheckbox);
		settingsMenu.add(notationMenuCheckbox);

		final JMenu helpMenu = new JMenu("Help");
		helpMenu.add(manualMenuItem);
		helpMenu.add(aboutMenuItem);

		setBorderPainted(false);
		add(fileMenu);
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
