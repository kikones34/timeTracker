package utilities;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

// Utility class which provides a simple interface for logging.
// Wraps a Logger object.
public final class Logging {
	
	private static Logger logger = null;
	
	private Logging() {
	}
	
	public static Logger getLogger() {
		if (logger == null) {
			logger = (Logger) LoggerFactory.getLogger("");
		}
		return logger;
	}
	
	public void setLevel(final Level level) {
		logger.setLevel(level);
	}
	
}
