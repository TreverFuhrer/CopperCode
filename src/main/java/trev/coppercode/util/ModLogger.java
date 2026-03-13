package trev.coppercode.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Centralized logging access for Copper Code. */
public final class ModLogger {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModConstants.MOD_ID);

	private ModLogger() {
	}

	public static Logger get() {
		return LOGGER;
	}

	public static void info(String message, Object... args) {
		LOGGER.info(message, args);
	}

	public static void warn(String message, Object... args) {
		LOGGER.warn(message, args);
	}

	public static void error(String message, Object... args) {
		LOGGER.error(message, args);
	}
}
