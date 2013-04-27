package org.sitc.views.swingview;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

/**
Manages persistent resources with high performance.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class Resources {//TODO singletonize
	public static final List<Image> ICON_IMAGES;
	static {
		final List<Image> images = new ArrayList<>();
		for (int size = 1; size <= 1 << 10; size <<= 1) {
			final URL url = Resources.class.getResource("/icon" + size + ".png");
			if (url != null) images.add(new ImageIcon(url).getImage());
		}
		ICON_IMAGES = Collections.unmodifiableList(images);
	}

	public static final ImageIcon ICON,
			LEFT_ICON,
			RIGHT_ICON,
			UP_ICON,
			DOWN_ICON,
			LEFT_RIGHT_ICON,
			UP_DOWN_ICON,
			PLUS_ICON,
			MINUS_ICON,
			PLAY_ICON,
			CALCULATE_ICON,
			SEARCH_ICON,
			BROWSE_ICON;
	static {
		URL url;

		url = Resources.class.getResource("/icon256.png");
		ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/arrow_left.png");
		LEFT_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/arrow_right.png");
		RIGHT_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/arrow_up.png");
		UP_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/arrow_down.png");
		DOWN_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/arrow_left_right.png");
		LEFT_RIGHT_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/arrow_up_down.png");
		UP_DOWN_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/plus.png");
		PLUS_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/minus.png");
		MINUS_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/triangle_right.png");
		PLAY_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/calculator.png");
		CALCULATE_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/magnifier.png");
		SEARCH_ICON = url != null ? new ImageIcon(url) : null;

		url = Resources.class.getResource("/folder.png");
		BROWSE_ICON = url != null ? new ImageIcon(url) : null;
	}

	public static final File DEVELOPER_MANUAL,
			USER_MANUAL;
	static {
		DEVELOPER_MANUAL = new File("doc", "developer.pdf");
		USER_MANUAL = new File("doc", "user.pdf");
	}

	private Resources() {
		throw new InstantiationError();
	}
}
