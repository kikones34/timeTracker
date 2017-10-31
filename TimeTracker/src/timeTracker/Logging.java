package timeTracker;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

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
	
	public void setLevel(Level level) {
		logger.setLevel(level);
	}
	
}
