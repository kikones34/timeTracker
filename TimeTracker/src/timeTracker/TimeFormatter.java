package timeTracker;

// Provides functionality to format time.
// Mustn't be instantiated, all methods are static.
public class TimeFormatter {
	
	private TimeFormatter() {
	}
	
	// prints the date as # years, # months, ..., # seconds
	public static String formatFull(long millis) {
		int[] periodDurations = {31536000, 2592000, 86400, 3600, 60, 1}; // in seconds
		String[] periodNames = {"year", "month", "day", "hour", "minute", "second"};
		
		String date = "";
		int remainingSeconds = (int)(millis/1000L);
		int count;
		String periodName;
		for (int i = 0; i < periodDurations.length; i++) {
			count = remainingSeconds/periodDurations[i];
			if (count != 0) {
				periodName = periodNames[i];
				if (count > 1) {
					periodName += "s";
				}
				date += Integer.toString(count) + " " + periodName + ", ";
			}
			remainingSeconds %= periodDurations[i];
		}
		
		return date.substring(0, date.length()-2);
	}
	
	// prints the date as hh:mm:ss
	public static String format(long millis) {
		int totalSeconds = (int)(millis/1000L);
		int hours = totalSeconds/3600;
		int minutes = (totalSeconds % 3600)/60;
		int seconds = totalSeconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
