package utils;

import java.util.Arrays;

public class SQLUtils {
	private SQLUtils() {
	}

	public static <T extends AutoCloseable> void closed(@SuppressWarnings("unchecked") T... closedElements) {
		Arrays.stream(closedElements).forEach(e -> {
			if (e != null) {
				try {
					e.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}