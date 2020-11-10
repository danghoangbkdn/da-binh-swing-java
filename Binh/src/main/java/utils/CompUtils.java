package utils;

import java.awt.Component;

public class CompUtils {
	private CompUtils() {
	}

	public static int getPreWidth(Component com) {
		return com.getPreferredSize().width;
	}

	public static int getPreHeight(Component com) {
		return com.getPreferredSize().height;
	}
}