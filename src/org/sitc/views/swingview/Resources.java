package org.sitc.views.swingview;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

/**
Manages resources with high performance.

Caches resources upon loading them the first time and
 returns cached results unless unloading is requested.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Resources {
	private static List<Image> projectIcons;
	private static ImageIcon projectIcon = null,
			leftIcon = null,
			rightIcon = null,
			upIcon = null,
			downIcon = null,
			leftRightIcon = null,
			upDownIcon = null,
			addIcon = null,
			removeIcon = null,
			calculateIcon = null,
			searchIcon = null,
			browseIcon = null,
			playIcon = null,
			stopIcon = null;
	private static File userManual = null,
			developerManual = null;

	private Resources() {
		throw new InstantiationError();
	}

	public static List<Image> loadProjectIcons() {
		if (projectIcons == null) {
			final List<Image> icons = new ArrayList<>();
			for (int size = 1; size <= 1 << 16; size <<= 1) {
				final URL url = Resources.class.getResource("/icon" + size + ".png");
				if (url != null) icons.add(new ImageIcon(url).getImage());
			}
			projectIcons = Collections.unmodifiableList(icons);
		}
		return projectIcons;
	}

	public static void unloadProjectIcons() {
		projectIcons = null;
	}

	public static ImageIcon loadProjectIcon() {
		if (projectIcon == null) {
			final URL url = Resources.class.getResource("/icon.png");
			if (url != null) projectIcon = new ImageIcon(url);
		}
		return projectIcon;
	}

	public static void unloadProjectIcon() {
		projectIcon = null;
	}

	public static ImageIcon loadLeftIcon() {
		if (leftIcon == null) {
			final URL url = Resources.class.getResource("/arrow_left.png");
			if (url != null) leftIcon = new ImageIcon(url);
		}
		return leftIcon;
	}

	public static void unloadLeftIcon() {
		leftIcon = null;
	}

	public static ImageIcon loadRightIcon() {
		if (rightIcon == null) {
			final URL url = Resources.class.getResource("/arrow_right.png");
			if (url != null) rightIcon = new ImageIcon(url);
		}
		return rightIcon;
	}

	public static void unloadRightIcon() {
		rightIcon = null;
	}

	public static ImageIcon loadUpIcon() {
		if (upIcon == null) {
			final URL url = Resources.class.getResource("/arrow_up.png");
			if (url != null) upIcon = new ImageIcon(url);
		}
		return upIcon;
	}

	public static void unloadUpIcon() {
		upIcon = null;
	}

	public static ImageIcon loadDownIcon() {
		if (downIcon == null) {
			final URL url = Resources.class.getResource("/arrow_down.png");
			if (url != null) downIcon = new ImageIcon(url);
		}
		return downIcon;
	}

	public static void unloadDownIcon() {
		downIcon = null;
	}

	public static ImageIcon loadLeftRightIcon() {
		if (leftRightIcon == null) {
			final URL url = Resources.class.getResource("/arrow_left_right.png");
			if (url != null) leftRightIcon = new ImageIcon(url);
		}
		return leftRightIcon;
	}

	public static void unloadLeftRightIcon() {
		leftRightIcon = null;
	}

	public static ImageIcon loadUpDownIcon() {
		if (upDownIcon == null) {
			final URL url = Resources.class.getResource("/arrow_up_down.png");
			if (url != null) upDownIcon = new ImageIcon(url);
		}
		return upDownIcon;
	}

	public static void unloadUpDownIcon() {
		upDownIcon = null;
	}

	public static ImageIcon loadAddIcon() {
		if (addIcon == null) {
			final URL url = Resources.class.getResource("/plus.png");
			if (url != null) addIcon = new ImageIcon(url);
		}
		return addIcon;
	}

	public static void unloadAddIcon() {
		addIcon = null;
	}

	public static ImageIcon loadRemoveIcon() {
		if (removeIcon == null) {
			final URL url = Resources.class.getResource("/minus.png");
			if (url != null) removeIcon = new ImageIcon(url);
		}
		return removeIcon;
	}

	public static void unloadRemoveIcon() {
		removeIcon = null;
	}

	public static ImageIcon loadCalculateIcon() {
		if (calculateIcon == null) {
			final URL url = Resources.class.getResource("/calculator.png");
			if (url != null) calculateIcon = new ImageIcon(url);
		}
		return calculateIcon;
	}

	public static void unloadCalculateIcon() {
		calculateIcon = null;
	}

	public static ImageIcon loadSearchIcon() {
		if (searchIcon == null) {
			final URL url = Resources.class.getResource("/magnifier.png");
			if (url != null) searchIcon = new ImageIcon(url);
		}
		return searchIcon;
	}

	public static void unloadSearchIcon() {
		searchIcon = null;
	}

	public static ImageIcon loadBrowseIcon() {
		if (browseIcon == null) {
			final URL url = Resources.class.getResource("/folder.png");
			if (url != null) browseIcon = new ImageIcon(url);
		}
		return browseIcon;
	}

	public static void unloadBrowseIcon() {
		browseIcon = null;
	}

	public static ImageIcon loadPlayIcon() {
		if (playIcon == null) {
			final URL url = Resources.class.getResource("/triangle_right.png");
			if (url != null) playIcon = new ImageIcon(url);
		}
		return playIcon;
	}

	public static void unloadPlayIcon() {
		playIcon = null;
	}

	public static ImageIcon loadStopIcon() {
		if (stopIcon == null) {
			final URL url = Resources.class.getResource("/square.png");
			if (url != null) stopIcon = new ImageIcon(url);
		}
		return stopIcon;
	}

	public static void unloadStopIcon() {
		stopIcon = null;
	}

	public static File loadUserManual() {
		if (userManual == null) {
			userManual = new File("doc", "user.pdf");
		}
		return userManual;
	}

	public static void unloadUserManual() {
		userManual = null;
	}

	public static File loadDeveloperManual() {
		if (developerManual == null) {
			developerManual = new File("doc", "developer.pdf");
		}
		return developerManual;
	}

	public static void unloadDeveloperManual() {
		developerManual = null;
	}
}
