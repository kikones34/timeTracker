package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Utility class which provides useful functions related to dates.
public final class DateUtilities {
	private static SimpleDateFormat formatter = null;
	private static String format = null;
	
	private DateUtilities() {
	}
	
	public static void setDefaultFormat(final String format) {
		DateUtilities.format = format;
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
	
	public static Calendar maxDate(final Calendar date1, final Calendar date2) {
		assert date1 != null;
		assert date2 != null;
		if (date1.after(date2)) {
			return date1;
		} else {
			return date2;
		}
	}
	
	public static Calendar minDate(final Calendar date1, final Calendar date2) {
		assert date1 != null;
		assert date2 != null;
		if (date1.before(date2)) {
			return date1;
		} else {
			return date2;
		}
	}
	
}
