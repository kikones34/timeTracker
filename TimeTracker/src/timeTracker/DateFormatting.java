package timeTracker;

import java.text.SimpleDateFormat;

public class DateFormatting {
	private static SimpleDateFormat formatter = null;
	private static String format = null;
	
	private DateFormatting() {
	}
	
	public static void setFormat(String format) {
		DateFormatting.format = format;
	}
	
	public static SimpleDateFormat getFormatter() {
		if (formatter == null) {
			if (format == null) {
				formatter = new SimpleDateFormat();
			} else {
				formatter = new SimpleDateFormat(format);
			}
		}
		return formatter;
	}
	
}
