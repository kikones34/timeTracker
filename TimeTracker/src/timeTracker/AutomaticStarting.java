package timeTracker;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

// Task decorator which allows to set an automatic starting date.
// The task will automatically start on that date, and every next day.
public class AutomaticStarting extends TaskDecorator implements Observer {

	private static final long serialVersionUID = 1L;
	private Calendar startingDate;
	
	public AutomaticStarting(Task task, Calendar startingDate) {
		super(task);
		assert(this.task != null);
		this.startingDate = startingDate;
		Timer.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object calendarObj) {
		if (!isActive()) {
			Calendar calendar = (Calendar)calendarObj;
			if (calendar.after(startingDate)) {
				Logging.getLogger().debug("Date is after starting date.");
				// makes the task auto-start the next day
				startingDate.add(Calendar.DATE, 1);
				start();
			}
		}
	}
	
}
